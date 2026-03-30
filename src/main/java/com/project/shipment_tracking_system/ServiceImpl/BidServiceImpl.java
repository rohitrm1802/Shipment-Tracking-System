package com.project.shipment_tracking_system.ServiceImpl;

import com.project.shipment_tracking_system.DTO.BidRequest;
import com.project.shipment_tracking_system.DTO.BidResponse;
import com.project.shipment_tracking_system.Entity.Bid;
import com.project.shipment_tracking_system.Entity.Shipment;
import com.project.shipment_tracking_system.Entity.User;
import com.project.shipment_tracking_system.Enum.BidStatus;
import com.project.shipment_tracking_system.Enum.ShipmentStatus;
import com.project.shipment_tracking_system.Repository.BidRepository;
import com.project.shipment_tracking_system.Repository.ShipmentRepository;
import com.project.shipment_tracking_system.Repository.UserRepository;
import com.project.shipment_tracking_system.Service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidServiceImpl implements BidService
{
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BidResponse placeBid(Long shipmentId, BidRequest request)
    {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        User carrier = userRepository.findById(request.getCarrierId())
                .orElseThrow(()-> new RuntimeException("Carrier Not Found"));

        Bid bid = new Bid();
        bid.setPrice(request.getPrice());
        bid.setShipment(shipment);
        bid.setCarrier(carrier);
        bid.setStatus(BidStatus.PENDING);

        Bid saved = bidRepository.save(bid);

        BidResponse response = new BidResponse();
        response.setId(saved.getId());
        response.setPrice(saved.getPrice());
        response.setStatus(saved.getStatus());
        response.setCarrierId(saved.getCarrier().getId());
        response.setShipmentId(saved.getShipment().getId());
        response.setCarrierName(saved.getCarrier().getUsername());

        return response;
    }

    @Override
    public List<BidResponse> getBidsByShipment(Long shipmentId)
    {
        List<Bid> bids = bidRepository.findByShipmentId(shipmentId);

        List<BidResponse> responseList = new ArrayList<>();

        for(Bid bid : bids)
        {
            BidResponse response = new BidResponse();

            response.setId(bid.getId());
            response.setPrice(bid.getPrice());
            response.setStatus(bid.getStatus());
            response.setShipmentId(bid.getShipment().getId());
            response.setCarrierId(bid.getCarrier().getId());
            response.setCarrierName(bid.getCarrier().getUsername());

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public BidResponse getBidById(Long bidId)
    {
        Bid bid = bidRepository.findById(bidId)
                .orElseThrow(()-> new RuntimeException("Bid Not Found"));

        BidResponse response = new BidResponse();

        response.setId(bid.getId());
        response.setPrice(bid.getPrice());
        response.setStatus(bid.getStatus());
        response.setCarrierId(bid.getCarrier().getId());
        response.setShipmentId(bid.getShipment().getId());
        response.setCarrierName(bid.getCarrier().getUsername());

        return response;
    }

    @Override
    public BidResponse acceptBid(Long shipmentId, Long bidId)
    {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        List<Bid> bids = bidRepository.findByShipmentId(shipmentId);

        Bid acceptedBid = null;

        for(Bid bid : bids)
        {
            if(bid.getId().equals(bidId))
            {
                bid.setStatus(BidStatus.ACCEPTED);
                shipment.setStatus(ShipmentStatus.AWAITING_PICKUP);
                shipment.setCarrier(bid.getCarrier());
                acceptedBid = bid;
            }
            else
                bid.setStatus(BidStatus.REJECTED);
        }

        bidRepository.saveAll(bids);
        shipmentRepository.save(shipment);

        BidResponse response = new BidResponse();

        response.setId(acceptedBid.getId());
        response.setPrice(acceptedBid.getPrice());
        response.setStatus(acceptedBid.getStatus());
        response.setShipmentId(shipment.getId());
        response.setCarrierId(acceptedBid.getCarrier().getId());
        response.setCarrierName(acceptedBid.getCarrier().getUsername());

        return response;
    }

    @Override
    public BidResponse rejectBid(Long shipmentId, Long bidId)
    {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        List<Bid> bids = bidRepository.findByShipmentId(shipmentId);

        Bid acceptedBid = null;

        for(Bid bid : bids)
        {
            if(bid.getId().equals(bidId))
            {
                bid.setStatus(BidStatus.REJECTED);
                shipment.setCarrier(null);
                acceptedBid = bid;
            }

        }

        bidRepository.saveAll(bids);
        shipmentRepository.save(shipment);

        BidResponse response = new BidResponse();

        response.setId(acceptedBid.getId());
        response.setPrice(acceptedBid.getPrice());
        response.setStatus(acceptedBid.getStatus());
        response.setShipmentId(acceptedBid.getShipment().getId());
        response.setCarrierId(acceptedBid.getCarrier().getId());

        return response;
    }
}
