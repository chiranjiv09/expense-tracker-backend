package com.chiranjiv.expense.repo;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.chiranjiv.expense.entity.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

	@Query("from Expense where userId=?1 and isActive = 'Y'")
	List<Expense> getAllMonthlyExpenses(Integer userId);
	
	
	 @Query("FROM Expense e WHERE e.userId = :userId AND e.expenseDate >= :startDate AND e.expenseDate < :endDate AND e.isActive = 'Y'")
	    List<Expense> getParticularMonthExpense(@Param("userId") Integer userId,
	                                            @Param("startDate") Date startDateDate,
	                                            @Param("endDate") Date endDateDate);

}
