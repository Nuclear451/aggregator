package com.example.aggregator.model;

import javax.persistence.*;

@Entity
@Table(name = "rule")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rule_id")
    private Long ruleId;

    private String url;
    @Column(name = "element_tag")
    private String elementTag;
    @Column(name = "element_class")
    private String elementClass;
    @Column(name = "title_tag")
    private String titleTag;
    @Column(name = "title_class")
    private String titleClass;
    @Column(name = "text_tag")
    private String textTag;
    @Column(name = "text_class")
    private String textClass;
    @Column(name = "ref_tag")
    private String refTag;
    @Column(name = "ref_class")
    private String refClass;

    public Long getId() {
        return ruleId;
    }

    public void setId(Long id) {
        this.ruleId = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getElementTag() {
        return elementTag;
    }

    public void setElementTag(String elementTag) {
        this.elementTag = elementTag;
    }

    public String getElementClass() {
        return elementClass;
    }

    public void setElementClass(String elementClass) {
        this.elementClass = elementClass;
    }

    public String getTextTag() {
        return textTag;
    }

    public void setTextTag(String textTag) {
        this.textTag = textTag;
    }

    public String getTextClass() {
        return textClass;
    }

    public void setTextClass(String textClass) {
        this.textClass = textClass;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(String titleTag) {
        this.titleTag = titleTag;
    }

    public String getTitleClass() {
        return titleClass;
    }

    public void setTitleClass(String titleClass) {
        this.titleClass = titleClass;
    }

    public String getRefTag() {
        return refTag;
    }

    public void setRefTag(String refTag) {
        this.refTag = refTag;
    }

    public String getRefClass() {
        return refClass;
    }

    public void setRefClass(String refClass) {
        this.refClass = refClass;
    }
}
