package edu.neu.info6205.sorts;

import edu.neu.info6205.utils.SortUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import static java.util.Arrays.binarySearch;

/** HuskySort implementation as per professor Robin Hillyard's repository on github
 * https://github.com/rchillyard/The-repository-formerly-known-as
 * Implementation modified to accept array of chinese characters & their pinyin equivalents,
 * sort based on pinyin order but perform swap and rearrangements on the original chinese array**/

public class PureHuskySort {

    public static void sort( String[] pinyin, String[] chinese) throws BadHanyuPinyinOutputFormatCombination {
//
        // NOTE: First pass where we code to longs and sort according to those.

        //encode the pinyin array into longs
         long[] longs = huskyEncode(pinyin);


        /*
        use the long arrays to compare and sort, but replicate the swaps onto the chinese input array
        swaps aren't being done on the pinyin array to reduce number of array accesses & swaps
        after encoding of pinyin to long array, pinyin array is abandoned
        */
        introSort(chinese, longs, 0, longs.length, 2 * floor_lg(chinese.length));

        // NOTE: Second pass ; insertion sort to catch any remaining inversions
        insertionSortPostIntro(chinese);
    }

    public static void doSort(String[] chinese){
        //wrapper for testing and benchmarking
        try{
            String[] chineseConv = SortUtils.convertToPinyin(chinese);
            sort(chineseConv, chinese);
        }
        catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination){
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
    }

    public static void insertionSortPostIntro(String[] chinese) throws BadHanyuPinyinOutputFormatCombination{
        //insertion sort to catch any remaining inversions
        //chinese input is being converted to pinyin since sorting is being done in pinyin order
        //conversion needs to be done again since initial pinyin array was abandoned post encoding
        int from = 0;
        String[] pinyin = SortUtils.convertToPinyin(chinese);
        int to = pinyin.length;
        for (int i = from + 1; i < to; i++) {
            int j = i;
            while (j > from && swapStableConditional(pinyin, chinese, j)) j--;
        }
    }

    public static boolean swapStableConditional(String[] pinyin, String[] chinese, int i){
         String v = pinyin[i];
         String w = pinyin[i - 1];
        boolean result = v.compareTo(w) < 0;
        if (result) {
            SortUtils.swap(pinyin, i, i-1);
            SortUtils.swap(chinese, i, i-1);

        }
        return result;
    }


    private static void introSort( String[] objects,  long[] longs,  int from,  int to,  int depthThreshold) {
        if (to - from <= sizeThreshold + 1) {
            insertionSort(objects, longs, from, to);
            return;
        }
        if (depthThreshold == 0) {
            heapSort(objects, longs, from, to);
            return;
        }

        final int hi = to - 1;

        if (longs[hi] < longs[from]) swap(objects, longs, from, hi);

        int lt = from + 1, gt = hi - 1;
        int i = from + 1;
        while (i <= gt) {
            if (longs[i] < longs[from]) swap(objects, longs, lt++, i++);
            else if (longs[hi] < longs[i]) swap(objects, longs, i, gt--);
            else i++;
        }
        swap(objects, longs, from, --lt);
        swap(objects, longs, hi, ++gt);
        introSort(objects, longs, from, lt, depthThreshold - 1);
        if (longs[lt] < longs[gt]) introSort(objects, longs, lt + 1, gt, depthThreshold - 1);
        introSort(objects, longs, gt + 1, hi + 1, depthThreshold - 1);
    }

    static void insertionSort( String[] objects ,  long[] longs,  int from,  int to) {
        for (int i = from + 1; i < to; i++)
            if (OPTIMIZED)
                swapIntoSorted(objects, longs, i);
            else
                for (int j = i; j > from && longs[j] < longs[j - 1]; j--)
                    swap(objects, longs, j, j - 1);
    }

    private static void swapIntoSorted( String[] chinese,  long[] longs,  int i) {
        int j = binarySearch(longs, 0, i, longs[i]);
        if (j < 0) j = -j - 1;
        if (j < i) swapInto(chinese, longs, j, i);
    }

    static void swapInto( String[] chinese,  long[] longs,  int i,  int j) {
        if (j > i) {
             String x = chinese[j];
            System.arraycopy(chinese, i, chinese, i + 1, j - i);
            chinese[i] = x;
             long l = longs[j];
            System.arraycopy(longs, i, longs, i + 1, j - i);
            longs[i] = l;
        }
    }

    private static void swap( String[] chinese,  long[] longs,  int i,  int j) {
        // Swap longs
         long temp1 = longs[i];
        longs[i] = longs[j];
        longs[j] = temp1;
        // Swap chinese
        SortUtils.swap(chinese, i, j);

    }

    private static void heapSort( String[] objects,  long[] longs,  int from,  int to) {
        if (to - from <= sizeThreshold + 1) {
            insertionSort(objects, longs, from, to);
            return;
        }
         int n = to - from;
        for (int i = n / 2; i >= 1; i = i - 1) {
            downHeap(objects, longs, i, n, from);
        }
        for (int i = n; i > 1; i = i - 1) {
            swap(objects, longs, from, from + i - 1);
            downHeap(objects, longs, 1, i - 1, from);
        }
    }

    private static void downHeap( String[] objects,  long[] longs, int i,  int n,  int lo) {
         long d = longs[lo + i - 1];
         String od = objects[lo + i - 1];
        int child;
        while (i <= n / 2) {
            child = 2 * i;
            if (child < n && longs[lo + child - 1] < longs[lo + child]) child++;
            if (d >= longs[lo + child - 1]) break;
            longs[lo + i - 1] = longs[lo + child - 1];
            objects[lo + i - 1] = objects[lo + child - 1];
            i = child;
        }
        longs[lo + i - 1] = d;
        objects[lo + i - 1] = od;
    }

    public static long[] huskyEncode(String[] xs){
        //encode String characters into 64 bit longs
        long[] result = new long[xs.length];
        for(int i = 0; i < xs.length ; i++){
            result[i] = stringToLong(xs[i], MAX_LENGTH_ASCII, BIT_WIDTH_ASCII, MASK_ASCII);
        }
        return result;
    }

    private static long stringToLong( String str,  int maxLength,  int bitWidth,  int mask) {
         int length = Math.min(str.length(), maxLength);
         int padding = maxLength - length;
        long result = 0L;
        if (((mask ^ MASK_SHORT) & MASK_SHORT) == 0)
            for (int i = 0; i < length; i++) result = result << bitWidth | str.charAt(i);
        else
            for (int i = 0; i < length; i++) result = result << bitWidth | str.charAt(i) & mask;
        result = result << bitWidth * padding;
        return result;
    }

    private static int floor_lg(final int a) {
        return (int) (Math.floor(Math.log(a) / Math.log(2)));
    }



    private static final int sizeThreshold = 16;

    private static final boolean OPTIMIZED = false;

    private static final int MASK_SHORT = 0xFFFF;
    private static final int BITS_LONG = 64;
    private static final int BIT_WIDTH_ASCII = 7;
    private static final int MAX_LENGTH_ASCII = BITS_LONG / BIT_WIDTH_ASCII;
    private static final int MASK_ASCII = 0x7F;
}
