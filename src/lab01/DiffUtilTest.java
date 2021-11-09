package lab01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class DiffUtilTest extends DiffUtil {

    private int[] arr1, arr2, arr3, arr4, arr5, arr6, arr7;

    @Before
    public void setUp() throws Exception { // Why is the access modifier protected in the example provided on the assignment page?
        arr1 = new int[0];
        arr2 = new int[] { 3, 3, 3 };
        arr3 = new int[] { 52, 4, -8, 0, -17 };
        arr4 = new int[] { 0, 1, 2, 3, 4, 5 };
        arr5 = new int[] { 0, -1, -2, -3, -4, -5 };
        arr6 = new int[] { 926, 943, 880, 636, 71, 861, 671, 136, 502, 638, 326, 912, 321, 218, 472 };
        arr7 = new int[] { 22, -469, 417, 78, 61, 879, 911, 390, -904, 912, 656, -712, 614, 871, 198 };
    }

    @Test
    public void emptyArray() {
        assertEquals(-1, DiffUtil.findSmallestDiff(arr1));
    }

    @Test
    public void allArrayElementsEqual() {
        assertEquals(0, DiffUtil.findSmallestDiff(arr2));
    }

    @Test
    public void smallRandomArrayElements() {
        assertEquals(4, DiffUtil.findSmallestDiff(arr3));
    }

    @Test
    public void smallPositiveSortedArrayElements() {
        assertEquals(1, DiffUtil.findSmallestDiff(arr4));
    }

    @Test
    public void smallNegativeSortedArrayElements() {
        assertEquals(1, DiffUtil.findSmallestDiff(arr5));
    }

    @Test
    public void largePositiveRandomArrayElements() {
        assertEquals(2, DiffUtil.findSmallestDiff(arr6));
    }

    @Test
    public void largeRandomArrayElements() {
        assertEquals(1, DiffUtil.findSmallestDiff(arr7));
    }

    @After
    public void tearDown() throws Exception {
        arr1 = null;
        arr2 = null;
        arr3 = null;
        arr4 = null;
        arr5 = null;
        arr6 = null;
        arr7 = null;
    }
}