package assignment05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 12/03/2021 - 3:49 PM
 */
public class BinarySearchTreeTest extends BinarySearchTree {
    BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();


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

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        ArrayList<Integer> arr = bst1.toArrayList();
        System.out.println(arr);
        bst1.remove(6);
        arr = bst1.toArrayList();
        System.out.println(arr);
        bst1.remove(19);
        arr = bst1.toArrayList();
        System.out.println(arr);
        bst1.add(19);
        bst1.add(6);
        arr = bst1.toArrayList();
        System.out.println(arr);
    }
}