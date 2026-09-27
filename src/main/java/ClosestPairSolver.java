import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairSolver {


    public static double findClosest(Point[] points) {

        if (points == null || points.length < 2) {
            return Double.POSITIVE_INFINITY;
        }


        Point[] byX = points.clone();
        Point[] byY = points.clone();


        Arrays.sort(
                byX,
                Comparator.comparingDouble(p -> p.x));


        Arrays.sort(
                byY,
                Comparator.comparingDouble(p -> p.y));


        return closest(byX, byY);
    }


    private static double closest(
            Point[] byX,
            Point[] byY) {


        int n = byX.length;


        if (n <= 3) {

            return bruteForce(byX);
        }


        int middle = n / 2;

        Point middlePoint = byX[middle];


        Point[] leftX =
                Arrays.copyOfRange(
                        byX,
                        0,
                        middle);


        Point[] rightX =
                Arrays.copyOfRange(
                        byX,
                        middle,
                        n);


        Point[] leftY =
                Arrays.stream(byY)
                        .filter(
                                p ->
                                        p.x < middlePoint.x)
                        .toArray(Point[]::new);


        Point[] rightY =
                Arrays.stream(byY)
                        .filter(
                                p ->
                                        p.x >= middlePoint.x)
                        .toArray(Point[]::new);


        double leftDistance =
                closest(leftX, leftY);


        double rightDistance =
                closest(rightX, rightY);


        double distance =
                Math.min(
                        leftDistance,
                        rightDistance);


        Point[] strip =
                Arrays.stream(byY)
                        .filter(
                                p ->
                                        Math.abs(
                                                p.x -
                                                        middlePoint.x)
                                                < distance)
                        .toArray(Point[]::new);


        return Math.min(
                distance,
                stripClosest(strip, distance));
    }


    private static double stripClosest(
            Point[] strip,
            double distance) {


        double min = distance;


        for (int i = 0;
             i < strip.length;
             i++) {


            for (int j = i + 1;
                 j < strip.length &&
                         strip[j].y -
                                 strip[i].y < min;
                 j++) {


                min =
                        Math.min(
                                min,
                                distance(
                                        strip[i],
                                        strip[j]));
            }
        }


        return min;
    }


    private static double bruteForce(
            Point[] points) {


        double min =
                Double.POSITIVE_INFINITY;


        for (int i = 0;
             i < points.length;
             i++) {


            for (int j = i + 1;
                 j < points.length;
                 j++) {


                min =
                        Math.min(
                                min,
                                distance(
                                        points[i],
                                        points[j]));
            }
        }


        return min;
    }


    private static double distance(
            Point a,
            Point b) {


        double dx = a.x - b.x;

        double dy = a.y - b.y;


        return Math.sqrt(
                dx * dx +
                        dy * dy);
    }
}