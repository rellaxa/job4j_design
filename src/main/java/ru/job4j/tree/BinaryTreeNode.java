package ru.job4j.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class BinaryTreeNode {
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode parent;
    public Integer value;

    public BinaryTreeNode(Integer value, BinaryTreeNode parent) {
        this.value = value;
        this.parent = parent;
    }

    public void insertNode(Integer value) {
        insertNode(value, this);
    }

    private void insertNode(Integer value, BinaryTreeNode parentNode) {
        if (value < parentNode.value) {
            if (parentNode.left == null) {
                parentNode.left = new BinaryTreeNode(value, parentNode);
            } else {
                insertNode(value, parentNode.left);
            }
        }
        if (value > parentNode.value) {
            if (parentNode.right == null) {
                parentNode.right = new BinaryTreeNode(value, parentNode);
            } else {
                insertNode(value, parentNode.right);
            }
        }
    }

    public void removeNode(Integer value) {
        removeNode(value, this);
    }

    private BinaryTreeNode removeNode(Integer value, BinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = removeNode(value, node.left);
        } else if (value > node.value) {
            node.right = removeNode(value, node.right);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
        }

        BinaryTreeNode original = node;
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }

        node.right = removeNode(value, original.right);
        node.left = original.left;
        return node;
    }

    public BinaryTreeNode findNode(Integer value) {
        BinaryTreeNode node = this;
        while (node != null) {
            if (Objects.equals(value, node.value)) {
                return node;
            }
            if (value < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public void traverseRecursive() {
        traverseRecursive(this);
    }

    private void traverseRecursive(BinaryTreeNode node) {
        if (node != null) {
            System.out.println("node = " + node.value);
            traverseRecursive(node.left);
            traverseRecursive(node.right);
        }
    }

    public void traverseWithStack() {
        Deque<BinaryTreeNode> stack = new ArrayDeque<>();
        stack.push(this);
        while (!stack.isEmpty()) {
            BinaryTreeNode currentNode = stack.pop();

            System.out.println("node = " + currentNode.value);

            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
    }

    public void traverseWithQueue() {
        Deque<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.push(this);
        while (!queue.isEmpty()) {
            BinaryTreeNode currentNode = queue.removeFirst();
            System.out.println("node" + currentNode.value);
            if (currentNode.left != null) {
                queue.push(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.push(currentNode.right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode tree = new BinaryTreeNode(19, null);
        tree.insertNode(14);
        tree.insertNode(53);
        tree.insertNode(3);
        tree.insertNode(15);
        tree.insertNode(29);
        tree.insertNode(26);
        tree.insertNode(58);
        tree.traverseRecursive();
    }
}
