package org.example.java8;

import java.util.Arrays;
import java.util.List;

public class ForeachDemo {
    private int x = 1;

    public static void main(String[] args) {
        ForeachDemo demo = new ForeachDemo();
        demo.test();
        System.out.println(demo.x);
    }

    private void test() {
        List list = Arrays.asList(1, 2);
        int y = 1;
        list.forEach(e -> {
            x = 2;
            System.out.println(e);
            //y = 2; // 无法编译：local variables referenced from a lambda expression must be final or effectively final
        });
    }
}
