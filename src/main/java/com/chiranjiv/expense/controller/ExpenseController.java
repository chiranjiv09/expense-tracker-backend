package com.chiranjiv.expense.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chiranjiv.expense.entity.Expense;
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.service.ExpenseService;
import com.chiranjiv.expense.utils.UserHelperUtils;

@RestController
@RequestMapping("expense")
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {
	
	@Autowired ExpenseService expenseService;
	
	
	@PostMapping("/add-expense")
    public ResponseEntity<Map<String, Object>> addMonthlyExpenses(@RequestBody Expense expense, HttpServletRequest req, HttpServletResponse res) {
        Map<String, Object> map = new HashMap<>();
        try {
            Users user = UserHelperUtils.getLoggedInUser();
            if (user != null) {
                expenseService.postMonthlyExpense(user, expense, map);
                return ResponseEntity.ok(map); // Returning success response
            } else {
                map.put("status", false);
                map.put("message", "User is not logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map); // Unauthorized response
            }
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", "Exception while adding monthly expense");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map); // Internal server error response
        }
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
