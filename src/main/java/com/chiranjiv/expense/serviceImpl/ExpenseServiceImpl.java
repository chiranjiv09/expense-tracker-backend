package com.chiranjiv.expense.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chiranjiv.expense.entity.Expense;
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.repo.ExpenseRepo;
import com.chiranjiv.expense.service.ExpenseService;
import com.chiranjiv.expense.utils.ConstantData;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired ExpenseRepo expenseRepo;
	

	public void getMonthlyExpenseList(Users user, Integer monthId, Map<String, Object> map) {
	    Integer month = ConstantData.monthExpenseMap.get(monthId);
	    int currentYear = LocalDate.now().getYear();
	    YearMonth yearMonth = YearMonth.of(currentYear, month);
	    LocalDate startDate = yearMonth.atDay(1);
	    LocalDate endDate = yearMonth.plusMonths(1).atDay(1); 
	    
	    // Convert LocalDate to Date
	    Date startDateDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    Date endDateDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	    List<Expense> monthlyExpense = expenseRepo.getParticularMonthExpense(user.getUserId(), startDateDate, endDateDate);
	    map.put("status", true);
	    map.put("result", monthlyExpense);
	}


	@Override
	public void postMonthlyExpense(Users user, Expense expense, Map<String, Object> map) {
		expense.setUserId(user.getUserId());
		expenseRepo.save(expense);
		map.put("status", true);
		map.put("data", expense);
	}


	

}
