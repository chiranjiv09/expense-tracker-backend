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
public class Users implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    
	private String name;
	
    private String userName;
    
    private Long mobile;
    
    private String password;
    
    @JsonIgnore
    @Column(nullable = false)
    private String isActive;
    
    @CreationTimestamp
    @JsonIgnore
    @Column(name = "createdate", nullable = false, updatable = false)
    private Date createdate;
    
    @JsonIgnore
    @UpdateTimestamp
    private Date modidate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return authorities/roles of the user
        return null;
    }

    @Override
    public String getPassword() {
        return this.password; // or your equivalent field
    }

    @Override
    public String getUsername() {
        return (this.mobile+""); // or your equivalent field
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    
    
}
