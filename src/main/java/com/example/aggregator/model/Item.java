package com.example.aggregator.model;

import javax.persistence.*;

@Entity

@Table(name = "item") // TODO: 02.06.2019 add index to title column
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    Long itemId;

    @Column(length = 1000)
    private String title;

    @Column(length = 1000)
    private String text;

    private String ref;

    @ManyToOne
    @JoinColumn(name = "ruleId")
    private Rule rule;

    public Long getId() {
        return itemId;
    }

    public void setId(Long id) {
        this.itemId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Item && title.equals(((Item) obj).title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                '}';
    }
}
