package Algos.String;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Префиксная функция
 * */
public class PIFunction {
    public static void main(String[] args) {
        String str = "abcaaabcabcd";
        System.out.println(Arrays.toString(piFunction(str)));
        System.out.println(Arrays.toString(piFunctionExt(str)));

        String text = "это разделитель, который не должен нигде более встречаться. Посчитаем для префикс. Теперь рассмотрим её значения, кроме первых\n" +
                "(которые, как видно, относятся к строке   и разделителю). По определению, значение\n" +
                "показывает наидлиннейшую длину подстроки, оканчивающейся в позиции   и совпадающего с префиксом. Но в\n" +
                "нашем случае это   — фактически длина наибольшего блока совпадения со строкой   и оканчивающегося в позиции\n" +
                "  . Больше, чем   , эта длина быть не может — за счёт разделителя. А вот равенство\n" +
                "оно достигается), означает, что в позиции   оканчивается искомое вхождение строки     (только не надо забывать, что";
        String strToFind = "префикс";
        String delimiter = "#";

        String prepText = (strToFind + delimiter + text);
        int[] pi = piFunctionExt(prepText);
        int[] indexes = IntStream.range(0, pi.length).filter(index -> pi[index] == strToFind.length())
                .map(index -> index - strToFind.length() * 2 + 1).toArray();
        System.out.printf("Count = %d,\n %s", indexes.length, Arrays.toString(indexes));
    }

    private static int[] piFunction(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 0; i < len; i += 1) {
            for (int j = 0; j < i; j += 1) {
                String ss1 = str.substring(0, j + 1).intern();
                String ss2 = str.substring(i - j, i + 1).intern();
                if (ss1 == ss2) pi[i] = j + 1;
            }
        }
        return pi;
    }

    private static int[] piFunctionExt(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int j = pi[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) j = pi[j - 1];
            if (str.charAt(i) == str.charAt(j)) j += 1;
            pi[i] = j;
        }
        return pi;
    }
}
