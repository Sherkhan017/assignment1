import java.util.Arrays;
public class Main {

    public static void main(String[] args) {

        int[] mergeArray = {
                7, 3, 5, 2, 9, 1,
                8, 4, 6, 10, 15, 12
        };

        System.out.println("MERGE SORT");
        System.out.println("Before: " + Arrays.toString(mergeArray));
        MergeSorter.sort(mergeArray);
        System.out.println("After:  " + Arrays.toString(mergeArray));
        System.out.println("Comparisons: " + MergeSorter.getComparisons());
        System.out.println("Max recursion depth: " + MergeSorter.getMaxDepth());
        System.out.println();



        int[] quickArray = {
                8, 2, 6, 1, 5, 3,
                9, 7, 4, 10
        };


        System.out.println("QUICK SORT");
        System.out.println("Before: " + Arrays.toString(quickArray));
        QuickSorter.sort(quickArray);
        System.out.println("After:  " + Arrays.toString(quickArray));
        System.out.println("Comparisons: " + QuickSorter.getComparisons());
        System.out.println("Max recursion depth: " + QuickSorter.getMaxDepth());
    }
}