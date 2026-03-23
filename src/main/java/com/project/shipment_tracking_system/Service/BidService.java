package com.project.shipment_tracking_system.Service;

import com.project.shipment_tracking_system.DTO.BidRequest;
import com.project.shipment_tracking_system.DTO.BidResponse;
import com.project.shipment_tracking_system.Entity.Bid;

import java.util.List;

public interface BidService {

    public BidResponse placeBid(Long shipmentId, BidRequest request);

    public List<BidResponse> getBidsByShipment(Long shipmentId);

    public BidResponse getBidById(Long bidId);

    public BidResponse acceptBid(Long shipmentId, Long bidId);

    public BidResponse rejectBid(Long shipmentId, Long bidId);
}
