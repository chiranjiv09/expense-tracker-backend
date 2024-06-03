package com.chiranjiv.expense.DTO;

import lombok.Data;

@Data
public class ExpenseSplitDto {
	
	Integer userId;
    public Double balance;
    
    public ExpenseSplitDto(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
