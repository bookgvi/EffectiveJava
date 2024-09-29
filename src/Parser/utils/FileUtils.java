package Parser.utils;

import Parser.dto.TextInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class FileUtils {

    /**
     * Retrieves text information from a file specified by the given path.
     *
     * @param path the path to the file from which text information is retrieved
     * @return a TextInfo object containing the processed text information
     */
    public TextInfo getTextFromFile(String path) {
        TextInfo res = new TextInfo();
        Path filePath = Paths.get(path);
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            res = buildTextInfo(bufferedReader.lines());
        } catch (IOException ignored) {
            // ignore
        }
        res.setPath(path);
        return res;
    }

    /**
     * Builds a TextInfo object based on the provided lines of text.
     *
     * @param lines a Stream of strings representing lines of text
     * @return a TextInfo object containing the processed text information
     */
    public TextInfo buildTextInfo(Stream<String> lines) {
        TextInfo res = new TextInfo();
        StringBuilder sb = new StringBuilder();
        final int[] index = {0};
        final int[] lineNum = {1};
        Map<Integer, String> indexToTextMap = res.getIndexToTextMap();
        Map<Integer, Integer> indexToLineMap = res.getIndexToLineMap();
        lines.forEach(line -> {
            line = line.trim();
            sb.append(line);
            indexToTextMap.put(index[0], line);
            indexToLineMap.put(index[0], lineNum[0]);
            index[0] += line.length();
            lineNum[0] += 1;
        });
        res.setText(sb.toString());
        return res;
    }

}
