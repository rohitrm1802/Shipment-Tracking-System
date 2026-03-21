package com.project.shipment_tracking_system.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.shipment_tracking_system.Enum.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;

    private String destination;

    private Double weight;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private User Shipper;

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private User Carrier;

    @OneToMany(mappedBy = "shipment")
    @JsonManagedReference
    private List<Bid> bids;
}
