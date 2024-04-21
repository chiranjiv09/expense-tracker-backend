package com.chiranjiv.expense.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.service.UsersService;

@RestController
@RequestMapping("user/v1")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> userRegistration(@RequestBody Users user){
		Map<String, Object> map = new HashMap<>();
		try {
			map = usersService.registerUser(map,user);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
