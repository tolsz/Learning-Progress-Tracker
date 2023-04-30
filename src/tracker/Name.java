package tracker;

public class Name {
    String[] input;

    public void setInput(String input) {
        this.input = input.split(" ");
    }

    public boolean checker() {
        String[] lastNames = new String[input.length - 2];
        for (int i = 1; i < input.length - 1; i++) {
            lastNames[i - 1] = input[i];
        }
        if (!nameChecker(input[0])) {
            System.out.println("Incorrect first name.");
            return false;
        } else if (!surnameChecker(lastNames)) {
            System.out.println("Incorrect last name.");
            return false;
        } else if (!emailChecker(input[input.length - 1])) {
            System.out.println("Incorrect email.");
            return false;
        }
        System.out.println("The student has been added.");
        return true;
    }

    boolean nameChecker(String name) {
        if (name.length() < 2) return false;
        int repeat = 0;
        String[] letters = name.split("");
        for (int i = 0; i < name.length(); i++) {
            if (i == 0 || i == name.length() - 1) {
                if (letters[i].matches("[-']")) return false;
            }
            if (!letters[i].matches("[a-zA-Z\\-']")) return false;
            if (letters[i].matches("[-']")) {
                repeat++;
                if(repeat == 2) return false;
                continue;
            }
            repeat = 0;
        }
        return true;
    }

    boolean surnameChecker(String[] lastNames) {
        int repeat = 0;
        for (String s : lastNames) {
            if (s.length() < 2) return false;
            String[] letters = s.split("");
            for (int i = 0; i < s.length(); i++) {
                if (i == 0 || i == s.length() - 1) {
                    if (letters[i].matches("[-']")) return false;
                }
                if (!letters[i].matches("[a-zA-Z\\-']")) return false;
                if (letters[i].matches("[-']")) {
                    repeat++;
                    if(repeat == 2) return false;
                    continue;
                }
                repeat = 0;
            }
        }
        return true;
    }

    boolean emailChecker(String email) {
        String regexPattern = "^(.+)@(.+)\\.(.+)$";
        String[] letters = email.split("");
        int at = 0;
        for (int i = 0; i < email.length(); i++) {
            if (letters[i].matches("@")) at++;
        }
        if (email.matches(regexPattern) && at < 2) return true;
        return false;
    }
}