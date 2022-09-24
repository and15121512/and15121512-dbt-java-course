package ru.sber.javacourse.enumtype.impl;

import ru.sber.javacourse.enumtype.Client;
import ru.sber.javacourse.enumtype.ClientType;
import ru.sber.javacourse.utils.Utils;

public class ClientHolding implements Client {
    private final ClientType clientType;
    private int holdingType;

    public ClientHolding(String jsonstring) {
        clientType = ClientType.HOLDING;
        Utils.parseJson(jsonstring, (name, value) -> {
            if ("holdingType".equals(name)) {
                holdingType = Integer.parseInt(value);
            }
        });
    }

    public ClientType getClientType() {
        return clientType;
    }

    public int getHoldingType() {
        return holdingType;
    }
}
