package com.usergroupmanager.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.usergroupmanager.exception.RequiredObjectIsNullException;
import com.usergroupmanager.exception.ResourceNotFoundException;
import com.usergroupmanager.model.Group;
import com.usergroupmanager.repository.GroupRepository;
import com.usergroupmanager.service.GroupService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

  @Mock
  private GroupRepository groupRepository;

  @InjectMocks
  private GroupService groupService;

  @Test
  void createGroup_ValidGroup_ReturnsSavedGroup() {
    // Arrange
    Group group = new Group();
    group.setName("Test Group");
    group.setDescription("Test Description");

    when(groupRepository.save(any(Group.class))).thenReturn(group);

    // Act
    Group savedGroup = groupService.createGroup(group);

    // Assert
    assertNotNull(savedGroup);
    assertEquals(group.getName(), savedGroup.getName());
    assertEquals(group.getDescription(), savedGroup.getDescription());
  }

  @Test
  void createGroup_NullGroup_ThrowsException() {
    // Arrange
    Group nullGroup = null;

    // Act and Assert
    assertThrows(RequiredObjectIsNullException.class, () -> groupService.createGroup(nullGroup));
  }

  @Test
  void getGroupById_ExistingId_ReturnsGroup() {
    // Arrange
    Long groupId = 1L;
    Group group = new Group();
    group.setId(groupId);
    group.setName("Test Group");
    group.setDescription("Test Description");

    when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

    // Act
    Group retrievedGroup = groupService.getGroupById(groupId);

    // Assert
    assertNotNull(retrievedGroup);
    assertEquals(group.getId(), retrievedGroup.getId());
    assertEquals(group.getName(), retrievedGroup.getName());
    assertEquals(group.getDescription(), retrievedGroup.getDescription());
  }

}
