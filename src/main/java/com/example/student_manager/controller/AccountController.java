package com.example.student_manager.controller;

import com.example.student_manager.entity.Account;
import com.example.student_manager.service.Account.AccountSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    AccountSerivce accountSerivce;

    private static final String REGISTER_PATH = "account/register";
    private static final String LOGIN_PATH = "account/login";
    private static final String DETAIL_PATH = "account/detail";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showFormLogin(Model model) {
        model.addAttribute("account", new Account());
        return LOGIN_PATH;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String responseRegisterForm(Model model) {
        model.addAttribute("account", new Account());
        return REGISTER_PATH;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String Register(@Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return REGISTER_PATH;
        }

        accountSerivce.create(account);
        return "redirect:/account/login";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String getAccount(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername();
        Account account = accountSerivce.findByEmail(email);
        model.addAttribute("account", account);
        return DETAIL_PATH;
    }

}
