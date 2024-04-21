package com.chiranjiv.expense.entity;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Expense {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer expenseId;
	private Integer userId;
	private Integer groupId;
	private Integer categoryId;
	private String description;
	private Integer price;
	private Date expenseDate;

}
