package com.project.shipment_tracking_system.DTO;

import com.project.shipment_tracking_system.Enum.ShipmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentResponse {

    private Long id;
    private String origin;
    private String destination;
    private Double weight;
    private ShipmentStatus status;
    private Long shipperId;

}