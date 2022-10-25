package ru.sber.javacourse.impl;

import ru.sber.javacourse.*;

import java.util.*;
import java.util.stream.Collectors;

public class GarageImpl<T extends Vehicle> implements Garage<T> {
    private final HashMap<Long, T> carId2Cars;
    private final HashMap<Long, Owner> ownerId2Owners;

    private final HashMap<String, HashSet<T>> brand2Cars;
    private final HashMap<Owner, HashSet<T>> owner2Cars;
    private final HashMap<String, HashSet<Owner>> brand2Owners;

    private final TreeSet<T> carsSortedByMaxVelocity;
    private final TreeSet<T> carsSortedByPower;

    private T ceilingPower(int power) {
        for (var curr : carsSortedByPower) {
            if (curr.getPower() <= power) {
                return curr;
            }
        }
        return null;
    }

    public GarageImpl() {
        carId2Cars = new HashMap<>();
        ownerId2Owners = new HashMap<>();

        brand2Cars = new HashMap<>();
        owner2Cars = new HashMap<>();
        brand2Owners = new HashMap<>();

        carsSortedByMaxVelocity = new TreeSet<>(byPowerAndCarIdComparator());
        carsSortedByPower = new TreeSet<>(byMaxVelocityAndCarIdComparator());
    }

    private Comparator<T> byPowerAndCarIdComparator() {
        return Comparator.comparing(T::getPower).thenComparing(T::getId).reversed();
    }

    private Comparator<T> byMaxVelocityAndCarIdComparator() {
        return Comparator.comparing(T::getMaxVelocity).thenComparing(T::getId).reversed();
    }

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return owner2Cars.keySet();
    }

    @Override
    public Collection<T> topThreeCarsByMaxVelocity() {
        return carsSortedByMaxVelocity.stream().limit(3).toList();
    }

    @Override
    public Collection<T> allCarsOfBrand(String brand) {
        return brand2Cars.get(brand);
    }

    @Override
    public Collection<T> carsWithPowerMoreThan(int power) {
        return carsSortedByPower.headSet(ceilingPower(power));
    }

    @Override
    public Collection<T> allCarsOfOwner(Owner owner) {
        return owner2Cars.get(owner);
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        return (int)brand2Owners.get(brand).stream().mapToDouble(owner -> (double)(owner.getAge())).average().orElse(0);
    }

    @Override
    public int meanCarNumberForEachOwner() {
        return (int)owner2Cars.values().stream().mapToDouble(cars -> (double)(cars.size())).average().orElse(0);
    }

    @Override
    public T removeCar(long carId) {
        if (!(carId2Cars.containsKey(carId))) {
            return null;
        }

        T carToRemove = carId2Cars.get(carId);
        Owner ownerToRemove = ownerId2Owners.get(carToRemove.getOwnerId());

        carsSortedByMaxVelocity.remove(carToRemove);
        carsSortedByPower.remove(carToRemove);

        GarageImpl.removeElemOrHashSet(brand2Cars, carToRemove.getBrand(), carToRemove);
        GarageImpl.removeElemOrHashSet(owner2Cars, ownerId2Owners.get(carToRemove.getOwnerId()), carToRemove);
        if (!(owner2Cars.containsKey(ownerToRemove))) {
            GarageImpl.removeElemOrHashSet(brand2Owners, carToRemove.getBrand(), ownerToRemove);
            ownerId2Owners.remove(ownerToRemove.getOwnerId());
        }

        carId2Cars.remove(carId);
        return carToRemove;
    }

    @Override
    public void addCar(T car, Owner owner) {
        carId2Cars.put(car.getId(), car);
        ownerId2Owners.put(car.getOwnerId(), owner);

        GarageImpl.createOrAddToHashSet(brand2Cars, car.getBrand(), car);
        GarageImpl.createOrAddToHashSet(owner2Cars, owner, car);
        GarageImpl.createOrAddToHashSet(brand2Owners, car.getBrand(), owner);

        carsSortedByMaxVelocity.add(car);
        carsSortedByPower.add(car);
    }

    private static <K, V> boolean removeElemOrHashSet(HashMap<K, HashSet<V>> m, K key, V value) {
        var coll = m.get(key);
        if (coll == null || coll.remove(value)) {
            return false;
        }
        if (coll.isEmpty()) {
            m.remove(key);
        }
        return true;
    }

    private static <K, V> void createOrAddToHashSet(HashMap<K, HashSet<V>> m, K key, V value) {
        var coll = m.computeIfAbsent(key, k -> new HashSet<>());
        coll.add(value);
    }

    public void addCars(Collection<T> cars, Owner owner) {
        for (var car : cars) {
            addCar(car, owner);
        }
    }

    public T removeCar(T car) {
        return removeCar(car.getId());
    }

    public void removeAll(Collection<T> cars) {
        for (var car : cars) {
            removeCar(car);
        }
    }

    public Collection<T> upgradeAllVehiclesWith(VehicleUpgrader<T, T> upgrader) {
        return carId2Cars.values().stream().map(upgrader::upgrade).collect(Collectors.toList());
    }

    public Collection<T> filterCars(Predicate<T> predicate) {
        return carId2Cars.values().stream().filter(predicate::check).collect(Collectors.toList());
    }
}
