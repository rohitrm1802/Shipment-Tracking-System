package com.project.shipment_tracking_system.Repository;

import com.project.shipment_tracking_system.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
}
