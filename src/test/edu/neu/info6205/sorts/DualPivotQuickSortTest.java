package edu.neu.info6205.sorts;

import edu.neu.info6205.utils.SortUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests the basic sort functionality, partition test as well as the file reader and sort
 */
public class DualPivotQuickSortTest {
    @Test
    public void testSort(){
        try{
            String[] chinese = Arrays.copyOf(SortUtils.chineseExample,SortUtils.chineseExample.length);
            String[] chineseExampleSortedTranslation = SortUtils.convertToPinyin(SortUtils.chineseExampleSorted);
            DualPivotQuickSort.doSort(chinese);
            String[] chineseTranslated = SortUtils.convertToPinyin(chinese);
            for (int i = 0; i < chinese.length; i++) {
                assertEquals(chineseTranslated[i], chineseExampleSortedTranslation[i]);
            }
        }
        catch (BadHanyuPinyinOutputFormatCombination e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPartition(){
        try{
            String[] chinese = Arrays.copyOf(SortUtils.chineseExample,SortUtils.chineseExample.length);
            String[] arr = SortUtils.convertToPinyin(chinese);
            int[] pivot;
            pivot = DualPivotQuickSort.partition(arr, chinese, 0, chinese.length-1);
            assertEquals(0,pivot[0]);
            assertEquals(6, pivot[1]);
            pivot = DualPivotQuickSort.partition(arr, chinese, 1, 5);
            assertEquals(2,pivot[0]);
            assertEquals(5, pivot[1]);
            pivot = DualPivotQuickSort.partition(arr, chinese, 3, 4);
            assertEquals(3,pivot[0]);
            assertEquals(4, pivot[1]);
            pivot = DualPivotQuickSort.partition(arr, chinese, 7, 8);
            assertEquals(7,pivot[0]);
            assertEquals(8, pivot[1]);
        }catch (BadHanyuPinyinOutputFormatCombination e){
            e.printStackTrace();
        }

    }

    @Test
    public void readFileSortTest() {
        try{
            String[] chinese = SortUtils.readFromFile("src/resources/chineseExample.txt");
            String[] chineseExampleSortedTranslation = SortUtils.convertToPinyin(SortUtils.chineseExampleSorted);
            assertEquals(chinese.length,SortUtils.chineseExample.length);
            DualPivotQuickSort.doSort(chinese);
            String[] chineseTranslated = SortUtils.convertToPinyin(chinese);
            for (int i = 0; i < chinese.length; i++) {
                assertEquals(chineseTranslated[i], chineseExampleSortedTranslation[i]);
            }
        }catch (BadHanyuPinyinOutputFormatCombination e){
            e.printStackTrace();
        }

    }
}
