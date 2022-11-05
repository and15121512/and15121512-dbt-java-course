package ru.sber.javacourse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sber.javacourse.garage.Garage;
import ru.sber.javacourse.garage.Owner;
import ru.sber.javacourse.garage.impl.Car;
import ru.sber.javacourse.garage.impl.GarageImpl;
import ru.sber.javacourse.report.generator.impl.VehicleReportGenerator;

import java.util.*;

public class ReportTest {
    @Test
    void test_ReportNoAliases() {
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
        var entities = garage.topThreeCarsByMaxVelocity();

        // Act
        var reportGen = new VehicleReportGenerator<Car>();
        var report = reportGen.generate(entities, null, "Top 3 by max velocity");
        var actualData = report.getReportData();

        var expectedData = Arrays.asList(
                Arrays.asList("brand", "id", "maxVelocity", "modelName", "ownerId", "power"),
                Arrays.asList("Nissan", "123", "300", "Z350", "54321", "300"),
                Arrays.asList("Toyota", "456", "300", "Camry", "54321", "100"),
                Arrays.asList("Kia", "345", "250", "Sorento", "54321", "100")
        );

        Assertions.assertEquals(expectedData.size(), actualData.size());
        var expIter = expectedData.iterator();
        var actIter = actualData.iterator();
        while (expIter.hasNext() && actIter.hasNext()) {
            Assertions.assertEquals(expIter.next(), (List<String>)actIter.next());
        }
    }

    @Test
    void test_ReportWithAliases() {
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
        var entities = garage.topThreeCarsByMaxVelocity();
        var fields2Aliases = new TreeMap<String, String>() {{
            put("id", "ID авто");
            put("brand", "Марка");
            put("modelName", "Модель");
            put("maxVelocity", "Макс. скорость");
            put("power", "Мощность");
            put("ownerId", "ID владельца");
        }};

        // Act
        var reportGen = new VehicleReportGenerator<Car>();
        var report = reportGen.generate(entities, fields2Aliases, "Top 3 by max velocity");
        var actualData = report.getReportData();

        var expectedData = Arrays.asList(
                Arrays.asList("Марка", "ID авто", "Макс. скорость", "Модель", "ID владельца", "Мощность"),
                Arrays.asList("Nissan", "123", "300", "Z350", "54321", "300"),
                Arrays.asList("Toyota", "456", "300", "Camry", "54321", "100"),
                Arrays.asList("Kia", "345", "250", "Sorento", "54321", "100")
        );

        Assertions.assertEquals(expectedData.size(), actualData.size());
        var expIter = expectedData.iterator();
        var actIter = actualData.iterator();
        while (expIter.hasNext() && actIter.hasNext()) {
            Assertions.assertEquals(expIter.next(), (List<String>)actIter.next());
        }
    }
}
