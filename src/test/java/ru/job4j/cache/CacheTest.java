package ru.job4j.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {
    private Cache cache;
    private Base base;

    @BeforeEach
    public void before() {
        cache = new Cache();
        base = Base.of(1, 1, "Name");
    }

    @Test
    public void whenAddThenTrue() {
        assertThat(cache.add(base)).isTrue();
        assertThat(cache.get(1)).isPresent().get().isEqualTo(Base.of(1, 1, "Name"));
    }

    @Test
    public void whenAddIsPresentThenFalse() {
        cache.add(base);
        assertThat(cache.add(Base.of(1, 1, "Viktor"))).isFalse();
        assertThat(cache.get(1)).isPresent().get().isEqualTo(Base.of(1, 1, "Name"));
    }

    @Test
    public void whenUpdateThenTrue() {
        cache.add(base);
        assertThat(cache.update(Base.of(1, 1, "Viktor"))).isTrue();
        assertThat(cache.get(1)).isPresent().get().isEqualTo(Base.of(1, 2, "Viktor"));
    }

    @Test
    public void whenUpdateWithWrongVersionThenOptimisticException() {
        cache.add(base);
        assertThatExceptionOfType(OptimisticException.class).isThrownBy(() -> cache.update(Base.of(1, 2, "Viktor")));
        assertThat(cache.get(1)).isPresent().get().isEqualTo(Base.of(1, 1, "Name"));
    }

    @Test
    public void whenUpdateIdNotFoundThenFalse() {
        cache.add(base);
        assertThat(cache.update(Base.of(2, 2, "Viktor"))).isFalse();
    }

    @Test
    public void whenDelete() {
        cache.add(base);
        assertThat(cache.get(1)).isPresent().get().isEqualTo(Base.of(1, 1, "Name"));
        cache.delete(base);
        assertThat(cache.get(1).isEmpty()).isTrue();
    }

    @Test
    public void whenGet() {
        cache.add(base);
        assertThat(cache.get(1)).isPresent().get().isEqualTo(Base.of(1, 1, "Name"));
    }
}