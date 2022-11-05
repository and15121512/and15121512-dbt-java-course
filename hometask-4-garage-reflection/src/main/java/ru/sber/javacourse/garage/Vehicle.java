package ru.sber.javacourse.garage;

public interface Vehicle {
    long getId();
    String getBrand();
    int getMaxVelocity();
    int getPower();
    long getOwnerId();
}
