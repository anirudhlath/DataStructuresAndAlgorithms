package lab05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 11/22/2021 - 11:46 PM
 */
public class DoublyLinkedListTest {
    DoublyLinkedList<Integer> list = new DoublyLinkedList();



    @Before
    public void setUp() throws Exception {
        list.addFirst(1);
        list.addLast(3);
        list.add(1, 2);

    }

    @Test
    public void additionTest() {
        // Test addFirst() Method
        list.addFirst(0);
        if (list.toArray()[0] == (Object) 0 && list.size() == 4 ) {
            Assert.assertEquals(1,1);
        } else {
            Assert.assertEquals(1, 0);
        }

        // Test addLast() Method
        list.addLast(0);
        if (list.toArray()[list.size() - 1] == (Object) 0 && list.size() == 5) {
            Assert.assertEquals(1,1);
        } else {
            System.out.println(list.size());
            System.out.println(list.toArray()[list.size() - 1]);
            Assert.assertEquals(1, 0);
        }

        // Test add() Method
        list.add(2,0);
        if (list.toArray()[2] == (Object) 0 && list.size() == 6) {
            Assert.assertEquals(1,1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    @Test
    public void getTest() {
        // Test getFirst() Method
        Assert.assertEquals((Integer) 1, list.getFirst());

        // Test getLast() Method
        Assert.assertEquals((Integer) 3, list.getLast());

        // Test get() Method
        Assert.assertEquals((Integer) 2, list.get(1));

    }

    @Test
    public void removeTest() {
        // Test removeFirst() Method
        Assert.assertEquals((Integer) 1, list.removeFirst());
        if (list.toArray()[0] != (Object) 1 && list.size() == 2) {
            Assert.assertEquals(1,1);
        } else {
            Assert.assertEquals(1, 0);
        }

        // Test removeLast() Method
        Assert.assertEquals((Integer) 3, list.removeLast());
        if (list.toArray()[list.size() - 1] != (Object) 3 && list.size() == 1) {
            Assert.assertEquals(1,1);
        } else {
            Assert.assertEquals(1, 0);
        }

        // Test remove() Method in case the size of the list == 1
        Assert.assertEquals((Integer) 2, list.remove(0));
        if (list.size() == 0) {
            Assert.assertEquals(1,1);
        } else {
            Assert.assertEquals(1, 0);
        }

        // Test remove() Method in case the size of the list == 2
        list.addFirst(1);
        list.addLast(3);
        Assert.assertEquals((Integer) 1, list.remove(0));
        if (list.toArray()[0] != (Object) 1 && list.size() == 1) {
            Assert.assertEquals(1,1);
        } else {
            Assert.assertEquals(1, 0);
        }

        // Test remove() Method in case the size of the list == 3 and removing the node at index 1.
        list.addFirst(1);
        list.addFirst(1);
        Assert.assertEquals((Integer) 1, list.remove(1));
        if (list.toArray()[1] != (Object) 1 && list.size() == 2) {
            Assert.assertEquals(1,1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void NoSuchElementTest() {
        list.clear();
        list.getFirst();
        list.getLast();
        list.removeFirst();
        list.removeLast();
        if (list.size() != 0) {
            Assert.assertEquals(0,1);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void IndexOutOfBoundsExceptionTest() {

        list.add(-1, 2);
        if (list.size() != 3) {
            Assert.assertEquals(0,1);
        }

        list.add(4, 2);
        if (list.size() != 3) {
            Assert.assertEquals(0,1);
        }

        list.get(-1);
        list.get(3);

        list.remove(-1);
        if (list.size() != 3) {
            Assert.assertEquals(0,1);
        }

        list.remove(3);
        if (list.size() != 3) {
            Assert.assertEquals(0,1);
        }
    }

    @Test
    public void genericTest() {
        // Test indexOf() Method
        Assert.assertEquals(0, list.indexOf(1));
        Assert.assertEquals(-1, list.indexOf(5));

        // Test lastIndexOf() Method
        list.addLast(1);
        Assert.assertEquals(3, list.lastIndexOf(1));
        Assert.assertEquals(-1, list.lastIndexOf(5));
        list.removeLast();

        // Test toArray() Method
        Object[] arr = new Object[] {1,2,3};
        for (int i = 0; i < 3; i++) {
            if (!arr[i].equals(list.toArray()[i])) {
                Assert.assertFalse(true);
            }
        }

        // Test size() Method
        if (list.size() != 3) {
            Assert.assertFalse(true);
        }

        // Test isEmpty() & clear() Methods
        if (list.isEmpty()) {
            Assert.assertFalse(true);
        }
        list.clear();
        if (!list.isEmpty()) {
            Assert.assertFalse(true);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorTest() {
        Object[] arr = new Object[] {1,2,3};
        int i = 0;


        // Test iterator(), hasNext(), next() Method
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            Object temp = iterator.next();
            if (!arr[i].equals(temp)) {
                Assert.assertFalse(true);
            }
            i++;
        }

        // Test remove() Method
        iterator = list.iterator();
        while(iterator.hasNext()) {
            Object temp = iterator.next();
            iterator.remove();
            break;
        }
        if (list.toArray()[0].equals(1)) {
            Assert.assertFalse(true);
        }
        iterator.remove(); // Throws exception since trying to remove before iterating.


    }
}