package ru.job4j.collection;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        ForwardLinked.Node<T> newNode = new ForwardLinked.Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            ForwardLinked.Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T deleted = head.item;
        Node<T> cont = head.next;
        head.item = null;
        head.next = null;
        head = cont;
        size--;
        modCount++;
        return deleted;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int expectedModCount = modCount;
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.item;
                current = current.next;
                return value;
            }
        };
    }

    private static class Node<T> {

        private T item;
        private Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}
