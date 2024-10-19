package com.vehicles.controllers;

import com.vehicles.entities.Vehicle;
import com.vehicles.entities.dtos.CreateVehicleDTO;
import com.vehicles.entities.dtos.UpdateVehicleDTO;
import com.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    ResponseEntity<Vehicle> createVehicle(@RequestBody CreateVehicleDTO createVehicleDTO) throws Exception {
        return new ResponseEntity<>(vehicleService.createVehicle(createVehicleDTO), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Vehicle>> listAllVehicles() {
        return new ResponseEntity<>(vehicleService.listVehicles(), HttpStatus.OK);
    }

    @GetMapping("/vehicles-for-sale")
    ResponseEntity<List<Vehicle>> listVehiclesForSaleSortedByPrice() {
        return new ResponseEntity<>(vehicleService.listVehiclesForSaleSortedByPrice(), HttpStatus.OK);
    }

    @GetMapping("/sold-vehicles")
    ResponseEntity<List<Vehicle>> listSoldVehiclesSortedByPrice() {
        return new ResponseEntity<>(vehicleService.listSoldVehiclesSortedByPrice(), HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    ResponseEntity<Optional<Vehicle>> updateVehicle(@PathVariable String id, @RequestBody UpdateVehicleDTO updateVehicleDTO) throws Exception {
        return new ResponseEntity<>(vehicleService.updateVehicle(id, updateVehicleDTO), HttpStatus.OK);
    }
}
