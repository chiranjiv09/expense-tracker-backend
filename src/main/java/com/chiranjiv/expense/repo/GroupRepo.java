package com.chiranjiv.expense.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.chiranjiv.expense.entity.Group;

public interface GroupRepo extends JpaRepository<Group, Integer> {

	Group findByGroupId(Integer groupId);

	@Query("from Group g where g.isActive = :isActive and g.groupId in :groupIds")
	List<Group> findByListOfGroupIds(@Param("groupIds") List<Integer> groupIds, @Param("isActive") String isActive);


}
