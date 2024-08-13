package com.victorfelipejr.stocks_tracker.entities;


public class News {
    private String newsUrl;
    private String newsTitle;
    private String newsSource;
    private String newsText;
    private String newsDate;
    private String newsImage;

    // Empty constructor
    public News() {
    }

    // Constructor with parameters
    public News(String newsUrl, String newsTitle, String newsSource, String newsText, String newsDate) {
        this.newsUrl = newsUrl;
        this.newsTitle = newsTitle;
        this.newsSource = newsSource;
        this.newsText = newsText;
        this.newsDate = newsDate;
        this.newsImage = newsImage;
    }

    // Getters
    public String getNewsUrl() {
        return newsUrl;
    }
    public String getNewsTitle() {
        return newsTitle;
    }
    public String getNewsSource() {
        return newsSource;
    }
    public String getNewsText() {
        return newsText;
    }
    public String getNewsDate() {
        return newsDate;
    }
    public String getNewsImage() {
        return newsImage;
    }

    // Setters
    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }
    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }
    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }
    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }
}
