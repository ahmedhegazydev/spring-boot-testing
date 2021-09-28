package com.example.demo.student;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    void itShouldCheckIfStudentExistEmail(){

        //give
        String email = "engahmedhegazy2025@gmail.com";
        Student student = new Student(
                "Ahmed Mohamed",
                email,
                Gender.FEMALE);
        studentRepository.save(student);

        //when
        boolean expected =
                studentRepository.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();

    }

}
