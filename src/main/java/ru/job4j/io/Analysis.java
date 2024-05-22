package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean flag = false;
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                if (("400".equals(strings[0]) || "500".equals(strings[0])) != flag) {
                    writer.write(strings[1]);
                    writer.write(';');
                    flag = !flag;
                    if (!flag) {
                        writer.write("\n");
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
