package org.faker.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MailsTest {

    @Test
    void getRandomMail() {
        String email = Mail.getRandomMail();
        assertNotNull(email);
        assertFalse(email.isEmpty());
    }

    @Test
    void getIcloud() {
        String mail = Mail.getIcloud();
        assertNotNull(mail);
        assertFalse(mail.isEmpty());
    }

    @Test
    void getHotmail() {
        String mail = Mail.getHotmail();
        assertNotNull(mail);
        assertFalse(mail.isEmpty());
    }

    @Test
    void getAol() {
        String mail = Mail.getAol();
        assertNotNull(mail);
        assertFalse(mail.isEmpty());
    }

    @Test
    void getYahoo() {
        String mail = Mail.getYahoo();
        assertNotNull(mail);
        assertFalse(mail.isEmpty());
    }

    @Test
    void getProton() {
        String mail = Mail.getProton();
        assertNotNull(mail);
        assertFalse(mail.isEmpty());
    }

    @Test
    void getGmail() {
        String mail = Mail.getGmail();
        assertNotNull(mail);
        assertFalse(mail.isEmpty());
    }

}
