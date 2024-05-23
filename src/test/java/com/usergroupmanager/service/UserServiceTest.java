package com.usergroupmanager.service;

import com.usergroupmanager.dto.UserDTO;
import com.usergroupmanager.exception.RequiredObjectIsNullException;
import com.usergroupmanager.exception.ResourceNotFoundException;
import com.usergroupmanager.model.User;
import com.usergroupmanager.repository.UserRepository;
import com.usergroupmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createUser() {
    User user = new User();
    user.setUsername("testuser");
    user.setPassword("password");
    user.setEmail("test@example.com");

    when(userRepository.save(any(User.class))).thenReturn(user);

    User createdUser = userService.createUser(user);

    assertNotNull(createdUser);
    assertEquals("testuser", createdUser.getUsername());
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void createUser_NullUser() {
    assertThrows(RequiredObjectIsNullException.class, () -> userService.createUser(null));
  }

  @Test
  void getUserById() {
    User user = new User();
    user.setId(1L);
    user.setUsername("testuser");

    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    User foundUser = userService.getUserById(1L);

    assertNotNull(foundUser);
    assertEquals("testuser", foundUser.getUsername());
    verify(userRepository, times(1)).findById(1L);
  }

  @Test
  void getUserById_NotFound() {
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
  }

  @Test
  void getAllUsers() {
    User user1 = new User();
    user1.setUsername("user1");
    User user2 = new User();
    user2.setUsername("user2");

    List<User> users = Arrays.asList(user1, user2);

    when(userRepository.findAll()).thenReturn(users);

    List<User> allUsers = userService.getAllUsers();

    assertNotNull(allUsers);
    assertEquals(2, allUsers.size());
    verify(userRepository, times(1)).findAll();
  }

  @Test
  void updateUser() {
    User existingUser = new User();
    existingUser.setId(1L);
    existingUser.setUsername("existingUser");

    User updatedUser = new User();
    updatedUser.setUsername("updatedUser");
    updatedUser.setEmail("updated@example.com");

    when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(any(User.class))).thenReturn(existingUser);

    User result = userService.updateUser(1L, updatedUser);

    assertNotNull(result);
    assertEquals("updatedUser", result.getUsername());
    verify(userRepository, times(1)).findById(1L);
    verify(userRepository, times(1)).save(existingUser);
  }

  @Test
  void deleteUser() {
    // Arrange
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    // Act
    userService.deleteUser(userId);
    // Assert
    verify(userRepository).delete(user);
  }

  @Test
  void deleteUser_InvalidUserId_ThrowsException() {
    // Arrange
    Long invalidUserId = 999L;

    when(userRepository.findById(invalidUserId)).thenReturn(Optional.empty());

    // Act and Assert
    assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(invalidUserId));
  }
}

