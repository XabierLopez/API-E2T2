package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.Student;
import eus.fpsanturtzilh.pag.e2t2.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id " + id));
    }

    public Student createStudent(Student student) {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student name cannot be null or blank");
        }
        if (student.getSurname() == null || student.getSurname().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student surname cannot be null or blank");
        }
        return studentRepo.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student studentOld = studentRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id " + id));
        if (updatedStudent.getName() == null || updatedStudent.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student name cannot be null or blank");
        }
        if (updatedStudent.getSurname() == null || updatedStudent.getSurname().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student surname cannot be null or blank");
        }
        studentOld.setName(updatedStudent.getName());
        studentOld.setSurname(updatedStudent.getSurname());
        return studentRepo.save(studentOld);
    }

    public void deleteStudent(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id " + id);
        }
        studentRepo.deleteById(id);
    }
}
