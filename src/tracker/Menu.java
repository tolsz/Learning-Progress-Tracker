package tracker;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Menu {
    Set<Student> students;

    public Menu() {
        students = new HashSet<>();
    }

    public void start() {
        System.out.println("Learning Progress Tracker");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            if (next.trim().isEmpty()) System.out.println("No input.");
            else if ("exit".equals(next)) {
                System.out.println("Bye!");
                break;
            } else if ("add students".equals(next)) {
                addStudent();
            } else if ("back".equals(next)) {
                System.out.println("Enter 'exit' to exit the program.");
            } else if ("list".equals(next)) {
                list();
            } else if ("add points".equals(next)) {
                addPoints();
            } else if ("find".equals(next)) {
                find();
            } else System.out.println("Error: unknown command!");
        }
    }

    private void addStudent() {
        int number = 0;
        System.out.println("Enter student credentials or 'back' to return:");
        while (true) {
            String option = new Scanner(System.in).nextLine();
            String[] check = option.split(" ");
            if ("back".equals(option)) {
                System.out.println("Total " + number + " students have been added.");
                break;
            } else if (check.length < 3) {
                System.out.println("Incorrect credentials.");
            } else {
                Name name = new Name();
                name.setInput(option);
                Student student = name.checker();
                if (student == null) {
                    continue;
                }
                int size = students.size();
                students.add(student);
                if (students.size() == size) {
                    System.out.println("This email is already taken.");
                    continue;
                }
                System.out.println("The student has been added.");
                number++;
            }
        }
    }

    private void list() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("Students:");
        students.forEach(n -> System.out.println(n.hashCode()));
    }

    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String[] points = new Scanner(System.in).nextLine().split(" ");
            if ("back".equals(points[0])) break;
            int[] pointsInt = new int[5];
            if (points.length != 5) {
                System.out.println("Incorrect points format.");
                continue;
            }
            int j = 0;
            try {
                for (int i = 0; i < 5; i++) {
                    pointsInt[i] = Integer.parseInt(points[i]);
                    j++;
                }
            } catch (NumberFormatException e) {
                if (j == 0) {
                    System.out.println("No student is found for id=" + points[0] + ".");
                    break;
                }
                System.out.println("Incorrect points format.");
                continue;
            }
            int checking = 0;
            for (Student s : students) {
                checking = iterate(s, pointsInt);
                if (checking == -1) break;
                else if (checking == 1) break;
            }
            if (checking == 0) System.out.println("No student is found for id=" + pointsInt[0] + ".");
            else if (checking == 1)System.out.println("Points updated.");
        }
    }

    private void find() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            String option = new Scanner(System.in).next();
            int id = Integer.MIN_VALUE;
            if ("back".equals(option)) break;
            try {
                id = Integer.parseInt(option);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect id format.");
            }
            boolean flag = false;
            for (Student s : students) {
                if (s.points[0] == id) {
                    flag = true;
                    System.out.println(id + " points: Java=" + s.points[1] + "; DSA="
                            + s.points[2] + "; Databases=" + s.points[3] + "; Spring=" + s.points[4]);
                }
            }
            if (!flag) System.out.println("No student is found for id=" + id + ".");
        }
    }

    private int iterate(Student s, int[] pointsInt) {
        // -1 - not correct, 0 - not found, 1 - found and correct
        if (s.points[0] == pointsInt[0]) {
            for (int i = 1; i < 5; i++) {
                if (pointsInt[i] < 0) {
                    System.out.println("Incorrect points format.");
                    return -1;
                }
            }
            for (int i = 1; i < 5; i++) {
                s.points[i] += pointsInt[i];
            }
        } else return 0;
        return 1;
    }
}