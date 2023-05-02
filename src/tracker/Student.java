package tracker;

import java.util.Objects;

public class Student {
    String name;
    String[] lastNames;
    String email;
    int[] points;

    public Student(String name, String[] lastNames, String email) {
        this.name = name;
        this.lastNames = lastNames;
        this.email = email;
        points = new int[5];
        points[0] = Objects.hash(email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (!(other instanceof Student student)) return false;

        return Objects.equals(email, student.email);
    }
}
