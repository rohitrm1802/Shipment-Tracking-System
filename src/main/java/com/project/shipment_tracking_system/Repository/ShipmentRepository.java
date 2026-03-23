package com.project.shipment_tracking_system.Repository;

import com.project.shipment_tracking_system.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {

    List<Shipment> findByShipperId(Long shipperId);
}
