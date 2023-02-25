package readConsoleInput;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final String prompt = "Enter data of any type ('quit' to stop): ";

    static void readConsoleDataWithScanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        String input = "";
        while (scanner.hasNext()) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) break;
            getDataType(input);
            System.out.print("\n" + prompt);
        }
        scanner.close();
    }

    static void readConsoleDataWithConsoleClass() {
        Console console = System.console();
        System.out.print(prompt);
        String input = "";
        while (true) {
            input = console.readLine();
            if (input.equalsIgnoreCase("quit")) break;
            getDataType(input);
            System.out.print("\n" + prompt);
        }
    }

    static void readConsoleDataWithBufferedReader() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String input = "";
        System.out.print(prompt);
        while (true) {
            try {
                input = br.readLine();
                if (input.equalsIgnoreCase("quit")) break;
                getDataType(input);
                System.out.print("\n" + prompt);
            } catch (IOException e) {
                System.out.println("Failed to read input");
            }
        }
        // Close resources
        try {
            br.close();
            inputStreamReader.close();
        } catch (IOException e) {}
    }

    static void getDataType(String input) {
        // If input consists of only digits
        if (Pattern.matches("\\d+", input)) {
            System.out.println("Integer Entered: " + input);
        }

        // If input contains a dot surrounded by digits
        else if (Pattern.matches("\\d+\\.\\d+", input)) {
            System.out.println("Double Entered: " + input);
        }

        // If input is true or false
        else if (Pattern.matches("true|false", input)) {
            System.out.println("Boolean Entered: " + input);
        }

        // Input type is String
        else {
            System.out.println("String Entered: " + input);
        }
    }


    public static void main(String[] args) {
        // readConsoleDataWithScanner();
        // readConsoleDataWithBufferedReader();
        readConsoleDataWithConsoleClass();
    }
}
