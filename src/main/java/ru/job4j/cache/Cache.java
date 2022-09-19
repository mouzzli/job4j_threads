package ru.job4j.cache;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (id, base) -> {
            if (model.getVersion() != base.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base updatedBase = new Base(model.getId(), model.getVersion() + 1, model.getName());
            updatedBase.setName(model.getName());
            return updatedBase;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Optional<Base> get(int id) {
        Optional<Base> base = Optional.ofNullable(memory.get(id));
        if (base.isPresent()) {
            return Optional.of(Base.of(memory.get(id)));
        }
        return base;
    }
}