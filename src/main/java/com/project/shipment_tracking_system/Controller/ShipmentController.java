package com.project.shipment_tracking_system.Controller;

import com.project.shipment_tracking_system.Entity.Shipment;
import com.project.shipment_tracking_system.Enum.ShipmentStatus;
import com.project.shipment_tracking_system.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/create/{shipperId}")
    public Shipment createShipment(@PathVariable Long shipperId,@RequestBody Shipment shipment)
    {
        return shipmentService.CreateShipment(shipperId,shipment);
    }

    @GetMapping("/getAllShipment")
    public List<Shipment> getAllShipments()
    {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/getShipmentById/{id}")
    public Shipment getShipmentById(@PathVariable Long id)
    {
        return shipmentService.getShipmentById(id);
    }

    @PutMapping("/updateShipmentStatus/{shipmentId}")
    public Shipment updateStatus(@PathVariable Long shipmentId, @RequestParam ShipmentStatus shipmentStatus)
    {
        return shipmentService.updateStatus(shipmentId,shipmentStatus);
    }
}
