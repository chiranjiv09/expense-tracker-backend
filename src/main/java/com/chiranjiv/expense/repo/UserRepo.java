package com.chiranjiv.expense.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chiranjiv.expense.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	Users findByMobile(Long mobile);


	Users findByUserIdAndIsActive(Integer userId, String isActive);

	Users findByUserNameAndIsActive(String userName, String isActive);
}
