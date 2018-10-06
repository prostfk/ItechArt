package by.prostrmk.firstsqltask.repository;

import by.prostrmk.firstsqltask.model.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findStudentById(int id);
    List<Student> findAll();
    List<Student> findStudentsByFirstNameLikeIgnoreCase(String firstName);
    List<Student> findStudentsBySex(boolean sex);
    List<Student> findStudentsByHostelLive(boolean hostelLive);
    List<Student> findStudentsByBirthDate(Date date);
    List<Student> findStudentsByLastName(String lastName);

}
