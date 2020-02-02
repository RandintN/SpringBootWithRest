package com.robsoncassiano.demo.service;

import com.robsoncassiano.demo.dao.StudentDao;
import com.robsoncassiano.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("mongoDb") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int persistNewStudent(UUID studentId, Student student) {
        UUID studentUid = studentId == null ? UUID.randomUUID() : studentId;
        student.setId(studentId);
        return studentDao.insertNewStudent(studentUid, student);
    }

    public Student getStudentById(UUID studentId) {
        return studentDao.selectStudentById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    public int updateStudentByID(UUID studentId, Student studentUpdate) {
        UUID studentUid = studentId == null ? UUID.randomUUID() : studentId;
        studentUpdate.setId(studentUid);
        return studentDao.updateStudentByID(studentId, studentUpdate);
    }

    public int deleteStudentById(UUID studentId) {
        Student student = getStudentById(studentId);
        if (student == null) {
            throw new IllegalStateException();
        }
        return studentDao.deleteStudentById(studentId);
    }
}