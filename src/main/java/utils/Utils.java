package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
    public static String randomString(int length) {
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);

    }
    public static String randomNumber(int length) {
        boolean useLetters = false;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);

    }
    public static int randomInt(int length) {
        boolean useLetters = false;
        boolean useNumbers = true;
        return Integer.parseInt(RandomStringUtils.random(length, useLetters, useNumbers));

    }
}