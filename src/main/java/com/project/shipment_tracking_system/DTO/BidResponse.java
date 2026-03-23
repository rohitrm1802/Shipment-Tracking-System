package com.project.shipment_tracking_system.DTO;

import com.project.shipment_tracking_system.Enum.BidStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidResponse {

    private Long id;
    private Double price;
    private BidStatus status;

    private Long shipmentId;
    private Long carrierId;

    private String carrierName;
}
