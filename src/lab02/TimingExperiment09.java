package lab02;

/**
 * @author Anirudh Lath
 * @project CS6012
 * @created 11/09/2021 - 8:21 PM
 */
public class TimingExperiment09

{
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        for (int i = 1; i < 11; i++) {
            Math.sqrt(i);
        }
        long stopTime = System.nanoTime();
        System.out.println("The time taken to compute square roots of numbers 1-10 took " + (stopTime - startTime) + " nanoseconds.");
    }
}
