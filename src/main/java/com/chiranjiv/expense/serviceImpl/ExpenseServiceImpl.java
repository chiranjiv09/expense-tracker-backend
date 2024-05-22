package com.chiranjiv.expense.serviceImpl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chiranjiv.expense.entity.Expense;
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.repo.ExpenseRepo;
import com.chiranjiv.expense.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired ExpenseRepo expenseRepo;
	

	@Override
	public void getMonthlyExpenseList(Users user, Map<String, Object> map) {
		List<Expense> monthlyExpense = expenseRepo.getAllMonthlyExpenses(user.getUserId());
		map.put("status", true);
		map.put("result", monthlyExpense);
	}


	@Override
	public void postMonthlyExpense(Users user, Expense expense, Map<String, Object> map) {
		expense.setUserId(user.getUserId());
		expenseRepo.save(expense);
		map.put("status", true);
	}


	

}
