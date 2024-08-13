package com.victorfelipejr.stocks_tracker.entities;


public class News {
    private String newsTitle;
    private String newsUrl;
    private String photoUrl;
    private String newsSource;
    private String newsPostedTime;

    public News() {
    }
    public News(String newsTitle, String newsUrl, String photoUrl, String newsSource, String newsPostedTime) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.photoUrl = photoUrl;
        this.newsSource = newsSource;
        this.newsPostedTime = newsPostedTime;
    }

    // Getters
    public String getNewsTitle() {
        return newsTitle;
    }
    public String getNewsUrl() {
        return newsUrl;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public String getNewsSource() {
        return newsSource;
    }
    public String getNewsPostedTime() {
        return newsPostedTime;
    }

    // Setters
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }
    public void setNewsPostedTime(String newsPostedTime) {
        this.newsPostedTime = newsPostedTime;
    }

}
