package com.self.solution;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author yuanxin
 * @date 2021-10-28
 */
public class CharMatchTest {
    @Test
    public void test() {
        CharMatch charMatch = new CharMatch();
        assertTrue(charMatch.solution("{(x[])}")) ;
        assertTrue(charMatch.solution("{}()"));
        assertTrue(charMatch.solution("x{}(x)"));
        assertFalse(charMatch.solution("{"));
        assertFalse(charMatch.solution("{(})"));
    }
}
