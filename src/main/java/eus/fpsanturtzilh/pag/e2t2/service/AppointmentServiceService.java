package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.Appointment_service;
import eus.fpsanturtzilh.pag.e2t2.model.ServiceEntity;
import eus.fpsanturtzilh.pag.e2t2.dto.AppointmentServiceDTO;
import eus.fpsanturtzilh.pag.e2t2.model.Appointment;
import eus.fpsanturtzilh.pag.e2t2.repository.AppointmentServiceRepository;
import eus.fpsanturtzilh.pag.e2t2.repository.ServiceRepository;
import eus.fpsanturtzilh.pag.e2t2.repository.AppointmentRepository;

@Service
public class AppointmentServiceService {

    private final AppointmentServiceRepository appointmentServiceRepo;
    private final ServiceRepository serviceRepo;
    private final AppointmentRepository appointmentRepo;

    public AppointmentServiceService(AppointmentServiceRepository appointmentServiceRepo, ServiceRepository serviceRepo, AppointmentRepository appointmentRepo) {
        this.appointmentServiceRepo = appointmentServiceRepo;
        this.serviceRepo = serviceRepo;
        this.appointmentRepo = appointmentRepo;
    }

    public List<Appointment_service> getAllAppointmentServices() {
        return appointmentServiceRepo.findAll();
    }

    public Appointment_service getAppointmentServiceById(Long id) {
        return appointmentServiceRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment_service not found with id " + id));
    }

    @Transactional(readOnly = true)//hibernatek jakiteko ez dela transakzioan aldatuko
    public List<AppointmentServiceDTO> getAppointmentServicesDTOByAppointmentId(Long appointmentId) {
        return appointmentServiceRepo.findAllByAppointmentId(appointmentId)
            .stream()
            .map(a -> {
                AppointmentServiceDTO dto = new AppointmentServiceDTO();
                dto.setId(a.getId());
                dto.setComment(a.getComment());

                dto.setAppointmentId(a.getAppointment().getId());
                dto.setAppointmentDate(a.getAppointment().getDate());
                dto.setAppointmentStartTime(a.getAppointment().getStart_time());
                dto.setAppointmentEndTime(a.getAppointment().getEnd_time());

                dto.setServiceId(a.getService().getId());
                dto.setServiceName(a.getService().getName());
                dto.setDuration(a.getService().getDuration());
                return dto;
            })
            .toList();
    }

    @Transactional(readOnly = true)//hibernatek jakiteko ez dela transakzioan aldatuko
    public List<AppointmentServiceDTO> getAppointmentServicesDTOByServiceId(Long serviceId) {
        return appointmentServiceRepo.findAllByAppointmentId(serviceId)
            .stream()
            .map(a -> {
                AppointmentServiceDTO dto = new AppointmentServiceDTO();
                dto.setId(a.getId());
                dto.setComment(a.getComment());

                dto.setAppointmentId(a.getAppointment().getId());
                dto.setAppointmentDate(a.getAppointment().getDate());
                dto.setAppointmentStartTime(a.getAppointment().getStart_time());
                dto.setAppointmentEndTime(a.getAppointment().getEnd_time());

                dto.setServiceId(a.getService().getId());
                dto.setServiceName(a.getService().getName());
                dto.setDuration(a.getService().getDuration());
                return dto;
            })
            .toList();
    }

    @Transactional
    public Appointment_service createAppointmentService(Appointment_service appointmentService) {
        if (appointmentService.getService() == null || appointmentService.getService().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service ID cannot be null");
        }
        if (appointmentService.getAppointment() == null || appointmentService.getAppointment().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Appointment ID cannot be null");
        }
        
        ServiceEntity service = serviceRepo.findById(appointmentService.getService().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found with id " + appointmentService.getService().getId()));
        Appointment appointment = appointmentRepo.findById(appointmentService.getAppointment().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found with id " + appointmentService.getAppointment().getId()));
        appointmentService.setService(service);
        appointmentService.setAppointment(appointment);
        return appointmentServiceRepo.save(appointmentService);
    }

    @Transactional
    public Appointment_service updateAppointmentService(Long id, Appointment_service updated) {
        Appointment_service old = appointmentServiceRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment_service not found with id " + id));
        if (updated.getService() != null && updated.getService().getId() != null) {
            ServiceEntity service = serviceRepo.findById(updated.getService().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found with id " + updated.getService().getId()));
            old.setService(service);
        }
        if (updated.getAppointment() != null && updated.getAppointment().getId() != null) {
            Appointment appointment = appointmentRepo.findById(updated.getAppointment().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found with id " + updated.getAppointment().getId()));
            old.setAppointment(appointment);
        }
        old.setComment(updated.getComment());
        return appointmentServiceRepo.save(old);
    }

    public void deleteAppointmentService(Long id) {
        if (!appointmentServiceRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment_service not found with id " + id);
        }
        appointmentServiceRepo.deleteById(id);
    }
}
