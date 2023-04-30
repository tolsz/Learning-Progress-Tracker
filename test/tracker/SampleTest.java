package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    @Test
    @DisplayName("Name Norman, result should be true")
    void nameTest1() {
        Name name = new Name();
        assertTrue(name.nameChecker("Norman"));
    }

    @Test
    @DisplayName("- at the beginning, result should be false")
    void nameTest2() {
        Name name = new Name();
        assertFalse(name.nameChecker("-norman"));
    }

    @Test
    @DisplayName("' at the end, result should be false")
    void nameTest3() {
        Name name = new Name();
        assertFalse(name.nameChecker("norman'"));
    }

    @Test
    @DisplayName("J. should be incorrect")
    void nameTest4() {
        Name name = new Name();
        assertFalse(name.nameChecker("J."));
    }

    @Test
    @DisplayName("Name Jean-Clause, result should be true")
    void nameTest5() {
        Name name = new Name();
        assertTrue(name.nameChecker("Jean-Clause"));
    }

    @Test
    @DisplayName("陳 港 生 weird name, should be false")
    void nameTest6() {
        Name name = new Name();
        assertFalse(name.nameChecker("陳 港 生"));
    }

    @Test
    @DisplayName("-'john, should be false")
    void nameTest7() {
        Name name = new Name();
        assertFalse(name.nameChecker("-'john"));
    }

    @Test
    @DisplayName("D. should be false")
    void secondNameTest1() {
        Name name = new Name();
        String[] names = {"D."};
        assertFalse(name.surnameChecker(names));
    }

    @Test
    @DisplayName("Ronald Reuel Tolkien name, should be true")
    void secondNameTest2() {
        Name name = new Name();
        String[] names = {"Ronald", "Reuel", "Tolkien"};
        assertTrue(name.surnameChecker(names));
    }

    @Test
    @DisplayName("Luis 'john should be false")
    void secondNameTest3() {
        Name name = new Name();
        assertFalse(name.nameChecker("Luis 'john"));
    }

    @Test
    @DisplayName("luis i, should be false")
    void secondNameTest4() {
        Name name = new Name();
        String[] names = {"luis", "i", "Tolkien"};
        assertFalse(name.surnameChecker(names));
    }

    @Test
    @DisplayName("Don i--, should be false")
    void secondNameTest5() {
        Name name = new Name();
        String[] names = {"Don", "i--"};
        assertFalse(name.surnameChecker(names));
    }

    @Test
    @DisplayName("jdoe@mail.net, should be true")
    void emailTest1() {
        Name name = new Name();
        assertTrue(name.emailChecker("jdoe@mail.net"));
    }

    @Test
    @DisplayName("email, should be false")
    void emailTest2() {
        Name name = new Name();
        assertFalse(name.emailChecker("email"));
    }

    @Test
    @DisplayName("jc@google.itt, should be true")
    void emailTest3() {
        Name name = new Name();
        assertTrue(name.emailChecker("jc@google.it"));
    }

    @Test
    @DisplayName("jane.doe@yahoo.com, should be true")
    void emailTest4() {
        Name name = new Name();
        assertTrue(name.emailChecker("jane.doe@yahoo.com"));
    }

    @Test
    @DisplayName("email@emailxyz, should be false")
    void emailTest5() {
        Name name = new Name();
        assertFalse(name.emailChecker("email@emailxyz"));
    }

    @Test
    @DisplayName("email@e@mail.xyz, should be false")
    void emailTest6() {
        Name name = new Name();
        assertFalse(name.emailChecker("email@e@mail.xyz"));
    }
}