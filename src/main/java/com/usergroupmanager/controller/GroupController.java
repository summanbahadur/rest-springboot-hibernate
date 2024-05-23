package com.usergroupmanager.controller;

import com.usergroupmanager.model.Group;
import com.usergroupmanager.model.User;
import com.usergroupmanager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

  @Autowired
  private GroupService groupService;

  @PostMapping
  public ResponseEntity<Group> createGroup(@Validated @RequestBody Group group) {
    return ResponseEntity.ok(groupService.createGroup(group));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
    return ResponseEntity.ok(groupService.getGroupById(id));
  }

  @GetMapping
  public ResponseEntity<List<Group>> getAllGroups() {
    return ResponseEntity.ok(groupService.getAllGroups());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Group> updateGroup(@PathVariable Long id, @Validated @RequestBody Group group) {
    return ResponseEntity.ok(groupService.updateGroup(id, group));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
    groupService.deleteGroup(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{groupId}/users")
  public ResponseEntity<List<User>> getGroupUsers(@PathVariable Long groupId) {
    return ResponseEntity.ok(groupService.getGroupUsers(groupId));
  }
}

