package com.learning.app02helloworld;

import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Application Started!");
        System.out.println("--------------------");
        System.out.println("---- MENU ----");
        System.out.println("1. Print Default Message");
        System.out.println("2. Get the Name and Say Hello");
        System.out.println("Select an Item:");
        String userInput = scanner.nextLine();
        if (userInput.equals("1")) {
            System.out.println("Hello World!");
        }
        if (userInput.equals("2")) {
            System.out.println("Please Enter Your Name:");
            String username = scanner.nextLine();
            System.out.println("Hello " + username);
        }
        scanner.close();
    }
}
