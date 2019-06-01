package com.example.aggregator.controller;

import com.example.aggregator.model.Rule;
import com.example.aggregator.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private RuleService ruleService;

    @Autowired
    public HomeController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping
    public String home(){
        return "home";
    }


    @PostMapping
    public String saveRule(Rule rule){
        ruleService.saveRule(rule);
        return "redirect:/home";
    }

}
