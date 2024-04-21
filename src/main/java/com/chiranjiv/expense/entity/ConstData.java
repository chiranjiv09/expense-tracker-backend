package com.chiranjiv.expense.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@JsonInclude(Include.NON_NULL)
@Data
public class ConstData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer constId;
	
	private String constName;
	
	private String constType;
	
	@JsonIgnore
	private String isActive;
	
	@JsonIgnore
	@CreationTimestamp
	private Date createdate;
	
	@JsonIgnore
	@UpdateTimestamp
	private Date modidate;
}
