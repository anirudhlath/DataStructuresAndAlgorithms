package assignment03;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Binary search set test.
 */
public class BinarySearchSetTest/* extends BinarySearchSet*/ {

    /**
     * The Arr 1.
     */
    BinarySearchSet<Integer> arr1 = new BinarySearchSet<>();

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        arr1.add(1);
        arr1.add(3);

    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Addition test.
     */
    @Test
    public void additionTest() {
        // Test Add
        Assert.assertTrue(arr1.add(2));
        Assert.assertTrue(arr1.add(5));
        Assert.assertTrue(arr1.add(-1));
        Assert.assertFalse(arr1.add(1));
        Assert.assertFalse(arr1.add(3));
        Assert.assertEquals(5, arr1.size());
        Object[] arr = new Integer[] {-1,1,2,3,5};
        if (arr.length == arr1.size()) {
            for (int i = 0; i < arr.length; i++) {
                if (!Integer.valueOf((Integer) arr1.toArray()[i]).equals(Integer.valueOf((Integer) arr[i]))) {
                    Assert.assertEquals(1, 0);
                }
            }
        } else {
            Assert.assertEquals(0,1);
        }

        // Test Add All
        ArrayList<Integer> set1 = new ArrayList<>();
        set1.add(-1);
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(5);

        Assert.assertFalse(arr1.addAll(set1));

        set1.add(-11);
        set1.add(12);
        set1.add(22);
        set1.add(23);
        set1.add(53);

        Assert.assertTrue(arr1.addAll(set1));
        Assert.assertEquals(10, arr1.size());

        arr = new Integer[] {-11, -1, 1, 2, 3, 5, 12, 22, 23, 53};
        if (arr.length == arr1.size()) {
            for (int i = 0; i < arr.length; i++) {
                if (!Integer.valueOf((Integer) arr1.toArray()[i]).equals(Integer.valueOf((Integer) arr[i]))) {
                    Assert.assertEquals(1, 0);
                }
            }
        } else {
            Assert.assertEquals(0,1);
        }

    }

    /**
     * Contains test.
     */
    @Test
    public void containsTest() {
        // Test Contains
        Assert.assertTrue(arr1.contains(1));
        Assert.assertFalse(arr1.contains(5));

        // Test Contains All
        ArrayList<Integer> set1 = new ArrayList<>();
        set1.add(-1);
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(5);
        set1.add(-11);
        set1.add(12);
        set1.add(22);
        set1.add(23);
        set1.add(53);
        arr1.addAll(set1);
        Assert.assertTrue(arr1.containsAll(set1));
        set1.add(554);
        Assert.assertFalse(arr1.containsAll(set1));


    }

    /**
     * Removes test.
     */
    @Test
    public void removesTest() {
        // Test Remove
        Assert.assertTrue(arr1.remove(1));
        Assert.assertEquals(1, arr1.size());
        Assert.assertFalse(arr1.remove(5));


        // Test Remove All
        ArrayList<Integer> set1 = new ArrayList<>();
        set1.add(-1);
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(5);
        set1.add(-11);
        set1.add(12);
        set1.add(22);
        set1.add(23);
        set1.add(53);

        Assert.assertTrue(arr1.removeAll(set1));
        Assert.assertEquals(0, arr1.size());
    }

    /**
     * Generic test.
     */
    @Test
    public void genericTest() {
        // Test Comparator
        Assert.assertEquals(null, arr1.comparator());

        // Test Size
        Assert.assertEquals(2, arr1.size());

        // Test First
        Assert.assertEquals((Integer) 1, arr1.first());

        // Test Last
        Assert.assertEquals((Integer) 3, arr1.last());

        // Test Clear and isEmpty
        arr1.clear();
        Assert.assertTrue(arr1.isEmpty());

        // Test toArray
        Random random = new Random();
        int min = -50000;
        int max = 50000;
        for (int i = 0; i < 100000; i++) {
            arr1.add(random.nextInt(max - min) + min);
        }

        Object[] arr = arr1.toArray();
        for (int i = 0; i < arr1.size() - 1; i++) {
            if (Integer.valueOf((Integer) arr[i]) >= (Integer.valueOf((Integer) arr[i + 1]))) {
                Assert.assertEquals(0 , 1);
            }
        }




    }

}