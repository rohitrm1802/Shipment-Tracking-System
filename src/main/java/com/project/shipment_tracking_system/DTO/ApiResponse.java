package com.project.shipment_tracking_system.DTO;

import lombok.Getter;

@Getter
public class ApiResponse<Data>{

    private boolean success;

    private String message;

    private Data data;

    public ApiResponse(boolean success, String message, Data data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
