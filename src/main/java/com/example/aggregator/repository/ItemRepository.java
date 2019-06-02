package com.example.aggregator.repository;

import com.example.aggregator.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByTitle(String title);

    List<Item> findByTitleContains(String subTitle);
}
