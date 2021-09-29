package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import com.example.demo.student.exception.StudentNotFoundException;
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
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.never;
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
//    @Disabled
    void willThrowWhenEmailIsTaken() {

        //given
        String email = "engahmedhegazy2025@gmail.com";
        Student student = new Student(
                "Ahmed Mohamed",
                email,
                Gender.FEMALE);

        given(studentRepository.selectExistsEmail(
//                student.getEmail()
                anyString()
        ))
                .willReturn(true);

        //when
        //then
        assertThatThrownBy(() -> {
            studentService.addStudent(student);
        })
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

        verify(studentRepository, never()).save(any());

    }

    @Test
//    @Disabled
    void canDeleteAllStudent() {
        //when
        studentService.deleteAllStudents();

        //then
        verify(studentRepository).deleteAll();//Passed
//        verify(studentRepository).findAll();//Failed
    }

    @Test
    void canDeleteStudent() {
        // given
        long id = 10;
        given(studentRepository.existsById(id))
                .willReturn(true);
        // when
        studentService.deleteStudent(id);

        // then
        verify(studentRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeleteStudentNotFound() {
        // given
        long id = 10;
        given(studentRepository.existsById(id))
                .willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> studentService.deleteStudent(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + id + " does not exists");

        verify(studentRepository, never()).deleteById(any());
    }



}