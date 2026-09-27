import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ClosestPairSolver {

    private static long comparisons;
    private static int maxDepth;

    public static double findClosest(Point[] points) {

        comparisons = 0;
        maxDepth = 0;

        if (points == null ||
                points.length < 2) {

            return Double.POSITIVE_INFINITY;
        }

        Point[] byX = points.clone();

        Point[] byY = points.clone();

        Arrays.sort(byX, Comparator.comparingDouble(
                        p -> p.x));


        Arrays.sort(byY, Comparator.comparingDouble(p -> p.y));


        return closest(byX, byY, 1);
    }


    private static double closest(Point[] byX, Point[] byY, int depth) {

        maxDepth = Math.max(maxDepth, depth);
        int n = byX.length;

        if (n <= 3) {
            return bruteForce(
                    byX);
        }

        int middle = n / 2;
        Point middlePoint = byX[middle];
        Point[] leftX = Arrays.copyOfRange(byX, 0, middle);


        Point[] rightX = Arrays.copyOfRange(byX, middle, n);

        Set<Point> leftPoints = new HashSet<>(Arrays.asList(leftX));
        Point[] leftY = new Point[leftX.length];


        Point[] rightY = new Point[rightX.length];
        int leftIndex = 0;
        int rightIndex = 0;
        for (Point point : byY) {

            if (leftPoints.contains(point)) {

                leftY[leftIndex] = point;

                leftIndex++;

            } else {
                rightY[rightIndex] = point;
                rightIndex++;
            }
        }

        double leftDistance = closest(leftX, leftY, depth + 1);


        double rightDistance = closest(rightX, rightY, depth + 1);
        double distance = Math.min(leftDistance, rightDistance);

        Point[] strip = new Point[n];
        int stripSize = 0;
        for (Point point : byY) {
            if (Math.abs(point.x - middlePoint.x) < distance) {
                strip[stripSize] =
                        point;

                stripSize++;
            }
        }
        double stripDistance = stripClosest(strip, stripSize, distance);


        return Math.min(distance, stripDistance);
    }
    private static double stripClosest(
            Point[] strip,
            int size,
            double distance) {

        double min = distance;

        for (int i = 0; i < size; i++) {


            for (int j = i + 1; j < size && strip[j].y - strip[i].y < min; j++) {
                double currentDistance = distance(
                                strip[i],
                                strip[j]);
                if (currentDistance < min) {

                    min =
                            currentDistance;
                }
            }
        }


        return min;
    }


    private static double bruteForce(Point[] points) {

        double min = Double.POSITIVE_INFINITY;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1;
                 j < points.length;
                 j++) {
                double currentDistance = distance(points[i], points[j]);
                if (currentDistance < min) {
                    min = currentDistance;
                }
            }
        }
        return min;
    }
    private static double distance(
            Point a,
            Point b) {

        comparisons++;
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public static long getComparisons() {
        return comparisons;
    }
    public static int getMaxDepth() {
        return maxDepth;
    }
}