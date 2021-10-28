package com.self.solution;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author yuanxin
 * @date 2021-10-28
 */
public class MultiThreadPrintTest {

    @Test
    public void test(){
        MultiThreadPrint model = new MultiThreadPrint();
        int count = 10;
        StringBuilder assertString = new StringBuilder();
        for (int i = 0; i < count; i++) {
            assertString.append("123");
        }
        model.solution(count);
//        assertEquals(assertString.toString(), solution);
    }
}
