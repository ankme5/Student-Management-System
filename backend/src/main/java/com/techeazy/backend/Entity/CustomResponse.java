package com.techeazy.backend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;

@Data
public class CustomResponse {

    private String message;
    private int statusCode;
    private HttpStatus status;


}
