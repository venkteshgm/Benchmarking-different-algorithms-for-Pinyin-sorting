## The project structure is split up into main(Sources root), resources(Resources root), test(Test root) and "report and paper" with Driver.java as the main class.

### The "sorts" package consists of:
1) DualPivotQuickSort.java which uses pinyin4j for preprocessing and performs dual pivot quick sort on the chinese array.
2) MSDRadixSort.java which makes of pinyin4j library to convert chinese text to pinyin and then sorts the pinyin array whilst also sorting the original chinese text array
3) TimSort.java which sorts the chinese array post conversion to pinyin
4) LSRadixSort.java which sorts the pinyin array post conversion
5) MSDRadixSortWithCutoff.java which is an enhancement to MSDRadixSort with a cutoff to Insertion sort
6) PureHuskySort.java which sorts pinyin array in Dual Pivot QuickSort format but uses hashed long versions to compare and sort

### The "utils" package consists of:
1) Benchmark utilities (Benchmark, Benchmark_Timer, BenchmarkTarget, Timer) to be able to measure the running times of the sort algorithms (Credits: Info6205 assignments repo)
2) SortUtils.java that houses functions for compares, swapping, stubs for unit tests, file reader and Chinese to pinyin converter
3) Logger utils to log and format text displayed for benchmarks

### The "resources" directory consists of:
1) The chinese text files containing data sets for sort (Courtesy of Prof. Robin Hillyard) and reproduced for the benchmark sizes of 250k, 500k, 1M, 2M and 4M
2) log4j.properties file for the configuration of the logger
3) chineseExample.txt for unit tests
4) sortedArraySamples which consists of a part of shuffledChinese.txt (1500 words) in sorted order which has been sorted using our implementation of the MSDRadix, LSDRadix, DPQuick, Tim, PureHusky and MSDRadix with cutoff sort algorithms (respective suffixes added)

### The "test" directory consists of:
1) Tests for all the sort mechanisms (with the inclusion of partition test for DualPivotQuickSort)
2) Tests for the benchmark utility (Credits: info6205 assignments repo)

### The "report and paper" directory consists of:
1) Final_project_Report.pdf which contains the report of our findings in this project
2) PSA_Final_Paper_LiteratureSurvey.pdf which contains the literature survey of the 3 papers read by the team along with the work we have done in relation to the papers

### Additional libraries used to complete the project:
1) pinyin4j
2) ini4j
3) log4j
4) junit

### Credits: In addition to links provided in each file
1) Prof. Robin Hillyard and contributors of the Info6205 assignments & huskysort repositories (https://github.com/rchillyard/INFO6205 ; https://github.com/rchillyard/The-repository-formerly-known-as)
2) pinyin4j (http://pinyin4j.sourceforge.net/)
3) log4j (https://logging.apache.org/log4j/2.x/)
4) ini4j (http://ini4j.sourceforge.net/)
5) stackoverflow and geeksforgeeks community
