package com.example.aggregator.service;

import com.example.aggregator.model.Item;
import com.example.aggregator.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private static final Logger logger = LoggerFactory.getLogger(ItemRepository.class);

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItems(Set<Item> items){
        for (Item item : items) {
            if (itemRepository.findByTitle(item.getTitle()) == null){
                convertUrl(item);
                logger.info("saving item: {}", item);
                itemRepository.save(item);
            }
        }
    }

    public Page<Item> getAllItems(int page){
        return itemRepository.findAll(PageRequest.of(page, 10));
    }

    public List<Item> getByTitleContains(String query){
        logger.info("got search query: {}", query);
        return itemRepository.findByTitleContains(query);
    }

    private void convertUrl(Item item){
        if (!item.getRef().contains("http")){
            item.setRef(item.getRule().getUrl().concat(item.getRef()));
        };
    }
}
