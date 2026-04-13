package com.example.chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountServiceTest {

    @Test
    public void usernameShouldBeValid() {
        AccountService service = new AccountService();
        assertTrue(service.usernameAccepted("xy_1"));
    }

    @Test
    public void usernameShouldBeInvalid() {
        AccountService service = new AccountService();
        assertFalse(service.usernameAccepted("invalidName"));
    }

    @Test
    public void passwordShouldPassRules() {
        AccountService service = new AccountService();
        assertTrue(service.passwordAccepted("Valid@123"));
    }

    @Test
    public void passwordShouldFailRules() {
        AccountService service = new AccountService();
        assertFalse(service.passwordAccepted("weakpass"));
    }

    @Test
    public void cellphoneShouldBeAccepted() {
        AccountService service = new AccountService();
        assertTrue(service.cellphoneAccepted("+27838968976"));
    }

    @Test
    public void cellphoneShouldBeRejected() {
        AccountService service = new AccountService();
        assertFalse(service.cellphoneAccepted("083456"));
    }

    @Test
    public void accountCreationShouldSucceed() {
        AccountService service = new AccountService();

        String result = service.createAccount("xy_1", "Valid@123", "+27838968976");

        assertEquals("Account setup completed.", result);
    }

    @Test
    public void loginShouldWorkCorrectly() {
        AccountService service = new AccountService();

        service.createAccount("xy_1", "Valid@123", "+27838968976");

        assertTrue(service.authenticateUser("xy_1", "Valid@123"));
    }

    @Test
    public void loginShouldFailForWrongPassword() {
        AccountService service = new AccountService();

        service.createAccount("xy_1", "Valid@123", "+27838968976");

        assertFalse(service.authenticateUser("xy_1", "Wrong@321"));
    }

    @Test
    public void loginSuccessMessageShouldAppear() {
        AccountService service = new AccountService();

        String result = service.showLoginResult(true, "xy_1");

        assertTrue(result.contains("Good to see you again"));
    }

    @Test
    public void loginFailureMessageShouldAppear() {
        AccountService service = new AccountService();

        String result = service.showLoginResult(false, "xy_1");

        assertEquals("Access denied. Invalid login details.", result);
    }
}
