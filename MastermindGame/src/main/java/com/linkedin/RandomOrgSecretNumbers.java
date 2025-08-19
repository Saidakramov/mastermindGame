package com.linkedin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RandomOrgSecretNumbers {
    public static int[] generateSecretNumbers() {
        try {
            // API recommended parameters
            String apiKey = ConfigLoader.getApiKey();
            int num = 4;
            int min = 0;
            int max = 7;
            int col = 1;
            String base = "10";
            String format = "plain";
            String rnd = "new";

            // Connect to URL
            URL url = new URL("https://api.random.org/json-rpc/4/invoke");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // JSON request body
            JSONObject requestJson = new JSONObject();
            requestJson.put("jsonrpc", "2.0");
            requestJson.put("method", "generateIntegers");

            JSONObject params = new JSONObject();
            params.put("apiKey", apiKey);
            params.put("n", num);
            params.put("min", min);
            params.put("max", max);
            params.put("replacement", true); // duplicates allowed
            requestJson.put("params", params);
            requestJson.put("id", 42);

            // Send JSON
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestJson.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read response
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
            }

            // Parse JSON response
            JSONObject responseJson = new JSONObject(response.toString());
            JSONArray numbersJson = responseJson
                    .getJSONObject("result")
                    .getJSONObject("random")
                    .getJSONArray("data");

            int[] numbers = new int[num];
            for (int i = 0; i < num; i++) {
                numbers[i] = numbersJson.getInt(i);
            }

            return numbers;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
