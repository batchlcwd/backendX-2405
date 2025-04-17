package com.core.revision.streams;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample
{
    public static void main(String[] args) {
        // Example usage of streams
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);


        List<Integer> newList = numbers
                .stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(newList);


        List<Integer> collectedList = numbers
                .stream()
                .map(value -> value * 10)
                .collect(Collectors.toList());


        List<Integer> newCollectedList = numbers.
                stream()
                .map(value -> value * 5)
                .filter(value -> value % 2 != 0)
                .collect(Collectors.toList());

        System.out.println(newCollectedList);


        Integer reduce = numbers
                .stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println(reduce);


    }
}
