package com.chiranjiv.expense.service;

import java.util.Map;

import com.chiranjiv.expense.entity.Group;
import com.chiranjiv.expense.entity.Users;


public interface GroupService {

	void groupDataEdit(Users user, Group group, Integer userId, String identifier, Map<String, Object> responseMap);

}
