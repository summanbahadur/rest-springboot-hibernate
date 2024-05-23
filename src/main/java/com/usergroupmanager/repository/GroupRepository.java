package com.usergroupmanager.repository;

import com.usergroupmanager.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
