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

    @PostMapping("/create_student")
    public ResponseEntity<CustomResponse> create_students(@RequestBody Student student){
        logger.info("Started Create Student API");
        return new ResponseEntity<>(service.saveStudent(student), HttpStatus.OK);
    }

    @PostMapping("/create_subject")
    public ResponseEntity<CustomResponse> create_subject(@RequestBody Subject subject){
        logger.info("Started Create subject API");
        return new ResponseEntity<>(service.saveSubject(subject), HttpStatus.OK);
    }

}
