package com.uit.user_service.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class GetPropertyDto {
    private String propertyName;
    private String images;
    private String address;
    private double price;
    private UUID id;
}
