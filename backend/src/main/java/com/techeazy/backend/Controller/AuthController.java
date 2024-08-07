package com.techeazy.backend.Controller;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Entity.Users;
import com.techeazy.backend.Service.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {


    @Autowired
    private AuthServices authServices;

    private CustomResponse response;

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@RequestBody Users users) {

        response = authServices.login(users);

        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("/register")
    public ResponseEntity<CustomResponse> register(@RequestBody Users users){

        response=authServices.registration(users);

        return new ResponseEntity<>(response,response.getStatus());
    }

    @GetMapping("/getToken")
    public ResponseEntity<Map<String,String>> getToken(@RequestBody Users users){
        return new ResponseEntity<>(authServices.fetchToken(users), HttpStatus.OK);

    }

}
