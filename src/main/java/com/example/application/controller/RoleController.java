package com.example.application.controller;

import com.example.application.model.Role;
import com.example.application.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {
    @Autowired
    RoleRepository roleRepository;
    @RequestMapping(value = "/role",method = RequestMethod.GET)
    public ModelAndView role(){
        ModelAndView modelAndView = new ModelAndView();
        Role role = new Role();
        modelAndView.addObject("role",role);
        modelAndView.setViewName("role");
        return modelAndView;
    }
    @PostMapping("/role")
    public String saveRole(@ModelAttribute Role role, ModelMap modelMap){
        roleRepository.save(role);
        modelMap.put("message","Role added successfully");
        modelMap.put("alertClass","alert-danger");
        return "role";
    }
}
