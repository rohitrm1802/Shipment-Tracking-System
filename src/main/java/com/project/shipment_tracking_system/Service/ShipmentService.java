package com.project.shipment_tracking_system.Service;

import com.project.shipment_tracking_system.DTO.ShipmentRequest;
import com.project.shipment_tracking_system.DTO.ShipmentResponse;
import com.project.shipment_tracking_system.DTO.UpdateRequest;
import com.project.shipment_tracking_system.Entity.Shipment;
import com.project.shipment_tracking_system.Enum.ShipmentStatus;

import java.util.List;

public interface ShipmentService {

    public ShipmentResponse createShipment(ShipmentRequest request);

    public List<ShipmentResponse> getAllShipments();

    public ShipmentResponse getShipmentById(Long shipmentId);

    public ShipmentResponse updateShipment(Long shipmentId, UpdateRequest update);
}
