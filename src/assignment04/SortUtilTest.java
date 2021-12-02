package assignment04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The type Sort util test.
 *
 * @param <T> the type parameter
 */
public class SortUtilTest<T> {
    /**
     * The constant fw.
     */
    public static FileWriter fw;


    /**
     * The constant testingSize.
     */
    public static int testingSize = 10;
    /**
     * The constant diff.
     */
    public static int diff = 5;
    /**
     * The Int quick sort time.
     */
    long[] intQuickSortTime = new long[testingSize];
    /**
     * The Int merge sort time.
     */
    long[] intMergeSortTime = new long[testingSize];

    /**
     * The Str quick sort time.
     */
    long[] strQuickSortTime = new long[testingSize];
    /**
     * The Str merge sort time.
     */
    long[] strMergeSortTime = new long[testingSize];

    /**
     * The Timing table.
     */
    Map<String, Map<String, Map<String, long[]>>> timingTable = new HashMap<>();
    /**
     * The Testing data.
     */
    Map<String, ArrayList<Integer>[]> testingData = new HashMap<>();

    /**
     * The constant sortingMethods1.
     */
    public static String[] sortingMethods1 = new String[] {"quickSort", "insertionSort", "mergeSort"};
    /**
     * The constant pivotPositions2.
     */
    public static String[] pivotPositions2 = new String[] {"middlePosition", "randomPosition", "defaultPosition"};
    /**
     * The constant dataCases3.
     */
    public static String[] dataCases3 = new String[] {"bestCase", "averageCase", "worstCase"};


