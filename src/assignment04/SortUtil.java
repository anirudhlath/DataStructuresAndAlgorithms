package assignment04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * The type Sort util.
 *
 * @param <T> the type parameter
 */
public class SortUtil<T> {
    /**
     * The constant threshold.
     */
    public static int threshold = 40;
    /**
     * The constant pivotPosition.
     */
    public static String pivotPosition = "randomPosition";

    /**
     * Mergesort.
     *
     * @param <T>        the type parameter
     * @param arrayList  the array list
     * @param comparator the comparator
     */
    public static <T> void mergesort(ArrayList<T> arrayList, Comparator<? super T> comparator) {
        mergeSortHelper(arrayList, 0, arrayList.size() - 1, comparator);

    }

    /**
     * Merge.
     *
     * @param <T>        the type parameter
     * @param arrayList  the array list
     * @param leftIdx    the left idx
     * @param rightIdx   the right idx
     * @param comparator the comparator
     */
    public static <T> void merge(ArrayList<T> arrayList, int leftIdx, int rightIdx, Comparator<? super T> comparator) {

        ArrayList<T> leftArr = new ArrayList<>();
        ArrayList<T> rightArr = new ArrayList<>();
        /** Create temp array to store the left and right part of the split array.
         *  set the middle index based on the high and low index
         * */
        int midIdx = (leftIdx + rightIdx) / 2;
        int leftArrLength = midIdx - leftIdx + 1;
        int rightArrLength = rightIdx - midIdx;

        /** add the element of the two sorted part of array into the temp array. */
        for (int i = 0; i < leftArrLength; ++i) {
            leftArr.add(arrayList.get(leftIdx + i));
        }
        for (int j = 0; j < rightArrLength; ++j) {
            rightArr.add(arrayList.get(midIdx + 1 + j));
        }
        int i = 0, j = 0;

        int counter = leftIdx;
        while (i < leftArrLength && j < rightArrLength) {
            T leftElement = leftArr.get(i);
            T rightElement = rightArr.get(j);
            /** Switch the the original array with the merge sorted left and right array
             *  up date the i & j counter accordingly */
            if (comparator.compare(leftElement, rightElement) <= 0) {
                arrayList.set( counter, leftElement);
                i++;
            }
            else {
                arrayList.set(counter ,rightElement);
                j++;
            }
            counter++;
        }

        /** If there there are still element left in the left or right array, updated accordingly  */
        while (i < leftArrLength) {
            arrayList.set( counter, leftArr.get(i));
            i++;
            counter++;
        }
        while (j < rightArrLength) {
            arrayList.set(counter ,rightArr.get(j));
            j++;
            counter++;
        }
    }


    /**
     * Merge sort helper.
     *
     * @param <T>        the type parameter
     * @param arr        the arr
     * @param leftIdx    the left idx
     * @param rightIdx   the right idx
     * @param comparator the comparator
     */
    public static <T> void mergeSortHelper(ArrayList<T> arr, int leftIdx, int rightIdx,  Comparator<? super T> comparator) {
        if (leftIdx < rightIdx) {
            /** if the index is valid, get the middle index. */
            int midIdx = leftIdx+ (rightIdx- leftIdx)/2;
            if (rightIdx - leftIdx < threshold) {
                /** if the size of subarray is smaller enough switch to the insertion sort function  */
                insertionSort(arr, leftIdx, rightIdx, comparator);
            } else {
                /** recersively split and merge the sub array based on the index. */
                mergeSortHelper(arr, leftIdx, midIdx, comparator);
                mergeSortHelper(arr, midIdx + 1, rightIdx, comparator);
            }
            /** merge the two left and right semi sorted array. */
            merge(arr, leftIdx, rightIdx, comparator);
        }
    }

    /**
     * Insertion sort.
     *
     * @param <T>        the type parameter
     * @param arrayList  the array list
     * @param lowIdx     the low idx
     * @param highIdx    the high idx
     * @param comparator the comparator
     */
    public static <T> void insertionSort(ArrayList<T> arrayList, int lowIdx, int highIdx, Comparator<? super T> comparator) {
        for (int i = lowIdx + 1; i < highIdx; i++) {
            T currElement = arrayList.get(i);
            /** through every element of the array begin at low 1 as we can consider the first element of the array as a
             trivially sorted region of only one element insertion sort allows us to insert new elements anywhere within the sorted region
             */
            int j;
            for ( j = i - 1; j >= 0 && comparator.compare(currElement, arrayList.get(j)) < 0; j--) {
                /** iterate left through the sorted region, looking for a legal spot to insert currElement
                 */
                arrayList.set(j + 1, arrayList.get(j));
            }
            arrayList.set(j + 1, currElement);
        }
    }

