package Parser.utils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class PatternSearchTest {

    @Test
    void searchTest1() {
        String[] words = {"he", "she"};
        String text = "She was a hero";
        PatternSearch patternSearch = new PatternSearch(words);
        Map<String, List<Integer>> res = patternSearch.search(text);

        assertFalse(res.isEmpty());
        assertEquals(2, res.get("he").size());
        assertNull(res.get("she"));
    }
}