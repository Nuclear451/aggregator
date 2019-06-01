package com.example.aggregator.repository;

import com.example.aggregator.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Rule findByUrl(String url);
}
