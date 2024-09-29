package Parser.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

public class PatternSearch {

    private final int ABC = 2000;
    private final int MAX_STATES;
    private final int[][] g;
    private final int[] suffixLinks;
    private final int[] out;
    private final String[] keyWords;

    public PatternSearch(String[] keyWords) {
        this.keyWords = Optional.ofNullable(keyWords).orElse(new String[]{});
        this.MAX_STATES = Arrays.stream(this.keyWords)
                .filter(Objects::nonNull)
                .filter(str -> !str.isEmpty())
                .map(String::length)
                .reduce(Integer::sum)
                .orElse(0) + 1;
        this.g = new int[MAX_STATES][ABC];
        this.suffixLinks = new int[MAX_STATES];
        this.out = new int[MAX_STATES];
    }

    private void buildStatesMachine() {
        for (int i = 0; i < MAX_STATES; ++i) {
            Arrays.fill(g[i], -1);
        }
        int states = 1;
        for (int i = 0, wordsCount = keyWords.length; i < wordsCount; ++i) {
            String word = keyWords[i];
            int curState = 0;
            for (int j = 0, wordLen = word.length(); j < wordLen; ++j) {
                int ch = word.charAt(j);
                if (g[curState][ch] == -1) {
                    g[curState][ch] = states++;
                }
                curState = g[curState][ch];
            }
            out[curState] |= (1 << i);
        }

        Arrays.fill(suffixLinks, -1);
        Queue<Integer> q = new LinkedList<>();
        for (int ch = 0; ch < ABC; ++ch) {
            if (g[0][ch] == -1) {
                g[0][ch] = 0;
            } else if (g[0][ch] > 0) {
                suffixLinks[g[0][ch]] = 0;
                q.add(g[0][ch]);
            }
        }

        while(!q.isEmpty()) {
            int state = q.poll();
            for (int ch = 0; ch < ABC; ++ch) {
                if (g[state][ch] == -1) {
                    continue;
                }
                int sufLinkState = suffixLinks[state];
                while (g[sufLinkState][ch] == -1) {
                    sufLinkState = suffixLinks[sufLinkState];
                }
                sufLinkState = g[sufLinkState][ch];
                suffixLinks[g[state][ch]] = sufLinkState;
                q.add(g[state][ch]);

                out[g[state][ch]] |= out[sufLinkState];
            }
        }
    }

    private int getState(int state, int ch) {
        int ans = state;
        while (g[ans][ch] == -1) {
            ans = suffixLinks[ans];
        }
        return g[ans][ch];
    }

    public Map<String, List<Integer>> search(String text) {
        text = Optional.ofNullable(text).orElse("");
        buildStatesMachine();
        int state = 0;
        Map<String, List<Integer>> wordToIndexesMap = new HashMap<>();
        for (int i = 0, textLen = text.length(); i < textLen; ++i) {
            state = getState(state, text.charAt(i));
            if (state == 0) {
                continue;
            }
            for (int j = 0, wordsCount = keyWords.length; j < wordsCount; ++j) {
                if ((out[state] & (1 << j)) > 0) {
                    List<Integer> indexes = wordToIndexesMap.getOrDefault(keyWords[j], new ArrayList<>());
                    indexes.add(i - keyWords[j].length() + 1);
                    wordToIndexesMap.putIfAbsent(keyWords[j], indexes);
                }
            }
        }
        return wordToIndexesMap;
    }
}
