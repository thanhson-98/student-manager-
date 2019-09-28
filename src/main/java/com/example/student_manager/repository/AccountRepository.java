package com.example.student_manager.repository;

import com.example.student_manager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findFirstByEmail(String email);
}
