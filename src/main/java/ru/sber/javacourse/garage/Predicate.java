package ru.sber.javacourse.garage;

public interface Predicate<T extends Vehicle> {
    boolean check(T vehicle);
}
