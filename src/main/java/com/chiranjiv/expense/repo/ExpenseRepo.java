package com.chiranjiv.expense.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.chiranjiv.expense.entity.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

	@Query("from Expense where userId=?1 and isActive = 'Y'")
	List<Expense> getAllMonthlyExpenses(Integer userId);

}
