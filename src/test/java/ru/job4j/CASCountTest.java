package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {

    @Test
    public void when3incrementThenGetEqual3() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread firstThread = new Thread(() -> casCount.increment());
        Thread secondThread = new Thread(() -> casCount.increment());
        Thread thirdThread = new Thread(() -> casCount.increment());
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        firstThread.join();
        secondThread.join();
        thirdThread.join();
        assertThat(casCount.get()).isEqualTo(3);
    }
}

