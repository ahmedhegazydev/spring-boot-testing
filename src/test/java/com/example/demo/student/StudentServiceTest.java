package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


//    @Autowired
    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;
//    private AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository);
    }

//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void getAllStudents() {

        //when
        studentService.getAllStudents();

        //then
        verify(studentRepository).findAll();//Passed
//        verify(studentRepository).deleteAll();///Failed

    }

    @Test
//    @Disabled
    void canAddStudent() {

        //give
        String email = "engahmedhegazy2025@gmail.com";
        Student student = new Student(
                "Ahmed Mohamed",
                email,
                Gender.FEMALE);
        studentService.addStudent(student);

        //when
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        //then
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);


    }

    @Test
    @Disabled
    void deleteStudent() {
    }
}