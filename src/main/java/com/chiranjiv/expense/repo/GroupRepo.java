package com.chiranjiv.expense.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiranjiv.expense.entity.Group;

public interface GroupRepo extends JpaRepository<Group, Integer> {

	Group findByGroupId(Integer groupId);

}
