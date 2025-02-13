package com.example.petcare;

public class Quote {
    private String key, quote, author;

    public Quote() {
        // Default constructor required for Firebase
    }

    public Quote(String key, String quote, String author) {
        this.key = key;
        this.quote = quote;
        this.author = author;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}