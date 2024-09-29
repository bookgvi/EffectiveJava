package Parser.utils;

import Parser.dto.TextInfo;
import java.util.Map;
import java.util.List;
import java.util.Optional;

public class TextUtils {

    public TextInfo getLineByIndex(TextInfo textInfo, int index) {
        Map<Integer, String> indexToTextMap = textInfo.getIndexToTextMap();
        List<Integer> indexes = List.copyOf(indexToTextMap.keySet());
        int l = 0, r = indexes.size() - 1;
        int resultIndex = -1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            resultIndex = indexes.get(mid);
            if (index > resultIndex) {
                l = mid + 1;
            } else if (index < resultIndex) {
                r = mid - 1;
            } else {
                break;
            }
        }
        if (resultIndex == -1 && r > 0) {
            resultIndex = indexes.get(r);
        }
        return textInfo.buildForIndex(resultIndex);
    }

}
