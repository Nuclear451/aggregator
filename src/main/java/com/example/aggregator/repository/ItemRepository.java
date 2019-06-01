package com.example.aggregator.repository;

import com.example.aggregator.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByTitle(String title);
}
