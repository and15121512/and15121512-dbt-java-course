package ru.sber.javacourse.enumtype;

import ru.sber.javacourse.enumtype.impl.ClientHolding;
import ru.sber.javacourse.enumtype.impl.ClientIndividual;
import ru.sber.javacourse.enumtype.impl.ClientLegalEntity;
import ru.sber.javacourse.utils.Utils;

public enum ClientType {
    INDIVIDUAL {
        @Override
        public Client createClient(String jsonstring) {
            return new ClientIndividual(jsonstring);
        }
    },
    LEGAL_ENTITY {
        @Override
        public Client createClient(String jsonstring) {
            return new ClientLegalEntity(jsonstring);
        }
    },
    HOLDING {
        @Override
        public Client createClient(String jsonstring) {
            return new ClientHolding(jsonstring);
        }
    };

    public abstract Client createClient(String jsonstring);

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
