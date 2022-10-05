package ru.sber.javacourse;

import java.util.Objects;

public class Car {
    private final long carId;
    private final String brand;
    private final String modelName;
    private final int maxVelocity;
    private final int power;

    public long getOwnerId() {
        return ownerId;
    }

    private final long ownerId;

    public long getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public Car(long carId, String brand, String modelName, int maxVelocity, int power, int ownerId) {
        this.carId = carId;
        this.brand = brand;
        this.modelName = modelName;
        this.maxVelocity = maxVelocity;
        this.power = power;
        this.ownerId = ownerId;
    }

    public int compareByCarId(Car other) {
        return this.carId - other.carId > 0 ? 1 : -1;
    }

    public int compareByPowerAndCarId(Car other) {
        if (this.power - other.power != 0) {
            return this.power - other.power > 0 ? 1 : -1;
        }
        return this.carId - other.carId > 0 ? 1 : -1;
    }

    public int compareByMaxVelocityAndCarId(Car other) {
        if (this.maxVelocity - other.maxVelocity != 0) {
            return this.maxVelocity - other.maxVelocity > 0 ? 1 : -1;
        }
        return this.carId - other.carId > 0 ? 1 : -1;
    }

    public static Car CarWithPower(int power) {
        return new Car(0, "", "", 0, power, 0);
    }

    public static Car CarWithMaxVelocity(int maxVelocity) {
        return new Car(0, "", "", maxVelocity, 0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }
}
