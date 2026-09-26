public class MergeSorter {

    private static final int CUTOFF = 10;

    public static void sort(int[] array) {

        if (array == null || array.length < 2) {
            return;
        }
        int[] buffer = new int[array.length];
        mergeSort(array, buffer, 0, array.length - 1);
    }
    private static void mergeSort(
            int[] array,
            int[] buffer,
            int left,
            int right) {

        if (right - left + 1 <= CUTOFF) {
            insertionSort(array, left, right);
            return;
        }

        int middle = (left + right) / 2;

        mergeSort(array, buffer, left, middle);
        mergeSort(array, buffer, middle + 1, right);

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

            if (array[i] <= array[j]) {
                buffer[k++] = array[i++];
            } else {
                buffer[k++] = array[j++];
            }
        }

        while (i <= middle) {
            buffer[k++] = array[i++];
        }

        while (j <= right) {
            buffer[k++] = array[j++];
        }

        for (i = left; i <= right; i++) {
            array[i] = buffer[i];
        }
    }


    private static void insertionSort(
            int[] array,
            int left,
            int right) {

        for (int i = left + 1; i <= right; i++) {

            int current = array[i];
            int j = i - 1;

            while (j >= left && array[j] > current) {

                array[j + 1] = array[j];

                j--;
            }

            array[j + 1] = current;
        }
    }
}
