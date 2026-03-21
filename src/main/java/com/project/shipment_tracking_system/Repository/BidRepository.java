package com.project.shipment_tracking_system.Repository;

import com.project.shipment_tracking_system.Entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Long> {

    List<Bid> findByShipmentId(Long shipmentId);

}
