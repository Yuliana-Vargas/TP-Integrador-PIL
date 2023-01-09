package com.pil.group4.models;

import jakarta.persistence.*;

@Entity
@Table(name = "recycling_zone")
public class RecyclingZoneModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private OccupationCapacity.Level occupationCapacity;

    @Column
    private boolean needsReclassification;

    /*
    localization
    classification
    state
    complaints
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OccupationCapacity.Level getOccupationCapacity() {
        return occupationCapacity;
    }

    public void setOccupationCapacity(OccupationCapacity.Level occupationCapacity) {
        this.occupationCapacity = occupationCapacity;
    }

    public boolean isNeedsReclassification() {
        return needsReclassification;
    }

    public void setNeedsReclassification(boolean needsReclassification) {
        this.needsReclassification = needsReclassification;
    }
}