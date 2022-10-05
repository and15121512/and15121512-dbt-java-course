package ru.sber.javacourse.impl;

import ru.sber.javacourse.Car;
import ru.sber.javacourse.Garage;
import ru.sber.javacourse.Owner;

import java.util.*;

public class GarageImpl implements Garage {
    private HashMap<Long, Car> carId2Cars;
    private HashMap<Long, Owner> ownerId2Owners;

    private HashMap<String, HashSet<Car>> brand2Cars;
    private HashMap<Owner, HashSet<Car>> owner2Cars;
    private HashMap<String, HashSet<Owner>> brand2Owners;

    private TreeSet<Car> carsSortedByMaxVelocity;
    private TreeSet<Car> carsSortedByPower;

    public GarageImpl() {
        carId2Cars = new HashMap<>();
        ownerId2Owners = new HashMap<>();

        brand2Cars = new HashMap<>();
        owner2Cars = new HashMap<>();
        brand2Owners = new HashMap<>();

        carsSortedByMaxVelocity = new TreeSet<>((o1, o2) -> o2.compareByMaxVelocityAndCarId(o1));
        carsSortedByPower = new TreeSet<>((o1, o2) -> o2.compareByPowerAndCarId(o1));
    }

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return owner2Cars.keySet();
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        return carsSortedByMaxVelocity.stream().limit(3).toList();
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return brand2Cars.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        return carsSortedByPower.headSet(carsSortedByPower.ceiling(Car.CarWithPower(power)));
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
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
    public Car removeCar(long carId) {
        if (!(carId2Cars.containsKey(carId))) {
            return null;
        }

        Car carToRemove = carId2Cars.get(carId);
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
    public void addCar(Car car, Owner owner) {
        carId2Cars.put(car.getCarId(), car);
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
}
