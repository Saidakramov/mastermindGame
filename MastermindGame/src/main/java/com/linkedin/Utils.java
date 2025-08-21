package com.linkedin;

public class Utils {
    public static String arrayToString(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : arr) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }
}
