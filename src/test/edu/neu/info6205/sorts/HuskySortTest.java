package edu.neu.info6205.sorts;

import edu.neu.info6205.utils.SortUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class HuskySortTest {
    @Test
    public void testSort() {
        try {
            String[] chinese = Arrays.copyOf(SortUtils.chineseExample, SortUtils.chineseExample.length);
            String[] chineseExampleSortedTranslation = SortUtils.convertToPinyin(SortUtils.chineseExampleSorted);
            PureHuskySort.doSort(chinese);
            String[] chineseTranslated = SortUtils.convertToPinyin(chinese);
            for (int i = 0; i < chinese.length; i++) {
                assertEquals(chineseTranslated[i], chineseExampleSortedTranslation[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFileSortTest() {
        try {
            String[] chinese = SortUtils.readFromFile("src/resources/chineseExample.txt");
            String[] chineseExampleSortedTranslation = SortUtils.convertToPinyin(SortUtils.chineseExampleSorted);
            assertEquals(chinese.length, SortUtils.chineseExample.length);
            PureHuskySort.doSort(chinese);
            String[] chineseTranslated = SortUtils.convertToPinyin(chinese);
            for (int i = 0; i < chinese.length; i++) {
                assertEquals(chineseTranslated[i], chineseExampleSortedTranslation[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
    }
}
