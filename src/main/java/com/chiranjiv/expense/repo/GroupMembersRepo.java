package com.chiranjiv.expense.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiranjiv.expense.entity.GroupMembers;

public interface GroupMembersRepo extends JpaRepository<GroupMembers,Integer> {


	GroupMembers findByUserIdAndGroupId(Integer userId, Integer groupId);

}
