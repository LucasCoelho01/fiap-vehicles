package com.vehicles.entities.dtos;

import java.math.BigDecimal;

public record CreateVehicleDTO(
        String brand,
        String model,
        String year,
        String color,
        String status,
        BigDecimal price
) {
}
