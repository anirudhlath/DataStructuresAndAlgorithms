package lab05;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 11/22/2021 - 11:46 PM
 */
public class DoublyLinkedListTest extends DoublyLinkedList {
    DoublyLinkedList<Integer> list = new DoublyLinkedList();


    @Before
    public void setUp() throws Exception {
        list.addFirst(1);
        list.addLast(3);
        list.add(1, 2);

    }

    @Test
    public void additionTest() {

    }
}