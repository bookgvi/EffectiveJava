package Parser.utils;

import Parser.dto.TextInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    // Retrieves text information from a valid file path
    @Test
    public void retrieves_text_from_valid_file_path() {
        FileUtils fileUtils = new FileUtils();
        String validFilePath = "src/Parser/utils/PatternSearch.java";
        TextInfo textInfo = fileUtils.getTextFromFile(validFilePath);

        assertNotNull(textInfo);
        assertEquals(validFilePath, textInfo.getPath());
        assertFalse(textInfo.getText().isEmpty());
    }

    // File does not exist at the given path
    @Test
    public void file_does_not_exist_at_given_path() {
        FileUtils fileUtils = new FileUtils();
        String invalidFilePath = "src/test/resources/nonExistentFile.txt";
        TextInfo textInfo = fileUtils.getTextFromFile(invalidFilePath);

        assertNotNull(textInfo);
        assertEquals(invalidFilePath, textInfo.getPath());
        assertTrue(textInfo.getText().isEmpty());
    }
}