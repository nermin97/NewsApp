package com.softraysolutions.hibernate.entity;


import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Indexed
@Table(name = "news", schema = "public")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull (message = "Title can not be null")
    @Column
    @Field
    private String title;


    @Column(name = "description", length = 100000)
    @Field
    private String description;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @NotNull
    @Column(name = "edited_by")
    private String editedBy;

    public News() {};

    public News(String title, String description, Date creationDate, User createdBy, String editedBy) {
        this.setTitle(title);
        this.setDescription(description);
        this.setCreationDate(creationDate);
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

    public Date getCreationDate() {
        return creationDate;
    }

    public User getCreatedBy() {
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }
}
