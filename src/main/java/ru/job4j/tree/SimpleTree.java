package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> optParNode = findBy(parent);
        Optional<Node<E>> optChildNode = findBy(child);
        if (optParNode.isPresent() && optChildNode.isEmpty()) {
            Node<E> parentNode = optParNode.get();
            parentNode.children.add(new Node<>(child));
            result = true;
        }
        return result;
    }

    public boolean isBinary() {
        return findByPredicate(node -> node.children.size() > 2)
                .isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.add(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(node -> node.value.equals(value));
    }
}