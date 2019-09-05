package com.example.application.controller;

import com.example.application.model.Role;
import com.example.application.model.User;
import com.example.application.repositories.RoleRepository;
import com.example.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView login(Principal principal, Model user, String error, String logout) {
        if (principal != null && ((Authentication) principal).isAuthenticated())
            return new ModelAndView("redirect:/home");

        ModelAndView model = new ModelAndView();

        if (error != null)
            user.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            user.addAttribute("message", "You have been logged out successfully.");

        model.setViewName("/login");
        return model;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("home");
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        User current_user = userRepository.findByUsername(userDetail.getUsername());
        return modelAndView;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView admin(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("dashboard");
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        User current_user = userRepository.findByUsername(userDetail.getUsername());
        return modelAndView;
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("registerUser");
        return modelAndView;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final String USER_ROLE = "ROLE_ADMIN";
    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user, ModelMap modelMap) {
        if (user.getPassword().equals(user.getPasswordConf())) {
            user.setActive(1);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Set Role to ROLE_ADMIN
          // Role role = roleRepository.findByRoleName("ADMIN");
            // Set Role to ROLE_USER
            Role role = roleRepository.findByRoleName("USER");
            user.setRoles(role);
            user.setEnabled(true);
            userRepository.save(user);
            modelMap.put("message", "Registration successful");
            modelMap.put("alertClass", "alert-danger");
            return "registerUser";
        } else {
            modelMap.put("message", "Typed password doesn't match with previous typed password");
            modelMap.put("alertClass", "alert-danger");
            return "registerUser";
        }
    }
}