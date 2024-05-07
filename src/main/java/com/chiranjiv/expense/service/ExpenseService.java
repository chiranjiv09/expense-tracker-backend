package com.chiranjiv.expense.service;

import java.util.Map;

import com.chiranjiv.expense.entity.Expense;
import com.chiranjiv.expense.entity.Users;

public interface ExpenseService {

	void getMonthlyExpenseList(Users user, Map<String, Object> map);

	void postMonthlyExpense(Users user, Expense expense, Map<String, Object> map);

}
