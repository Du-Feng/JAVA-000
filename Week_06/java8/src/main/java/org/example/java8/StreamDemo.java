package org.example.java8;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 2, 3, 5, 1, 2, 2, 7, 6);
        print(list);

        Optional<Integer> first = list.stream().findFirst();
        System.out.println(first.map(i -> i * 100).orElse(100));

        int sum = list.stream()
                .filter(i -> i < 4)
                .distinct()
                .reduce(0, (a, b) -> a = b);
        System.out.println("sum = " + sum);

        Map map = list.stream()
                .distinct()
                .collect(Collectors.toMap(a -> a, a -> (a + 1)));
        print(map);

        Map<Integer, Integer> map2 = list.parallelStream()
                .collect(Collectors.toMap(a -> a, a -> (a + 1), (a, b) -> a, LinkedHashMap::new));
        print(map2);
        map2.forEach((k, v) -> System.out.println("Key:Value = " + k + ":" + v));

        List<Integer> list1 = map2.entrySet().parallelStream()
                .map(e -> e.getKey() + e.getValue())
                .collect(Collectors.toList());
        print(list1);
    }

    private static void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }
}
