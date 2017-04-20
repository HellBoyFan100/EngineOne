package me.engineone.core.listenable;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by BinaryBench on 4/20/2017.
 */
public class BasicPriorityListenable<T> implements PriorityListenable<T> {

    private Map<Consumer<T>, Float> listeners = new HashMap<>();

    @Override
    public void removeListener(Consumer<T> listener) {
        listeners.remove(listener);
    }

    @Override
    public void addListener(Consumer<T> listener, float priority) {
        listeners.put(listener, priority);
    }

    @Override
    public boolean isRegistered(Consumer<T> listener) {
        return listeners.containsKey(listener);
    }

    @Override
    public float getPriority(Consumer<T> listener) {
        return listeners.get(listener);
    }

    public void call(T t) {
        listeners
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> entry.getKey().accept(t));
    }

}
