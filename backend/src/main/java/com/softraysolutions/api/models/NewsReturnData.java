package com.softraysolutions.api.models;

import java.util.Date;

public class NewsReturnData {

    private int id;
    private String title;
    private String description;
    private String creationDate;
    private String createdBy;
    private String editedBy;

    public NewsReturnData() {}

    public NewsReturnData(int id, String title, String description, String creationDate, String createdBy, String editedBy) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setCreationDate(creationDate.toString());
        this.setCreatedBy(createdBy);
        this.setEditedBy(editedBy);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }
}
