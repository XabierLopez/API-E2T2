package eus.fpsanturtzilh.pag.e2t2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.model.Shift;
import eus.fpsanturtzilh.pag.e2t2.service.ShiftService;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    private final ShiftService service;

    public ShiftController(ShiftService service) {
        this.service = service;
    }

    @GetMapping
    public List<Shift> getAllShifts() {
        return service.getAllShifts();
    }

    @GetMapping("/{id}")
    public Shift getShiftById(@PathVariable Long id) {
        return service.getShiftById(id);
    }

    @PostMapping
    public Shift createShift(@RequestBody Shift shift) {
        return service.createShift(shift);
    }

    @PutMapping("/{id}")
    public Shift updateShift(@PathVariable Long id, @RequestBody Shift updatedShift) {
        return service.updateShift(id, updatedShift);
    }

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable Long id) {
        service.deleteShift(id);
    }
}
