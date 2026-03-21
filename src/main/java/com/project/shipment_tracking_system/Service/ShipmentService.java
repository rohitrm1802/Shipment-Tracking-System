package com.project.shipment_tracking_system.Service;

import com.project.shipment_tracking_system.Entity.Shipment;
import com.project.shipment_tracking_system.Enum.ShipmentStatus;

import java.util.List;

public interface ShipmentService {

    public Shipment CreateShipment(Long shipperId, Shipment shipment);

    public List<Shipment> getAllShipments();

    public Shipment getShipmentById(Long id);

    public Shipment updateStatus(Long shipmentId, ShipmentStatus shipmentStatus);
}
