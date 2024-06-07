package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Relaxa";
        int age = 24;
        long size = 1024L;
        double price = 99999999999999.9D;
        char symbol = 9999;
        boolean bool = true;
        short sh = 128;
        float f = 1.1F;
        LOG.debug("User info name : {}, age : {}, size : {}, " +
                        "price : {}, symbol : {}, bool : {}, sh : {}, f : {}",
                name, age, size, price, symbol, bool, sh, f);
    }
}