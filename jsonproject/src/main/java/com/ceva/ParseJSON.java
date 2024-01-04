package com.ceva;

import java.io.StringReader;
import javax.json.Json;
import javax.json.stream.JsonParser;
public class ParseJSON {
    public static void main(String[] args) {
        String result = "{\"name\":\"Fulano\",\"age\":21,\"boolean\":true}";

        JsonParser parser = Json.createParser(new StringReader(result));
        String key = null;
        String value = null;
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    key = parser.getString();
                    System.out.println(key);
                    break;
                case VALUE_STRING:
                    String string = parser.getString();
                    System.out.println(string);
                    break;
                case VALUE_NUMBER:
                    int number = parser.getInt();
                    System.out.println(number);
                    break;
                case VALUE_TRUE:
                    System.out.println(true);
                    break;
                case VALUE_FALSE:
                    System.out.println(false);
                    break;
            }
        }
        parser.close();
    }
}
