package org.example.java8.exception;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ExceptionDemo {
    public static void main(String[] args) {
        testFunction();
        testConsumer();
    }

    private static void testConsumer() {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);

        System.out.println("\n在lambda代码块中直接处理异常：");
        integers.forEach(i -> {
            try {
                System.out.println(i);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("\n通过ConsumerWrapper处理异常，代码将会更简洁：");
        integers.forEach(ThrowingConsumer.handlingConsumerWrapper(i -> System.out.println(i), Exception.class));
    }

    private static void testFunction() {
        try {
            ThrowingFunction.sneakyThrow(new IOException());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Optional.of(42).map(ThrowingFunction.unchecked(ExceptionDemo::throwException));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String throwException(Integer i) throws IOException {
        throw new IOException("whoopsie.");
    }
}
