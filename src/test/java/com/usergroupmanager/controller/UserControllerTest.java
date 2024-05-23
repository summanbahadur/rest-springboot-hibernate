package com.usergroupmanager.controller;

import com.usergroupmanager.controller.UserController;
import com.usergroupmanager.dto.UserDTO;
import com.usergroupmanager.model.Group;
import com.usergroupmanager.model.User;
import com.usergroupmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {
  private MockMvc mockMvc;
  @Mock
  private UserService userService;
  @InjectMocks
  private UserController userController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  void createUser() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setUsername("testUser");
    user.setEmail("test@example.com");

    when(userService.createUser(any(User.class))).thenReturn(user);

    MockHttpServletRequestBuilder requestBuilder = post("/api/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"username\":\"testUser\",\"email\":\"test@example.com\"}");

    // Act and Assert
    mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.username").value(user.getUsername()))
        .andExpect(jsonPath("$.email").value(user.getEmail()));
  }

  @Test
  void getUserById() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setUsername("testuser");

    when(userService.getUserById(1L)).thenReturn(user);

    mockMvc.perform(get("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("testuser"));
  }

  @Test
  void getAllUsers() throws Exception {
    User user1 = new User();
    user1.setUsername("user1");
    User user2 = new User();
    user2.setUsername("user2");

    List<User> users = Arrays.asList(user1, user2);

    when(userService.getAllUsers()).thenReturn(users);

    mockMvc.perform(get("/api/users")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].username").value("user1"))
        .andExpect(jsonPath("$[1].username").value("user2"));
  }

  @Test
  void updateUser() throws Exception {
    // Arrange
    User user = new User();
    user.setId(1L);
    user.setUsername("updatedUser");
    user.setEmail("updated@example.com");

    when(userService.updateUser(any(Long.class), any(User.class))).thenReturn(user);

    MockHttpServletRequestBuilder requestBuilder = put("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"username\":\"updatedUser\",\"email\":\"updated@example.com\"}");

    // Act and Assert
    mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.username").value(user.getUsername()))
        .andExpect(jsonPath("$.email").value(user.getEmail()));
  }

  @Test
  void deleteUser() throws Exception {
    mockMvc.perform(delete("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void addUserToGroup() throws Exception {
    mockMvc.perform(post("/api/users/1/groups/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void removeUserFromGroup() throws Exception {
    mockMvc.perform(delete("/api/users/1/groups/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void getUserGroups() throws Exception {
    Group group1 = new Group();
    group1.setName("group1");
    Group group2 = new Group();
    group2.setName("group2");

    List<Group> groups = Arrays.asList(group1, group2);

    when(userService.getUserGroups(1L)).thenReturn(groups);

    mockMvc.perform(get("/api/users/1/groups")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("group1"))
        .andExpect(jsonPath("$[1].name").value("group2"));
  }
}

