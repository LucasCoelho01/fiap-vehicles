package com.vehicles.entities;

import com.vehicles.entities.dtos.CreateVehicleDTO;
import com.vehicles.entities.dtos.UpdateVehicleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
public class Vehicle implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String vehicleId;
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleYear;
    private String vehicleColor;
    private String vehicleStatus;
    private BigDecimal vehiclePrice;

    public Vehicle(CreateVehicleDTO createVehicleDTO) {
        this.vehicleBrand = createVehicleDTO.brand();
        this.vehicleModel = createVehicleDTO.model();
        this.vehicleYear = createVehicleDTO.year();
        this.vehicleColor = createVehicleDTO.color();
        this.vehicleStatus = createVehicleDTO.status();
        this.vehiclePrice = createVehicleDTO.price();
    }
}
