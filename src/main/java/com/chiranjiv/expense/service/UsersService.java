package com.chiranjiv.expense.service;

import java.util.Map;

import com.chiranjiv.expense.entity.Users;

public interface UsersService {

	Map<String, Object> registerUser(Map<String, Object> map, Users user);

}
