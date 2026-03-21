package com.project.shipment_tracking_system.Controller;

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
    public Bid placeBid(@PathVariable Long shipmentId, @RequestBody Bid bid)
    {
        return bidService.placeBid(shipmentId,bid);
    }

    @GetMapping("/getBids/{shipmentId}")
    public List<Bid> getBidsByShipment(@PathVariable Long shipmentId)
    {
        return bidService.getBidsByShipment(shipmentId);
    }

    @GetMapping("/getBidById/{bidId}")
    public Bid getBidById(@PathVariable Long bidId)
    {
        return bidService.getBidById(bidId);
    }

    @PutMapping("/acceptBid/{shipmentId}/{bidId}")
    public String acceptBid(@PathVariable Long shipmentId, @PathVariable Long bidId)
    {
        return bidService.acceptBid(shipmentId,bidId);
    }

    @PutMapping("/rejectBid/{shipmentId}/{bidId}")
    public String rejectBid(@PathVariable Long shipmentId, @PathVariable Long bidId)
    {
        return bidService.rejectBid(shipmentId,bidId);
    }
}
