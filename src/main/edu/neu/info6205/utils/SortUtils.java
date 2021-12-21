package edu.neu.info6205.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.lang.reflect.Array;
import java.text.CollationKey;
import java.text.Collator;
import java.util.*;
import java.util.function.Function;

/**
 * Credits: pinyin4j
 * http://pinyin4j.sourceforge.net/
 */
public class SortUtils {

    private static Collator coll = Collator.getInstance(Locale.CHINA);

    /**
     *
     * @param a the original Chinese string array
     * @return String array of pinyin conversion from the original chinese array
     * @throws BadHanyuPinyinOutputFormatCombination if format mismatch is present
     */
    public static String[] convertToPinyin(String[] a) throws BadHanyuPinyinOutputFormatCombination {
        String[] temp = new String[a.length];
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        long length = a.length;
        for(int i =0; i< length; i++){
            String s = a[i];
            String tempString ="";
            for(int j=0; j<s.length();j++){
                String[] pinArr = PinyinHelper.toHanyuPinyinStringArray(s.charAt(j), format);
                tempString = tempString.concat(pinArr[0]);
            }
            temp[i] = tempString;
        }
        return temp;
    }

    public static String[] chineseExample ={
            "刘持平",
            "洪文胜",
            "樊辉辉",
            "苏会敏",
            "高民政",
            "曹玉德",
            "袁继鹏",
            "阿鼎",
            "阿"
    };
    public static void swap(String[] arr, int i, int j){
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Comparators
     * @param s1 the first string in the comparison
     * @param s2 the second string to compare with
     * @return boolean of compare condition
     */
    public static boolean lessThan(String s1, String s2){
        return (s1.compareTo(s2)<0);
    }
    public static boolean greaterThan(String s1, String s2){
        return (s1.compareTo(s2)>0);
    }
    public static boolean greaterThanOrEqualTo(String s1, String s2){
        return (s1.compareTo(s2)>=0);
    }
    public static boolean lessThanOrEqualTo(String s1, String s2){
        return (s2.compareTo(s2)<=0);
    }

    public static <T> T[] asArray(Collection<T> ts) {
        if (ts.isEmpty()) throw new RuntimeException("ts may not be empty");
        @SuppressWarnings("unchecked") T[] result = (T[]) Array.newInstance(ts.iterator().next().getClass(), 0);
        return ts.toArray(result);
    }

    /**
     * Credits: info6205 assignments repo
     * Create a string representing an double, with three decimal places.
     *
     * @param x the number to show.
     * @return a String representing the number rounded to three decimal places.
     */
    public static String formatDecimal3Places(double x) {
        double scaleFactor = 1000.0;
        return String.format("%.3f", round(x * scaleFactor) / scaleFactor);
    }

    /**
     * Credits: info6205 assignments repo
     * Create a string representing an integer, with commas to separate thousands.
     *
     * @param x the integer.
     * @return a String representing the number with commas.
     */
    public static String formatWhole(int x) {
        return String.format("%,d", x);
    }

    public static String asInt(double x) {
        final int i = round(x);
        return formatWhole(i);
    }

    public static int round(double x) {
        return (int) (Math.round(x));
    }

    public static <T> T[] fillRandomArray(Class<T> clazz, Random random, int n, Function<Random, T> f) {
        @SuppressWarnings("unchecked") T[] result = (T[]) Array.newInstance(clazz, n);
        for (int i = 0; i < n; i++) result[i] = f.apply(random);
        return result;
    }

    /**
     * Credits: info6205 assignments repo
     * Method to calculate log to tbe base 2 of n.
     *
     * @param n the number whose log we need.
     * @return lg n.
     */
    public static double lg(double n) {
        return Math.log(n) / Math.log(2);
    }

    public static String[] readFromFile(String filename){
        String[] arr;
        try{
            Scanner sc = new Scanner(new File(filename));
            List<String> lines = new ArrayList<String>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            arr = lines.toArray(new String[0]);
            return  arr;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String[] chineseExampleSorted = {
            "阿",
            "阿鼎",
            "曹玉德",
            "樊辉辉",
            "高民政",
            "洪文胜",
            "刘持平",
            "苏会敏",
            "袁继鹏",
    };

    public static String[] filesSourceArray = {
            "src/resources/shuffledChinese_250k.txt",
            "src/resources/shuffledChinese_500K.txt",
            "src/resources/shuffledChinese.txt",
            "src/resources/shuffledChinese_2M.txt",
            "src/resources/shuffledChinese_4M.txt"
    };
    public static void writeToFile(String[] arr) {
        File fout = new File("src/resources/sortedArraySample.txt");
        int n = 0;
        if(arr.length<=1500){
            n = arr.length;
        }
        else{
            n=1500;
        }
        try (FileOutputStream fos = new FileOutputStream(fout); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));) {
            for (int i = 0; i <n; i++) {
                bw.write(arr[i]);
                bw.newLine();
            }
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }
    public static int MSD_CUTOFF = 16;
}