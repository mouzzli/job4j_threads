package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    void whenSum() {
        int[][] matrix = {{5, 3}, {4, 2}};
        RolColSum.Sums[] actual = new RolColSum.Sums[]{new RolColSum.Sums(8, 9), new RolColSum.Sums(6, 5)};
        assertThat(actual).isEqualTo(RolColSum.sum(matrix));
    }

    @Test
    void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{5, 3}, {4, 2}};
        RolColSum.Sums[] actual = new RolColSum.Sums[]{new RolColSum.Sums(8, 9), new RolColSum.Sums(6, 5)};
        assertThat(actual).isEqualTo(RolColSum.asyncSum(matrix));
    }
}