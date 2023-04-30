package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Learning Progress Tracker");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            if (next.trim().isEmpty()) System.out.println("No input.");
            else if ("exit".equals(next)) {
                System.out.println("Bye!");
                break;
            } else if ("add students".equals(next)) {
                int number = 0;
                System.out.println("Enter student credentials or 'back' to return:");
                while (true) {
                    String option = scanner.nextLine();
                    String[] check = option.split(" ");
                    if ("back".equals(option)) {
                        System.out.println("Total " + number + " students have been added.");
                        break;
                    } else if (check.length < 3) {
                        System.out.println("Incorrect credentials.");
                    } else {
                        Name name = new Name();
                        name.setInput(option);
                        if (!name.checker()) {
                            continue;
                        }
                        number++;
                    }
                }
            } else if ("back".equals(next)) {
                System.out.println("Enter 'exit' to exit the program.");
            } else if ("list".equals(next)) {

            } else System.out.println("Error: unknown command!");
        }
    }
}
