package org.elzacontiero.m3assessments.guessthenumber;

import org.junit.jupiter.api.Test;

import static org.elzacontiero.m3assessments.guessthenumber.GameUtils.checkingForEAndP;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {

    @Test
    public void testCheckingForEAndP() {
        char[] computer = new char[] { '1', '2', '3', '4'};
        char[] guess = new char[] {'4', '2', '7', '8'};
        char[] result = checkingForEAndP(computer, guess);
        assertEquals('P', result[0]);
        assertEquals('E', result[1]);
        assertEquals('0', result[2]);
        assertEquals('0', result[3]);

    }
}
