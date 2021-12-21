package edu.neu.info6205.sorts;

import edu.neu.info6205.utils.SortUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Credits: geeksforgeeks for basic reference that allowed for use case modification
 * https://www.geeksforgeeks.org/dual-pivot-quicksort/
 */
public class DualPivotQuickSort {

    public static void sort(String[] arr, String[] chinese, int low, int high) {
        if (low < high) {
            int[] pivot = partition(arr, chinese, low, high);
            sort(arr, chinese, low, pivot[0] - 1);
            sort(arr, chinese, pivot[0] + 1, pivot[1] - 1);
            sort(arr, chinese, pivot[1] + 1, high);

        }
    }

    public static void doSort(String[] arr) {
        try {
            String[] pinyin = SortUtils.convertToPinyin(arr);
            sort(pinyin, arr, 0, arr.length - 1);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

    }

    public static int[] partition(String[] arr, String[] chinese, int low, int high) {

        if (SortUtils.greaterThan(arr[low], arr[high])) {
            SortUtils.swap(arr, low, high);
            SortUtils.swap(chinese, low, high);
        }

        int j = low + 1;
        int g = high - 1;
        int k = low + 1;
        String p = arr[low];
        String q = arr[high];

        while (k <= g) {
            if (SortUtils.lessThan(arr[k], p)) {
                SortUtils.swap(arr, k, j);
                SortUtils.swap(chinese, k, j);
                j++;
            } else if (SortUtils.greaterThanOrEqualTo(arr[k], q)) {
                while (SortUtils.greaterThan(arr[g], q) && (k < g)) {
                    g--;
                }
                SortUtils.swap(arr, k, g);
                SortUtils.swap(chinese, k, g);
                g--;
                if (SortUtils.lessThan(arr[k], p)) {
                    SortUtils.swap(arr, k, j);
                    SortUtils.swap(chinese, k, j);
                    j++;
                }
            }
            k++;

        }
        j--;
        g++;

        SortUtils.swap(arr, low, j);
        SortUtils.swap(chinese, low, j);
        SortUtils.swap(arr, high, g);
        SortUtils.swap(chinese, high, g);
        return new int[]{j, g};
    }
}
