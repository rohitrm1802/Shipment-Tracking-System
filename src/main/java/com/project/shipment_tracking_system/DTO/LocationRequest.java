package com.project.shipment_tracking_system.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationRequest {

    private Long shipmentId;
    private double latitude;
    private double longitude;
}
