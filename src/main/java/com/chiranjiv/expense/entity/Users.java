package com.chiranjiv.expense.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Users implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	private String userName;
	
	private Long mobile;
	
	private String password;
	
	@JsonIgnore
	private String isActive;
	
	@CreationTimestamp
	@JsonIgnore
	@Column(name="createdate", nullable = false, updatable = false)
	private Date createdate;
	
	@JsonIgnore
	@UpdateTimestamp
	private Date modidate;

	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        // Return the authorities/roles assigned to the user (if applicable)
	        return null;
	    }

	    @Override
	    public String getUsername() {
	        return userName; // Return the username of the user
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true; // Assuming account expiration is not implemented, return true
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true; // Return the account status to determine if it's locked
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true; // Assuming credential expiration is not implemented, return true
	    }

	    @Override
	    public boolean isEnabled() {
	        return true; // Return the account status to determine if it's enabled
	    }
	
	
	
}