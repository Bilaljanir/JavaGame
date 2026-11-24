package ch.jobtrek.game.input;

import java.util.Scanner;

public final class InputValidator {

    private InputValidator() {
    }

    /**
     * Reads lines until the user enters a whole number between {@code min} and {@code max} (inclusive).
     *
     * @throws java.util.NoSuchElementException if the input is closed before a valid number is entered
     */
    public static int readValidInt(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // not a number: fall through to the error message below
            }
            System.out.println("Invalid choice, please try again. (enter a number between " + min + " and " + max
                    + ")");
        }
    }
}
