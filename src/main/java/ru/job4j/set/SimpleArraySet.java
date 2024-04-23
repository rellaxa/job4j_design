package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean added = !contains(value);
        if (added) {
            set.add(value);
        }
        return added;
    }

    @Override
    public boolean contains(T value) {
        boolean found = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
