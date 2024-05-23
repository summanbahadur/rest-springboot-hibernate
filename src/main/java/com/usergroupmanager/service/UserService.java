package com.usergroupmanager.service;

import java.util.List;

import com.usergroupmanager.dto.UserDTO;
import com.usergroupmanager.exception.RequiredObjectIsNullException;
import com.usergroupmanager.exception.ResourceNotFoundException;
import com.usergroupmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.usergroupmanager.model.Group;
import com.usergroupmanager.repository.GroupRepository;
import com.usergroupmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private GroupRepository groupRepository;

  public UserDTO createUser(User user) {
    if (user == null) {
      throw new RequiredObjectIsNullException("User object cannot be null");
    }
    return convertToDTO(userRepository.save(user));
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + id));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public UserDTO updateUser(Long id, User user) {
    if (user == null) {
      throw new RequiredObjectIsNullException("User object cannot be null");
    }
    return userRepository.findById(id).map(existingUser -> {
      existingUser.setUsername(user.getUsername());
      if (user.getPassword() != null && !user.getPassword().isEmpty()) {
        existingUser.setPassword(user.getPassword());
      }
      existingUser.setEmail(user.getEmail());
      existingUser.setGroups(user.getGroups());
      return convertToDTO( userRepository.save(existingUser));
    }).orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + id));
  }
  private UserDTO convertToDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setUsername(user.getUsername());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }
  public void deleteUser(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + id));
    userRepository.delete(user);
  }

  public void addUserToGroup(Long userId, Long groupId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + userId));
    Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group not found with id :: " + groupId));
    user.getGroups().add(group);
    userRepository.save(user);
  }

  public void removeUserFromGroup(Long userId, Long groupId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + userId));
    Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group not found with id :: " + groupId));
    user.getGroups().remove(group);
    userRepository.save(user);
  }

  public List<Group> getUserGroups(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + userId));
    return List.copyOf(user.getGroups());
  }
}


