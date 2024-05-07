package com.chiranjiv.expense.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	private Double price;
	@CreationTimestamp
	private Date expenseDate;
	private Boolean isActive;
	
	@CreationTimestamp
	@JsonIgnore
	@Column(name="createdate", nullable = false, updatable = false)
	private Date createdate;
	
	@JsonIgnore
	@UpdateTimestamp
	private Date modidate;

}
