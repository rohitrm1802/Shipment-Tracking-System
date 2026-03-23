package com.project.shipment_tracking_system.ServiceImpl;

import com.project.shipment_tracking_system.DTO.ShipmentRequest;
import com.project.shipment_tracking_system.DTO.ShipmentResponse;
import com.project.shipment_tracking_system.DTO.UpdateRequest;
import com.project.shipment_tracking_system.Entity.Shipment;
import com.project.shipment_tracking_system.Entity.User;
import com.project.shipment_tracking_system.Enum.ShipmentStatus;
import com.project.shipment_tracking_system.Repository.ShipmentRepository;
import com.project.shipment_tracking_system.Repository.UserRepository;
import com.project.shipment_tracking_system.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService
{
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ShipmentResponse createShipment(ShipmentRequest request)
    {
        User shipper = userRepository.findById(request.getShipperId())
                .orElseThrow(()-> new RuntimeException("Shipper Not Found"));

        Shipment shipment = new Shipment();
        shipment.setOrigin(request.getOrigin());
        shipment.setDestination(request.getDestination());
        shipment.setWeight(request.getWeight());
        shipment.setShipper(shipper);
        shipment.setStatus(ShipmentStatus.CREATED);

        Shipment saved = shipmentRepository.save(shipment);

        ShipmentResponse response = new ShipmentResponse();
        response.setId(saved.getId());
        response.setOrigin(saved.getOrigin());
        response.setDestination(saved.getDestination());
        response.setWeight(saved.getWeight());
        response.setStatus(saved.getStatus());
        response.setShipperId(saved.getShipper().getId());

        return response;
    }

    @Override
    public List<ShipmentResponse> getAllShipments()
    {
        List<Shipment> shipments = shipmentRepository.findAll();

        List<ShipmentResponse> responseList = new ArrayList<>();

        for(Shipment shipment : shipments)
        {
            ShipmentResponse responses = new ShipmentResponse();

            responses.setId(shipment.getId());
            responses.setDestination(shipment.getDestination());
            responses.setOrigin(shipment.getOrigin());
            responses.setWeight(shipment.getWeight());
            responses.setShipperId(shipment.getShipper().getId());
            responses.setStatus(shipment.getStatus());

            responseList.add(responses);
        }

        return responseList;
    }

    @Override
    public ShipmentResponse getShipmentById(Long shipmentId)
    {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        ShipmentResponse response = new ShipmentResponse();

        response.setId(shipment.getId());
        response.setDestination(shipment.getDestination());
        response.setOrigin(shipment.getOrigin());
        response.setWeight(shipment.getWeight());
        response.setStatus(shipment.getStatus());
        response.setShipperId(shipment.getShipper().getId());

        return response;
    }

    @Override
    public ShipmentResponse updateShipment(Long shipmentId, UpdateRequest request)
    {
        Shipment selectedId = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        selectedId.setDestination(request.getDestination());
        selectedId.setStatus(request.getShipmentStatus());

        Shipment saved = shipmentRepository.save(selectedId);

        ShipmentResponse response = new ShipmentResponse();

        response.setId(saved.getId());
        response.setDestination(saved.getDestination());
        response.setOrigin(saved.getOrigin());
        response.setWeight(saved.getWeight());
        response.setStatus(saved.getStatus());
        response.setShipperId(saved.getShipper().getId());

        return response;
    }
}
