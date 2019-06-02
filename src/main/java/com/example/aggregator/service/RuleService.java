package com.example.aggregator.service;

import com.example.aggregator.model.Rule;
import com.example.aggregator.repository.RuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);

    private RuleRepository ruleRepository;

    @Autowired
    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public void saveRule(Rule rule){
        if (ruleRepository.findByUrl(rule.getUrl()) == null){
            logger.info("saving rule: {}", rule);
            ruleRepository.save(rule);
        }
    }
}
