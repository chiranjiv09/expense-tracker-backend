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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(Include.NON_NULL)
public class Expense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer expenseId;
	
	private Integer userId;
	private Integer groupId;
	private Integer categoryId;
	private String description;
	private Double price;
	private Date expenseDate;
	
	@JsonIgnore
	private String isActive;
	
	@CreationTimestamp
	@JsonIgnore
	@Column(name="createdate", nullable = false, updatable = false)
	private Date createdate;
	
	@JsonIgnore
	@UpdateTimestamp
	private Date modidate;

}
