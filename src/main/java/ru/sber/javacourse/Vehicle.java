package ru.sber.javacourse;

import ru.sber.javacourse.impl.Car;

public interface Vehicle {
    long getId();
    String getBrand();
    int getMaxVelocity();
    int getPower();
    long getOwnerId();
}
