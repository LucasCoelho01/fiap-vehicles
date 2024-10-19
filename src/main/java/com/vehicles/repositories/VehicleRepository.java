package com.vehicles.repositories;

import com.vehicles.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
     List<Vehicle> findAllByOrderByVehiclePriceAsc();
}
