package eus.fpsanturtzilh.pag.e2t2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import eus.fpsanturtzilh.pag.e2t2.model.ServiceEntity;
import eus.fpsanturtzilh.pag.e2t2.service.ServiceService;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceEntity> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceEntity getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @PostMapping
    public ServiceEntity createService(@RequestBody ServiceEntity service) {
        return serviceService.createService(service);
    }

    @PutMapping("/{id}")
    public ServiceEntity updateService(@PathVariable Long id, @RequestBody ServiceEntity updatedService) {
        return serviceService.updateService(id, updatedService);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }
}
