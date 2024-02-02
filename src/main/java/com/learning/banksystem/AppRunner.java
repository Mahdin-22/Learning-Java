package com.learning.banksystem;

import com.learning.banksystem.view.ConsoleUI;


public class AppRunner {
    public static void main(String[] args) {
        try (ConsoleUI consoleUI = new ConsoleUI()) {
            consoleUI.startMenu();
        } catch (Throwable exception) {
            System.out.println("Unknown Exception Occurred!");
        }
    }
}
