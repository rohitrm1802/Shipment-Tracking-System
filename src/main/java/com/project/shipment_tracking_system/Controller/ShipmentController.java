package com.project.shipment_tracking_system.Controller;

import com.project.shipment_tracking_system.DTO.ApiResponse;
import com.project.shipment_tracking_system.DTO.ShipmentRequest;
import com.project.shipment_tracking_system.DTO.ShipmentResponse;
import com.project.shipment_tracking_system.DTO.UpdateRequest;
import com.project.shipment_tracking_system.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ShipmentResponse>> createShipment(@RequestBody ShipmentRequest request)
    {
        ShipmentResponse response = shipmentService.createShipment(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true,"Shipment Created",response));
    }

    @GetMapping("/getAllShipment")
    public ResponseEntity<ApiResponse<List<ShipmentResponse>>> getAllShipments()
    {
        List<ShipmentResponse> response = shipmentService.getAllShipments();

        return ResponseEntity.ok(
                new ApiResponse<>(true,"Shipment Fetched", response)
        );
    }

    @GetMapping("/getShipmentById/{shipmentId}")
    public ResponseEntity<ApiResponse<ShipmentResponse>> getShipmentById(@PathVariable Long shipmentId)
    {
        ShipmentResponse response = shipmentService.getShipmentById(shipmentId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Successfully Fetched", response)
        );
    }

    @PutMapping("/updateShipment/{shipmentId}")
    public ResponseEntity<ApiResponse<ShipmentResponse>> updateShipment(@PathVariable Long shipmentId, @RequestBody UpdateRequest request)
    {
        ShipmentResponse response = shipmentService.updateShipment(shipmentId,request);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Shipment updated", response)
            );
    }
}
