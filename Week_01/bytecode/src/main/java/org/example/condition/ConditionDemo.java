package org.example.condition;

public class ConditionDemo {
    public static void main(String[] args) {
        demoA(true);
        demoA(false);
        demoB(-2);
        demoB(1);
        demoB(3);
        demoB(1024);
    }

    private static void demoA(boolean tag) {
        if (tag) {
            System.out.println("This is ture");
        } else {
            System.out.println("This is false");
        }
    }

    private static void demoB(int num) {
        if (num <= 0) {
            System.out.println("This is 1");
        } else if (num > 0 && num < 3) {
            System.out.println("This is 2");
        } else if (num == 3) {
            System.out.println("This is 3");
        } else {
            System.out.println("This is something else");
        }
    }
}
