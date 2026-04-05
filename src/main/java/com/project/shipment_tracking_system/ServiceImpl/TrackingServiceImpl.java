package com.project.shipment_tracking_system.ServiceImpl;

import com.project.shipment_tracking_system.DTO.LocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class TrackingServiceImpl {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void processLocation(LocationRequest request)
    {
//        double latitude = ThreadLocalRandom.current().nextDouble(-90,90);
//
//        double longitude = ThreadLocalRandom.current().nextDouble(-180,180);

        double baseLat = 21.1458;
        double baseLng = 79.0882;

        double latitude = baseLat + ThreadLocalRandom.current().nextDouble(-0.01, 0.01);
        double longitude = baseLng + ThreadLocalRandom.current().nextDouble(-0.01, 0.01);

        request.setLatitude(latitude);
        request.setLongitude(longitude);

        String topic = "/topic/shipment/" + request.getShipmentId();

        messagingTemplate.convertAndSend(topic,request);
    }
}
