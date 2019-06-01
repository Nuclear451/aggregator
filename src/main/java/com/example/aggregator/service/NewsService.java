package com.example.aggregator.service;

import com.example.aggregator.model.Item;
import com.example.aggregator.model.Rule;
import com.example.aggregator.repository.RuleRepository;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    private WebClient client;
    private ItemService itemService;
    private RuleRepository ruleRepository;

    private static final String XPATH_BEGIN = ".//";
    private static final String XPATH_CLASS = "[@class='";
    private static final String XPATH_END = "']";

    @Autowired
    public NewsService(RuleRepository ruleRepository, WebClient client, ItemService itemService) {
        this.ruleRepository = ruleRepository;
        this.client = client;
        this.itemService = itemService;
    }

    @Scheduled(fixedRate = 1000 * 60)
    private void collectNews() throws Exception{
        List<Rule> rules = ruleRepository.findAll();

        if (rules == null) return;

        for (Rule rule : rules) {
            List<Item> items = applyRuleToSearch(rule);
            itemService.saveItems(items);
        }

        System.out.println(rules);
    }

    private List<Item> applyRuleToSearch(Rule rule){
        List<Item> items = new ArrayList<>();
        try {
            HtmlPage page = client.getPage(rule.getUrl());
            List<HtmlElement> elements = page
                    .getByXPath("//" + rule.getElementTag()
                            + "[contains(@class, " + rule.getElementClass() + ")]");

            for (HtmlElement element : elements) {
                Item item = parseElementToItem(element, rule);
                if (item != null) items.add(item);
            }
        } catch (Exception e){

        }
        return items;
    }

    @Nullable
    private Item parseElementToItem(HtmlElement element, Rule rule){
        Item item = null;

        HtmlElement title = element.getFirstByXPath(XPATH_BEGIN + rule.getTitleTag()
                + XPATH_CLASS + rule.getTitleClass() + XPATH_END);
        if (title == null) return null;
        HtmlElement text = element.getFirstByXPath(XPATH_BEGIN + rule.getTextTag()
                + XPATH_CLASS + rule.getTextClass() + XPATH_END);
        if (text == null) return null;
        HtmlElement ref = element.getFirstByXPath(XPATH_BEGIN + rule.getRefTag()
                + XPATH_CLASS + rule.getRefClass() + XPATH_END);
        if (ref == null) return null;

        item = new Item();

        item.setTitle(title.getTextContent().trim());
        item.setText(text.getTextContent().trim());
        item.setRef(ref.getAttribute("href").trim());
        item.setRule(rule);

        return item;
    }
}
