package org.gitors.jobdemo.utils;

/**
 * @author : liuwenlong
 * @desc :
 * @company : 正元智慧
 * @date : 2019-07-16 10:19
 */
public class RtyTest {
    public static void main(String[] args) {
        testTry(true);
    }


    private static void testTry(boolean flag){
        System.out.println("进入方法");
        if (flag){
            return;
        }
        try {
            System.out.println("进入到了try");
            throw new RuntimeException("111");
        }catch (Exception e){
            System.out.println("catch 了 一个 异常 ：" + e.getMessage() );
        }finally {
            System.out.println("这里是finally");
        }
    }
}
