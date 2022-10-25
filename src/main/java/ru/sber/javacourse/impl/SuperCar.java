package ru.sber.javacourse.impl;

import ru.sber.javacourse.Vehicle;

import java.util.Objects;

public class SuperCar implements Vehicle {
    private final long id;
    private final String brand;
    private final String modelName;
    private final int maxVelocity;
    private final int power;
    private final long ownerId;

    public long getId() {
        return id;
    }
    public String getBrand() {
        return brand;
    }
    public int getMaxVelocity() {
        return maxVelocity;
    }
    public int getPower() {
        return power;
    }
    public long getOwnerId() {
        return ownerId;
    }

    public SuperCar(long id, String brand, String modelName, int maxVelocity, int power, int ownerId) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.maxVelocity = maxVelocity;
        this.power = power;
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperCar superCar = (SuperCar) o;
        return id == superCar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
