package com.techeazy.backend.Controller;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Entity.Student;
import com.techeazy.backend.Entity.Subject;
import com.techeazy.backend.Service.AdminService;
import jakarta.persistence.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    Logger logger= LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService service;

    private final CustomResponse response=new CustomResponse();

    @PostMapping("/create_student")
    public ResponseEntity<CustomResponse> create_students(@RequestBody Student student){
        try {
            service.saveStudent(student);
            logger.info("Started Create Student API");
            response.setMessage(student.getName() + " Student Created Successfully");
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(HttpStatus.CREATED.value());
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, response.getStatus());

    }

    @PostMapping("/create_subject")
    public ResponseEntity<CustomResponse> create_subject(@RequestBody Subject subject){
        logger.info("Started Create subject API");
        try {
            service.saveSubject(subject);
            logger.info("Started Create Subject API");
            response.setMessage(subject.getName() + " Subject Created Successfully");
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(HttpStatus.CREATED.value());
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(response, response.getStatus());
    }

}
