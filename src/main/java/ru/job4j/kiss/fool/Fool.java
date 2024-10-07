package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static String checkWord(int number) {
        String word = String.valueOf(number);
        if (number % 3 == 0 && number % 5 == 0) {
            word = "FizzBuzz";
        } else if (number % 3 == 0) {
            word = "Fizz";
        } else if (number % 5 == 0) {
            word = "Buzz";
        }
        return word;
    }

    public static int computeAnswer(int num, String word) {
        String correct = checkWord(num);
        num = isCorrect(correct, word) ? num + 1 : 0;
        return num;
    }

    public static boolean isCorrect(String correct, String word) {
        boolean result = correct.equals(word);
        if (!result) {
            System.out.println("Ошибка. Начинай снова.");
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(checkWord(startAt));
            startAt++;
            var answer = input.nextLine();
            startAt = computeAnswer(startAt, answer);
        }
    }
}
