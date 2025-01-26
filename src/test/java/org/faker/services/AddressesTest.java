package org.faker.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddressesTest {

    @Test
    void testAddressesGeneration() {
        String fulladress = Address.getFullAddress();
        assertNotNull(fulladress);
        assertFalse(fulladress.isEmpty());
    }
}
