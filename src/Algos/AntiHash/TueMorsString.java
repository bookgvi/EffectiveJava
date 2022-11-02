package Algos.AntiHash;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TueMorsString {
    private static final String firstChar = "A";
    private static int firstCharByte = "A".getBytes(StandardCharsets.UTF_8)[0];
    private static Map<Integer, String> dummyMap = new HashMap<>();
    private static List<Integer> dummyList = new ArrayList<>();
    private static StringBuilder dummySB = new StringBuilder();
}
