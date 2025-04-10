package ca.lenguyen.resfulwebserviceproject.controllers;

import ca.lenguyen.resfulwebserviceproject.beans.Student;
import ca.lenguyen.resfulwebserviceproject.repository.DatabaseAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/students") // default URL: http://localhost:8080/api/students
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    private DatabaseAccess da;


    public StudentController(DatabaseAccess da){
        this.da = da;
    }


    @GetMapping
    public List<Student> getStudentCollection() {
        return da.findAll();
    }


    @GetMapping(value = "/{id}")  // "value" only here to illustrate our Mappings can //do more!
    public Student getIndividualStudent(@PathVariable Long id) {
        return da.findById(id).get();
    }


    @PostMapping(consumes = "application/json")
    public Student postStudent(@RequestBody Student student) {
        return da.save(student);
    }

    @PutMapping(consumes = "application/json")
    public Long putStudentCollection(@RequestBody List<Student> studentList) {
        //da.deleteAll();
        da.saveAll(studentList);
        return da.count();
    }

    @PutMapping("/{id}")
    public Long putIndividualStudent(@RequestBody Student student,@PathVariable Long id) {
        student.setId(id);
        da.save(student);
        log.info("student " + student);
        return da.count();
    }

    @DeleteMapping("/{id}")
    public Long deleteIndividualStudent(@PathVariable Long id) {
        log.info("delete","delete student " + id);
        da.deleteById(id);
        return da.count();
    }

    @DeleteMapping
    public Long deleteAllStudent() {
        da.deleteAll();
        return da.count();
    }


}
