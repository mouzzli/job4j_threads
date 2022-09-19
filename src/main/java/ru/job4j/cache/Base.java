package ru.job4j.cache;

import java.util.Objects;

public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public static Base of(Base base) {
        return new Base(base.getId(), base.getVersion(), base.getName());
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Base base)) {
            return false;
        }
        return getId() == base.getId() && getVersion() == base.getVersion() && getName().equals(base.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVersion(), getName());
    }

    @Override
    public String toString() {
        return "Base{" + "id=" + id + ", version=" + version + ", name='" + name + '\'' + '}';
    }
}