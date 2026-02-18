package eus.fpsanturtzilh.pag.e2t2.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentServiceDTO {
    private Long id;
    private String comment;

    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentStartTime;
    private LocalTime appointmentEndTime;

    private Long serviceId;
    private String serviceName;
    private Integer duration;
}
