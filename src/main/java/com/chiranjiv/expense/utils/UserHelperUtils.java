package com.chiranjiv.expense.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chiranjiv.expense.entity.Users;


@Component
public class UserHelperUtils {
	
	private static final Logger logger=LoggerFactory.getLogger(UserHelperUtils.class);
	
	public static Users getLoggedInUser() {
		try {
			Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return user;
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("exception in fetching logged in user details Message : {}",e.getMessage());
		}
		return null;
	}

}
