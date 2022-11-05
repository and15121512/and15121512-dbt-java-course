package ru.sber.javacourse.garage;

public interface VehicleUpgrader<S extends Vehicle, T extends Vehicle> {
    T upgrade(S vehicle);
}
