package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.ServiceEntity;
import eus.fpsanturtzilh.pag.e2t2.repository.ServiceRepository;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepo;

    public ServiceService(ServiceRepository serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    public List<ServiceEntity> getAllServices() {
        return serviceRepo.findAll();
    }

    public ServiceEntity getServiceById(Long id) {
        return serviceRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found with id " + id));
    }

    public ServiceEntity createService(ServiceEntity service) {
        if (service.getName() == null || service.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service name cannot be null or blank");
        }
        if (service.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service price cannot be null");
        }
        if (service.getHome_price() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service home_price cannot be null");
        }
        if (service.getDuration() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service duration cannot be null");
        }
        return serviceRepo.save(service);
    }

    public ServiceEntity updateService(Long id, ServiceEntity updatedService) {
        ServiceEntity serviceOld = serviceRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found with id " + id));

        if (updatedService.getName() == null || updatedService.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service name cannot be null or blank");
        }
        if (updatedService.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service price cannot be null");
        }
        if (updatedService.getHome_price() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service home_price cannot be null");
        }
        if (updatedService.getDuration() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service duration cannot be null");
        }

        serviceOld.setName(updatedService.getName());
        serviceOld.setPrice(updatedService.getPrice());
        serviceOld.setHome_price(updatedService.getHome_price());
        serviceOld.setDuration(updatedService.getDuration());

        return serviceRepo.save(serviceOld);
    }

    public void deleteService(Long id) {
        if (!serviceRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found with id " + id);
        }
        serviceRepo.deleteById(id);
    }
}
