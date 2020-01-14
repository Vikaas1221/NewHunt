package com.hrvikas.dailynews;

public class CatergoryModel
{
    String categoryText;

    public CatergoryModel(String categoryText)
    {
        this.categoryText = categoryText;
    }

    public String getCategoryText()
    {
        return categoryText;
    }

    public void setCategoryText(String categoryText)
    {
        this.categoryText = categoryText;
    }
}
