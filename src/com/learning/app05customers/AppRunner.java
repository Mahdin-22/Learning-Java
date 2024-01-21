package com.learning.app05customers;

import com.learning.app05customers.view.ConsoleUI;


public class AppRunner {
    public static void main(String[] args) {
        try (ConsoleUI consoleUI = new ConsoleUI()) {
            consoleUI.startMenu();
        } catch (Throwable exception) {
            System.out.println("Unknown Exception Occurred!");
        }
    }
}
