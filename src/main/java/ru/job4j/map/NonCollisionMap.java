package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        boolean result = false;
        int index = getIndex(key);
        if (isEmpty(key)) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private boolean isEmpty(K key) {
        return table[getIndex(key)] == null;
    }

    private boolean equalsKeys(K key) {
        int index = getIndex(key);
        return !isEmpty(key) && Objects.hashCode(key) == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        Arrays.stream(table)
                .filter(Objects::nonNull)
                .forEach(el -> newTable[getIndex(el.key)] = el);
        table = newTable;
    }

    @Override
    public V get(K key) {
        return equalsKeys(key) ? table[getIndex(key)].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (equalsKeys(key)) {
            table[index].key = null;
            table[index].value = null;
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {

            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}