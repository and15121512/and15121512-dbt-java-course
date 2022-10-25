package ru.sber.javacourse;

import ru.sber.javacourse.impl.Car;

import java.util.Collection;
import java.util.List;

public interface Garage<E extends Vehicle> {
    Collection<Owner> allCarsUniqueOwners();

    /**
     * Complexity should be less than O(n)
     */
    Collection<E> topThreeCarsByMaxVelocity();

    /**
     * Complexity should be O(1)
     */
    Collection<E> allCarsOfBrand(String brand);

    /**
     * Complexity should be less than O(n)
     */
    Collection<E> carsWithPowerMoreThan(int power);

    /**
     * Complexity should be O(1)
     */
    Collection<E> allCarsOfOwner(Owner owner);

    /**
     * @return mean value of owner age that has cars with given brand
     */
    int meanOwnersAgeOfCarBrand(String brand);

    /**
     * @return mean value of cars for all owners
     */
    int meanCarNumberForEachOwner();

    /**
     * Complexity should be less than O(n)
     * @return removed car
     */
    E removeCar(long carId);

    /**
     * Complexity should be less than O(n)
     */
    void addCar(E car, Owner owner);

    void addCars(Collection<E> cars, Owner owner);

    E removeCar(E car);

    void removeAll(Collection<E> cars);

    Collection<E> upgradeAllVehiclesWith(VehicleUpgrader<E, E> upgrader);

    Collection<E> filterCars(Predicate<E> predicate);
}
