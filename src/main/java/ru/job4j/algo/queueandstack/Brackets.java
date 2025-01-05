package ru.job4j.algo.queueandstack;

import java.util.*;

public class Brackets {

    public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (Character ch : s.toCharArray()) {
            if (ch == '(') {
                deque.addLast(')');
            } else if (ch == '[') {
                deque.addLast(']');
            } else if (ch == '{') {
                deque.addLast('}');
            } else if (deque.isEmpty() || deque.pollLast() != ch) {
                return false;
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Brackets().isValid("(]"));
    }
}