    /**
     * Quicksort.
     *
     * @param <T>        the type parameter
     * @param arrayList  the array list
     * @param comparator the comparator
     */
    public static <T> void quicksort(ArrayList<T> arrayList, Comparator<? super T> comparator) {
        if (arrayList.size() <= 1) return;
        /**
         * Quick sort dirver function.
         * */
        quickSortHelper(arrayList, 0, arrayList.size() - 1, comparator);
    }

    /**
     * Swap helper.
     *
     * @param <T>       the type parameter
     * @param arrayList the array list
     * @param i         the
     * @param j         the j
     */
    public static <T> void swapHelper(ArrayList<T> arrayList, int i, int j) {
        T temp = arrayList.get(i);
        /** Swap the two index's elements */
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j, temp);
    }

    /**
     * Partition int.
     *
     * @param <T>        the type parameter
     * @param arrayList  the array list
     * @param low        the low
     * @param high       the high
     * @param comparator the comparator
     * @return the int
     */
    public static <T> int partition(ArrayList<T> arrayList, int low, int high, Comparator<? super T> comparator) {
        int pivotIdx = getPivotIndex(arrayList, low, high, comparator);
        /** Get the pivot index using the helper function.
         *  Swap the pivot to the right most
         * */
        swapHelper(arrayList, pivotIdx, high);
        T pivotElement = arrayList.get(high);
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            /** if the current element is smaller than pivot element, swap it. */
            if (comparator.compare(arrayList.get(j), pivotElement)< 0) {
                i++;
                swapHelper(arrayList, i, j);
            }
        }
        swapHelper(arrayList, i + 1, high);
        return (i + 1);
    }

    /**
     * Quick sort helper.
     *
     * @param <T>        the type parameter
     * @param arrayList  the array list
     * @param low        the low
     * @param high       the high
     * @param comparator the comparator
     */
    public static <T> void quickSortHelper(ArrayList<T> arrayList, int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pi = partition(arrayList, low, high, comparator);
            /** Partition based on the pivot index. */
            quickSortHelper(arrayList, low, pi - 1, comparator);
            quickSortHelper(arrayList, pi + 1, high, comparator);
        }
    }


    /**
     * Gets pivot index.
     *
     * @param <T>        the type parameter
     * @param arrayList  the array list
     * @param low        the low
     * @param high       the high
     * @param comparator the comparator
     * @return the pivot index
     */
    public static <T> int getPivotIndex(ArrayList<T> arrayList, int low, int high, Comparator<? super T> comparator) {
        switch (pivotPosition) {
            case "randomPosition":
                return new Random().nextInt(high - low) + low;
            case "middlePosition":
                return (low + high) / 2;
            default:
                return high;
        }


    }

    /**
     * Generate best case array list.
     *
     * @param size the size
     * @return the array list
     */
    public static ArrayList<Integer> generateBestCase(int size) {
        ArrayList<Integer> newArr = new ArrayList<Integer>();
        for (int num = 0; num < size; num++) {
            newArr.add(num);
        }
        return newArr;
    }


    /**
     * Generate average case array list.
     *
     * @param size the size
     * @return the array list
     */
    public static ArrayList<Integer> generateAverageCase(int size) {
        ArrayList<Integer> newArr = new ArrayList<Integer>();
        for (int idx = 0; idx < size; idx++) {
            int num = (int) Math.random() * size;
            newArr.add(num);
        }
        return newArr;
    }

    /**
     * Generate worst case array list.
     *
     * @param size the size
     * @return the array list
     */
    public static ArrayList<Integer> generateWorstCase(int size) {
        ArrayList<Integer> newArr = new ArrayList<Integer>();
        for (; size > 0; size--) {
            newArr.add(size);
        }
        return newArr;
    }
}
