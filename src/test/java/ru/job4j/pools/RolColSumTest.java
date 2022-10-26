package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    void whenSum() {
        int[][] matrix = {{5, 3}, {4, 1}};
        Sums[] actual = new Sums[]{new Sums(8, 9), new Sums(5, 4)};
        assertThat(actual).isEqualTo(RolColSum.sum(matrix));
    }

    @Test
    void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{5, 3}, {4, 2}};
        Sums[] actual = new Sums[]{new Sums(8, 9), new Sums(6, 5)};
        assertThat(actual).isEqualTo(RolColSum.asyncSum(matrix));
    }
}