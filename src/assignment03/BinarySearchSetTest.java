package assignment03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BinarySearchSetTest/* extends BinarySearchSet*/ {

    BinarySearchSet<Integer> arr1 = new BinarySearchSet<>();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1() {
        Random rand = new Random();
        int max = 20;
        int min = -20;
        for (int i = 0; i < 1000; i++) {
            arr1.add(rand.nextInt(max - min) + min);
            System.out.println(arr1.getSize());
        }

    }

}