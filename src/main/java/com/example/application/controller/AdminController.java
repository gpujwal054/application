package com.example.application.controller;

import com.example.application.model.Admin;
import com.example.application.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping("/admin/signUp")
    public String adminSignUp(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/adminSignUp";
    }

    @RequestMapping("/admin")
    String admin() {
        return "admin/admin";
    }

    @PostMapping("signup")
    public String saveAdmin(@ModelAttribute Admin admin, ModelMap modelMap) {
        if (admin.getPassword().equals(admin.getPasswordConf())) {
            adminRepository.save(admin);
            modelMap.put("message", "Registration successful");
            return "admin/adminLogin";
        } else {
            modelMap.put("message", "Password and Confirm password must match");
            return "admin/adminSignUp";
        }

    }

    @PostMapping("login")
    public String logInAdmin(@ModelAttribute Admin admin, ModelMap modelMap) {
        Admin a = adminRepository.findByAdminEmail(admin.getAdminEmail());
        if (a.getPassword().equals(admin.getPassword())) {
            modelMap.put("message", "Admin sign in successfully");
            return "admin/admin";
        } else {
            modelMap.put("message", "Email and password must match!!");
            return "admin/adminLogin";
        }
    }
}
