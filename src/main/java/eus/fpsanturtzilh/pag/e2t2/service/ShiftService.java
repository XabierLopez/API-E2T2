package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.Shift;
import eus.fpsanturtzilh.pag.e2t2.model.Student;
import eus.fpsanturtzilh.pag.e2t2.repository.ShiftRepository;
import eus.fpsanturtzilh.pag.e2t2.repository.StudentRepository;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepo;
    private final StudentRepository studentRepo;

    public ShiftService(ShiftRepository shiftRepo, StudentRepository studentRepo) {
        this.shiftRepo = shiftRepo;
        this.studentRepo = studentRepo;
    }

    public List<Shift> getAllShifts() {
        return shiftRepo.findAll();
    }

    public Shift getShiftById(Long id) {
        return shiftRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift not found with id " + id));
    }

    public Shift createShift(Shift shift) {
        if (shift.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shift type cannot be null");
        }
        if (shift.getStudent() == null || shift.getStudent().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student ID cannot be null");
        }
        Student student = studentRepo.findById(shift.getStudent().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id " + shift.getStudent().getId()));
        shift.setStudent(student);
        return shiftRepo.save(shift);
    }

    public Shift updateShift(Long id, Shift updatedShift) {
        Shift shiftOld = shiftRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift not found with id " + id));
        if (updatedShift.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shift type cannot be null");
        }
        shiftOld.setType(updatedShift.getType());
        if (updatedShift.getStudent() != null && updatedShift.getStudent().getId() != null) {
            Student student = studentRepo.findById(updatedShift.getStudent().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id " + updatedShift.getStudent().getId()));
            shiftOld.setStudent(student);
        }
        return shiftRepo.save(shiftOld);
    }

    public void deleteShift(Long id) {
        if (!shiftRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift not found with id " + id);
        }
        shiftRepo.deleteById(id);
    }
}
