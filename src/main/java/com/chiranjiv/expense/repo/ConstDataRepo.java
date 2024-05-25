package com.chiranjiv.expense.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chiranjiv.expense.entity.ConstData;

public interface ConstDataRepo extends JpaRepository<ConstData, Integer>{
	
	@Query(value = "SELECT a.const_id, a.const_name, a.const_type FROM const_data a WHERE a.const_type IN (:groupNames) AND a.is_active = 'Y'", nativeQuery = true)
    List<ConstData> getAllConstantDataList(@Param("groupNames") List<String> groupNames);

}
