package com.example.aggregator.service;

import com.example.aggregator.model.Item;
import com.example.aggregator.model.Rule;
import com.example.aggregator.repository.RuleRepository;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NewsService {
    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

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

    @Scheduled(fixedRate = 1000 * 60 * 5)
    private void collectNews() throws Exception{
        List<Rule> rules = ruleRepository.findAll();

        if (rules == null) return;

        logger.info("start parsing with rules: {}", rules);

        for (Rule rule : rules) {
            Set<Item> items = applyRuleToSearch(rule);
            itemService.saveItems(items);
        }

        System.out.println(rules);
    }

    private Set<Item> applyRuleToSearch(Rule rule){
        Set<Item> items = new HashSet<>();
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
        String xPathForRef = XPATH_BEGIN + rule.getRefTag();
        if (StringUtils.isNotBlank(rule.getRefClass())){
            xPathForRef = xPathForRef.concat(XPATH_CLASS + rule.getRefClass() + XPATH_END);
        }
        HtmlElement ref = element.getFirstByXPath(xPathForRef);

        if (ref == null) return null;

        if (! ref.getAttribute("href").trim().matches(".*[a-zA-Z].*"))
            return null;

        item = new Item();

        item.setTitle(title.getTextContent().trim());
        item.setText(text.getTextContent().trim());
        if (item.getText().length() > 950){
            item.setText(item.getText().substring(0, 950));
        }
        item.setRef(ref.getAttribute("href").trim());
        item.setRule(rule);

        return item;
    }
}
