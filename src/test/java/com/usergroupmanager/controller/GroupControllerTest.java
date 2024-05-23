package com.usergroupmanager.controller;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.usergroupmanager.controller.GroupController;
import com.usergroupmanager.model.Group;
import com.usergroupmanager.model.User;
import com.usergroupmanager.service.GroupService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(GroupController.class)
public class GroupControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GroupService groupService;

  @Test
  void createGroup_ValidGroup_ReturnsCreated() throws Exception {
    // Arrange
    Group group = new Group();
    group.setId(1L);
    group.setName("Test Group");

    when(groupService.createGroup(any(Group.class))).thenReturn(group);

    // Act and Assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Test Group\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Group"));
  }
}

