package com.pil.group4.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "recycling_zone")
public class RecyclingZoneModel {
    @Id
    @Column(name = "rec_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Column
    private String name;

    public RecyclingZoneModel() {

    }

    public RecyclingZoneModel(Long id, String name, OccupationCapacity occupationCapacity, StateOfTheZone stateOfTheZone, ClassificationType classificationType, boolean needsReclassification, SupervisorModel supervisor) {
        this.id = id;
        this.name = name;
        this.occupationCapacity = occupationCapacity;
        this.stateOfTheZone = stateOfTheZone;
        this.classificationType = classificationType;
        this.needsReclassification = needsReclassification;
        this.supervisor = supervisor;
    }

    public RecyclingZoneModel(Long id, String name, OccupationCapacity occupationCapacity, StateOfTheZone stateOfTheZone, ClassificationType classificationType, boolean needsReclassification) {
        this.id = id;
        this.name = name;
        this.occupationCapacity = occupationCapacity;
        this.stateOfTheZone = stateOfTheZone;
        this.classificationType = classificationType;
        this.needsReclassification = needsReclassification;
    }

    @Column
    private OccupationCapacity occupationCapacity;

    @Column
    private StateOfTheZone stateOfTheZone;

    @Column
    private ClassificationType classificationType;

    @Column
    private boolean needsReclassification;

    @JoinColumn(name = "fk_sup_id", referencedColumnName = "sup_id")
    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SupervisorModel supervisor;

    @JoinColumn(name = "fk_loc_id", referencedColumnName = "loc_id")
    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LocationModel location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_com_id", referencedColumnName = "rec_id")
    private List<ComplaintModel> complaints;

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

    public ClassificationType getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(ClassificationType classificationType) {
        this.classificationType = classificationType;
    }

    public void setNeedsReclassification(boolean needsReclassification) {
        this.needsReclassification = needsReclassification;
    }

    public SupervisorModel getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(SupervisorModel supervisor) {
        this.supervisor = supervisor;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public Collection<ComplaintModel> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<ComplaintModel> complaints) {
        this.complaints = complaints;
    }

}
