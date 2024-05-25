package com.chiranjiv.expense.controller;

import java.util.HashMap;
import java.util.Map;
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
import com.chiranjiv.expense.entity.Expense;
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.service.ExpenseService;
import com.chiranjiv.expense.utils.UserHelperUtils;

@RestController
@RequestMapping("expense")
public class ExpenseController {
	
	@Autowired ExpenseService expenseService;
	
	
	@PostMapping("/add-expense")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> addMonthlyExpenses(@RequestBody Expense expense){
		Map<String, Object> map = new HashMap<>();
		try {
			Users user = UserHelperUtils.getLoggedInUser();
			if(user != null) {
				expenseService.postMonthlyExpense(user,expense,map);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}else {
				map.put("status",false);
				map.put("message", "user is not logged-in");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			map.put("status",false);
			map.put("message", "exception while adding monthly expense");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/get-monthly-expense")
	public ResponseEntity<Map<String, Object>> getMonthlyExpenses(@RequestParam Integer monthId){
		Map<String, Object> map = new HashMap<>();
		try {
			Users user = UserHelperUtils.getLoggedInUser();
			if(user != null) {
				expenseService.getMonthlyExpenseList(user,monthId,map);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}else {
				map.put("status",false);
				map.put("message", "user is not logged-in");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			map.put("status",false);
			map.put("message", "exception while getting monthly expense");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
}
