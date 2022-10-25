package ru.sber.javacourse;

public interface VehicleUpgrader<S extends Vehicle, T extends Vehicle> {
    T upgrade(S vehicle);
}
