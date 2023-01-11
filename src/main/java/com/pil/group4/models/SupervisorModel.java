package com.pil.group4.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "supervisor")
@Data
public class SupervisorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column
    private String supervisorName;

    @Column
    private RecyclingZoneModel recyclingZoneModel;

}
