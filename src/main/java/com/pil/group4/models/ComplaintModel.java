package com.pil.group4.models;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class ComplaintModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "com_id", nullable = false)
    private Long id;

    @Column
    private TypeOfComplaint typeOfComplaint;

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
