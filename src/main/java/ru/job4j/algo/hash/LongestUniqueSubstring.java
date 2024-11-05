package ru.job4j.algo.hash;

import java.util.*;

public class LongestUniqueSubstring {

    public static String longestUniqueSubstring(String str) {
        HashMap<Character, Integer> map = new LinkedHashMap<>();
        String rsl = "";
        int start = 0;
        for (int end = 0; end < str.length(); end++) {
            char symbol = str.charAt(end);
            if (map.containsKey(symbol)) {
                start = Math.max(map.get(symbol) + 1, start);
            }
            if (rsl.length() < end - start + 1) {
                rsl = str.substring(start, end + 1);
            }
            map.put(symbol, end);
        }
        return rsl;
    }

    public static void main(String[] args) {
        String s = "abcbcde";
        System.out.println(longestUniqueSubstring(s));
    }
}