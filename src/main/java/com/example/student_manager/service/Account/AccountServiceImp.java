package com.example.student_manager.service.Account;

import com.example.student_manager.entity.Account;
import com.example.student_manager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImp implements AccountSerivce {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Account create(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByEmail(String email) {
        System.out.println("find email");
        if (email == null) System.out.println("email is null");
        else System.out.println("email is :"+ email);
        return accountRepository.findFirstByEmail(email).orElse(null);
    }
}
