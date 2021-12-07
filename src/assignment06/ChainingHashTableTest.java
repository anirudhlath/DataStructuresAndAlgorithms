package assignment06;

import VisualizationHelper.RecordData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Chaining hash table test.
 *
 * @author Anirudh Lath
 * @project CS6012
 * @created 12 /06/2021 - 9:13 PM
 */
public class ChainingHashTableTest {
    /**
     * The Exp.
     */
    int exp = 5;
    /**
     * The Capacity.
     */
    int capacity = (int) Math.pow(2, exp);
    /**
     * The Bad hash table.
     */
    ChainingHashTable badHashTable = new ChainingHashTable(capacity, new BadHashFunctor());
    /**
     * The Med hash table.
     */
    ChainingHashTable medHashTable = new ChainingHashTable(capacity, new MediocreHashFunctor());
    /**
     * The Good hash table.
     */
    ChainingHashTable goodHashTable = new ChainingHashTable(capacity, new GoodHashFunctor());
    /**
     * The List.
     */
    ArrayList<String> list = new ArrayList<>();
    /**
     * The Dictionary.
     */
    File dictionary = new File("dictionary.txt"); // Please refer to the dictionary file in the assignment folder.


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        Scanner scanner = new Scanner(dictionary);
        while(scanner.hasNext()) {
            list.add(scanner.next());
        }

    }

    /**
     * Generic test.
     */
    @Test
    public void genericTest() {
        Assert.assertEquals(0, badHashTable.size());
        Assert.assertEquals(0, medHashTable.size());
        Assert.assertEquals(0, goodHashTable.size());
        badHashTable.addAll(list);
        medHashTable.addAll(list);
        goodHashTable.addAll(list);
        Assert.assertEquals(2914, badHashTable.size());
        Assert.assertEquals(2914, medHashTable.size());
        Assert.assertEquals(2914, goodHashTable.size());
        Assert.assertFalse(badHashTable.isEmpty());
        Assert.assertFalse(medHashTable.isEmpty());
        Assert.assertFalse(goodHashTable.isEmpty());
        badHashTable.clear();
        medHashTable.clear();
        goodHashTable.clear();
        Assert.assertEquals(0, badHashTable.size());
        Assert.assertEquals(0, medHashTable.size());
        Assert.assertEquals(0, goodHashTable.size());
        Assert.assertTrue(badHashTable.isEmpty());
        Assert.assertTrue(medHashTable.isEmpty());
        Assert.assertTrue(goodHashTable.isEmpty());

    }

    /**
     * Addition test.
     */
    @Test
    public void additionTest() {
        Assert.assertTrue(badHashTable.add("Anirudh"));
        Assert.assertFalse(badHashTable.add("Anirudh"));
        Assert.assertTrue(medHashTable.add("Anirudh"));
        Assert.assertFalse(medHashTable.add("Anirudh"));
        Assert.assertTrue(goodHashTable.add("Anirudh"));
        Assert.assertFalse(goodHashTable.add("Anirudh"));
        Assert.assertEquals(1, badHashTable.size());
        Assert.assertEquals(1, medHashTable.size());
        Assert.assertEquals(1, goodHashTable.size());

    }

    /**
     * Contains test.
     */
    @Test
    public void containsTest() {
        Assert.assertFalse(badHashTable.contains("Anirudh"));
        Assert.assertTrue(badHashTable.add("Anirudh"));
        Assert.assertTrue(badHashTable.contains("Anirudh"));
        Assert.assertFalse(medHashTable.contains("Anirudh"));
        Assert.assertTrue(medHashTable.add("Anirudh"));
        Assert.assertTrue(medHashTable.contains("Anirudh"));
        Assert.assertFalse(goodHashTable.contains("Anirudh"));
        Assert.assertTrue(goodHashTable.add("Anirudh"));
        Assert.assertTrue(goodHashTable.contains("Anirudh"));

    }

    /**
     * Remove test.
     */
    @Test
    public void removeTest() {
        Assert.assertFalse(badHashTable.remove("Anirudh"));
        Assert.assertTrue(badHashTable.add("Anirudh"));
        Assert.assertTrue(badHashTable.remove("Anirudh"));
        Assert.assertFalse(medHashTable.remove("Anirudh"));
        Assert.assertTrue(medHashTable.add("Anirudh"));
        Assert.assertTrue(medHashTable.remove("Anirudh"));
        Assert.assertFalse(goodHashTable.remove("Anirudh"));
        Assert.assertTrue(goodHashTable.add("Anirudh"));
        Assert.assertTrue(goodHashTable.remove("Anirudh"));
        Assert.assertFalse(badHashTable.remove("Anirudh"));
        Assert.assertFalse(medHashTable.remove("Anirudh"));
        Assert.assertFalse(goodHashTable.remove("Anirudh"));
    }


    /**
     * Bad hash addition analysis.
     *
     * @throws InterruptedException the interrupted exception
     * @throws IOException          the io exception
     */
    @Test
    public void badHashAdditionAnalysis() throws InterruptedException, IOException {
        RecordData data = new RecordData("badHashTiming.csv");
        RecordData data2 = new RecordData("badHashCollision.csv");

        /* Collect Data
         ** First, spin computing stuff until one second has gone by.
         ** This allows this thread to stabilize.
         */
        long startTime, midpointTime, stopTime;
        long timesToLoop = 10000;

        startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1000000000) { // empty block
        }

        // Now, run the test.
        startTime = System.nanoTime();

        for (long i = 0; i < timesToLoop; i++)
            badHashTable.addAll(list);
        badHashTable.clear();

        midpointTime = System.nanoTime();

        // Run an empty loop to capture the cost of running the loop.

        for (long i = 0; i < timesToLoop; i++) { // empty block
            badHashTable.clear();
        }

        stopTime = System.nanoTime();

        // Compute the time, subtract the cost of running the loop
        // from the cost of running the loop and computing square roots.
        // Average it over the number of runs.

        double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
        badHashTable.addAll(list);
        data.writeData(capacity, averageTime);
        data2.writeData(capacity, badHashTable.getCollisionCount());
        System.out.println(
                "It takes exactly " + averageTime + " nanoseconds to add array of words in a hash table of " + badHashTable.size() + " strings with " + badHashTable.getCollisionCount() + " collisions.");

        data.closeFile();
        data2.closeFile();
        if (exp < 15) {
            exp++;
            capacity = (int) Math.pow(2, exp);
            badHashTable = new ChainingHashTable(capacity, new BadHashFunctor());
            badHashAdditionAnalysis();
        }
    }

    /**
     * Mediocre hash addition analysis.
     *
     * @throws InterruptedException the interrupted exception
     * @throws IOException          the io exception
     */
    @Test
    public void mediocreHashAdditionAnalysis() throws InterruptedException, IOException {
        RecordData data = new RecordData("medHashTiming.csv");
        RecordData data2 = new RecordData("medHashCollision.csv");

        /* Collect Data
         ** First, spin computing stuff until one second has gone by.
         ** This allows this thread to stabilize.
         */
        long startTime, midpointTime, stopTime;
        long timesToLoop = 10000;

        startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1000000000) { // empty block
        }

        // Now, run the test.
        startTime = System.nanoTime();

        for (long i = 0; i < timesToLoop; i++)
            medHashTable.addAll(list);
            medHashTable.clear();

        midpointTime = System.nanoTime();

        // Run an empty loop to capture the cost of running the loop.

        for (long i = 0; i < timesToLoop; i++) { // empty block
            medHashTable.clear();
        }

        stopTime = System.nanoTime();

        // Compute the time, subtract the cost of running the loop
        // from the cost of running the loop and computing square roots.
        // Average it over the number of runs.

        double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
        medHashTable.addAll(list);
        data.writeData(capacity, averageTime);
        data2.writeData(capacity, medHashTable.getCollisionCount());
        System.out.println(
                "It takes exactly " + averageTime + " nanoseconds to add array of words in a hash table of " + medHashTable.size() + " strings with " + medHashTable.getCollisionCount() + " collisions.");

        data.closeFile();
        data2.closeFile();
        if (exp < 15) {
            exp++;
            capacity = (int) Math.pow(2, exp);
            medHashTable = new ChainingHashTable(capacity, new MediocreHashFunctor());
            mediocreHashAdditionAnalysis();
        }
    }

    /**
     * Good hash addition analysis.
     *
     * @throws InterruptedException the interrupted exception
     * @throws IOException          the io exception
     */
    @Test
    public void goodHashAdditionAnalysis() throws InterruptedException, IOException {
        RecordData data = new RecordData("goodHashTiming.csv");
        RecordData data2 = new RecordData("goodHashCollision.csv");

        /* Collect Data
         ** First, spin computing stuff until one second has gone by.
         ** This allows this thread to stabilize.
         */
        long startTime, midpointTime, stopTime;
        long timesToLoop = 10000;

        startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1000000000) { // empty block
        }

        // Now, run the test.
        startTime = System.nanoTime();

        for (long i = 0; i < timesToLoop; i++)
            goodHashTable.addAll(list);
            goodHashTable.clear();

        midpointTime = System.nanoTime();

        // Run an empty loop to capture the cost of running the loop.

        for (long i = 0; i < timesToLoop; i++) { // empty block
            goodHashTable.clear();
        }

        stopTime = System.nanoTime();

        // Compute the time, subtract the cost of running the loop
        // from the cost of running the loop and computing square roots.
        // Average it over the number of runs.

        double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
        goodHashTable.addAll(list);
        data.writeData(capacity, averageTime);
        data2.writeData(capacity, goodHashTable.getCollisionCount());
        System.out.println(
                "It takes exactly " + averageTime + " nanoseconds to add array of words in a hash table of " + goodHashTable.size() + " strings with " + goodHashTable.getCollisionCount() + " collisions.");

        data.closeFile();
        data2.closeFile();

        if (exp < 15) {
            exp++;
            capacity = (int) Math.pow(2, exp);
            goodHashTable = new ChainingHashTable(capacity, new GoodHashFunctor());
            goodHashAdditionAnalysis();
        }
    }
}