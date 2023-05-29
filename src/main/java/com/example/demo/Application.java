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
            Student ahmed = new Student(
                    "Ahmed",
                    "Ali",
                    "ahmed.ali@swinpostnetworks.ug",
                    18
            );

            System.out.println("Adding Maria and Ahmed");
            studentRepository.saveAll(List.of(maria,ahmed));

            System.out.print("Number of students: ");
            System.out.println(studentRepository.count());
            studentRepository
                    .findById(2L)
                    .ifPresentOrElse(
                            student -> System.out.println(student),
                            () -> System.out.println("Student with ID 2 not found"));
            studentRepository
                    .findById(3L)
                    .ifPresentOrElse(
                            student -> System.out.println(student),
                            () -> System.out.println("Student with ID 3 not found"));

            System.out.println("select all students");
            List<Student> students = studentRepository.findAll();
            students.forEach(System.out::println);

            System.out.println("delete maria");
            studentRepository.deleteById(1L);

            System.out.print("Number of students: ");
            System.out.println(studentRepository.count());
        };
    }

}
