package com.chiranjiv.expense.entity;

import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	private String userName;
	
	private Integer mobile;
	
	private String password;
	
	@JsonIgnore
	private Boolean isActive;
	
	@CreationTimestamp
	@JsonIgnore
	private Date createdate;
	
	@JsonIgnore
	@UpdateTimestamp
	private Date modidate;
	
	
	
	
}