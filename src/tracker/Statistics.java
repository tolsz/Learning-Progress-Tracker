package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    //constants, number of points needed to finish the course
    private final double[] thresholds = {600, 400, 480, 550};
    private final String[] courses = {"Java", "DSA", "Databases", "Spring"};
    int[] submissions = new int[4];
    int[] summaryPoints;
    int[] studentsPerCourse;

    public void showData(Set<Student> students) {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        //check if was any submission
        if (zeroActivity()) {
            System.out.println("""
                    Most popular: n/a
                    Least popular: n/a
                    Highest activity: n/a
                    Lowest activity: n/a
                    Easiest course: n/a
                    Hardest course: n/a""");
        } else {
            popularity(true);
            popularity(false);
            difficulty();
            System.out.println();
        }
        while (true) {
            String option = new Scanner(System.in).nextLine();
            if ("back".equals(option)) break;
            else if (List.of("java", "dsa", "databases", "spring").contains(option.toLowerCase())) {
                topLearner(option.toLowerCase(), students);
            } else System.out.println("Unknown course.");
        }
    }
    public void addSubmission(int iteration) {
        submissions[iteration - 1] += 1;
    }

    public void updateStudentsPerCourse(Set<Student> students) {
        studentsPerCourse = new int[4];
        summaryPoints = new int[4];
        for (Student s : students) {
            for (int i = 1; i < 5; i++) {
                if (s.points[i] != 0) {
                    studentsPerCourse[i - 1] += 1;
                }
                summaryPoints[i - 1] += s.points[i];
            }
        }
    }

    private boolean zeroActivity() {
        for (int i : submissions) {
            if (i != 0) return false;
        }
        return true;
    }

    private void popularity(boolean popularity) {
        List<Integer> positionsMin = new ArrayList<>();
        List<Integer> positionsMax = new ArrayList<>();
        int max;
        int min;
        if (popularity) {
            max = Arrays.stream(studentsPerCourse).max().getAsInt();
            min = Arrays.stream(studentsPerCourse).min().getAsInt();
        } else {
            max = Arrays.stream(submissions).max().getAsInt();
            min = Arrays.stream(submissions).min().getAsInt();
        }
        for (int i = 0; i < 4; i++) {
            if (popularity) {
                if (studentsPerCourse[i] == min) {
                    positionsMin.add(i);
                }
                if (studentsPerCourse[i] == max) {
                    positionsMax.add(i);
                }
            } else {
                if (submissions[i] == min) {
                    positionsMin.add(i);
                }
                if (submissions[i] == max) {
                    positionsMax.add(i);
                }
            }
        }
        if (popularity) {
            System.out.print("Most popular: ");
        } else {
            System.out.print("\nMost activity: ");
        }
        int iteration = 0;
        for (int i : positionsMax) {
            if (iteration == positionsMax.size() - 1) {
                System.out.print(courses[i]);
            } else System.out.print(courses[i] + ", ");
            iteration++;
        }
        if (popularity) {
            System.out.print("\nLeast popular: ");
        } else {
            System.out.print("\nLeast activity: ");
        }
        positionsMin.removeAll(positionsMax);
        if (positionsMin.size() == 0) System.out.print("n/a");
        else {
            iteration = 0;
            for (int i : positionsMin) {
                if (iteration == positionsMin.size() - 1) {
                    System.out.print(courses[i]);
                } else System.out.print(courses[i] + ", ");
                iteration++;
            }
        }
    }

    private void difficulty() {
        ArrayList<Integer> easiest = new ArrayList<>();
        ArrayList<Integer> hardest = new ArrayList<>();
        double easy = Integer.MIN_VALUE;
        double hard = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            if (submissions[i] == 0) continue;
            double avg = (double) summaryPoints[i] / submissions[i];
            if (avg > easy) easy = avg;
            if (avg < hard) hard = avg;
        }
        for (int i = 0; i < 4; i++) {
            if (submissions[i] == 0) continue;
            if ((double) summaryPoints[i] / submissions[i] == easy) easiest.add(i);
            if ((double) summaryPoints[i] / submissions[i] == hard) hardest.add(i);
        }
        System.out.print("\nEasiest course: ");
        if (easiest.size() == 1) System.out.print(courses[easiest.get(0)]);
        else if (easiest.size() == 4) System.out.print("n/a");
        else easiest.forEach(n -> {
            if (Objects.equals(n, easiest.get(easiest.size() - 1))) System.out.print(courses[n]);
            else System.out.print(courses[n] + ", ");
        });
        System.out.print("\nHardest course: ");
        if (hardest.size() == 1) System.out.print(courses[hardest.get(0)]);
        else if (hardest.size() == 4) System.out.print("n/a");
        else hardest.forEach(n -> {
            if (Objects.equals(n, hardest.get(hardest.size() - 1))) System.out.print(courses[n]);
            else System.out.print(courses[n] + ", ");
        });
    }

    private void topLearner(String course, Set<Student> students) {
        int whichOne = -1;
        switch (course) {
            case "java" -> whichOne = 1;
            case "dsa" -> whichOne = 2;
            case "databases" -> whichOne = 3;
            case "spring" -> whichOne = 4;
        }
        Map<Integer, Integer> pointsAndID = new HashMap<>();
        // FIXME
        for (Student s : students) {
            if (s.points[whichOne] != 0) {
                pointsAndID.put(s.points[0], s.points[whichOne]);
            }
        }
        Map<Integer,Integer>  sortedMapReverseOrder =  pointsAndID.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(courses[whichOne - 1]);
        System.out.println("id        points completed");
        for (var entry : sortedMapReverseOrder.entrySet()) {
            BigDecimal completed = new BigDecimal(((double)entry.getValue()/thresholds[whichOne - 1]) * 100).
                    setScale(1, RoundingMode.HALF_UP);
            System.out.println(entry.getKey() + " " + entry.getValue() + "     " + completed + "%");
        }
    }
}