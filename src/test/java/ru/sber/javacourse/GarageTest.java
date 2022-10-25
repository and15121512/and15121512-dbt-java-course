package ru.sber.javacourse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sber.javacourse.impl.Car;
import ru.sber.javacourse.impl.GarageImpl;

import java.util.*;

public class GarageTest {
    private static class OwnerCar {
        public Owner owner;
        public Car car;

        public OwnerCar(Owner owner, Car car) {
            this.owner = owner;
            this.car = car;
        }
    }

    @Test
    void test_GarageAddCar() {
        // Acquire
        Owner owner = new Owner(
                12345,
                "Vasya",
                "Petrov",
                25
        );
        Garage<Car> garage = new GarageImpl<Car>();
        var storedColl = new ArrayList<>(Arrays.asList(
                new Car(
                12345,
                "Nissan",
                "Z350",
                300,
                300,
                54321),
                new Car(
                67890,
                "Renault",
                "Logan",
                200,
                100,
                54321)
        ));

        // Act
        for (var stored : storedColl) {
            garage.addCar(stored, owner);
        }

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        12345,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        54321),
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));
        var gotColl = garage.allCarsOfOwner(owner);
        Assertions.assertTrue(gotColl.size() == 2);
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageAddCars() {
        // Acquire
        Owner owner = new Owner(
                12345,
                "Vasya",
                "Petrov",
                25
        );
        Garage<Car> garage = new GarageImpl<Car>();
        var storedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        12345,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        54321),
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));

        // Act
        garage.addCars(storedColl, owner);

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        12345,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        54321),
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));
        var gotColl = garage.allCarsOfOwner(owner);
        Assertions.assertTrue(gotColl.size() == 2);
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageRemoveCarById() {
        // Acquire
        Owner owner = new Owner(
                12345,
                "Vasya",
                "Petrov",
                25
        );
        Garage<Car> garage = new GarageImpl<Car>();
        var storedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        12345,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        54321),
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));

        // Act
        for (var stored : storedColl) {
            garage.addCar(stored, owner);
        }
        garage.removeCar(12345);

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));
        var gotColl = garage.allCarsOfOwner(owner);
        Assertions.assertTrue(gotColl.size() == 1);
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageRemoveCar() {
        // Acquire
        Owner owner = new Owner(
                12345,
                "Vasya",
                "Petrov",
                25
        );
        Garage<Car> garage = new GarageImpl<Car>();
        var storedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        12345,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        54321),
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));

        // Act
        for (var stored : storedColl) {
            garage.addCar(stored, owner);
        }
        garage.removeCar(storedColl.get(0));

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));
        var gotColl = garage.allCarsOfOwner(owner);
        Assertions.assertTrue(gotColl.size() == 1);
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageRemoveAll() {
        // Acquire
        Owner owner = new Owner(
                12345,
                "Vasya",
                "Petrov",
                25
        );
        Garage<Car> garage = new GarageImpl<Car>();
        var storedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        12345,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        54321),
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));

        // Act
        for (var stored : storedColl) {
            garage.addCar(stored, owner);
        }
        garage.removeAll(storedColl);

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        67890,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321)
        ));
        var gotColl = garage.allCarsOfOwner(owner);
        Assertions.assertTrue(gotColl.size() == 0);
    }

    @Test
    void test_GarageAllCarsUniqueOwners() {
        // Acquire
        var tests = new HashMap<>() {{
            put(
                    new Owner(
                            12345,
                            "Vasya",
                            "Vasev",
                            20),
                    new Car(
                            12345,
                            "Nissan",
                            "Z350",
                            300,
                            300,
                            54321)
            );
            put(
                    new Owner(
                            67890,
                            "Petr",
                            "Petrov",
                            30),
                    new Car(
                            67890,
                            "Renault",
                            "Logan",
                            200,
                            100,
                            54321)
            );
        }};
        Garage<Car> garage = new GarageImpl<Car>();
        for (var test : tests.entrySet()) {
            garage.addCar((Car)test.getValue(), (Owner)test.getKey());
        }

        // Act
        var gotColl = garage.allCarsUniqueOwners();

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Owner(
                        12345,
                        "Vasya",
                        "Vasev",
                        20),
                new Owner(
                        67890,
                        "Petr",
                        "Petrov",
                        30)
        ));
        Assertions.assertTrue(gotColl.size() == 2);
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageTopThreeCarsByMaxVelocity() {
        // Acquire
        Owner owner = new Owner(
                12345,
                "Vasya",
                "Petrov",
                25
        );
        Garage<Car> garage = new GarageImpl<Car>();
        var storedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        123,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        54321),
                new Car(
                        234,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        54321),
                new Car(
                        345,
                        "Kia",
                        "Sorento",
                        250,
                        100,
                        54321),
                new Car(
                        456,
                        "Toyota",
                        "Camry",
                        300,
                        100,
                        54321)
        ));
        for (var stored : storedColl) {
            garage.addCar(stored, owner);
        }

        // Act
        var gotColl = garage.topThreeCarsByMaxVelocity();

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        123,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        54321),
                new Car(
                        345,
                        "Kia",
                        "Sorento",
                        250,
                        100,
                        54321),
                new Car(
                        456,
                        "Toyota",
                        "Camry",
                        300,
                        100,
                        54321)
        ));
        Assertions.assertTrue(gotColl.size() == 3);
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageAllCarsOfBrand() {
        // Acquire
        var tests = new ArrayList<>(Arrays.asList(
                new OwnerCar(
                        new Owner(
                                12345,
                                "Vasya",
                                "Vasev",
                                20),
                        new Car(
                                123,
                                "Nissan",
                                "Z350",
                                300,
                                300,
                                12345)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                234,
                                "Nissan",
                                "GTR",
                                350,
                                350,
                                67890)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                345,
                                "Renault",
                                "Logan",
                                200,
                                100,
                                67890)
                )
        ));

        Garage<Car> garage = new GarageImpl<Car>();
        for (var test : tests) {
            garage.addCar(test.car, test.owner);
        }

        // Act
        var gotColl = garage.allCarsOfBrand("Nissan");

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        123,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        12345),
                new Car(
                        234,
                        "Nissan",
                        "GTR",
                        350,
                        350,
                        67890)
        ));
        Assertions.assertTrue(gotColl.size() == 2, String.format("Got coll of size %d, expected %d. Id: %d", gotColl.size(), 2, ((Car)gotColl.toArray()[0]).getId()));
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageCarsWithPowerMoreThan() {
        // Acquire
        var tests = new ArrayList<>(Arrays.asList(
                new OwnerCar(
                        new Owner(
                                12345,
                                "Vasya",
                                "Vasev",
                                20),
                        new Car(
                                123,
                                "Nissan",
                                "Z350",
                                300,
                                300,
                                12345)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                234,
                                "Nissan",
                                "GTR",
                                350,
                                350,
                                67890)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                345,
                                "Renault",
                                "Logan",
                                200,
                                100,
                                67890)
                )
        ));

        Garage<Car> garage = new GarageImpl<Car>();
        for (var test : tests) {
            garage.addCar(test.car, test.owner);
        }

        // Act
        var gotColl = garage.carsWithPowerMoreThan(200);

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        123,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        12345),
                new Car(
                        234,
                        "Nissan",
                        "GTR",
                        350,
                        350,
                        67890)
        ));
        Assertions.assertTrue(gotColl.size() == 2);
        Assertions.assertTrue(gotColl.stream().toList().containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageAllCarsOfOwner() {
        // Acquire
        var tests = new ArrayList<>(Arrays.asList(
                new OwnerCar(
                        new Owner(
                                12345,
                                "Vasya",
                                "Vasev",
                                20),
                        new Car(
                                123,
                                "Nissan",
                                "Z350",
                                300,
                                300,
                                12345)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                234,
                                "Nissan",
                                "GTR",
                                350,
                                350,
                                67890)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                345,
                                "Renault",
                                "Logan",
                                200,
                                100,
                                67890)
                )
        ));
        var queriedOwner = new Owner(
                67890,
                "Petr",
                "Petrov",
                30);

        Garage<Car> garage = new GarageImpl<Car>();
        for (var test : tests) {
            garage.addCar(test.car, test.owner);
        }

        // Act
        var gotColl = garage.allCarsOfOwner(queriedOwner);

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        345,
                        "Renault",
                        "Logan",
                        200,
                        100,
                        67890),
                new Car(
                        234,
                        "Nissan",
                        "GTR",
                        350,
                        350,
                        67890)
        ));
        Assertions.assertTrue(gotColl.size() == 2);
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }

    @Test
    void test_GarageMeanOwnersAgeOfCarBrand() {
        // Acquire
        var tests = new ArrayList<>(Arrays.asList(
                new OwnerCar(
                        new Owner(
                                12345,
                                "Vasya",
                                "Vasev",
                                20),
                        new Car(
                                123,
                                "Nissan",
                                "Z350",
                                300,
                                300,
                                12345)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                234,
                                "Nissan",
                                "GTR",
                                350,
                                350,
                                67890)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                345,
                                "Renault",
                                "Logan",
                                200,
                                100,
                                67890)
                )
        ));

        Garage<Car> garage = new GarageImpl<Car>();
        for (var test : tests) {
            garage.addCar(test.car, test.owner);
        }

        // Act
        var got = garage.meanOwnersAgeOfCarBrand("Nissan");

        // Assert
        var expected = 25;
        Assertions.assertEquals(expected, got);
    }

    @Test
    void test_GarageMeanCarNumberForEachOwner() {
        // Acquire
        var tests = new ArrayList<>(Arrays.asList(
                new OwnerCar(
                        new Owner(
                                12345,
                                "Vasya",
                                "Vasev",
                                20),
                        new Car(
                                123,
                                "Nissan",
                                "Z350",
                                300,
                                300,
                                12345)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                234,
                                "Nissan",
                                "GTR",
                                350,
                                350,
                                67890)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                345,
                                "Renault",
                                "Logan",
                                200,
                                100,
                                67890)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                456,
                                "Volkswagen",
                                "Tiguan",
                                200,
                                100,
                                67890)
                )
        ));

        Garage<Car> garage = new GarageImpl<Car>();
        for (var test : tests) {
            garage.addCar(test.car, test.owner);
        }

        // Act
        var got = garage.meanCarNumberForEachOwner();

        // Assert
        var expected = 2;
        Assertions.assertEquals(expected, got);
    }

    @Test
    void test_GarageFilterCars() {
        // Acquire
        var tests = new ArrayList<>(Arrays.asList(
                new OwnerCar(
                        new Owner(
                                12345,
                                "Vasya",
                                "Vasev",
                                20),
                        new Car(
                                123,
                                "Nissan",
                                "Z350",
                                300,
                                300,
                                12345)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                234,
                                "Nissan",
                                "GTR",
                                350,
                                350,
                                67890)
                ),
                new OwnerCar(
                        new Owner(
                                67890,
                                "Petr",
                                "Petrov",
                                30),
                        new Car(
                                345,
                                "Renault",
                                "Logan",
                                200,
                                100,
                                67890)
                )
        ));

        Garage<Car> garage = new GarageImpl<Car>();
        for (var test : tests) {
            garage.addCar(test.car, test.owner);
        }

        // Act
        var gotColl = garage.filterCars(car -> Objects.equals(car.getBrand(), "Nissan"));

        // Assert
        var expectedColl = new ArrayList<>(Arrays.asList(
                new Car(
                        123,
                        "Nissan",
                        "Z350",
                        300,
                        300,
                        12345),
                new Car(
                        234,
                        "Nissan",
                        "GTR",
                        350,
                        350,
                        67890)
        ));
        Assertions.assertTrue(gotColl.size() == 2, String.format("Got coll of size %d, expected %d. Id: %d", gotColl.size(), 2, ((Car)gotColl.toArray()[0]).getId()));
        Assertions.assertTrue(gotColl.containsAll(expectedColl));
        Assertions.assertTrue(expectedColl.containsAll(gotColl));
    }
}
