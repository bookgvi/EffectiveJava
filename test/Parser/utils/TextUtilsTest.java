package Parser.utils;

import Parser.dto.TextInfo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextUtilsTest {

    // Retrieve line text correctly when index exists
    @Test
    public void test_retrieve_line_text_when_index_exists() {
        TextInfo textInfo = new TextInfo();
        textInfo.setPath("sample/path");
        textInfo.setText("Line1;Line2;Line3;");
        int index0 = 0;
        textInfo.getIndexToTextMap().put(index0, "Line1;");
        textInfo.getIndexToLineMap().put(index0, 0);
        int index1 = index0 + textInfo.getIndexToTextMap().get(index0).length();
        textInfo.getIndexToTextMap().put(index1, "Line2;");
        textInfo.getIndexToLineMap().put(index1, 1);
        int index2 = index1 + textInfo.getIndexToTextMap().get(index1).length();
        textInfo.getIndexToTextMap().put(index2, "Line3;");
        textInfo.getIndexToLineMap().put(index2, 2);

        TextUtils textUtils = new TextUtils();
        TextInfo result = textUtils.getLineByIndex(textInfo, index1);

        assertEquals("Line2;", result.getText());
        assertEquals("sample/path", result.getPath());
        assertEquals(index1, result.getLineStartIndex());
    }

    // Handle empty indexToTextMap gracefully
    @Test
    public void test_handle_empty_index_to_text_map() {
        TextInfo textInfo = new TextInfo();
        textInfo.setPath("sample/path");
        textInfo.setText("");

        TextUtils textUtils = new TextUtils();
        TextInfo result = textUtils.getLineByIndex(textInfo, 0);

        assertEquals("", result.getText());
        assertEquals("sample/path", result.getPath());
        assertEquals(0, result.getLineStartIndex());
    }

    @Test
    void getLineByIndexTest1() {
        FileUtils fileUtils = new FileUtils();
        String validFilePath = "src/Parser/utils/PatternSearch.java";
        TextInfo textInfo = fileUtils.getTextFromFile(validFilePath);

        PatternSearch patternSearch = new PatternSearch(new String[]{"class"});
        Map<String, List<Integer>> wordToIndexesMap = patternSearch.search(textInfo.getText());

        List<TextInfo> result = new ArrayList<>();
        TextUtils textUtils = new TextUtils();
        for (List<Integer> indexes : wordToIndexesMap.values()) {
            for (int index : indexes) {
                result.add(textUtils.getLineByIndex(textInfo, index));
            }
        }

        assertFalse(result.isEmpty());
    }
}