package Parser.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class TextInfo {

    private String path;
    private String text;
    private int lineStartIndex = 0;
    private final Map<Integer, String> indexToTextMap = new LinkedHashMap<>();
    private final Map<Integer, Integer> indexToLineMap = new LinkedHashMap<>();

    public TextInfo() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setText(String text) {
        text = Optional.ofNullable(text).orElse("");
        this.text = text;
    }

    public String getText() {
        return Optional.ofNullable(text).orElse("");
    }

    public int getLineStartIndex() {
        return lineStartIndex;
    }

    public void setLineStartIndex(int lineStartIndex) {
        this.lineStartIndex = lineStartIndex;
    }

    public Map<Integer, String> getIndexToTextMap() {
        return indexToTextMap;
    }

    public Map<Integer, Integer> getIndexToLineMap() {
        return indexToLineMap;
    }
    
    public TextInfo buildForIndex(int index) {
        index = Math.max(0, index);
        TextInfo resultTextInfo = new TextInfo();
        String path = this.path;
        resultTextInfo.setPath(path);
        resultTextInfo.setLineStartIndex(index);
        if (indexToLineMap.get(index) != null) {
            resultTextInfo.setText(this.indexToTextMap.get(index));
            resultTextInfo.getIndexToTextMap().put(index, indexToTextMap.get(index));
            resultTextInfo.getIndexToLineMap().put(index, indexToLineMap.get(index));
        }
        return resultTextInfo;
    }
}
