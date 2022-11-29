package com.chickelletta.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration// to make this a configuration file...
public class StudentConfig {

    @Bean//to make the function run...
    CommandLineRunner commandLineRunner(StudentRepository repository){//to access the student repository, we inject an object of the StudentRepository class...
        return args -> {
            Student Mariam = new Student(
                    "Mariam",
                    "mariam.jamaL@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

                 Student Joseph = new Student(
                            "Joseph",
                            "joeyG.jamaL@gmail.com",
                            LocalDate.of(2007, Month.OCTOBER, 15)
                    );
//saving our student objects to our database by invoking the repository interface...
repository.saveAll(
        List.of(Mariam, Joseph)
);
        };

    }

}
