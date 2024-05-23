package com.usergroupmanager.controller;

import com.usergroupmanager.dto.UserDTO;
import com.usergroupmanager.model.Group;
import com.usergroupmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import com.usergroupmanager.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@Validated @RequestBody User user) {
    return ResponseEntity.ok(userService.createUser(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Validated @RequestBody User user) {
    return ResponseEntity.ok(userService.updateUser(id, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{userId}/groups/{groupId}")
  public ResponseEntity<Void> addUserToGroup(@PathVariable Long userId, @PathVariable Long groupId) {
    userService.addUserToGroup(userId, groupId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}/groups/{groupId}")
  public ResponseEntity<Void> removeUserFromGroup(@PathVariable Long userId, @PathVariable Long groupId) {
    userService.removeUserFromGroup(userId, groupId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{userId}/groups")
  public ResponseEntity<List<Group>> getUserGroups(@PathVariable Long userId) {
    return ResponseEntity.ok(userService.getUserGroups(userId));
  }

}
