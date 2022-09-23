package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelIndexSearchTest {

    private final Integer[] intArray = new Integer[]{1, 2, 3, 4, 5, 37, 7, 8, 9, 11, 11, 12, 12, 25, 64};
    private final String[] strArray = new String[]{"11", "22", "33", "44", "55", "66", "77", "88", "99", "100", "111", "122"};

    @Test
    public void whenIntArrayThen37() {
        ParallelIndexSearch parallelIndexSearch = new ParallelIndexSearch(intArray, 37);
        assertThat(5).isEqualTo(parallelIndexSearch.search());
    }

    @Test
    public void whenIntArrayThen11() {
        ParallelIndexSearch parallelIndexSearch = new ParallelIndexSearch(intArray, 11);
        assertThat(9).isEqualTo(parallelIndexSearch.search());
    }

    @Test
    public void whenStrArrayThen2() {
        ParallelIndexSearch parallelIndexSearch = new ParallelIndexSearch(strArray, "33");
        assertThat(2).isEqualTo(parallelIndexSearch.search());
    }

    @Test
    public void whenStrArrayThen5() {
        ParallelIndexSearch parallelIndexSearch = new ParallelIndexSearch(strArray, "66");
        assertThat(5).isEqualTo(parallelIndexSearch.search());
    }
}