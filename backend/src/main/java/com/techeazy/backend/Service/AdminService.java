package com.techeazy.backend.Service;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Entity.Student;
import com.techeazy.backend.Entity.Subject;
import com.techeazy.backend.Exception.MissingFieldException;
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


    public void saveStudent(Student student){
        if(student.getName() == null|| student.getAddress() ==null || student.getName().isEmpty() || student.getAddress().isEmpty())
            throw new MissingFieldException("Name and Address Required");
        studentDao.save(student);
        logger.info("SuccessFully Save Student");
    }

    public void saveSubject(Subject subject){

        if(subject.getName() == null|| subject.getName().isEmpty())
            throw new MissingFieldException("Subject Name Required");
        subjectDao.save(subject);
        logger.info("SuccessFully Save Subject");
    }
}
