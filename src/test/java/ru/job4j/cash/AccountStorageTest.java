package ru.job4j.cash;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AccountStorageTest {

    @Test
    void whenAdd() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstAccount.amount()).isEqualTo(100);
    }

    @Test
    void whenAddIsPresentThenFalse() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        assertThat(storage.add(new Account(1, 200))).isFalse();
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstAccount.amount()).isEqualTo(100);
    }

    @Test
    void whenAddIsNotPresentThenTrue() {
        var storage = new AccountStorage();
        assertThat(storage.add(new Account(1, 200))).isTrue();
    }

    @Test
    void whenUpdate() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.update(new Account(1, 200));
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstAccount.amount()).isEqualTo(200);
    }

    @Test
    void whenUpdateIsPresentThenTrue() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        assertThat(storage.update(new Account(1, 200))).isTrue();
    }

    @Test
    void whenUpdateIsNotPresentThenFalse() {
        var storage = new AccountStorage();
        assertThat(storage.update(new Account(1, 200))).isFalse();
    }

    @Test
    void whenDelete() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.delete(1);
        assertThat(storage.getById(1)).isEmpty();
    }

    @Test
    void whenDeleteIsPresentThenTrue() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        assertThat(storage.delete(1)).isTrue();
    }

    @Test
    void whenDeleteIsNotPresentThenFalse() {
        var storage = new AccountStorage();
        assertThat(storage.delete(1)).isFalse();
    }

    @Test
    void whenGetById() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        var expected = new Account(1, 100);
        assertThat(firstAccount).isEqualTo(expected);
    }

    @Test
    void whenGetByIsNotPresentThenIsEmpty() {
        var storage = new AccountStorage();
        assertThat(storage.getById(1)).isEmpty();
    }

    @Test
    void whenTransfer() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.add(new Account(2, 100));
        storage.transfer(1, 2, 100);
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        var secondAccount = storage.getById(2)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstAccount.amount()).isEqualTo(0);
        assertThat(secondAccount.amount()).isEqualTo(200);
    }

    @Test
    void whenTransferNot() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 50));
        storage.add(new Account(2, 100));
        assertThat(storage.transfer(1, 2, 100)).isFalse();
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        var secondAccount = storage.getById(2)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        assertThat(firstAccount.amount()).isEqualTo(50);
        assertThat(secondAccount.amount()).isEqualTo(100);
    }
}