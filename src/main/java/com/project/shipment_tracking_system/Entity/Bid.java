package com.project.shipment_tracking_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.shipment_tracking_system.Enum.BidStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @Enumerated(EnumType.STRING)
    private BidStatus status;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    @JsonBackReference
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private User carrier;
}
