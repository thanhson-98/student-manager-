package com.example.student_manager.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AptechClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "aptech_class_student", joinColumns = @JoinColumn(name = "aptech_class_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Account> account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccount(Set<Account> account) {
        this.account = account;
    }
}
