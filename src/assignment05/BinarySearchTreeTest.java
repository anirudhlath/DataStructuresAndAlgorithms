package assignment05;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Binary search tree test.
 *
 * @author Anirudh Lath
 * @project CS6012
 * @created 12 /03/2021 - 3:49 PM
 */
public class BinarySearchTreeTest extends BinarySearchTree {
    /**
     * The Bst 1.
     */
    BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        bst1.add(5);
        bst1.add(1);
        bst1.add(2);
        bst1.add(3);
        bst1.add(4);
        bst1.add(6);
        bst1.add(7);
        bst1.add(8);
        bst1.add(9);
        bst1.add(-9);
        bst1.add(19);
        bst1.add(18);
        bst1.add(29);
        bst1.add(27);
        bst1.add(-29);
        bst1.add(139);


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
     * Generic test.
     */
    @Test
    public void genericTest() {

        // first() Test
        Assert.assertEquals((Integer) (-29), bst1.first());

        // last() Test
        Assert.assertEquals((Integer) (139), bst1.last());

        // isEmpty() Test
        Assert.assertFalse(bst1.isEmpty());

        // toArrayList() Test
        ArrayList<Integer> arr = bst1.toArrayList();
        for (int i = 0; i < bst1.size() - 1; i++) {
            if (arr.get(i).compareTo(arr.get(i + 1)) >= 0 || arr.get(i) == null) {
                Assert.assertFalse(true);
            }
        }

        // clear() Test
        bst1.clear();
        if (!bst1.isEmpty()) {
           Assert.assertFalse(true);
        }

    }

    /**
     * Addition test.
     */
    @Test
    public void additionTest() {

        // add() Test
        Assert.assertTrue(bst1.add(1000));
        Assert.assertFalse(bst1.add(1000));
        Assert.assertEquals(17, bst1.size());

        // addAll() Test
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            arr.add(random.nextInt(100));
        }
        Assert.assertTrue(bst1.addAll(arr));
        Assert.assertFalse(bst1.addAll(arr));


    }

    /**
     * Contains test.
     */
    @Test
    public void containsTest() {
        // contains() Test
        Assert.assertTrue(bst1.contains(139));
        Assert.assertFalse(bst1.contains(-139));

        // containsAll() Test
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            arr.add(random.nextInt(100));
        }
        Assert.assertFalse(bst1.containsAll(arr));
        bst1.addAll(arr);
        Assert.assertTrue(bst1.containsAll(arr));

    }

    /**
     * Remove test.
     */
    @Test
    public void removeTest() {

        // remove() Test
        Assert.assertTrue(bst1.remove(139)); // Leaf Node
        Assert.assertTrue(bst1.remove(19)); // Has 2 childs
        Assert.assertTrue(bst1.remove(8)); // Has one child
        Assert.assertFalse(bst1.remove(139));
        Assert.assertFalse(bst1.remove(19));
        Assert.assertFalse(bst1.remove(8));
        Assert.assertEquals(13, bst1.size());

        // removeAll() Test
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            arr.add(random.nextInt(100));
        }
        Assert.assertTrue(bst1.removeAll(arr));
        Assert.assertFalse(bst1.removeAll(arr));


    }
}