package com.polaris.airlines.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "airports")
public class Airport {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;  // Full airport name

    @Column(name = "municipality")
    private String municipality;  // City name

    @Column(name = "iata_code")
    private String iataCode;  // 3-letter code: BOM, DEL, BLR

    @Column(name = "scheduled_service")
    private Boolean scheduledService;  // Filter: only commercial airports

    @Column(name = "type")
    private String type;  // large_airport, medium_airport (for filtering)

    @Column(name = "iso_country")
    private String isoCountry;  // IN, US, GB (for country filtering)
}
