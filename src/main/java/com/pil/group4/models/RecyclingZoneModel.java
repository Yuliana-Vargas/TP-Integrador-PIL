package com.pil.group4.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

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

    @Column
    private TypeOfComplaint typeOfComplaint;
    
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    private SupervisorModel supervisorModel;

    public SupervisorModel getSupervisorModel() {
        return supervisorModel;
    }

    public void setSupervisorModel(SupervisorModel supervisorModel) {
        this.supervisorModel = supervisorModel;
    }

    public RecyclingZoneModel() {
    }

    public RecyclingZoneModel(String name, OccupationCapacity occupationCapacity, StateOfTheZone stateOfTheZone,
                              ClassificationType classificationType, boolean needsReclassification,
                              TypeOfComplaint typeOfComplaint, SupervisorModel supervisorModel) {
        this.name = name;
        this.occupationCapacity = occupationCapacity;
        this.stateOfTheZone = stateOfTheZone;
        this.classificationType = classificationType;
        this.needsReclassification = needsReclassification;
        this.typeOfComplaint = typeOfComplaint;
        this.supervisorModel = supervisorModel;
    }

    public RecyclingZoneModel(String name) {
        this.name = name;

    }

    /*
    localization
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
    public TypeOfComplaint getTypeOfComplaint() { return typeOfComplaint; }

    public void setTypeOfComplaint(TypeOfComplaint typeOfComplaint) {this.typeOfComplaint = typeOfComplaint; }
}
