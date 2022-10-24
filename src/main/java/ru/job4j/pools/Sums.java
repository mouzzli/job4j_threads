package ru.job4j.pools;

import java.util.Objects;

public class Sums {
    private final int rowSum;
    private final int colSum;
    /* Getter and Setter */

    public int getRowSum() {
        return rowSum;
    }

    public int getColSum() {
        return colSum;
    }

    public Sums(int rowSum, int colSum) {
        this.rowSum = rowSum;
        this.colSum = colSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sums sums)) {
            return false;
        }
        return getRowSum() == sums.getRowSum() && getColSum() == sums.getColSum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowSum(), getColSum());
    }
}
