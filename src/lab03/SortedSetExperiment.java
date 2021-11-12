package lab03;

import VisualizationHelper.RecordData;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SortedSetExperiment {
    public static void main(String[] args) throws IOException, InterruptedException {
        int size = (int) Math.pow(2, 20);
        boolean isOn = true;
        int testCount = 1;
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        RecordData recorder = new RecordData("data.csv");


        int sum = 0;
        int numRuns = 1000;
        for (int i = 0; i < numRuns; i++) {
            long startTime = System.nanoTime();
            Arrays.sort(arr);
            long stopTime = System.nanoTime();
            sum += stopTime - startTime;

        }

        recorder.writeData(size, (sum / numRuns));
        recorder.closeFile();

    }
}
