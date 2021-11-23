package assignment03;

import VisualizationHelper.RecordData;

import java.io.IOException;
import java.util.Random;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 11/22/2021 - 8:21 PM
 */
public class BinarySearchSetAnalysis {
    public static void main(String[] args) throws IOException, InterruptedException {
        int power = 10;
        RecordData data = new RecordData("assignment03data.csv");
        // Create the experiment
        BinarySearchSet<Integer> set1 = new BinarySearchSet<>();
        Random random = new Random();
        double size = Math.pow(2, power);
        int searchElement = 0;
        while (set1.size() != size) {
            int num = random.nextInt();
            if (set1.add(num)) {
                searchElement = num; // Select a random search element.
            }
        }

        while (power < 21) {
            while (set1.size() != size) {
                int num = random.nextInt();
                set1.add(num);
            }




            // Collect Data
            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.
            long startTime, midpointTime, stopTime;

            Thread.sleep(1000);

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 10000;

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++)
                set1.contains(searchElement);

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            for (long i = 0; i < timesToLoop; i++) { // empty block
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

            data.writeData(power, averageTime);

            System.out.println(
                    "It takes exactly " + averageTime + " nanoseconds to search " + searchElement + " in a sorted set of " + size + " integers.");
            power++;
            size = Math.pow(2, power);
            set1.clear();
        }
        data.closeFile();
    }
}
