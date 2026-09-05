package ecommerce.utility;

import java.util.Scanner;

public final class InputValidator {

    private InputValidator() {
    }

    public static String readNonBlank(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("❌ Value cannot be empty.");
        }
    }

    public static int readInt(
            Scanner scanner, String prompt, int min, int max) {

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println(
                    "❌ Enter a number between " + min + " and " + max + ".");
        }
    }

    public static long readLong(
            Scanner scanner, String prompt, long min, long max) {

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                long value = Long.parseLong(input);

                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println(
                    "❌ Enter a valid number between " + min + " and " + max + ".");
        }
    }
}
