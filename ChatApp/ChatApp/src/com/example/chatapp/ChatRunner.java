package com.example.chatapp;

import java.util.Scanner;

public class ChatRunner {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        AccountService accountService = new AccountService();

        System.out.println("********************************");
        System.out.println("       CHAT LOGIN PORTAL         ");
        System.out.println("********************************");

        System.out.println("\n--- New User Registration ---");

        System.out.print("Choose username: ");
        String username = reader.nextLine();

        System.out.print("Choose password: ");
        String password = reader.nextLine();

        System.out.print("Enter cellphone (+27): ");
        String cellphone = reader.nextLine();

        String signUpMessage = accountService.createAccount(username, password, cellphone);
        System.out.println(signUpMessage);

        System.out.println("\n--- Existing User Login ---");

        System.out.print("Username: ");
        String enteredUsername = reader.nextLine();

        System.out.print("Password: ");
        String enteredPassword = reader.nextLine();

        boolean loginState = accountService.authenticateUser(enteredUsername, enteredPassword);

        String loginResponse = accountService.showLoginResult(loginState, enteredUsername);
        System.out.println(loginResponse);

        reader.close();
    }
}
