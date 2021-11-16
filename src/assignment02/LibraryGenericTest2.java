package assignment02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The type Library generic test 2.
 */
public class LibraryGenericTest2 extends LibraryGeneric {

    /**
     * The Lib 1.
     */
    LibraryGeneric<String> lib1 = new LibraryGeneric<String>();
    /**
     * The To be added.
     */
    ArrayList<LibraryBookGeneric<String>> toBeAdded = new ArrayList<>();
    /**
     * The Patron 1.
     */
    String patron1 = "Jane Doe";


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        toBeAdded.add(new LibraryBookGeneric<String>(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
        toBeAdded.add(new LibraryBookGeneric<String>(9780330351690L, "Jon Krakauer", "Into the Wild"));
        toBeAdded.add(new LibraryBookGeneric<String>(9780446580342L, "David Baldacci", "Simple Genius"));
        lib1.addAll(toBeAdded);
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
     * Test add.
     */
    @Test
    public void testAdd() {
        lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib1.add(9780446580342L, "David Baldacci", "Simple Genius");
        assertTrue(lib1.getInventoryList().size() == 6);
    }

    /**
     * Test add all from file.
     */
    @Test
    public void testAddAllFromFile() {
        lib1.addAll("Mushroom_Publishing.txt");
        assertTrue(lib1.getInventoryList().size() == 26);
    }

    /**
     * Test add all from list.
     */
    @Test
    public void testAddAllFromList() {
        lib1.addAll(toBeAdded);
        assertTrue(lib1.getInventoryList().size() == 6);
    }

    /**
     * Test lookup isbn.
     */
    @Test
    public void testLookupISBN() {
        lib1.checkout(9780374292799L, patron1, 1, 16,2000);
        assertTrue(lib1.lookup(9780374292799L).equals(patron1));

    }

    /**
     * Test lookup holder.
     */
    @Test
    public void testLookupHolder() {
        lib1.checkout(9780374292799L, patron1, 1, 16,2000);
        assertTrue(lib1.lookup(patron1).size() == 1);
    }

    /**
     * Test checkout.
     */
    @Test
    public void testCheckout() {
        assertTrue(lib1.checkout(9780374292799L, patron1, 1, 16,2000));
        assertFalse(lib1.checkout(9780374292722L, patron1, 1, 16,2000));
    }

    /**
     * Test checkin isbn.
     */
    @Test
    public void testCheckinISBN() {
        lib1.checkout(9780374292799L, patron1, 1, 16,2000);
        assertTrue(lib1.checkin(9780374292799L));
        assertFalse(lib1.checkin(9780374292799L));
    }

    /**
     * Test checkin holder.
     */
    @Test
    public void testCheckinHolder() {
        lib1.checkout(9780374292799L, patron1, 1, 16,2000);
        assertTrue(lib1.checkin(patron1));
        assertFalse(lib1.checkin(patron1));
    }

    /**
     * Test get inventory list.
     */
    @Test
    public void testGetInventoryList() {
        assertTrue(lib1.getInventoryList().size() == 3);
    }

    /**
     * Test get ordered by author.
     */
    @Test
    public void testGetOrderedByAuthor() {
        lib1.add(9780374292792L, "Thomas L. Friedman", "The World is Not Flat");
        assertTrue(lib1.getOrderedByAuthor().get(0).getAuthor().equals("David Baldacci"));
        assertTrue(lib1.getOrderedByAuthor().get(1).getAuthor().equals("Jon Krakauer"));
        assertTrue(lib1.getOrderedByAuthor().get(2).getAuthor().equals("Thomas L. Friedman"));
        assertTrue(lib1.getOrderedByAuthor().get(2).getTitle().equals("The World is Flat"));
        assertTrue(lib1.getOrderedByAuthor().get(3).getAuthor().equals("Thomas L. Friedman"));
        assertTrue(lib1.getOrderedByAuthor().get(3).getTitle().equals("The World is Not Flat"));
    }

    /**
     * Test get overdue list.
     */
    @Test
    public void testGetOverdueList() {
        lib1.checkout(9780330351690L, patron1, 1, 1, 2008);
        lib1.checkout(9780374292799L, patron1, 1, 2, 2008);
        assertTrue(lib1.getOverdueList(2,2,2008).get(0).getDueDate().equals(new GregorianCalendar(2008,1,1)));
    }
}