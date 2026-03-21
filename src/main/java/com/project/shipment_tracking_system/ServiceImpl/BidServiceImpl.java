package com.project.shipment_tracking_system.ServiceImpl;

import com.project.shipment_tracking_system.Entity.Bid;
import com.project.shipment_tracking_system.Entity.Shipment;
import com.project.shipment_tracking_system.Entity.User;
import com.project.shipment_tracking_system.Enum.BidStatus;
import com.project.shipment_tracking_system.Repository.BidRepository;
import com.project.shipment_tracking_system.Repository.ShipmentRepository;
import com.project.shipment_tracking_system.Repository.UserRepository;
import com.project.shipment_tracking_system.Service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Bid placeBid(Long shipmentId, Bid bid)
    {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        User carrier = userRepository.findById(bid.getCarrier().getId())
                .orElseThrow(()-> new RuntimeException("Carrier Not Found"));

        bid.setShipment(shipment);
        bid.setCarrier(carrier);

        bid.setStatus(BidStatus.PENDING);

        return bidRepository.save(bid);
    }

    @Override
    public List<Bid> getBidsByShipment(Long shipmentId)
    {
        return bidRepository.findByShipmentId(shipmentId);
    }

    @Override
    public Bid getBidById(Long bidId)
    {
        return bidRepository.findById(bidId)
                .orElseThrow(()-> new RuntimeException("Bid Not Found"));
    }

    @Override
    public String acceptBid(Long shipmentId, Long bidId)
    {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        List<Bid> bids = bidRepository.findByShipmentId(shipmentId);

        for(Bid bid : bids)
        {
            if(bid.getId().equals(bidId))
            {
                bid.setStatus(BidStatus.ACCEPTED);
                shipment.setCarrier(bid.getCarrier());
            }
            else
                bid.setStatus(BidStatus.REJECTED);

        }
        bidRepository.saveAll(bids);
        shipmentRepository.save(shipment);

        return "Bid Id "+bidId+" is Successfully Accepted";
    }

    @Override
    public String rejectBid(Long shipmentId, Long bidId)
    {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(()-> new RuntimeException("Shipment Not Found"));

        Bid selectedBid = bidRepository.findById(bidId)
                .orElseThrow(()-> new RuntimeException("Bid Not Found"));

        if(!selectedBid.getShipment().getId().equals(shipmentId))
            throw new RuntimeException("Bid Id Does Not Belongs To This Shipment Id");

        if(selectedBid.getStatus().equals(BidStatus.ACCEPTED))
        {
            selectedBid.setStatus(BidStatus.REJECTED);
            shipment.setCarrier(null);
        }

        bidRepository.save(selectedBid);

        shipmentRepository.save(shipment);

        return "Bid Id "+bidId+" is Successfully Rejected";
    }
}
