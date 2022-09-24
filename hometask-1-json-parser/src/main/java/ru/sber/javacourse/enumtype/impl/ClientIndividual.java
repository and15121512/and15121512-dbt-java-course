package ru.sber.javacourse.enumtype.impl;

import ru.sber.javacourse.enumtype.Client;
import ru.sber.javacourse.enumtype.ClientType;
import ru.sber.javacourse.utils.Utils;

public class ClientIndividual implements Client {
    private final ClientType clientType;
    private String firstName;
    private String lastName;
    private String inn;
    int age;

    public ClientIndividual(String jsonstring) {
        clientType = ClientType.INDIVIDUAL;
        Utils.parseJson(jsonstring, (name, value) -> {
            switch (name) {
                case "firstName" -> firstName = value;
                case "lastName" -> lastName = value;
                case "inn" -> inn = value;
                case "age" -> age = Integer.parseInt(value);
            }
        });
    }

    public ClientType getClientType() {
        return clientType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInn() {
        return inn;
    }

    public int getAge() {
        return age;
    }
}
