package edu.neu.info6205.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BenchmarkTarget {
    public static void benchmarkTarget( Supplier supplier, Consumer<String[]> c, Integer runNumber, Long count, String description){

        final Benchmark<String[]> benchmark = new Benchmark_Timer<>(
                description+": " + (runNumber)+" for " + count + " Strings",
                c
        );
        System.out.println("Completed in "+benchmark.runFromSupplier(supplier, 1)+ " ms");
    }
}
