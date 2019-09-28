package com.example.student_manager.config;


import com.example.student_manager.entity.Account;
import com.example.student_manager.repository.AccountRepository;

import com.example.student_manager.service.Account.AccountSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.logging.Level;
import java.util.logging.Logger;

public class MyUserDetailService implements UserDetailsService {
    private static Logger LOGGER = Logger.getLogger(MyUserDetailService.class.getSimpleName());
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountSerivce accountSerivce;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.log(Level.INFO, String.format("Try to login with username: %s", email));
        Account account = accountSerivce.findByEmail(email);
        if (account != null) {

            UserDetails userDetails = User.builder()
                    .username(account.getEmail())
                    .password(account.getPassword())
                    .roles("MEMBER")
                    .build();

            return userDetails;
        } else {
            System.out.println("login failure");
            throw new UsernameNotFoundException("User not found");
        }
    }
}

