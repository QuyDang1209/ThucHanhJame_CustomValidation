package com.cg.demovalidaton_customvalidation.controller;

import com.cg.demovalidaton_customvalidation.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping()
    public String showForm(Model model){
        model.addAttribute("user", new User());
            return "create";
    }
//    @PostMapping
//    public String checkvalidation(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
//        new User().validate(user,bindingResult);
//        if(bindingResult.hasFieldErrors()){
//            return "create";
//        }
//        return "result";
//    }
    @PostMapping
    public ModelAndView checkvalidation(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("create");
        new User().validate(user,bindingResult);
        if(bindingResult.hasFieldErrors()){
            return modelAndView;
        }
        modelAndView = new ModelAndView("result");
        return  modelAndView;
    }
}
