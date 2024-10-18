package com.vehicles.services;

import com.vehicles.entities.Vehicle;
import com.vehicles.entities.dtos.CreateVehicleDTO;
import com.vehicles.entities.dtos.UpdateVehicleDTO;
import com.vehicles.repositories.VehicleRepository;
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
    public Vehicle updateVehicle(UpdateVehicleDTO updateVehicleDTO, String vehicleId) throws Exception {
        var vehicle = getVehicleById(vehicleId);


        if (vehicle.isPresent()) {
            var updatedVehicle = new Vehicle(updateVehicleDTO);

            return vehicleRepository.save(updatedVehicle);
        } else {
            new Exception("Vehicle not found");
        }

        return new Vehicle();
    }

    public List<Vehicle> listVehiclesForSaleSortedByPrice() {
        var allVehicles = listVehicles();
        var vehiclesForSale = new ArrayList<Vehicle>();

        allVehicles.forEach(vehicle -> {
            if (vehicle.getVehicleStatus().contains("à venda")) {
                vehiclesForSale.add(vehicle);
            }
        });

        return vehiclesForSale;
    }

    public List<Vehicle> listSoldVehiclesSortedByPrice() {
        var allVehicles = listVehicles();
        var soldVehicles = new ArrayList<Vehicle>();

        allVehicles.forEach(vehicle -> {
            if (vehicle.getVehicleStatus().contains("vendido")) {
                soldVehicles.add(vehicle);
            }
        });

        return soldVehicles;
    }
}
