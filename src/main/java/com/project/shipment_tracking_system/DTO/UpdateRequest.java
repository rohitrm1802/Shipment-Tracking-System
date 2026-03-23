package com.project.shipment_tracking_system.DTO;

import com.project.shipment_tracking_system.Enum.ShipmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {

    private String destination;
    private ShipmentStatus shipmentStatus;
}
