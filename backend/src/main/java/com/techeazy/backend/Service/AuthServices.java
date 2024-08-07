package com.techeazy.backend.Service;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Entity.Users;
import com.techeazy.backend.Exception.MissingFieldException;
import com.techeazy.backend.Repo.UserDao;
import com.techeazy.backend.utils.JWTHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServices {

    @Autowired
    UserDao userDao;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTHelper jwtHelper;


    private CustomResponse response=new CustomResponse();


    public CustomResponse registration(Users users) {

        try {
            if (users.getUsername() == null || users.getPassword() == null || users.getRole() == null) {
                throw new MissingFieldException("Username,password and role is Mandatory");
            }
            users.setPassword(new BCryptPasswordEncoder(12).encode(users.getPassword()));
            users.setRole("ROLE_"+users.getRole().toUpperCase());
            userDao.save(users);
            response.setMessage("Register Successfully");
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(HttpStatus.CREATED.value());
        }catch (Exception e){
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage(e.getMessage());
        }
        return  response;
    }

    public CustomResponse login(Users users) {

        Authentication authentication = manager
                .authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String Token = jwtHelper.generateToken(userDetails.getUsername(), userDetails.getAuthorities().iterator().next().getAuthority());
            userDao.updateToken(Token, userDetails.getUsername());
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Login Successfully");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Login Failed");
            response.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }

    public Map<String, String> fetchToken(Users users) {
        Map<String, String> resp_map = new HashMap<>();
        try {
            Users details = userDao.findByUsername(users.getUsername());

            if (details == null) {
                throw new RuntimeException("user not found");
            }
            if (new BCryptPasswordEncoder(12).matches(users.getPassword(), details.getPassword())) {
                resp_map.put("Token", details.getToken());
                return resp_map;
            } else {
                resp_map.put("message", "Password Mismatched");
            }
        }catch (RuntimeException e){
            resp_map.put("error", e.getMessage());
        }
        return resp_map;
    }
}
