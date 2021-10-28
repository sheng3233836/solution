package com.self.solution;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

/**
 * @author yuanxin
 * @date 2021-10-28
 */
public class MultiThreadPrintTest {
    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void test() {
        int times = 10;
        MultiThreadPrint model = new MultiThreadPrint(times);
        model.print();
        StringBuilder assertRes = new StringBuilder();
        for (int i = 0; i < times; i++) {
            assertRes.append("123");
        }
        while (!model.isFinish()) {}
        Assert.assertEquals(assertRes.toString(), systemOutRule.getLog());
    }
}
