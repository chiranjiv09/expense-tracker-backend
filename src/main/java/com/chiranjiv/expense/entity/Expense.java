package com.chiranjiv.expense.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
