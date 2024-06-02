package com.chiranjiv.expense.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.chiranjiv.expense.entity.GroupMembers;

public interface GroupMembersRepo extends JpaRepository<GroupMembers,Integer> {


	GroupMembers findByUserIdAndGroupId(Integer userId, Integer groupId);

	GroupMembers findByUserIdAndGroupIdAndIsActive(Integer userId, Integer groupId, String string);
	
 	@Modifying
    @Transactional
    @Query("update GroupMembers gm set gm.isActive = ?3 where gm.userId = ?1 and gm.groupId = ?2")
    void updateIsActiveOfGroupMember(Integer userId, Integer groupId, String isActive);

}
