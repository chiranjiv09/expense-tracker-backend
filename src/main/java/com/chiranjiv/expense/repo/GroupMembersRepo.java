package com.chiranjiv.expense.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chiranjiv.expense.entity.GroupMembers;

public interface GroupMembersRepo extends JpaRepository<GroupMembers,Integer> {


	GroupMembers findByUserIdAndGroupId(Integer userId, Integer groupId);

	GroupMembers findByUserIdAndGroupIdAndIsActive(Integer userId, Integer groupId, String string);
	
 	@Modifying
    @Transactional
    @Query("update GroupMembers gm set gm.isActive = ?3 where gm.userId = ?1 and gm.groupId = ?2")
    void updateIsActiveOfGroupMember(Integer userId, Integer groupId, String isActive);

 	@Query(value = "SELECT gm.group_id FROM group_members gm WHERE gm.user_id = :userId AND gm.is_active = :isActive", nativeQuery = true)
 	List<Integer> findByUserIdAndIsActive(@Param("userId") Integer userId, @Param("isActive") String isActive);

	
	List<GroupMembers> findByGroupIdAndIsActive(Integer groupId, String isActive);

}
