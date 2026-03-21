package com.project.shipment_tracking_system.Service;

import com.project.shipment_tracking_system.Entity.Bid;

import java.util.List;

public interface BidService {

    public Bid placeBid(Long shipmentId,Bid bid);

    public List<Bid> getBidsByShipment(Long shipmentId);

    public Bid getBidById(Long bidId);

    public String acceptBid(Long shipmentId, Long bidId);

    public String rejectBid(Long shipmentId, Long bidId);
}
