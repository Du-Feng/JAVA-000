package org.example.java8;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public class LambdaDemo<T extends Serializable & Comparable & Collection> {
    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo();

        // 匿名类
        MathOperation op = new MathOperation<Integer>() {
            @Override
            public Integer operation(int a, int b) {
                return 1;
            }
        };

        // Lambda
        MathOperation op1 = (a, b) -> 1;
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b + 1.0;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            int c = 1000;
            return a * b + c;
        };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + demo.operate(10, 5, addition));
        System.out.println("10 - 5 = " + demo.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + demo.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + demo.operate(10, 5, division));

        System.out.println("10 ^ 5 = " + demo.operate(10, 5, (a, b) -> Math.pow(a, b)));

        // expression
        GreetingService greetingService1 = message ->
                System.out.println("Hello 1 " + message);

        // statement
        GreetingService greetingService2 = (message) -> {
            System.out.println("Hello 2 " + message);
        };

        // Method reference
        GreetingService greetingService3 = System.out::println;

        greetingService1.sayMessage("Feng Du");
        greetingService2.sayMessage("Java");
        greetingService3.sayMessage("GeekTime");

        Arrays.asList(1, 2, 3).forEach(x -> System.out.println(x + 3));
        Arrays.asList(1, 2, 3).forEach(LambdaDemo::println);
    }

    private static void println(int x) {
        System.out.println(x + 3);
    }

    @FunctionalInterface
    interface MathOperation<T> {
        T operation(int a, int b); // 返回类型+函数名+参数类型的列表
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }

    private <T> T operate(int a, int b, MathOperation<T> mathOperation) {
        return mathOperation.operation(a, b);
    }
}
