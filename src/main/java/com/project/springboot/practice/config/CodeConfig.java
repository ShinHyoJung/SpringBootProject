package com.project.springboot.practice.config;

import java.util.Random;

public class CodeConfig {
    public static String makeCode() {
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        return numStr;
    }
}
