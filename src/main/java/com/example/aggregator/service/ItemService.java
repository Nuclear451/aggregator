package com.example.aggregator.service;

import com.example.aggregator.model.Item;
import com.example.aggregator.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItems(List<Item> items){
        for (Item item : items) {
            if (itemRepository.findByTitle(item.getTitle()) == null){
                itemRepository.save(item);
            }
        }
    }

    public Page<Item> getAllNews(int page){
        return itemRepository.findAll(PageRequest.of(page, 10));
    }
}
