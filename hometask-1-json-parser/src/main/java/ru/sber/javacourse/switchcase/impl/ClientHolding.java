package ru.sber.javacourse.switchcase.impl;

import ru.sber.javacourse.switchcase.Client;
import ru.sber.javacourse.switchcase.ClientType;
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
