package com.example.aggregator.service;

import com.example.aggregator.model.Rule;
import com.example.aggregator.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    private RuleRepository ruleRepository;

    @Autowired
    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public void saveRule(Rule rule){
        if (ruleRepository.findByUrl(rule.getUrl()) == null){
            ruleRepository.save(rule);
        }
    }
}
