package ch.jobtrek.game.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    void returnsValidNumber() {
        assertEquals(2, InputValidator.readValidInt(new Scanner("2\n"), 1, 3));
    }

    @Test
    void acceptsRangeLimitsAndSurroundingSpaces() {
        Scanner scanner = new Scanner("1\n 3 \n");

        assertEquals(1, InputValidator.readValidInt(scanner, 1, 3));
        assertEquals(3, InputValidator.readValidInt(scanner, 1, 3));
    }

    @Test
    void asksAgainAfterNonNumericInput() {
        assertEquals(2, InputValidator.readValidInt(new Scanner("abc\n\n2.5\n2\n"), 1, 3));
    }

    @Test
    void asksAgainAfterOutOfRangeNumber() {
        assertEquals(3, InputValidator.readValidInt(new Scanner("0\n4\n-1\n99999999999\n3\n"), 1, 3));
    }

    @Test
    void throwsWhenInputIsClosed() {
        assertThrows(NoSuchElementException.class, () -> InputValidator.readValidInt(new Scanner("abc\n"), 1, 3));
    }
}
