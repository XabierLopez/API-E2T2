package eus.fpsanturtzilh.pag.e2t2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.dto.AppointmentServiceDTO;
import eus.fpsanturtzilh.pag.e2t2.model.Appointment_service;
import eus.fpsanturtzilh.pag.e2t2.service.AppointmentServiceService;

@RestController
@RequestMapping("/api/appointment-services")
public class AppointmentServiceController {

    private final AppointmentServiceService service;

    public AppointmentServiceController(AppointmentServiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Appointment_service> getAllAppointmentServices() {
        return service.getAllAppointmentServices();
    }

    @GetMapping("/{id}")
    public Appointment_service getAppointmentServiceById(@PathVariable Long id) {
        return service.getAppointmentServiceById(id);
    }
    
    @GetMapping("/byAppointmentId/{appointmentId}")
    public List<AppointmentServiceDTO> getAppointmentServicesDTOByAppointmentId(@PathVariable Long appointmentId) {
        return service.getAppointmentServicesDTOByAppointmentId(appointmentId);
    }
    
    @GetMapping("/byServiceId/{serviceId}")
    public List<AppointmentServiceDTO> getAppointmentServicesDTOByServiceId(@PathVariable Long serviceId) {
        return service.getAppointmentServicesDTOByServiceId(serviceId);
    }

    @PostMapping
    public Appointment_service createAppointmentService(@RequestBody Appointment_service appointmentService) {
        return service.createAppointmentService(appointmentService);
    }

    @PutMapping("/{id}")
    public Appointment_service updateAppointmentService(@PathVariable Long id, @RequestBody Appointment_service updated) {
        return service.updateAppointmentService(id, updated);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointmentService(@PathVariable Long id) {
        service.deleteAppointmentService(id);
    }
}
