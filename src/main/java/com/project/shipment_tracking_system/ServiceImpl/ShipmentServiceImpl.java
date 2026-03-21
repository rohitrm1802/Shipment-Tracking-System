package com.project.shipment_tracking_system.ServiceImpl;

import com.project.shipment_tracking_system.Entity.Shipment;
import com.project.shipment_tracking_system.Entity.User;
import com.project.shipment_tracking_system.Enum.ShipmentStatus;
import com.project.shipment_tracking_system.Repository.ShipmentRepository;
import com.project.shipment_tracking_system.Repository.UserRepository;
import com.project.shipment_tracking_system.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService
{
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Shipment CreateShipment(Long shipperId,Shipment shipment)
    {
        User shipper = userRepository.findById(shipperId)
                        .orElseThrow(()-> new RuntimeException("Shipper Not Found"));

        shipment.setShipper(shipper);

        shipment.setStatus(ShipmentStatus.CREATED);
        return shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getAllShipments()
    {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(Long id)
    {
        return shipmentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));
    }

    @Override
    public Shipment updateStatus(Long shipmentId, ShipmentStatus shipmentStatus)
    {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        shipment.setStatus(shipmentStatus);

        return shipmentRepository.save(shipment);
    }
}
