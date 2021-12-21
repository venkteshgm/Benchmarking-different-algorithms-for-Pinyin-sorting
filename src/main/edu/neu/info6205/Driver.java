package edu.neu.info6205;

import edu.neu.info6205.sorts.*;
import edu.neu.info6205.utils.BenchmarkTarget;
import edu.neu.info6205.utils.SortUtils;

import java.util.Arrays;
import java.util.function.Supplier;

public class Driver {
    public static void main(String[] args) {
        {
            // Dual Pivot quick sort
            System.out.println("Dual Pivot Quick Sort Benchmark\n");
            for (int i = 0; i < SortUtils.filesSourceArray.length; i++) {
                String[] chinese = SortUtils.readFromFile(SortUtils.filesSourceArray[i]);
                long n = chinese.length;
                Supplier supplier = () -> Arrays.copyOf(chinese, chinese.length);
                BenchmarkTarget.benchmarkTarget(supplier, DualPivotQuickSort::doSort, 1, n, "Dual Pivot Quick Sort");
            }
        }
        {
            // MSD Radix Sort
            System.out.println("\nMSD Radix Sort Benchmark\n");
            for(int i=0; i< SortUtils.filesSourceArray.length; i++){
                String[] chinese = SortUtils.readFromFile(SortUtils.filesSourceArray[i]);
                long n = chinese.length;
                Supplier supplier = ()-> Arrays.copyOf(chinese, chinese.length);
                BenchmarkTarget.benchmarkTarget(supplier, MSDRadixSort::doSort, 1, n, "MSD Radix Sort");
             }
        }
        {
            // LSD Radix Sort
            System.out.println("\nLSD Radix Sort Benchmark\n");
            for(int i=0; i< SortUtils.filesSourceArray.length; i++){
                String[] chinese = SortUtils.readFromFile(SortUtils.filesSourceArray[i]);
                long n = chinese.length;
                Supplier supplier = ()-> Arrays.copyOf(chinese, chinese.length);
                BenchmarkTarget.benchmarkTarget(supplier, LSDRadixSort::doSort, 1, n, "LSD Radix Sort");
            }
        }
        {
            // Tim Sort
            System.out.println("\nTim Sort Benchmark\n");
            for(int i=0; i< SortUtils.filesSourceArray.length; i++){
                String[] chinese = SortUtils.readFromFile(SortUtils.filesSourceArray[i]);
                long n = chinese.length;
                Supplier supplier = ()-> Arrays.copyOf(chinese, chinese.length);
                BenchmarkTarget.benchmarkTarget(supplier, TimSort::doSort, 1, n, "Tim sort");
            }
        }
        {
            // Husky Sort
            System.out.println("\nHusky Sort Benchmark\n");
            for(int i=0; i< SortUtils.filesSourceArray.length; i++){
                String[] chinese = SortUtils.readFromFile(SortUtils.filesSourceArray[i]);
                long n = chinese.length;
                Supplier supplier = ()-> Arrays.copyOf(chinese, chinese.length);
                BenchmarkTarget.benchmarkTarget(supplier, PureHuskySort::doSort, 1, n, "Husky Sort");
            }
        }
        {
            // Enhanced MSD Radix Sort (MSDRadixSort with cutoff to insertion sort)
            System.out.println("\nMSDRadixSort with cutoff to insertion sort Benchmark\n");
            for(int i=0; i< SortUtils.filesSourceArray.length; i++){
                String[] chinese = SortUtils.readFromFile(SortUtils.filesSourceArray[i]);
                long n = chinese.length;
                Supplier supplier = ()-> Arrays.copyOf(chinese, chinese.length);
                BenchmarkTarget.benchmarkTarget(supplier, MSDRadixSortWithCutoff::doSort, 1, n, "Enhanced MSD Radix Sort");
            }
        }
    }
}
