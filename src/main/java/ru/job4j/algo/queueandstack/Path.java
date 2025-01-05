package ru.job4j.algo.queueandstack;

import java.util.Stack;

public class Path {

    public static String simplify(String path) {
        Stack<String> stack = new Stack<>();
        var components = path.split("/");
        for (var component : components) {
            if (component.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!component.isEmpty() && !component.equals(".") && !component.equals("..")) {
                stack.push(component);
            }
        }
        var result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        return !result.isEmpty() ? result.toString() : "/";
    }

    public static void main(String[] args) {
        System.out.println(simplify("/.../a/../b/c/../d/./"));
    }
}
