package ru.sber.javacourse.enumtype.impl;

import ru.sber.javacourse.enumtype.Client;
import ru.sber.javacourse.enumtype.ClientType;
import ru.sber.javacourse.utils.Utils;

public class ClientLegalEntity implements Client {
    private final ClientType clientType;
    private String fullName;
    private String shortName;
    private String inn;
    private String ogrn;
    private String kpp;

    public ClientLegalEntity(String jsonstring) {
        clientType = ClientType.LEGAL_ENTITY;
        Utils.parseJson(jsonstring, (name, value) -> {
            switch (name) {
                case "fullName" -> fullName = value;
                case "shortName" -> shortName = value;
                case "inn" -> inn = value;
                case "ogrn" -> ogrn = value;
                case "kpp" -> kpp = value;
            }
        });
    }

    public ClientType getClientType() {
        return clientType;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getInn() {
        return inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public String getKpp() {
        return kpp;
    }
}
