package com.chiranjiv.expense.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.chiranjiv.expense.entity.ConstData;

public interface ConstDataRepo extends JpaRepository<ConstData, Integer>{
	
	@Query(" from ConstData where constType = ?1 and isActive = 'Y'")
    List<ConstData> getAllConstantDataList(String groupName);

}
