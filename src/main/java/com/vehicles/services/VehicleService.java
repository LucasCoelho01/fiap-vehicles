package com.vehicles.services;

import com.vehicles.entities.Vehicle;
import com.vehicles.entities.dtos.CreateVehicleDTO;
import com.vehicles.entities.dtos.UpdateVehicleDTO;
import com.vehicles.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public Vehicle createVehicle(CreateVehicleDTO createVehicleDTO) throws Exception {
        Vehicle vehicle = new Vehicle(createVehicleDTO);

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> listVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(String id) {
        return vehicleRepository.findById(id);
    }

    @Transactional
    public Optional<Vehicle> updateVehicle(String vehicleId, UpdateVehicleDTO updateVehicleDTO) throws Exception {
        var vehicle = getVehicleById(vehicleId);

        if (vehicle.isPresent()) {
            vehicle.get().setVehicleBrand(updateVehicleDTO.vehicleBrand());
            vehicle.get().setVehicleColor(updateVehicleDTO.vehicleColor());
            vehicle.get().setVehicleModel(updateVehicleDTO.vehicleModel());
            vehicle.get().setVehiclePrice(updateVehicleDTO.vehiclePrice());
            vehicle.get().setVehicleStatus(updateVehicleDTO.vehicleStatus());
            vehicle.get().setVehicleYear(updateVehicleDTO.vehicleYear());

            Vehicle updatedVehicle = vehicle.get();

            vehicleRepository.save(updatedVehicle);
        } else {
            new Exception("Vehicle not found");
        }

        return vehicle;
    }

    public List<Vehicle> listVehiclesForSaleSortedByPrice() {
        var allVehicles = vehicleRepository.findAllByOrderByVehiclePriceAsc();
        var vehiclesForSale = new ArrayList<Vehicle>();

        allVehicles.forEach(vehicle -> {
            if (vehicle.getVehicleStatus().contains("à venda")) {
                vehiclesForSale.add(vehicle);
            }
        });

        return vehiclesForSale;
    }

    public List<Vehicle> listSoldVehiclesSortedByPrice() {
        var allVehicles = vehicleRepository.findAllByOrderByVehiclePriceAsc();
        var soldVehicles = new ArrayList<Vehicle>();

        allVehicles.forEach(vehicle -> {
            if (vehicle.getVehicleStatus().contains("vendido")) {
                soldVehicles.add(vehicle);
            }
        });

        return soldVehicles;
    }
}
