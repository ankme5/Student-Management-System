package com.techeazy.backend.Service;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Entity.Student;
import com.techeazy.backend.Entity.Subject;
import com.techeazy.backend.Repo.StudentDao;
import com.techeazy.backend.Repo.SubjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    Logger logger= LoggerFactory.getLogger(AdminService.class);

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    private StudentDao studentDao;

    private CustomResponse response=new CustomResponse();

    public CustomResponse saveStudent(Student student){
        try {
            studentDao.save(student);
            response.setMessage(student.getName()+" Student Created Successfully");
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(HttpStatus.CREATED.value());
            logger.info("SuccessFully Save Student");
        }catch (Exception e)
        {
            response.setMessage(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    public CustomResponse saveSubject(Subject subject){
        try {
            subjectDao.save(subject);
            response.setMessage(subject.getName()+" Subject Created Successfully");
            response.setStatus(HttpStatus.CREATED);
            response.setStatusCode(HttpStatus.CREATED.value());
            logger.info("SuccessFully Save Subject");
        }catch (Exception e)
        {
            response.setMessage(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }
}
