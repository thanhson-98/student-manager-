package com.example.student_manager.service.Account;

import com.example.student_manager.entity.Account;

import java.util.List;

public interface AccountSerivce {
    Account create(Account account);

    Account getAccount(long id);

    Account findByEmail(String email);
}
