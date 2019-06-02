package com.example.aggregator.controller;

import com.example.aggregator.model.Item;
import com.example.aggregator.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
    @Autowired
    private ItemService itemService;

    @RequestMapping
    public String news(){
        return "news";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public Page<Item> getAll(@RequestParam(name = "page") int page){
        return itemService.getAllItems(page);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public List<Item> search(@RequestParam(name = "query") String query){
        return itemService.getByTitleContains(query);
    }
}
