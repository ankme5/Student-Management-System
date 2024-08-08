package com.techeazy.backend.Controller;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Entity.Users;
import com.techeazy.backend.Exception.MissingFieldException;
import com.techeazy.backend.Service.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {


    @Autowired
    private AuthServices authServices;

    private final CustomResponse response=new CustomResponse();

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@RequestBody Users users) {

        try {
            authServices.login(users);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Login Successfully");
            response.setStatus(HttpStatus.OK);
        }catch (AuthenticationException e){
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setMessage(e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setStatus(HttpStatus.UNAUTHORIZED);
            response.setMessage("Login Failed");
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/register")
    public ResponseEntity<CustomResponse> register(@RequestBody Users users){

        try {
            authServices.registration(users);
            response.setMessage("Register Successfully");
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(HttpStatus.CREATED.value());
        }catch (MissingFieldException e){
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }


        return new ResponseEntity<>(response,response.getStatus());
    }

    @GetMapping("/getToken")
    public ResponseEntity<Map<String,String>> getToken(@RequestBody Users users){
        return new ResponseEntity<>(authServices.fetchToken(users), HttpStatus.OK);

    }

}
