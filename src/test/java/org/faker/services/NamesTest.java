package org.faker.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NamesTest {

    @Test
    void testNameGeneration() {
        String firstName = Names.getFirstName();
        assertNotNull(firstName);
        assertFalse(firstName.isEmpty());
    }

}
