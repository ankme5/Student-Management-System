package com.techeazy.backend.Service;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Entity.Student;
import com.techeazy.backend.Entity.Subject;
import com.techeazy.backend.Exception.StudentNotFoundException;
import com.techeazy.backend.Repo.StudentDao;
import com.techeazy.backend.Repo.SubjectDao;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StudentService {

    Logger logger= LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentDao studentDao;

    @Autowired
    SubjectDao subjectDao;


    private CustomResponse response=new CustomResponse();

    public List<Student> fetchAllStudents(){
        logger.info("Fetching All Students");
        return studentDao.findAll();
    }

    public List<Subject> fetchAllSubjects(){
        logger.info("Fetching All Subjects");
        return subjectDao.findAll();
    }


    public CustomResponse enrollSubject(Long id, Subject subject) {
        CustomResponse response = new CustomResponse();
        try {
            Student student = studentDao.findById(id)
                    .orElseThrow(() -> new StudentNotFoundException("Student not found for ID: " + id));

            Subject persistedSubject = subjectDao.findById(subject.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Subject not found for ID: " + subject.getId()));

            student.getSubjects().add(persistedSubject);
            Student s=studentDao.save(student);

            response.setStatus(HttpStatus.OK);
            response.setMessage("Student enrolled in " + subject.getName() + " successfully");
            response.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        }
            return response;
    }
}
