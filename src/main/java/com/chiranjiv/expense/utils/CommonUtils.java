package com.chiranjiv.expense.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chiranjiv.expense.entity.ConstData;
import com.chiranjiv.expense.repo.ConstDataRepo;

@Component
public class CommonUtils {
	
	@Autowired private ConstDataRepo constDataRepo;

	public List<ConstData> getConstantListfromGroupNameList(List<String> groupName, List<ConstData> resultList) {
		if(groupName != null && !groupName.isEmpty()) {
			for (String group : groupName) {
				resultList.addAll(constDataRepo.getAllConstantDataList(group));
			}
			return resultList;
		}
		return resultList;
	}
	
	

}
