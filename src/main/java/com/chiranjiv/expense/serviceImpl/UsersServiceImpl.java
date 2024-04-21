package com.chiranjiv.expense.serviceImpl;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.repository.UsersRepo;
import com.chiranjiv.expense.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	private static final Logger logger=LoggerFactory.getLogger(Users.class);
	
	@Autowired
	private UsersRepo usersRepo;

	@Override
	public Map<String, Object> registerUser(Map<String, Object> map, Users user){
		try {
			Users u = usersRepo.findByMobile(user.getMobile());
			if(u != null) {
				usersRepo.save(user);
				map.put("status", true);
			}
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "exception while registering user with mobile -->"+user.getMobile());
			logger.error("exception while registering user with mobile -->"+user.getMobile());
			e.printStackTrace();
		}
		return map;
	}

}
