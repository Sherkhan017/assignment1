public class MergeSorter {

    private static final int CUTOFF = 10;

    private static long comparisons;
    private static int maxDepth;


    public static void sort(int[] array) {
        comparisons = 0;
        maxDepth = 0;

        if (array == null || array.length < 2) {
            return;
        }

        int[] buffer = new int[array.length];

        mergeSort(array, buffer, 0, array.length - 1, 1);
    }


    private static void mergeSort(
            int[] array,
            int[] buffer,
            int left,
            int right,
            int depth) {

        maxDepth = Math.max(maxDepth, depth);


        if (right - left + 1 <= CUTOFF) {

            insertionSort(
                    array,
                    left,
                    right);

            return;
        }


        int middle = (left + right) / 2;

        mergeSort(array, buffer, left, middle, depth + 1);



        mergeSort(array, buffer, middle + 1, right, depth + 1);

        merge(array, buffer, left, middle, right);
    }
    private static void merge(
            int[] array,
            int[] buffer,
            int left,
            int middle,
            int right) {

        int i = left;
        int j = middle + 1;
        int k = left;


        while (i <= middle && j <= right) {

            comparisons++;

            if (array[i] <= array[j]) {

                buffer[k] = array[i];
                i++;

            } else {
                buffer[k] = array[j];
                j++;
            }

            k++;
        }



        while (i <= middle) {
            buffer[k] = array[i];
            i++;
            k++;
        }


        while (j <= right) {
            buffer[k] = array[j];
            j++;
            k++;
        }

        for (int index = left;
             index <= right;
             index++) {

            array[index] = buffer[index];
        }
    }


    private static void insertionSort(
            int[] array,
            int left,
            int right) {

        for (int i = left + 1;
             i <= right;
             i++) {

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

    public static long getComparisons() {

        return comparisons;
    }

    public static int getMaxDepth() {

        return maxDepth;
    }
}