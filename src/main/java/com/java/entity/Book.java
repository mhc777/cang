package com.java.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Book {
    private Integer bookid;

    private String bookname;

    private Double price;
@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubtime;

    private String author;

    private Integer typeid;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}