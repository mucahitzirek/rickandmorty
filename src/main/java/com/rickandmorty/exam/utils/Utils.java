package com.rickandmorty.exam.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public static JSONObject GET_API(String urlRead) throws IOException, ParseException {
        StringBuilder stringBuilder = new StringBuilder();
        URL url = new URL(urlRead);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonpObject = (JSONObject) jsonParser.parse(stringBuilder.toString());
        return jsonpObject;
    }

}
