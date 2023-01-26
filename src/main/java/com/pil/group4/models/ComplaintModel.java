package com.pil.group4.models;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class ComplaintModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "com_id", nullable = false)
    private Long id;

    public ComplaintModel() {
    }

    public ComplaintModel(Long id, TypeOfComplaint typeOfComplaint, String description) {
        this.id = id;
        this.typeOfComplaint = typeOfComplaint;
        this.description = description;
    }

    @Column
    private TypeOfComplaint typeOfComplaint;

    public ComplaintModel() {
    }
    
    @Column
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfComplaint getTypeOfComplaint() {
        return typeOfComplaint;
    }

    public void setTypeOfComplaint(TypeOfComplaint typeOfComplaint) {
        this.typeOfComplaint = typeOfComplaint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
