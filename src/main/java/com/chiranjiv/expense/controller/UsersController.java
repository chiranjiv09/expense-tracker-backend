package com.chiranjiv.expense.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.service.ExpenseService;
import com.chiranjiv.expense.service.UsersService;
import com.chiranjiv.expense.utils.UserHelperUtils;

@RestController
@RequestMapping("user/v1")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired ExpenseService expenseService;
	
	
	
	@PostMapping("/register")
	@ResponseBody
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
	
	
	@PostMapping("/auth/login")
	public ResponseEntity<Map<String, Object>> userMobileLogin(@RequestBody Users user,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		try {
			map = usersService.userLoginAuthentication(map,user);
			if(map.containsKey("token")) {
	        	Cookie passwordReset = new Cookie("token", map.get("token")+"");
	        	passwordReset.setMaxAge(1000*24*60*60);
	        	passwordReset.setHttpOnly(true);
	        	passwordReset.setSecure(true);
	        	passwordReset.setPath("/");
	        	response.addCookie(passwordReset);
	        }
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("home-page")
	public ResponseEntity<Map<String, Object>> callingHomePage(@RequestParam Integer monthId){
		Map<String, Object> map = new HashMap<>();
		try {
			Users user = UserHelperUtils.getLoggedInUser();
			if(user != null) {
				expenseService.getMonthlyExpenseList(user,monthId,map);
				map.put("status", true);
				map.put("user", user);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	        }else {
	        	map.put("status", false);
	        	map.put("user", null);
	        	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
	        }
		} catch (Exception e) {
			map.put("status", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	

	
	
	
	

}
