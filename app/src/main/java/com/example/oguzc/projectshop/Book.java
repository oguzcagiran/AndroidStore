package com.example.oguzc.projectshop;

/**
 * Created by oguzc on 12/17/2015.
 */
public class Book {

    private String type;
    private String name;
    private String writer;
    private String page;
    private String publisher;
    private String id;

    public Book(String type, String name, String writer, String page, String publisher, String id) {
        this.type = type;
        this.name = name;
        this.writer = writer;
        this.page = page;
        this.publisher = publisher;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}