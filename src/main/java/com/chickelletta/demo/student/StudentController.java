package com.chickelletta.demo.student;
//this class contains all the resources for our api...it is our api layer...

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController//makes the class serve restful endpoints...
@RequestMapping(path = "api/v1/student")//on the web browser, it will be...localhost/<port number>/api/v1/student...
public class StudentController {

    //setting the fields for the class...

    private final StudentService studentService;//this is a reference to the StudentService class...

//creating a constructor for the class...
    @Autowired// use this for dependency injection (@Autowired in the dependent class + @Component in the independent class
    // + saving the class in the constructor = dependency injection...)
    public StudentController(StudentService studentService) {
        this.studentService = studentService; //dependency injection...
    }

    @GetMapping//this makes this class a restful endpoint...//dependency injection...
    public List<Student> getStudents(){
            return studentService.getStudents();
    }

    //adding a new student to the database...
    @PostMapping//this is to make this function work...
    public void registerNewStudent(@RequestBody Student student){
       //@RequestBody allows us to take the student details the client provides...
        //invoking the service class...
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path ="studentId")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }
}
