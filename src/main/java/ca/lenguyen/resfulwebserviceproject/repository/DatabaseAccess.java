package ca.lenguyen.resfulwebserviceproject.repository;

import ca.lenguyen.resfulwebserviceproject.beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatabaseAccess extends JpaRepository<Student, Long> {
    List<Student> findStudentByName(String name);
}
