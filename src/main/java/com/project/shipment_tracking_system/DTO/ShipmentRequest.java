package com.project.shipment_tracking_system.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentRequest {

    private String origin;
    private String destination;
    private Double weight;
    private Long shipperId;
}
