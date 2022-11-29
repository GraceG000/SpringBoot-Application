package com.chickelletta.demo.student;

import javax.persistence.*;//whenever you import a package, use this...
import java.time.LocalDate;
import java.time.Period;


@Entity//for hibernate
@Table(name = "student") //for creating a table in the database use these two to connect to a table in a database...
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
//setting the fields for the class/database...
    private long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient//removes the need for this field to be a column, since it will be calculated first...
    private Integer age;

    //class constructor...
    public Student() {
    }

    //class constructor...
    public Student(long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    //saving the fields of the class...
    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    //creating our getters and setters...
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();//to calculate age...
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
