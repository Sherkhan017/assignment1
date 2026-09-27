public class DeterministicSelector {

    private static long comparisons;
    private static int maxDepth;
    public static int select(int[] array, int k) {

        comparisons = 0;
        maxDepth = 0;

        if (array == null ||
                array.length == 0 ||
                k < 0 ||
                k >= array.length) {

            throw new IllegalArgumentException(
                    "Invalid array or k");
        }

        return select(array, 0, array.length - 1, k, 1);
    }
    private static int select(int[] array, int left, int right, int k, int depth) {

        maxDepth = Math.max(maxDepth, depth);


        // Base case
        if (left == right) {
            return array[left];
        }
        int pivot = medianOfMedians(array, left, right, depth);

        int[] bounds = partition(array, left, right, pivot);

        if (k < bounds[0]) {
            return select(array, left, bounds[0] - 1, k, depth + 1);
        }

        else if (k > bounds[1]) {

            return select(array, bounds[1] + 1, right, k, depth + 1);
        }

        else {
            return pivot;
        }
    }

    private static int medianOfMedians(
            int[] array,
            int left,
            int right,
            int depth) {

        int size = right - left + 1;

        if (size <= 5) {
            insertionSort(
                    array,
                    left,
                    right);

            return array[
                    left + size / 2
                    ];
        }


        int medianCount = 0;

        for (int start = left;
             start <= right;
             start += 5) {

            int end = Math.min(start + 4, right);

            insertionSort(array, start, end);
            int medianIndex = start + (end - start) / 2;

            swap(array, left + medianCount, medianIndex);
            medianCount++;
        }

        int targetMedian =
                left + medianCount / 2;

        return select(
                array,
                left,
                left + medianCount - 1,
                targetMedian,
                depth + 1);
    }


    private static int[] partition(
            int[] array,
            int left,
            int right,
            int pivot) {

        int smaller = left;
        int current = left;
        int greater = right;


        while (current <= greater) {
            comparisons++;
            if (array[current] < pivot) {

                swap(
                        array,
                        smaller,
                        current);

                smaller++;
                current++;
            }

            else {
                comparisons++;
                if (array[current] > pivot) {

                    swap(
                            array,
                            current,
                            greater);

                    greater--;
                }
                else {

                    current++;
                }
            }
        }

        return new int[]{
                smaller,
                greater
        };
    }
    private static void insertionSort(
            int[] array,
            int left,
            int right) {

        for (int i = left + 1; i <= right; i++) {
            int current = array[i];

            int j = i - 1;
            while (j >= left) {
                comparisons++;
                if (array[j] <= current) {
                    break;
                }
                array[j + 1] = array[j];
                j--;
            }


            array[j + 1] = current;
        }
    }
    private static void swap(
            int[] array,
            int i,
            int j) {
        int temp = array[i];

        array[i] = array[j];
        array[j] = temp;
    }
    public static long getComparisons() {
        return comparisons;
    }


    public static int getMaxDepth() {
        return maxDepth;
    }
}