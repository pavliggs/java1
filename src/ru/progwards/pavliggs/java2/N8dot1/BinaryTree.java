package ru.progwards.pavliggs.java2.N8dot1;

import ru.progwards.java2.lessons.trees.AvlTree;

import java.util.Iterator;
import java.util.function.Consumer;

public class BinaryTree<K extends Comparable<K>,V> {
    public static final String KEY_EXIST = "Key already exist";
    public static final String KEY_NOT_EXIST = "Key not exist";

    class TreeLeaf {
        K key;
        V value;
        TreeLeaf parent;
        TreeLeaf left;
        TreeLeaf right;

        public TreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // метод ищет узел, к которому в последствии будет добавлен узел с ключом key
        private TreeLeaf find(K key) {
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

        // метод добавляет к найденному узлу узел leaf
        void add(TreeLeaf leaf) {
            int cmp = leaf.key.compareTo(this.key);
            if (cmp == 0)
                try {
                    throw new TreeException(KEY_EXIST);
                } catch (TreeException e) {
                    e.printStackTrace();
                }
            if (cmp > 0) {
                right = leaf;
                leaf.parent = this;
            } else {
                left = leaf;
                leaf.parent = this;
            }
        }

        // метод удаляет узел
        void delete() {
            if (parent.right == this) {
                parent.right = right;
                if (right != null)
                    right.parent = parent;
                if (left != null)
                    parent.find(left.key).add(left);
            } else {
                parent.left = left;
                if (left != null)
                    left.parent = parent;
                if (right != null)
                    parent.find(right.key).add(right);
            }
        }

        void process(Consumer<TreeLeaf> consumer) {
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }
    }

    private TreeLeaf root;

    // метод находит значение по ключу
    public V find(K key) {
        if (root == null)
            return null;
        TreeLeaf found = root.find(key);
        return found.key.compareTo(key) == 0 ? (V)found.value : null;
    }

    // метод добавляет узел
    public void add(K key, V value) {
        TreeLeaf leaf = new TreeLeaf(key, value);
        if (root == null)
            root = leaf;
        else
            root.find(key).add(leaf);
    }

    public void delete(K key) {
        internalDelete(key);
    }

    // метод проверяет не удаляем ли мы корень дерева
    private TreeLeaf internalDelete(K key) {
        if (root == null)
            try {
                throw new TreeException(KEY_NOT_EXIST);
            } catch (TreeException e) {
                e.printStackTrace();
            }

        TreeLeaf found = root.find(key);
        int cmp = found.key.compareTo(key);
        if (cmp != 0)
            try {
                throw new TreeException(KEY_NOT_EXIST);
            } catch (TreeException e) {
                e.printStackTrace();
            }
        if (found.parent == null) {
            if (found.right != null) {
                root = found.right;
                if (found.left != null)
                    add(found.left.key, found.left.value);
            } else if (found.left != null)
                root = found.left;
            else
                root = null;
        } else {
            found.delete();
        }
        return found;
    }

    public void process(Consumer<TreeLeaf> consumer) {
        root.process(consumer);
    }

    public Iterator<TreeLeaf> getIterator() {
        return new Iterator<TreeLeaf>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public TreeLeaf next() {
                return null;
            }
        };
    }

    public static void main(String[] args) {
        BinaryTree<Integer, Integer> tree = new BinaryTree<>();
        tree.add(15, 1);
        tree.add(10, 1);
        tree.add(20, 1);
        tree.add(18, 1);
        tree.add(27, 1);
        tree.add(5, 1);
        tree.add(12, 1);
        tree.add(6, 1);
        tree.add(2, 1);
        tree.add(1, 1);

        tree.process(System.out::println);
    }
}
