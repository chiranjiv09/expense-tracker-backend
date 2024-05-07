package com.chiranjiv.expense.serviceImpl;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.repo.UserRepo;
import com.chiranjiv.expense.security.JwtAuthResponse;
import com.chiranjiv.expense.security.JwtTokenHelper;
import com.chiranjiv.expense.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	private static final Logger logger=LoggerFactory.getLogger(Users.class);
	
	@Autowired UserRepo userRepo;
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtTokenHelper helper;

	@Override
	public Map<String, Object> registerUser(Map<String, Object> map, Users user){
		try {
			Users u = userRepo.findByMobile(user.getMobile());
			if(u == null) {
				u = new Users();
			}
			u.setMobile(user.getMobile());
			u.setUserName(user.getUsername());
			u.setPassword(passwordEncoder.encode(user.getPassword()));
			u.setIsActive("Y");
			userRepo.save(u);
			map.put("status", true);
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "exception while registering user with mobile -->"+user.getMobile());
			logger.error("exception while registering user with mobile -->"+user.getMobile());
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> userLoginAuthentication(Map<String, Object> map, Users user) {
		try {
			this.LoginAuthenticate(map,user.getMobile(), user.getPassword());
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getMobile()+"");
			if(map.get("status").equals(true)){
				String token = this.helper.generateToken(userDetails);
		        map.put("token", token);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "Exception while login");
		}
        return map;
	}

	private void LoginAuthenticate(Map<String, Object> map, Long mobile, String password) {
	    try {
	    	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(mobile, password);
	        manager.authenticate(authentication);
	        // Authentication succeeded, proceed with login
	        map.put("status", true);
	        map.put("message", "Login successful");
	    } catch (AuthenticationException e) {
	        map.put("status", false);
	        map.put("message", "Bad credentials for login");
	        logger.error("Bad credentials for login: " + e.getMessage());
	    }
	}



}
