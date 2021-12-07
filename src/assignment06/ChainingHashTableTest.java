package assignment06;

import VisualizationHelper.RecordData;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 12/06/2021 - 9:13 PM
 */
public class ChainingHashTableTest {
    int exp = 5;
    int capacity = (int) Math.pow(2, exp);
    ChainingHashTable badHashTable = new ChainingHashTable(capacity, new BadHashFunctor());
    ChainingHashTable medHashTable = new ChainingHashTable(capacity, new MediocreHashFunctor());
    ChainingHashTable goodHashTable = new ChainingHashTable(capacity, new GoodHashFunctor());
    String[] arr = new String[] {"true", "madden", "alive", "wine", "frame", "doctor", "though", "essence", "pin", "hold", "tail", "almost", "apply", "minister", "niece", "essence", "earn", "need", "degree", "water", "skirt", "afford", "boat", "weave", "sock", "nice", "passage", "soul", "shoot", "farm", "result", "just", "dear", "appoint", "mankind", "liar", "beak", "possible", "day", "tide", "upper", "audience", "pack", "eye", "widower", "several", "clever", "deer", "reed", "strict", "english"};
    ArrayList<String> list = new ArrayList<>();
    File dictionary = new File("dictionary.txt");
    Scanner scanner = new Scanner(dictionary);

    public ChainingHashTableTest() throws FileNotFoundException {
    }

    @Before
    public void setUp() throws Exception {

        while(scanner.hasNext()) {
            list.add(scanner.next());
        }

        /*for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }*/

    }

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