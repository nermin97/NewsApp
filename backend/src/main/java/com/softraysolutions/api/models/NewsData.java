package com.softraysolutions.api.models;

public class NewsData {

    private String title;
    private String Description;
    private String createdBy;

    public NewsData() {}

    public NewsData(String title, String description, String createdBy) {
        this.setTitle(title);
        this.setDescription(description);
        this.setCreatedBy(createdBy);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
