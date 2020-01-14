package com.hrvikas.dailynews;

public class homeScreenModel
{
    String title,author,date,image,url,content;

    public homeScreenModel(String title, String author, String date, String image, String url, String content) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.image = image;
        this.url = url;
        this.content = content;
    }

    public homeScreenModel(String title, String author, String date, String image) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
