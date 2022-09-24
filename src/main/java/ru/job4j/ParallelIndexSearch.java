package ru.job4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T value;
    private final int from;
    private final int to;
    private final static int ARRAY_SIZE = 10;

    private ParallelIndexSearch(T[] array, T value) {
        this.array = array;
        this.value = value;
        this.from = 0;
        this.to = array.length - 1;
    }

    private ParallelIndexSearch(T[] array, T value, int from, int to) {
        this.array = array;
        this.value = value;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (from - to <= ARRAY_SIZE) {
            return find(array, value, from, to);
        }
        int mid = (from - to) / 2;
        ParallelIndexSearch<T> leftSort = new ParallelIndexSearch<>(array, value, from, mid);
        ParallelIndexSearch<T> rightSort = new ParallelIndexSearch<>(array, value, mid + 1, to);
        leftSort.fork();
        rightSort.fork();
        return Math.max(leftSort.join(), rightSort.join());
    }

    private int find(T[] array, T value, int from, int to) {
        for (int i = from; i <= to; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> Integer search(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, value));
    }
}
