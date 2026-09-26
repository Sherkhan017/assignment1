import java.util.Random;
public class QuickSorter {

    private static final Random random = new Random();
    public static void sort(int[] array) {

        if (array == null || array.length < 2) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }


    private static void quickSort(
            int[] array,
            int low,
            int high) {

        while (low < high) {
            int pivotIndex =
                    low + random.nextInt(high - low + 1);
            int pivotPosition =
                    partition(array, low, high, pivotIndex);
            if (pivotPosition - low < high - pivotPosition) {
                quickSort(array, low, pivotPosition - 1);
                low = pivotPosition + 1;
            } else {
                quickSort(array, pivotPosition + 1, high);
                high = pivotPosition - 1;
            }
        }
    }
    private static int partition(
            int[] array,
            int low,
            int high,
            int pivotIndex) {
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, high);
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }

        swap(array, i, high);

        return i;
    }
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
