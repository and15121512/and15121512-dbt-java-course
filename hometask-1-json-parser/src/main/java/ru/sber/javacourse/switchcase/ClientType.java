package ru.sber.javacourse.switchcase;

import ru.sber.javacourse.utils.Utils;

import java.util.concurrent.atomic.AtomicReference;

public enum ClientType {
    INDIVIDUAL,
    LEGAL_ENTITY,
    HOLDING;

    private static ClientType getClientTypeReturn;

    static ClientType getClientType(String jsonstring) {
        Utils.parseJson(jsonstring, (name, value) -> {
            if ("clientType".equals(name)) {
                switch (value) {
                    case "INDIVIDUAL" -> getClientTypeReturn = ClientType.INDIVIDUAL;
                    case "LEGAL_ENTITY" -> getClientTypeReturn = ClientType.LEGAL_ENTITY;
                    case "HOLDING" -> getClientTypeReturn = ClientType.HOLDING;
                }
            }
        });
        return getClientTypeReturn;
    }
}
