package test;

import java.math.BigDecimal;

/**
 * Created by yjh on 16-6-1.
 */
public class Test {

    @org.testng.annotations.Test
    public void test(){
        System.out.println(String.format("%d",new BigDecimal(123.1).intValue()));
//        System.out.println((int)(Math.random() * 10));
    }

}
