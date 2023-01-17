package com.pil.group4.models;

import jakarta.persistence.*;

@Entity
@Table(name = "supervisor")
public class SupervisorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String supervisorName;

    @OneToOne(mappedBy = "supervisorModel")
    private RecyclingZoneModel recyclingZoneModel;

    public SupervisorModel() {
    }
    public SupervisorModel(String supervisorName, RecyclingZoneModel recyclingZoneModel) {
        this.supervisorName = supervisorName;
        this.recyclingZoneModel = recyclingZoneModel;
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

    public RecyclingZoneModel getRecyclingZoneModel() {
        return recyclingZoneModel;
    }

    public void setRecyclingZoneModel(RecyclingZoneModel recyclingZoneModel) {
        this.recyclingZoneModel = recyclingZoneModel;

    }

}