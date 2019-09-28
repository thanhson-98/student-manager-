package com.example.student_manager.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Set;

@Entity
public class Account {
    @Id
    private long studentId;
    @NotNull(message = "truong nay khong duoc bo trong")
    private String name;
    @NotNull(message = "truong nay khong duoc bo trong")
    private String password;
    @NotNull(message = "truong nay khong duoc bo trong")
    @Email(message = "email phai co dang la info@email.com")
    private String email;
    @ManyToMany(mappedBy = "account", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<AptechClass> aptechClassSet;

    public Account() {
        this.studentId = Long.parseLong(generateId());
    }

    private String generateId() {
        Long getTime = Calendar.getInstance().getTimeInMillis();
        return getTime.toString().substring(0, 7);
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AptechClass> getAptechClassSet() {
        return aptechClassSet;
    }

    public void setAptechClassSet(Set<AptechClass> aptechClassSet) {
        this.aptechClassSet = aptechClassSet;
    }
}
