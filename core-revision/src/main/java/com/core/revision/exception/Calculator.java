package com.core.revision.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        try {


            System.out.println("Enter first number: ");
            int x = scanner.nextInt();
            System.out.println("Enter second number: ");
            int y = scanner.nextInt();
            int result = x / y;
            System.out.println(result);


        } catch (InputMismatchException ex) {
            //handle karne wala code yaha aega
            System.out.println("Input Error occurred: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ArithmeticException ex) {
            System.out.println("Math Error occurred: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        }
        finally {
            System.out.println("Finally block executed");
            scanner.close();
        }

        System.out.println("End of program");


    }
}
