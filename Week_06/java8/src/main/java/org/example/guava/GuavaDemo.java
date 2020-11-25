package org.example.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;
import java.util.Map;

public class GuavaDemo {
    static EventBus bus = new EventBus();

    static {
        bus.register(new GuavaDemo());
    }

    public static void main(String[] args) {
        List<String> strings = testString();
        List<Integer> numbers = testList();
        testMap(numbers);
        testBiMap(strings);
        testEventBus();
    }

    private static List<String> testString() {
        List<String> strings = Lists.newArrayList("a", "b", "g", "8", "9");
        String mergedString = Joiner.on("-").join(strings);
        System.out.println("\nMerged String: " + mergedString);

        String test = "34344,34,34,哈哈";
        strings = Splitter.on(",").splitToList(test);
        System.out.println("Split String List:" + strings);

        return strings;
    }

    private static List<Integer> testList() {
        List<Integer> numbers = Lists.newArrayList(4, 2, 3, 5, 1, 2, 2, 7, 6, 9);
        List<List<Integer>> partition = Lists.partition(numbers, 3);
        System.out.println("\nNumber Partition:");
        print(partition);
        return numbers;
    }

    private static void testMap(List<Integer> list) {
        System.out.println("\nTest Map:");
        System.out.println("Input Number:");
        print(list);
        Multimap<Integer, Integer> bMultiMap = ArrayListMultimap.create();
        list.forEach(a -> bMultiMap.put(a, a + 1));
        print(bMultiMap);
    }

    private static void testBiMap(List<String> strings) {
        System.out.println("\nTest BiMap:");
        BiMap<String, Integer> words = HashBiMap.create();
        words.put("First", 1);
        words.put("Second", 2);
        words.put("Third", 3);

        System.out.println(words.get("Second").intValue());
        System.out.println(words.inverse().get(3));

        Map<String, String> map1 = Maps.toMap(strings.listIterator(), a -> a + "-value");
        print(map1);
    }

    private static void testEventBus() {
        System.out.println("\nTest Event Bus:");
        Student student = new Student(1, "Feng Du");
        System.out.println("I want " + student + " run now.");
        bus.post(new AEvent(student));
    }

    private static void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }

    @Subscribe
    public void handle(AEvent ae) {
        System.out.println(ae.getStudent() + " is running.");
    }
}
