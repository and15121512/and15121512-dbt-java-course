package ru.sber.javacourse;

public interface Predicate<T extends Vehicle> {
    boolean check(T vehicle);
}
