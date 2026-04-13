package com.example.chatapp;

public class AccountService {

    private user accountHolder;

    // Username check
    public boolean usernameAccepted(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    // Password check
    public boolean passwordAccepted(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean uppercaseExists = false;
        boolean digitExists = false;
        boolean specialExists = false;

        for (int index = 0; index < password.length(); index++) {

            char value = password.charAt(index);

            if (Character.isUpperCase(value)) {
                uppercaseExists = true;
            } else if (Character.isDigit(value)) {
                digitExists = true;
            } else if (!Character.isLetterOrDigit(value)) {
                specialExists = true;
            }
        }

        return uppercaseExists && digitExists && specialExists;
    }

    // Cellphone check
    public boolean cellphoneAccepted(String cellphone) {
        return cellphone != null && cellphone.matches("^\\+27\\d{9}$");
    }

    // Create account
    public String createAccount(String username, String password, String cellphone) {

        if (!usernameAccepted(username)) {
            return "Username is invalid. Include underscore and keep it short.";
        }

        if (!passwordAccepted(password)) {
            return "Password must contain uppercase, number and symbol.";
        }

        if (!cellphoneAccepted(cellphone)) {
            return "Cellphone number is not in correct SA format.";
        }

        accountHolder = new user(username, password, cellphone);

        return "Account setup completed.";
    }

    // Authenticate user
    public boolean authenticateUser(String username, String password) {

        if (accountHolder == null) {
            return false;
        }

        return accountHolder.getUsername().equals(username)
                && accountHolder.getPassword().equals(password);
    }

    // Login response
    public String showLoginResult(boolean isSuccessful, String username) {

        if (!isSuccessful) {
            return "Access denied. Invalid login details.";
        }

        String cleanName = username.replace("_", " ");

        return "Good to see you again, " + cleanName + ".";
    }
}
