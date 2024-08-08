package com.techeazy.backend.Service;

import com.techeazy.backend.Entity.Student;
import com.techeazy.backend.Entity.Subject;
import com.techeazy.backend.Repo.StudentDao;
import com.techeazy.backend.Repo.SubjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    Logger logger= LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentDao studentDao;

    @Autowired
    SubjectDao subjectDao;


    public List<Student> fetchAllStudents(){
        logger.info("Fetching All Students");
        return studentDao.findAll();
    }

    public List<Subject> fetchAllSubjects(){
        logger.info("Fetching All Subjects");
        return subjectDao.findAll();
    }


    public void enrollSubject(Long id, Subject subject) {
        Student student = studentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found for ID: " + id));

        Subject persistedSubject = subjectDao.findById(subject.getId())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found for ID: " + subject.getId()));

        student.getSubjects().add(persistedSubject);
        studentDao.save(student);
        logger.info("Enroll Successful");
    }
}
