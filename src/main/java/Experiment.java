import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Experiment {

    private static final Random random =
            new Random(123);


    public static void main(String[] args)
            throws Exception {


        int[] sizes = {
                100,
                1000,
                10000
        };


        File folder =
                new File("results");

        folder.mkdirs();


        PrintWriter writer =
                new PrintWriter(
                        "results/results.csv");


        writer.println(
                "algorithm,inputType,n,timeNs,maxDepth,comparisons");


        for (int n : sizes) {

            runSortingExperiments(
                    writer,
                    n);
        }


        for (int n : sizes) {

            runSelectExperiment(
                    writer,
                    n);
        }


        int[] pointSizes = {
                100,
                1000,
                5000
        };


        for (int n : pointSizes) {

            runClosestExperiment(
                    writer,
                    n);
        }


        writer.close();


        System.out.println(
                "Experiment finished.");

        System.out.println(
                "CSV saved to results/results.csv");
    }


    private static void runSortingExperiments(
            PrintWriter writer,
            int n) {


        String[] types = {
                "random",
                "sorted",
                "reverse",
                "duplicates"
        };


        for (String type : types) {

            int[] base =
                    createArray(n, type);


            int[] mergeArray =
                    base.clone();


            long start =
                    System.nanoTime();

            MergeSorter.sort(
                    mergeArray);

            long time =
                    System.nanoTime()
                            - start;


            writer.println(
                    "MergeSort," +
                            type + "," +
                            n + "," +
                            time + "," +
                            MergeSorter.getMaxDepth() + "," +
                            MergeSorter.getComparisons());


            int[] quickArray =
                    base.clone();


            start =
                    System.nanoTime();

            QuickSorter.sort(
                    quickArray);

            time =
                    System.nanoTime()
                            - start;


            writer.println(
                    "QuickSort," +
                            type + "," +
                            n + "," +
                            time + "," +
                            QuickSorter.getMaxDepth() + "," +
                            QuickSorter.getComparisons());
        }
    }


    private static void runSelectExperiment(
            PrintWriter writer,
            int n) {


        int[] array =
                createArray(
                        n,
                        "random");


        int k = n / 2;


        long start =
                System.nanoTime();


        DeterministicSelector.select(
                array,
                k);


        long time =
                System.nanoTime()
                        - start;


        writer.println(
                "DeterministicSelect," +
                        "random," +
                        n + "," +
                        time + "," +
                        DeterministicSelector.getMaxDepth() + "," +
                        DeterministicSelector.getComparisons());
    }


    private static void runClosestExperiment(
            PrintWriter writer,
            int n) {


        Point[] points =
                new Point[n];


        for (int i = 0;
             i < n;
             i++) {

            points[i] =
                    new Point(
                            random.nextDouble() * 10000,
                            random.nextDouble() * 10000);
        }


        long start =
                System.nanoTime();


        ClosestPairSolver.findClosest(
                points);


        long time =
                System.nanoTime()
                        - start;


        writer.println(
                "ClosestPair," +
                        "random," +
                        n + "," +
                        time + "," +
                        ClosestPairSolver.getMaxDepth() + "," +
                        ClosestPairSolver.getComparisons());
    }


    private static int[] createArray(
            int n,
            String type) {


        int[] array =
                new int[n];


        if (type.equals("random")) {

            for (int i = 0;
                 i < n;
                 i++) {

                array[i] =
                        random.nextInt(
                                n * 10 + 1);
            }
        }


        else if (type.equals("sorted")) {

            for (int i = 0;
                 i < n;
                 i++) {

                array[i] = i;
            }
        }


        else if (type.equals("reverse")) {

            for (int i = 0;
                 i < n;
                 i++) {

                array[i] =
                        n - i;
            }
        }


        else if (type.equals("duplicates")) {

            for (int i = 0;
                 i < n;
                 i++) {

                array[i] =
                        random.nextInt(5);
            }
        }


        return array;
    }
}