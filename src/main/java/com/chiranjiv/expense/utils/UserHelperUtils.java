package com.chiranjiv.expense.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.chiranjiv.expense.entity.Users;


@Component
public class UserHelperUtils {
	
	private static final Logger logger=LoggerFactory.getLogger(UserHelperUtils.class);
	
	public static Users getLoggedInUser() {
	    try {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
	            return (Users) authentication.getPrincipal();
	        } else {
	            logger.warn("Authentication is either null, not authenticated or principal is not an instance of Users");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.warn("Exception in fetching logged-in user details. Message: {}", e.getMessage());
	    }
	    return null;
	}
	
	

}
