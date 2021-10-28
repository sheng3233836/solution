package com.self.solution;


import org.junit.Test;

/**
 * @author yuanxin
 * @date 2021-10-28
 */
public class MultiThreadPrintTest {

    @Test
    public void test(){
        MultiThreadPrint model = new MultiThreadPrint(10);
        model.print();
    }
}
