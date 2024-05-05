package com.chiranjiv.expense.security;

import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByMobile(Long.valueOf(username));
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return  user;
    }
}
