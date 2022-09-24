package ru.sber.javacourse.switchcase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sber.javacourse.switchcase.ClientType;
import ru.sber.javacourse.switchcase.impl.ClientHolding;
import ru.sber.javacourse.switchcase.impl.ClientIndividual;
import ru.sber.javacourse.switchcase.impl.ClientLegalEntity;

public class ClientTest {
    @Test
    void test_ClientIndividual() {
        String jsonstring = """
            {
                "clientType": "INDIVIDUAL",
                "firstName": "Pyotr",
                "lastName": "Petrov",
                "inn": "1234567890",
                "age": 31
            }""";

        Client client = switch (ClientType.getClientType(jsonstring)) {
            case INDIVIDUAL -> new ClientIndividual(jsonstring);
            case LEGAL_ENTITY -> new ClientLegalEntity(jsonstring);
            case HOLDING -> new ClientHolding(jsonstring);
        };

        Assertions.assertTrue(client instanceof ClientIndividual);
        Assertions.assertEquals("Pyotr", ((ClientIndividual) client).getFirstName());
        Assertions.assertEquals("Petrov", ((ClientIndividual) client).getLastName());
        Assertions.assertEquals("1234567890", ((ClientIndividual) client).getInn());
        Assertions.assertEquals(31, ((ClientIndividual) client).getAge());
    }

    @Test
    void test_ClientLegalEntity() {
        String jsonstring = """
            {
                "clientType": "LEGAL_ENTITY",
                "fullName": "Pyotr",
                "shortName": "Petrov",
                "inn": "1234567890",
                "ogrn": "0987654321",
                "kpp": "777"
            }""";

        Client client = switch (ClientType.getClientType(jsonstring)) {
            case INDIVIDUAL -> new ClientIndividual(jsonstring);
            case LEGAL_ENTITY -> new ClientLegalEntity(jsonstring);
            case HOLDING -> new ClientHolding(jsonstring);
        };

        Assertions.assertTrue(client instanceof ClientLegalEntity);
        Assertions.assertEquals("Pyotr", ((ClientLegalEntity) client).getFullName());
        Assertions.assertEquals("Petrov", ((ClientLegalEntity) client).getShortName());
        Assertions.assertEquals("1234567890", ((ClientLegalEntity) client).getInn());
        Assertions.assertEquals("0987654321", ((ClientLegalEntity) client).getOgrn());
        Assertions.assertEquals("777", ((ClientLegalEntity) client).getKpp());
    }

    @Test
    void test_ClientHolding() {
        String jsonstring = """
            {
                "clientType": "HOLDING",
                "holdingType": 1
            }""";

        Client client = switch (ClientType.getClientType(jsonstring)) {
            case INDIVIDUAL -> new ClientIndividual(jsonstring);
            case LEGAL_ENTITY -> new ClientLegalEntity(jsonstring);
            case HOLDING -> new ClientHolding(jsonstring);
        };

        Assertions.assertTrue(client instanceof ClientHolding);
        Assertions.assertEquals(1, ((ClientHolding) client).getHoldingType());
    }
}
