package com.pil.group4.models;

import javax.persistence.*;

@Entity
@Table(name = "idea")
public class IdeaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idea_id", nullable = false)
    private Long id;

    @Column
    private String ideaName;

    @Column
    private String description;

    public IdeaModel() {
    }

    public IdeaModel(Long id, String ideaName,String description) {
        this.id = id;
        this.ideaName = ideaName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getIdeaName() {
        return ideaName;
    }

    public void setIdeaName(String ideaName) {
        this.ideaName = ideaName;
    }

    public String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description;}
}
