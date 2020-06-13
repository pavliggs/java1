package ru.progwards.java2.lessons.trees;

public class AvlTree<K extends Comparable<K>,V> {

    class Node<K extends Comparable<K>,V> {
        K key;
        V value;
        int height;
        Node<K,V> parent;
        Node<K,V> right;
        Node<K,V> left;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            height = 0;
        }

        private Node<K,V> find(K key) {
            int cmp = key.compareTo(this.key);
            if (cmp > 0) {
                if (right != null)
                    return right.find(key);
                else
                    return this;
            }
            if (cmp < 0) {
                if (left != null)
                    return left.find(key);
                else
                    return this;
            }
            return this;
        }

        void add(Node<K,V> node) {
            int cmp = node.key.compareTo(this.key);

            if (cmp == 0) {
                this.value = node.value;
                return;
            }
            if (cmp > 0) {
                right = node;
            } else {
                left = node;
            }
            node.parent = this;

            Node<K,V> parent1 = this;
            while (parent1 != null) {
                
            }
        }
    }

    private Node<K,V> root;

    public void put(K key, V value) {
        Node<K,V> node = new Node<>(key, value);
        if (root == null)
            root = node;
        else
            root.find(key).add(node);
    }
}
