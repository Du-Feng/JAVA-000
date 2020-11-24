package org.example.java8;

import java.util.List;
import java.util.stream.Collectors;

public class CollectionDemo {
    public static void main(String[] args) {

    }

    private static void print(List<Integer> list) {
        System.out.println(String.join(",", list.stream().
                map(i -> i.toString())
                .collect(Collectors.toList())
                .toArray(new String[]{})
        ));
    }
}
