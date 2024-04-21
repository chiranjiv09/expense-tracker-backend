package com.chiranjiv.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiranjiv.expense.entity.Users;

public interface UsersRepo extends JpaRepository<Users, Integer> {

	Users findByMobile(Integer mobile);
}
