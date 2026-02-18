package eus.fpsanturtzilh.pag.e2t2.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import eus.fpsanturtzilh.pag.e2t2.model.Group;
import eus.fpsanturtzilh.pag.e2t2.repository.GroupRepository;

@Service
public class GroupService {

    private final GroupRepository groupRepo;

    public GroupService(GroupRepository groupRepo) {
        this.groupRepo = groupRepo;
    }

    public List<Group> getAllGroups() {
        return groupRepo.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found with id " + id));
    }

    public Group createGroup(Group group) {
        if (group.getName() == null || group.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group name cannot be null or blank");
        }

        return groupRepo.save(group);
    }

    @Transactional
    public Group updateGroup(Long id, Group updatedGroup) {
        Group groupOld = groupRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found with id " + id));

        if (updatedGroup.getName() == null || updatedGroup.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group name cannot be null or blank");
        }

        groupOld.setName(updatedGroup.getName());

        return groupRepo.save(groupOld);
    }

    public void deleteGroup(Long id) {
        if (!groupRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found with id " + id);
        }
        groupRepo.deleteById(id);
    }
}
