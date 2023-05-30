package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student maria = new Student(
                   "Maria",
                   "Jones",
                   "maria.jones@swinpostnetworks.ug",
                    21
            );
            Student maria2 = new Student(
                    "Maria",
                    "Jones",
                    "maria2.jones@swinpostnetworks.ug",
                    25
            );
            Student ahmed = new Student(
                    "Ahmed",
                    "Ali",
                    "ahmed.ali@swinpostnetworks.ug",
                    18
            );

            System.out.println("Adding Maria and Ahmed");
            studentRepository.saveAll(List.of(maria,ahmed,maria2));

            studentRepository.findStudentByEmail("ahmed.ali@swinpostnetworks.ug")
                    .ifPresentOrElse(System.out::println,
                            () -> System.out.println("Student with email ahmed.ali@swinpostnetworks.ug not found"));
            studentRepository.selectStudentsWhereFirstNameAndAgeGreaterOrEquals(
                    "Maria",
                    21
            ).forEach(System.out::println);
            studentRepository.selectStudentsWhereFirstNameAndAgeGreaterOrEqualsNative(
                    "Maria",
                    21
            ).forEach(System.out::println);

            studentRepository.selectStudentsWhereFirstNameAndAgeGreaterOrEqualsParametizedNative(
                    "Maria",
                    21
            ).forEach(System.out::println);

            System.out.println("Deleting Maria 2");
            System.out.println(studentRepository.deleteStudentById(3L));
        };
    }

}
