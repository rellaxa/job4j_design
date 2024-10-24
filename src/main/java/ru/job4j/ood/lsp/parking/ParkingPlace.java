package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Truck;
import ru.job4j.ood.lsp.parking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlace implements ParkingControl {

    private int carPlace;
    private int truckPlace;
    private List<Vehicle> vehicles = new ArrayList<>();

    public ParkingPlace(int carPlace, int truckPlace) {
        this.carPlace = carPlace;
        this.truckPlace = truckPlace;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        int vehicleSize = vehicle.getVehicleSize();
        boolean parked = false;
        if (vehicleSize == 1 && carPlace > 0) {
            carPlace--;
            vehicles.add(vehicle);
            parked = true;
        } else if (vehicleSize > 1 && truckPlace > 0) {
            truckPlace--;
            vehicles.add(vehicle);
            parked = true;
        } else if (vehicleSize > 1 && vehicleSize <= carPlace) {
            carPlace -= vehicleSize;
            vehicles.add(vehicle);
            parked = true;
        }
        return parked;
    }

    public static void main(String[] args) {
        ParkingControl parking = new ParkingPlace(4, 1);
        Vehicle carOne = new Car();
        Vehicle carTwo = new Car();
        Vehicle truckOne = new Truck(3);
        Vehicle truckTwo = new Truck(2);
        System.out.println(parking.park(carOne));
        System.out.println(parking.park(carTwo));
        System.out.println(parking.park(truckTwo));
        System.out.println(parking.park(truckOne));
    }

}
