package com.project.shipment_tracking_system.Controller;

import com.project.shipment_tracking_system.DTO.BidRequest;
import com.project.shipment_tracking_system.DTO.BidResponse;
import com.project.shipment_tracking_system.Entity.Bid;
import com.project.shipment_tracking_system.Service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    @PostMapping("/placeBid/{shipmentId}")
    public BidResponse placeBid(@PathVariable Long shipmentId, @RequestBody BidRequest request)
    {
        return bidService.placeBid(shipmentId,request);
    }

    @GetMapping("/getBids/{shipmentId}")
    public List<BidResponse> getBidsByShipment(@PathVariable Long shipmentId)
    {
        return bidService.getBidsByShipment(shipmentId);
    }

    @GetMapping("/getBidById/{bidId}")
    public BidResponse getBidById(@PathVariable Long bidId)
    {
        return bidService.getBidById(bidId);
    }

    @PutMapping("/acceptBid/{shipmentId}/{bidId}")
    public BidResponse acceptBid(@PathVariable Long shipmentId, @PathVariable Long bidId)
    {
        return bidService.acceptBid(shipmentId,bidId);
    }

    @PutMapping("/rejectBid/{shipmentId}/{bidId}")
    public BidResponse rejectBid(@PathVariable Long shipmentId, @PathVariable Long bidId)
    {
        return bidService.rejectBid(shipmentId,bidId);
    }
}
