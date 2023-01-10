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
    private OccupationCapacity occupationCapacity;

    @Column
    private StateOfTheZone stateOfTheZone;

    @Column
    private ClassificationType classificationType;

    @Column
    private boolean needsReclassification;

    /*
    localization
    classification
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

    public OccupationCapacity getOccupationCapacity() {
        return occupationCapacity;
    }

    public void setOccupationCapacity(OccupationCapacity occupationCapacity) {
        this.occupationCapacity = occupationCapacity;
    }

    public boolean isNeedsReclassification() {
        return needsReclassification;
    }

    public StateOfTheZone getStateOfTheZone() {
        return stateOfTheZone;
    }

    public void setStateOfTheZone(StateOfTheZone stateOfTheZone) {
        this.stateOfTheZone = stateOfTheZone;
    }

    public ClassificationType getClassificationType(){ return classificationType; }

    public void setClassificationType(ClassificationType classificationType){ this.classificationType = classificationType; }

    public void setNeedsReclassification(boolean needsReclassification) {
        this.needsReclassification = needsReclassification;
    }
}