    /**
     * The constant intComparator.
     */
    public static Comparator<Integer> intComparator = new Comparator<>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };

    /**
     * The constant strComparator.
     */
    public static Comparator<String> strComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };

    /**
     * The Testing ints arr.
     */
    ArrayList<Integer>[] testingIntsArr = new ArrayList[testingSize];
    /**
     * The Testing str arr.
     */
    ArrayList<String>[] testingStrArr = new ArrayList[testingSize];

    /**
     * Instantiates a new Sort util test.
     *
     * @throws IOException the io exception
     */
    public SortUtilTest() throws IOException {
    }

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        try {
            fw = new FileWriter(new File("SortingUtil.tsv"));
            fw.write("+++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String sortingMethod : sortingMethods1) {
            Map<String, Map<String, long[]>> sortingMap = new HashMap<>();
            for (String pivotPosition : pivotPositions2) {
                Map<String,  long[]> pivotMap = new HashMap<>();
                for (String dataCase : dataCases3) {
                    pivotMap.put(dataCase, new long[testingSize]);
                }
                sortingMap.put(pivotPosition, pivotMap);
            }
            timingTable.put(sortingMethod, sortingMap);
        }

        testingData.put(dataCases3[0], new ArrayList[testingSize]);
        testingData.put(dataCases3[1], new ArrayList[testingSize]);
        testingData.put(dataCases3[2], new ArrayList[testingSize]);
        for (int exp = diff; exp < testingSize + diff; exp++) {
            int size = (int) Math.pow(2, exp);
            testingData.get(dataCases3[0])[exp-diff] = SortUtil.generateBestCase(size);
            testingData.get(dataCases3[1])[exp-diff] = SortUtil.generateAverageCase(size);
            testingData.get(dataCases3[2])[exp-diff] = SortUtil.generateWorstCase(size);
        }
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }
    //========================================


    /**
     * Threshold picking.
     */
    @Test
    public void thresholdPicking() {
        SortUtil.pivotPosition = "defaultPosition";
            for (int threshold = 0; threshold <= 50; threshold+= 10) {
                SortUtil.threshold = threshold;
                for (int idx = 0; idx < 10; idx++) {
                    ArrayList<Integer> tempArr = new ArrayList<>(testingData.get("averageCase")[idx]);
                    long totalTime = 0;
                    for (int time = 0; time < 1000; time++) {
                        long startTime = System.nanoTime();
                        SortUtil.mergesort(tempArr, intComparator);
                        long endTime = System.nanoTime();
                        totalTime += (endTime - startTime);
                }
                System.out.println(threshold + "\t" +  tempArr.size() + "\t" +   totalTime/1000);
            }
        }
    }


    /**
     * Mergesort.
     *
     * @throws IOException the io exception
     */
    @Test
    public void mergesort() throws IOException {
            for (String testingCase : dataCases3) {
                System.out.println("Merge Sort => " + testingCase);
                for (int idx = 0; idx < testingSize; idx++) {
                    long totalTime = 0;
                    ArrayList<Integer> tempArr = new ArrayList<>(testingData.get(testingCase)[idx]);
                    for (int time = 0; time < 1000; time++) {
                        long startTime = System.nanoTime();
                        SortUtil.mergesort(tempArr, intComparator);
                        long endTime = System.nanoTime();
                        totalTime += (endTime - startTime);
                    }
                    System.out.println(tempArr.size() + "\t" + (totalTime / 1000));
                }
            }
    }

    /**
     * Insertion sort.
     */
    @Test
    public void insertionSort() {
        for (String pivotPosition : pivotPositions2) {
            SortUtil.pivotPosition = pivotPosition;
            for (String testingCase : dataCases3) {
                System.out.println("Insertion Sort => " + SortUtil.pivotPosition + " => " + testingCase);
                for (int idx = 0; idx < testingSize; idx++) {
                    long totalTime = 0;
                    ArrayList<Integer> tempArr = new ArrayList<>(testingData.get(testingCase)[idx]);
                    for (int time = 0; time < 50; time++) {
                        long startTime = System.nanoTime();
                        SortUtil.insertionSort(tempArr, 1, tempArr.size(), intComparator);
                        long endTime = System.nanoTime();
                        totalTime += (endTime - startTime);
                    }
                    System.out.println(tempArr.size() + "\t" + (totalTime / 50));
                }
            }
        }
    }

    /**
     * Quicksort.
     */
    @Test
    public void quicksort() {
        for (String pivotPosition : pivotPositions2) {
            SortUtil.pivotPosition = pivotPosition;
            for (String testingCase : dataCases3) {
                System.out.println("Quick Sort => " + SortUtil.pivotPosition + " => " + testingCase);
                for (int idx = 0; idx < testingSize; idx++) {
                    long totalTime = 0;
                    ArrayList<Integer> tempArr = new ArrayList<>(testingData.get(testingCase)[idx]);
                    for (int time = 0; time < 100; time++) {
                        long startTime = System.nanoTime();
                        SortUtil.quicksort(tempArr, intComparator);
                        long endTime = System.nanoTime();
                        totalTime += (endTime - startTime);
                    }
                    System.out.println(tempArr.size() + "\t" + (totalTime / 100));
                }
            }
        }
    }

    /**
     * Insertion sort 2.
     */
    @Test
    public void insertionSort2() {
        ArrayList<Integer> arrint = generateInt(20);
        ArrayList<Integer> tempInt = new ArrayList<>(arrint);

        ArrayList<String> arrStr = generateString(10);
        ArrayList<String> tempStr = new ArrayList<>(arrStr);

        printArr(arrint);
        SortUtil.insertionSort(tempInt, 0, tempInt.size(), intComparator);
        printArr(tempInt);
        SortUtil.quicksort(arrint,intComparator);
        printArr(arrint);
    }


    /**
     * Generate int array list.
     *
     * @param end the end
     * @return the array list
     */
    public static ArrayList<Integer> generateInt( int end) {
        ArrayList<Integer> newArr = new ArrayList<>();
        for (int idx = 0; idx < end; idx++) {
            newArr.add((int) (Math.random() * end));
        }
        return newArr;
    }

    /**
     * Generate string array list.
     *
     * @param numWords the num words
     * @return the array list
     */
    public static ArrayList<String> generateString( int numWords) {
        String chars = "qwertyuioplkjhgfdsazxcvbnm";
        ArrayList<String> res = new ArrayList<>();
        for (int idx = 0; idx < numWords; idx++) {
            int length = new Random().nextInt(10);
            StringBuilder newStr = new StringBuilder();
            if (length > 0) {
                for (int charIdx = 0; charIdx < length; charIdx++) {
                    int newCharIdx = (int) (Math.random() * 100 % 26);
                    newStr.append(chars.charAt(newCharIdx));
                }
                res.add(new String(newStr));
            }
        }
        return res;
    }

    /**
     * Print arr.
     *
     * @param <T> the type parameter
     * @param arr the arr
     */
    public static <T> void printArr(ArrayList<T> arr) {
        for (T obj : arr) {
            System.out.print(obj + ", ");
        }
        System.out.println();
    }

    /**
     * Assert sorted boolean.
     *
     * @param <T>        the type parameter
     * @param arrayList  the array list
     * @param comparator the comparator
     * @return the boolean
     */
    public static <T> boolean assertSorted(ArrayList<T> arrayList, Comparator<? super T> comparator) {
        for (int idx = 1; idx < arrayList.size(); idx++) {
            T prevElement = arrayList.get(idx - 1);
            T currElement = arrayList.get(idx);
            if (comparator.compare(currElement, prevElement) < 0) return false;
        }
        return true;
    }


    /**
     * Generate before testing.
     */
    public static void generateBeforeTesting() {
        for (int exp = 10; exp < testingSize + 10; exp++) {
            int size = (int) Math.pow(2, exp);
//            testingIntsArr[exp-10] = generateInt(size);
//            testingStrArr[exp-10] = generateString(size);
        }
    }
}