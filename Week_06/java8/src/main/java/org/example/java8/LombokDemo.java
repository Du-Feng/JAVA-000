package org.example.java8;

import lombok.extern.java.Log;

@Log
public class LombokDemo {
    public static void main(String[] args) {
        LombokDemo demo = new LombokDemo();
        demo.demo();

        Student student1 = new Student();
        student1.setId(1);
        student1.setName("Feng Du");
        System.out.println(student1.toString());

        Student student2 = new Student(2, "Gong Jing");
        System.out.println(student2.toString());

        Student student3 = Student.builder()
                .id(3)
                .name("GeekTime")
                .build();
        System.out.println(student3.toString());
    }

    private void demo() {
        log.info("demo in log.");
    }
}
