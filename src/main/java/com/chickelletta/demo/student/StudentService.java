package com.chickelletta.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//this is the business/service layer of the application...
//the api layer talks to this layer...
@Service //making this a spring bean...
//@Service = @Component...you can use either...
//@Service allows you to implement business logic...

public class StudentService {

    private final StudentRepository studentRepository; //step one in performing dependency injection...

    @Autowired//step two in performing dependency injection...
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();//this returns a list...
    }

    public void addNewStudent(Student student) {
        Optional<Student>studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }
    public void deleteStudent(Long studentId){
      boolean exists = studentRepository.existsById(studentId);

      if(!exists){
          throw new IllegalStateException("Student with id " + studentId + " does not exist." );
      }
      studentRepository.deleteById(studentId);
    }

    @Transactional//lets the entity become a managed one...
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException(
                "Student with id " + studentId + " does not exist."));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student>studentOptional = studentRepository.findStudentByEmail(student.getEmail());

            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
