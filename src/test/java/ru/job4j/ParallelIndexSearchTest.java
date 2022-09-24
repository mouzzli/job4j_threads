package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelIndexSearchTest {

    @Test
    public void whenIntArrayThen37() {
        Integer[] intArray = {1, 2, 3, 4, 5, 37, 7, 8, 9, 11, 11, 12, 12, 25, 64};
        assertThat(5).isEqualTo(ParallelIndexSearch.search(intArray, 37));
    }

    @Test
    public void whenIntArrayThen11() {
        Integer[] intArray = {1, 2, 3, 4, 5, 37, 7, 8, 9, 11, 11, 12, 12, 25, 64};
        assertThat(9).isEqualTo(ParallelIndexSearch.search(intArray, 11));
    }

    @Test
    public void whenStrArrayThen2() {
        String[] strArray = {"11", "22", "33", "44", "55", "66", "77", "88", "99", "100", "111", "122"};
        assertThat(2).isEqualTo(ParallelIndexSearch.search(strArray, "33"));
    }

    @Test
    public void whenStrArrayThen5() {
        String[] strArray = {"11", "22", "33", "44", "55", "66", "77", "88", "99", "100", "111", "122"};
        assertThat(5).isEqualTo(ParallelIndexSearch.search(strArray, "66"));
    }

    @Test
    public void whenDoubleArrayThen4() {
        Double[] doubleArray = {12.45, 34.65, 17.76, 64.32, 6.54, 7.62, 99.11, 76.67, 48.34, 7.12, 55.45, 48.49};
        assertThat(4).isEqualTo(ParallelIndexSearch.search(doubleArray, 6.54));
    }

    @Test
    public void whenNotContainThenMinus1() {
        String[] strArray = {"11", "22", "33", "44", "55", "66", "77", "88", "99", "100", "111", "122"};
        assertThat(-1).isEqualTo(ParallelIndexSearch.search(strArray, "17"));
    }
}