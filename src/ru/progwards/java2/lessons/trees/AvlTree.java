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
            // пересчитать высоты узлов
            node.recountOfHeight();
        }

        // метод пересчитывает высоты в узлах
        void recountOfHeight() {
            Node<K,V> current = this;
            while (current.parent != null) {
                Node<K,V> leftElem = current.parent.left;
                Node<K,V> rightElem = current.parent.right;
                if (leftElem == current) {
                    if (rightElem != null)
                        current.parent.height = searchMaxOfHeight(leftElem.height, rightElem.height) + 1;
                    else
                        current.parent.height = leftElem.height + 1;
                }

                if (rightElem == current) {
                    if (leftElem != null)
                        current.parent.height = searchMaxOfHeight(leftElem.height, rightElem.height) + 1;
                    else
                        current.parent.height = rightElem.height + 1;
                }

                current = current.parent;
            }
        }

        // метод возвращает максимальное значение из двух переданных значений
        int searchMaxOfHeight(int height1, int height2) {
            if (height1 > height2)
                return height1;
            if (height1 < height2)
                return height2;
            return height1;
        }
    }

    private Node<K,V> root;

    public void put(K key, V value) {
        if (key == null || value == null)
            return;
        Node<K,V> node = new Node<>(key, value);
        if (root == null)
            root = node;
        else
            root.find(key).add(node);
    }
}
