package com.pil.group4.models;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "location")
public class LocationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "loc_id", nullable = false)
    private Long id;

    @Column
    private String department;

    @Column
    private String neighborhood;

    @Column
    private String address;

    @Column
    private int number;

    @Column
    private Point coordinates;

    public LocationModel() {
    }

    public LocationModel(String department, String neighborhood, String address, int number, Point coordinates) {
        this.department = department;
        this.neighborhood = neighborhood;
        this.address = address;
        this.number = number;
        this.coordinates = coordinates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

}
