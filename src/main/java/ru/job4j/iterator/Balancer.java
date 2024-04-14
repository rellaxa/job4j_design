package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        while (source.hasNext()) {
            nodes.get(index++).add(source.next());
            index = index == nodes.size() ? 0 : index;
        }
    }

    public static void main(String[] args) {
        List<ArrayList<Integer>> nodes = List.of(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        Iterator<Integer> source = List.of(1, 2, 3, 4).iterator();
        split(nodes, source);
        System.out.println(nodes);
    }
}
