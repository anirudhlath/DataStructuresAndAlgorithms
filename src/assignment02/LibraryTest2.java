package assignment02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * The type Library test 2.
 */
public class LibraryTest2 extends Library {

    /**
     * The Lib 1.
     */
    Library lib1 = new Library();
    /**
     * The To be added.
     */
    ArrayList<LibraryBook> toBeAdded = new ArrayList<>();
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
        toBeAdded.add(new LibraryBook(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
        toBeAdded.add(new LibraryBook(9780330351690L, "Jon Krakauer", "Into the Wild"));
        toBeAdded.add(new LibraryBook(9780446580342L, "David Baldacci", "Simple Genius"));
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
     * Test checkin.
     */
    @Test
    public void testCheckin() {
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
}