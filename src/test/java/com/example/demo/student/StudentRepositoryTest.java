package com.example.demo.student;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists(){

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

    @Test
    void itShouldCheckIfStudentEmailNotExists(){

        //give
        String email = "engahmedhegazy2025@gmail.com";

        //when
        boolean expected =
                studentRepository.selectExistsEmail(email);

        //then
//        assertThat(expected).isTrue();
        assertThat(expected).isFalse();


    }

}
