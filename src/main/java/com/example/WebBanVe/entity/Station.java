package com.example.WebBanVe.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.example.WebBanVe.Enumeration.eTransportType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lâm
@Entity
@Table(name = "station")
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
    
    @Column(name = "opening_time")
    private LocalDateTime openingTime;

    @Column(name = "closing_time")
    private LocalDateTime closingTime;

    @Column(name = "type")
    private eTransportType type;
    
    @OneToMany(mappedBy = "departure", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Route> departureRoutes;

    @OneToMany(mappedBy = "arrival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Route> arrivalRoutes;

}
