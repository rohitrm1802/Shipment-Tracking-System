package com.project.shipment_tracking_system.Controller;

import com.project.shipment_tracking_system.DTO.ApiResponse;
import com.project.shipment_tracking_system.DTO.BidRequest;
import com.project.shipment_tracking_system.DTO.BidResponse;
import com.project.shipment_tracking_system.Service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    @PostMapping("/placeBid/{shipmentId}")
    public ResponseEntity<ApiResponse<BidResponse>> placeBid(@PathVariable Long shipmentId, @RequestBody BidRequest request)
    {
        BidResponse response = bidService.placeBid(shipmentId,request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true,"Bid Placed Successfully",response));
    }

    @GetMapping("/getBids/{shipmentId}")
    public ResponseEntity<ApiResponse<List<BidResponse>>> getBidsByShipment(@PathVariable Long shipmentId)
    {
        List<BidResponse> response = bidService.getBidsByShipment(shipmentId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Bids Successfully Fetched",response)
        );
    }

    @GetMapping("/getBidById/{bidId}")
    public ResponseEntity<ApiResponse<BidResponse>> getBidById(@PathVariable Long bidId)
    {
        BidResponse response = bidService.getBidById(bidId);

        return ResponseEntity.ok(
                new ApiResponse<>(true,"Bid Successfully Fetched",response)
        );
    }

    @PutMapping("/acceptBid/{shipmentId}/{bidId}")
    public ResponseEntity<ApiResponse<BidResponse>> acceptBid(@PathVariable Long shipmentId, @PathVariable Long bidId)
    {
        BidResponse response = bidService.acceptBid(shipmentId,bidId);

        return ResponseEntity.ok(
                new ApiResponse(true,"Accepted Bid",response)
        );
    }

    @PutMapping("/rejectBid/{shipmentId}/{bidId}")
    public ResponseEntity<ApiResponse<BidResponse>> rejectBid(@PathVariable Long shipmentId, @PathVariable Long bidId)
    {
        BidResponse response = bidService.rejectBid(shipmentId,bidId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Rejected Bid",response)
        );
    }
}
