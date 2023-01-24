package com.pil.group4.models;

import javax.persistence.*;

@Entity
@Table(name = "supervisor")
public class SupervisorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sup_id", nullable = false)
    private Long id;

    @Column
    private String supervisorName;

    public SupervisorModel() {
    }

    public SupervisorModel(Long id, String supervisorName) {
        this.id = id;
        this.supervisorName = supervisorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

}