package ru.job4j.codirovka;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        Scanner scanner = new Scanner(System.in);
        boolean blockChat = false;
        boolean run = true;

        while (run) {
            String answer = answers.get((int) (Math.random() * answers.size()));
            String quest = scanner.nextLine();
            log.add(quest);
            if (quest.equals(OUT)) {
                run = false;
            }
            if (quest.equals(STOP)) {
                blockChat = true;
            }
            if (quest.equals(CONTINUE)) {
                blockChat = false;
            }
            if (!blockChat && run) {
                System.out.println(answer);
                log.add(answer);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            phrases = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }
    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8), false)) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chatLog.txt", "data/botAnswers.txt");
        consoleChat.run();
    }
}
