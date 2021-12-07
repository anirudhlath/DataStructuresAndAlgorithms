package assignment05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * The type Spell checker test.
 *
 * @author Anirudh Lath
 * @project CS6012
 * @created 12 /05/2021 - 8:33 PM
 */
public class SpellCheckerTest extends SpellChecker {

    /**
     * The My sc.
     */
    SpellChecker mySC = new SpellChecker(new File("dictionary.txt"));

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test add to dictionary.
     */
    @Test
    public void testAddToDictionary() {
        mySC.addToDictionary("anirudh");
        mySC.addToDictionary("anirudh");
        ArrayList<String> arr = mySC.dictionaryArray();
        int hasFound = 0;

        for (int i = 0; i < arr.size(); i++) {
           if (arr.get(i).equals("anirudh")) {
               hasFound++;
           }
        }

        Assert.assertEquals(1, hasFound);
    }

    /**
     * Test remove from dictionary.
     */
    @Test
    public void testRemoveFromDictionary() {
        mySC.addToDictionary("anirudh");
        mySC.removeFromDictionary("anirudh");
        ArrayList<String> arr = mySC.dictionaryArray();
        int hasFound = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals("anirudh")) {
                hasFound++;
            }
        }

        Assert.assertEquals(0, hasFound);
    }

    /**
     * Test spell check.
     */
    @Test
    public void testSpellCheck() {
        // This has already been tested in SpellCheckerDemo
    }
}