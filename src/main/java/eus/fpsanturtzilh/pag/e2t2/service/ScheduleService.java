package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.Group;
import eus.fpsanturtzilh.pag.e2t2.model.Schedule;
import eus.fpsanturtzilh.pag.e2t2.repository.GroupRepository;
import eus.fpsanturtzilh.pag.e2t2.repository.ScheduleRepository;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepo;
    private final GroupRepository groupRepo;

    public ScheduleService(ScheduleRepository scheduleRepo, GroupRepository groupRepo) {
        this.scheduleRepo = scheduleRepo;
        this.groupRepo = groupRepo;//erlazionatutako entitatearen repoa ere txertatu, erlazioaren gaineko eragiketak egiteko (group)
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found with id " + id));
    }

    @Transactional //beharrezkoa fetch type lazy erlazioen gaineko eragiketarako 
    public Schedule createSchedule(Schedule schedule) {

        if (schedule.getDay() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Day cannot be null");
        }
        if (schedule.getStart_time() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start time cannot be null");
        }
        if (schedule.getEnd_time() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End time cannot be null");
        }
        if (schedule.getGroup() == null || schedule.getGroup().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group ID cannot be null");
        }

        Group group = groupRepo.findById(schedule.getGroup().getId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Group not found with id " + schedule.getGroup().getId()
            ));

        schedule.setGroup(group);

        return scheduleRepo.save(schedule);
    }

    @Transactional
    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        Schedule scheduleOld = scheduleRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found with id " + id));

        if (updatedSchedule.getDay() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Day cannot be null");
        }
        if (updatedSchedule.getStart_date() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date cannot be null");
        }
        if (updatedSchedule.getEnd_date() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End date cannot be null");
        }
        if (updatedSchedule.getStart_time() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start time cannot be null");
        }
        if (updatedSchedule.getEnd_time() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End time cannot be null");
        }
        

        scheduleOld.setDay(updatedSchedule.getDay());
        scheduleOld.setStart_date(updatedSchedule.getStart_date());
        scheduleOld.setEnd_date(updatedSchedule.getEnd_date());
        scheduleOld.setStart_time(updatedSchedule.getStart_time());
        scheduleOld.setEnd_time(updatedSchedule.getEnd_time());

        if (updatedSchedule.getGroup() != null) {
            Group group = groupRepo.findById(updatedSchedule.getGroup().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found with id " + updatedSchedule.getGroup().getId()));
            scheduleOld.setGroup(group);
        }

        return scheduleRepo.save(scheduleOld);
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found with id " + id);
        }
        scheduleRepo.deleteById(id);
    }
}
