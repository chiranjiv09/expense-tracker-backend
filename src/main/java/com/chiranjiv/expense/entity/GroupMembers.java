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

@Entity
@Data
public class GroupMembers {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer memberId;
	
	private Integer groupId;

	@JsonIgnore
	private Integer userId;
	
	@JsonIgnore
	private String userName;
	
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
