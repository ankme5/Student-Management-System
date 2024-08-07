package com.techeazy.backend.Controller;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Entity.Student;
import com.techeazy.backend.Entity.Subject;
import com.techeazy.backend.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NativeDetector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {


    Logger logger= LoggerFactory.getLogger(StudentController.class);
    @Autowired
    StudentService service;

    private CustomResponse response=new CustomResponse();

    @GetMapping(value = "/getStudents")
    public ResponseEntity<List<Student>> get_AllStudents(){
        logger.info("Started Getting Student API");
        return new ResponseEntity<>(service.fetchAllStudents(), HttpStatus.OK);
    }

    @GetMapping(value = "/getSubjects")
    public ResponseEntity<List<Subject>> get_AllSubjects(){
        logger.info("Started Getting Subject API");
        return new ResponseEntity<>(service.fetchAllSubjects(),HttpStatus.OK);
    }

    @PostMapping("/{id}/enrollSubject")
    public ResponseEntity<CustomResponse> enrollSubject(@PathVariable Long id, @RequestBody Subject subject){
        response=service.enrollSubject(id,subject);

        return new ResponseEntity<>(response,response.getStatus());
    }

}
