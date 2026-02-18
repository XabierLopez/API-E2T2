package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.Appointment;
import eus.fpsanturtzilh.pag.e2t2.model.Client;
import eus.fpsanturtzilh.pag.e2t2.repository.AppointmentRepository;
import eus.fpsanturtzilh.pag.e2t2.repository.ClientRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final ClientRepository clientRepo;

    public AppointmentService(AppointmentRepository appointmentRepo, ClientRepository clientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.clientRepo = clientRepo;
    }


    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found with id " + id));
    }

    @Transactional
    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getSeat() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat cannot be null");
        }
        if (appointment.getDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date cannot be null");
        }
        if (appointment.getStart_time() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start time cannot be null");
        }
        if (appointment.getEnd_time() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End time cannot be null");
        }
        if (appointment.getName() == null || appointment.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be null or blank");
        }
        if (appointment.getClient() == null || appointment.getClient().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client ID cannot be null");
        }
        Client client = clientRepo.findById(appointment.getClient().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id " + appointment.getClient().getId()));
        appointment.setClient(client);
        return appointmentRepo.save(appointment);
    }

    @Transactional
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment appointmentOld = appointmentRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found with id " + id));

        if (updatedAppointment.getSeat() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat cannot be null");
        }
        if (updatedAppointment.getDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date cannot be null");
        }
        if (updatedAppointment.getStart_time() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start time cannot be null");
        }
        if (updatedAppointment.getEnd_time() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End time cannot be null");
        }
        if (updatedAppointment.getName() == null || updatedAppointment.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be null or blank");
        }
        appointmentOld.setSeat(updatedAppointment.getSeat());
        appointmentOld.setDate(updatedAppointment.getDate());
        appointmentOld.setStart_time(updatedAppointment.getStart_time());
        appointmentOld.setEnd_time(updatedAppointment.getEnd_time());
        appointmentOld.setComment(updatedAppointment.getComment());
        appointmentOld.setName(updatedAppointment.getName());
        if (updatedAppointment.getClient() != null && updatedAppointment.getClient().getId() != null) {
            Client client = clientRepo.findById(updatedAppointment.getClient().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id " + updatedAppointment.getClient().getId()));
            appointmentOld.setClient(client);
        }
        return appointmentRepo.save(appointmentOld);
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found with id " + id);
        }
        appointmentRepo.deleteById(id);
    }
}
