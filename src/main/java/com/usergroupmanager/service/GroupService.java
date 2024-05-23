package com.usergroupmanager.service;

import com.usergroupmanager.exception.RequiredObjectIsNullException;
import com.usergroupmanager.exception.ResourceNotFoundException;
import com.usergroupmanager.model.Group;
import com.usergroupmanager.model.User;
import com.usergroupmanager.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
  @Autowired
  private GroupRepository groupRepository;

  public Group createGroup(Group group) {
    if (group == null) {
      throw new RequiredObjectIsNullException("Group object is null");
    }
    return groupRepository.save(group);
  }

  public Group getGroupById(Long id) {
    return groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + id));
  }

  public List<Group> getAllGroups() {
    return groupRepository.findAll();
  }

  public Group updateGroup(Long id, Group group) {
    if (group == null) {
      throw new RequiredObjectIsNullException("Group object is null");
    }

    Group existingGroup =
        groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + id));

    existingGroup.setName(group.getName());
    existingGroup.setDescription(group.getDescription());
    return groupRepository.save(existingGroup);
  }

  public void deleteGroup(Long id) {
    if (!groupRepository.existsById(id)) {
      throw new ResourceNotFoundException("Group not found for this id :: " + id);
    }
    groupRepository.deleteById(id);
  }

  public List<User> getGroupUsers(Long groupId) {
    Group group =
        groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + groupId));
    return List.copyOf(group.getUsers());
  }
}

