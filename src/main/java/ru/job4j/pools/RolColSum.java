package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    private static Sums calculateSumsForIndex(int index, int[][] matrix) {
        int rowSum = 0;
        int colSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            rowSum += matrix[index][i];
            colSum += matrix[i][index];
        }
        return new Sums(rowSum, colSum);
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = calculateSumsForIndex(i, matrix);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int index = i;
            sums[i] = CompletableFuture.supplyAsync(() -> calculateSumsForIndex(index, matrix)).get();
        }
        return sums;
    }
}