import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmCorrectnessTest {
    @Test
    void testMergeSort() {
        checkMerge(new int[]{5, 2, 8, 1, 3});
        checkMerge(new int[]{1, 2, 3, 4, 5});
        checkMerge(new int[]{5, 4, 3, 2, 1});
        checkMerge(new int[]{3, 3, 1, 1, 2, 2});
        checkMerge(new int[]{});
        checkMerge(new int[]{5});}

    @Test
    void testQuickSort() {
        checkQuick(new int[]{5, 2, 8, 1, 3});
        checkQuick(new int[]{1, 2, 3, 4, 5});
        checkQuick(new int[]{5, 4, 3, 2, 1});
        checkQuick(new int[]{3, 3, 1, 1, 2, 2});
        checkQuick(new int[]{});
        checkQuick(new int[]{5});}

    private void checkMerge(int[] array) {
        int[] expected = array.clone();
        Arrays.sort(expected);
        int[] actual = array.clone();
        MergeSorter.sort(actual);

        assertArrayEquals(expected, actual);
    }
    private void checkQuick(int[] array) {
        int[] expected = array.clone();
        Arrays.sort(expected);
        int[] actual = array.clone();
        QuickSorter.sort(actual);
        assertArrayEquals(expected, actual);
    }
    @Test
    void testDeterministicSelect() {
        Random random = new Random(123);
        for (int test = 0; test < 100; test++) {
            int n = 20 + random.nextInt(80);
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt(1000);
            }
            int k = random.nextInt(n);
            int[] expected = array.clone();
            Arrays.sort(expected);

            int actual = DeterministicSelector.select(array.clone(), k);
            assertEquals(expected[k], actual);
        }
    }
    @Test
    void testClosestPair() {
        Random random = new Random(123);
        for (int test = 0; test < 20; test++) {
            int n = 100;
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(random.nextDouble() * 1000, random.nextDouble() * 1000);
            }
            double expected = bruteForce(points);
            double actual = ClosestPairSolver.findClosest(points);
            assertEquals(expected, actual, 0.000001);
        }
    }
    private double bruteForce(Point[] points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                min = Math.min(min, distance);
            }
        }
        return min;
    }
}