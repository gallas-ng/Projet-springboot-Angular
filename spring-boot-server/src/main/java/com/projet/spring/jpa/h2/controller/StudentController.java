package com.projet.spring.jpa.h2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.math.*;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import com.projet.spring.jpa.h2.model.Student;
        import com.projet.spring.jpa.h2.model.Tutorial;
        import com.projet.spring.jpa.h2.repository.StudentRepository;
        import com.projet.spring.jpa.h2.repository.TutorialRepository;

@CrossOrigin//(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TutorialRepository tutorialRepository;

    @CrossOrigin
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String name) {
        try {
            List<Student> students = new ArrayList<Student>();

            if (name == null)
                studentRepository.findAll().forEach(students::add);
            else
                studentRepository.findByNameContaining(name).forEach(students::add);

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student _student = studentRepository
                    .save(new Student(student.getName()));
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            Student _student = studentData.get();
            _student.setName(student.getName());
            return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/students")
    public ResponseEntity<HttpStatus> deleteAllStudents() {
        try {
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/students/groups")
    public ResponseEntity<List<Student>> randomizeGroups() {
        List<Student> students = studentRepository.findAll();
        List<Tutorial> tutorials = tutorialRepository.findAll();
        System.out.println(tutorials);
        // mélange aléatoirement les étudiants
        int groupSize = (students.size()/tutorials.size());
        Collections.shuffle(students);
        for (int i = 0; i < tutorials.size(); i++) {
            Tutorial tutorial = tutorials.get(i);
            for (int j = 0; j < groupSize; j++) {
                int index = i * groupSize + j;
                if (index < students.size()) {
                    Student student = students.get(index);
                    student.setTutorial(tutorial);
                    System.out.println(student.getTutorial());
                }
            }
            if (i == tutorials.size()-1)
            for (Student s: students) {
                if (s.getTutorial() == null){
                    Tutorial tuto = tutorials.get(i);
                    s.setTutorial(tuto);
                }
            }
        }
        studentRepository.saveAll(students);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


}
