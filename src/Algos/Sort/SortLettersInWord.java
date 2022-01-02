package Algos.Sort;

public class SortLettersInWord {
    private static int comparator(char i, char j) {
        return i - j;
    }

    private static String swap(int i, int j, String word) {
        return word.substring(0, i) + word.charAt(j) + word.substring(i + 1, j) + word.charAt(i) + word.substring(j + 1);
    }

    private static String sort(String word) {
        return insertSort(word);
    }

    private static String insertSort(String word) {
        String res = "";
        for (int i = 0, len = word.length(); i < len; i += 1)
            for (int j = i; j > 0 && comparator(word.charAt(j - 1), word.charAt(j)) > 0; j -= 1)
                word = swap(j - 1, j, word);
        return word;
    }

    public static void main(String[] args) {
        String word = "hello, world!!!";
        String sorted = sort(word);
    }
}
