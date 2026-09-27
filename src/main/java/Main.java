import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // MERGE SORT
        int[] mergeArray = {7, 3, 5, 2, 9, 1, 8, 4, 6, 10, 15, 12};
        System.out.println(" MERGE SORT");
        System.out.println("Before: " + Arrays.toString(mergeArray));
        MergeSorter.sort(mergeArray);
        System.out.println("After: " + Arrays.toString(mergeArray));
        System.out.println("Comparisons: " + MergeSorter.getComparisons());
        System.out.println("Max depth: " + MergeSorter.getMaxDepth());
        System.out.println();

        // QUICK SORT
        int[] quickArray = {8, 2, 6, 1, 5, 3, 9, 7, 4, 10};
        System.out.println(" QUICK SORT ");
        System.out.println("Before: " + Arrays.toString(quickArray));
        QuickSorter.sort(quickArray);
        System.out.println("After: " + Arrays.toString(quickArray));
        System.out.println("Comparisons: " + QuickSorter.getComparisons());
        System.out.println("Max depth: " + QuickSorter.getMaxDepth());
        System.out.println();


        int[] selectArray = {7, 2, 9, 1, 5, 8, 3, 6, 4};
        int k = 4;
        int result = DeterministicSelector.select(selectArray, k);

        System.out.println(" DETERMINISTIC SELECT");
        System.out.println("k = " + k);
        System.out.println("Result: " + result);
        System.out.println("Comparisons: " + DeterministicSelector.getComparisons());
        System.out.println("Max depth: " + DeterministicSelector.getMaxDepth());
        System.out.println();

        Point[] points = {

                new Point(1, 1),

                new Point(2, 2),

                new Point(10, 10),

                new Point(5, 5),

                new Point(8, 4),

                new Point(7, 3)
        };


        double closest = ClosestPairSolver.findClosest(points);


        System.out.println("CLOSEST PAIR");
        System.out.println("Closest distance: " + closest);
        System.out.println("Distance comparisons: " + ClosestPairSolver.getComparisons());
        System.out.println("Max depth: " + ClosestPairSolver.getMaxDepth());
    }
}