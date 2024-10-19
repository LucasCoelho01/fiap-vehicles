package com.vehicles.entities.dtos;

import java.math.BigDecimal;

public record UpdateVehicleDTO(
        String vehicleBrand,
        String vehicleModel,
        String vehicleYear,
        String vehicleColor,
        String vehicleStatus,
        BigDecimal vehiclePrice
) {
}
