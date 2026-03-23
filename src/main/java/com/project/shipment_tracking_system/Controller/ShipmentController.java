package com.project.shipment_tracking_system.Controller;

import com.project.shipment_tracking_system.DTO.ShipmentRequest;
import com.project.shipment_tracking_system.DTO.ShipmentResponse;
import com.project.shipment_tracking_system.DTO.UpdateRequest;
import com.project.shipment_tracking_system.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/create")
    public ShipmentResponse createShipment(@RequestBody ShipmentRequest request)
    {
        return shipmentService.createShipment(request);
    }

    @GetMapping("/getAllShipment")
    public List<ShipmentResponse> getAllShipments()
    {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/getShipmentById/{shipmentId}")
    public ShipmentResponse getShipmentById(@PathVariable Long shipmentId)
    {
        return shipmentService.getShipmentById(shipmentId);
    }

    @PutMapping("/updateShipmentStatus/{shipmentId}")
    public ShipmentResponse updateShipment(@PathVariable Long shipmentId, @RequestBody UpdateRequest request)
    {
        return shipmentService.updateShipment(shipmentId,request);
    }
}
