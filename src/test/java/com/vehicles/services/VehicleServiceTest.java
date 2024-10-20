package com.vehicles.services;

import com.vehicles.entities.Vehicle;
import com.vehicles.entities.dtos.CreateVehicleDTO;
import com.vehicles.entities.dtos.UpdateVehicleDTO;
import com.vehicles.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVehicle() throws Exception {
        // Arrange
        CreateVehicleDTO createVehicleDTO = new CreateVehicleDTO("FIAT", "PALIO", "2018", "Preto", "à venda", new BigDecimal(10.0));
        Vehicle vehicle = new Vehicle(createVehicleDTO);
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        // Act
        Vehicle createdVehicle = vehicleService.createVehicle(createVehicleDTO);

        // Assert
        assertNotNull(createdVehicle);
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    void testListVehicles() {
        // Arrange
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(/* parameters */));
        when(vehicleRepository.findAll()).thenReturn(vehicleList);

        // Act
        List<Vehicle> result = vehicleService.listVehicles();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void testGetVehicleById() {
        // Arrange
        String vehicleId = "123";
        Vehicle vehicle = new Vehicle(/* parameters */);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        // Act
        Optional<Vehicle> result = vehicleService.getVehicleById(vehicleId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(vehicle, result.get());
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }

    @Test
    void testUpdateVehicle() throws Exception {
        // Arrange
        String vehicleId = "123";
        UpdateVehicleDTO updateVehicleDTO = new UpdateVehicleDTO("FIAT", "PALIO", "2018", "Preto", "à venda", new BigDecimal(10.0));
        Vehicle vehicle = new Vehicle(/* parameters */);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        // Act
        Optional<Vehicle> updatedVehicle = vehicleService.updateVehicle(vehicleId, updateVehicleDTO);

        // Assert
        assertTrue(updatedVehicle.isPresent());
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void testListVehiclesForSaleSortedByPrice() {
        // Arrange
        Vehicle vehicleForSale = new Vehicle(/* parameters */);
        vehicleForSale.setVehicleStatus("à venda");
        List<Vehicle> allVehicles = List.of(vehicleForSale);
        when(vehicleRepository.findAllByOrderByVehiclePriceAsc()).thenReturn(allVehicles);

        // Act
        List<Vehicle> result = vehicleService.listVehiclesForSaleSortedByPrice();

        // Assert
        assertEquals(1, result.size());
        assertEquals("à venda", result.get(0).getVehicleStatus());
        verify(vehicleRepository, times(1)).findAllByOrderByVehiclePriceAsc();
    }

    @Test
    void testListSoldVehiclesSortedByPrice() {
        // Arrange
        Vehicle soldVehicle = new Vehicle(/* parameters */);
        soldVehicle.setVehicleStatus("vendido");
        List<Vehicle> allVehicles = List.of(soldVehicle);
        when(vehicleRepository.findAllByOrderByVehiclePriceAsc()).thenReturn(allVehicles);

        // Act
        List<Vehicle> result = vehicleService.listSoldVehiclesSortedByPrice();

        // Assert
        assertEquals(1, result.size());
        assertEquals("vendido", result.get(0).getVehicleStatus());
        verify(vehicleRepository, times(1)).findAllByOrderByVehiclePriceAsc();
    }
}
