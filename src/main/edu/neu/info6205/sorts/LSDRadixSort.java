package edu.neu.info6205.sorts;

import edu.neu.info6205.utils.SortUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Credits: Stackoverflow community
 * https://stackoverflow.com/questions/37619532/how-to-use-lsd-string-sort-without-having-to-enter-a-fixed-length/45905320
 */
public class LSDRadixSort {
        private static int R=65536;
        private static String[] aux;
        private static String[] aux1;

        public static void sort(String[] a, String[] chinese)  {
            int n = a.length;
            aux = new String[n];
            aux1 = new String[n];
            int w = longestString(a);
            sort(a, chinese, aux, aux1, a.length-1, w);
        }
    public static void doSort(String[] chinese) {
        try {
            String[] chineseConv = SortUtils.convertToPinyin(chinese);
            sort(chineseConv, chinese);
        }
        catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }

    }

        private static int charFind(int i, int d, String[] a)
        {
            if (d < 0 || d >= a[i].length())
            {
                return 0;
            }
            return a[i].charAt(d);
        }

        private static int longestString(String[] a)
        {
            int longest = 0;
            for (int i = 0; i < a.length; ++i)
            {
                if (a[i].length() > longest)
                {
                    longest = a[i].length();
                }
            }
            return longest;
        }
        private static void sort(String[] a, String[] chinese, String[] aux, String[] aux1, int n,int w) {

            for (int d = w - 1; d >= 0; d--)
            {
                int[] count = new int[R + 1];
                for (int i = 0; i < n+1; ++i)
                {
                    int c = charFind(i, d, a);
                    count[c + 1]++;
                }
                for (int r = 0; r < R; ++r)
                {
                    count[r + 1] += count[r];
                }
                for (int i = 0; i < n+1; ++i)
                {
                    int c = charFind(i, d, a);
                    aux1[count[c]] = chinese[i];
                    aux[count[c]++] = a[i];
                }
                for (int i = 0; i < n+1; ++i)
                {
                    a[i] = aux[i];
                    chinese[i]=aux1[i];
                }
            }
        }
    }