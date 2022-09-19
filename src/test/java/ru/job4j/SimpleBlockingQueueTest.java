package ru.job4j;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleBlockingQueueTest {

    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        SimpleBlockingQueue simpleBlockingQueue = new SimpleBlockingQueue(2);
        List<Integer> expected = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> actual = new ArrayList<>();
        Thread producer = new Thread(
                () -> {
                    for (Integer integer : expected) {
                        simpleBlockingQueue.offer(integer);
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    actual.add((Integer) simpleBlockingQueue.poll());
                    actual.add((Integer) simpleBlockingQueue.poll());
                    actual.add((Integer) simpleBlockingQueue.poll());
                    actual.add((Integer) simpleBlockingQueue.poll());
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(actual).isEqualTo(expected);
    }
}