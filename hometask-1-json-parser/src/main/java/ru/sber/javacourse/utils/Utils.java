package ru.sber.javacourse.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    static private final String jsonFieldPattern = "^\s+\"([A-Za-z0-9_]+?)\":\s+\"?(.+?)\"?,?$";

    public static void parseJson(String text, JsonFieldProcessor jpf) {
        Pattern pattern = Pattern.compile(jsonFieldPattern);

        text.lines().forEach(line -> {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                jpf.doProcessing(matcher.group(1), matcher.group(2));
            }
        });
    }
}
