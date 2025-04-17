package com.core.revision.collections;


import java.util.*;
import java.util.function.Consumer;

public class Main {

    static void listExample() {
        //  nischay, rahul, nikhil , saurabh

        //type safe list
        List<String> friendsList = new LinkedList<>();

        friendsList.add("nischay");
        friendsList.add("rahul");
        friendsList.add("nikhil");
        friendsList.add("saurabh");


        System.out.println(friendsList);
        friendsList.remove("rahul");
        System.out.println(friendsList);
        System.out.println(friendsList.contains("nikhil kumar"));
        System.out.println("size: " + friendsList.size());
        System.out.println("is empty: " + friendsList.isEmpty());

        friendsList.add(0, "nischay kumar");
        System.out.println(friendsList);


    }


    static void setExample() {


        Set<Integer> numberSet = new HashSet<>();
        numberSet.add(100);
        numberSet.add(1);
        numberSet.add(2);
        numberSet.add(3);
        numberSet.add(40);
        numberSet.add(5);
        numberSet.add(1); // duplicate value
        numberSet.add(2); // duplicate value
        numberSet.add(3); // duplicate value
        System.out.println(numberSet);
        Set<Integer> numberSet2 = new TreeSet<>();
        numberSet2.addAll(numberSet);
        numberSet2.add(0);
        System.out.println(numberSet2);


//        Iterator<Integer> iterator = numberSet2.iterator();
//        while (iterator.hasNext()){
//            int value=iterator.next();
//            System.out.println(value);
//        }
//


        numberSet2.forEach(System.out::println);


    }

    static void mapExample() {

        Map<String, Integer> map = new HashMap<>();

        map.put("Yash", 23);
        map.put("Kumar", 24);
        map.put("Shruti", 23);
        map.put("Manoj", 25);

        System.out.println(map);

        System.out.println(map.get("Yash"));
        System.out.println(map.get("Kumar"));

    }

    public static void main(String[] args) {

        mapExample();

    }
}
