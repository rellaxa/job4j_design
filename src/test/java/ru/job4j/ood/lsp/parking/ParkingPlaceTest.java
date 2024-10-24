package ru.job4j.ood.lsp.parking;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Truck;
import ru.job4j.ood.lsp.parking.model.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceTest {

    @Test
    public void whenParkedCarAndTruck() {
        ParkingControl parking = new ParkingPlace(1, 1);
        Vehicle car = new Car();
        Truck truck = new Truck(2);
        assertTrue(parking.park(car));
        assertTrue(parking.park(truck));
    }

    @Test
    public void whenNoPlacesForTruck() {
        ParkingControl parking = new ParkingPlace(1, 0);
        Truck truck = new Truck(2);
        assertFalse(parking.park(truck));
    }

    @Test
    public void whenEnoughPlacesOnCarPlaceForCarAndTruck() {
        ParkingControl parking = new ParkingPlace(3, 0);
        Car car = new Car();
        Vehicle truck = new Truck(2);
        assertTrue(parking.park(car));
        assertTrue(parking.park(truck));
    }

    @Test
    public void whenNoPlaceForCar() {
        ParkingControl parking = new ParkingPlace(0, 0);
        Vehicle car = new Car();
        assertFalse(parking.park(car));
    }

    @Test
    public void whenNoCarPlacesEvenWithOneTruckPlaceThanCarIsNotParked() {
        ParkingControl parking = new ParkingPlace(0, 1);
        Vehicle car = new Car();
        assertFalse(parking.park(car));
    }
}