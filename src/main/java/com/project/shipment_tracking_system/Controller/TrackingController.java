package com.project.shipment_tracking_system.Controller;

import com.project.shipment_tracking_system.DTO.LocationRequest;
import com.project.shipment_tracking_system.ServiceImpl.TrackingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    @Autowired
    private TrackingServiceImpl trackingService;

    @PostMapping("/location")
    public void sendLocation(@RequestBody LocationRequest request)
    {
       trackingService.processLocation(request);
    }
}
