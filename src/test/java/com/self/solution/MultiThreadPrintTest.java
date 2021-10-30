package com.self.solution;


import org.junit.Assert;
import org.junit.ComparisonFailure;
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
        StringBuilder assertRes = new StringBuilder();
        for (int i = 0; i < times; i++) {
            assertRes.append("123");
        }
        int test = 10000, success = 0, fail = 0;
        for (int i = 0; i < test; i++) {
            try {
                MultiThreadPrint model = new MultiThreadPrint(times);
                model.print();
                Assert.assertEquals(assertRes.toString(), systemOutRule.getLog());
                System.out.printf("第%d次验证通过%n", i);
                success ++;
            } catch (ComparisonFailure failure) {
                System.out.printf("第%d次验证失败%n", i);
                fail ++;
            } finally {
                systemOutRule.clearLog();
            }
        }
        Assert.assertEquals(test, success);
    }
}
