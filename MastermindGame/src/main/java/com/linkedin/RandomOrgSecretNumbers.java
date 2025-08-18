package com.linkedin;

import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.net.HttpURLConnection;
import java.net.URL;

public class RandomOrgSecretNumbers {
    public static void main(String[] args) {
        try {
            // API recommended parameters
            int num = 4;
            int min = 0;
            int max = 7;
            int col = 1;
            String base = "10";
            String format = "plain";
            String rnd = "new";

            // Construct URL
            String apiUrl = String.format(
                    "https://www.random.org/integers/?num=%d&min=%d&max=%d&col=%d&base=%s&format=%s&rnd=%s",
                    num, min, max, col, base, format, rnd);

            // Connect to URL
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            int[] numbers = new int[num];
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                for (int i =0; i < num; i++ ) {
                    String line = br.readLine();
                    numbers[i] = Integer.parseInt(line.trim());
                }
            }

            // Print numbers
            System.out.println("Numbers are:");
            for (int n : numbers) {
                System.out.print(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
