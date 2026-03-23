package com.project.shipment_tracking_system.DTO;

import com.project.shipment_tracking_system.Enum.BidStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidRequest {

    private Double price;
    private Long carrierId;
}
