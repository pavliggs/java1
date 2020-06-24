package ru.progwards.java2.lessons.trees;

import java.util.function.Consumer;

public class AvlTree<K extends Comparable<K>,V> {
    public static final String KEY_NOT_EXIST = "Key not exist";
    public static final String KEY_NOT_FIT = "Key not fit";

    class Node {
        K key;
        V value;
        public int height;
        private Node parent;
        private Node right;
        private Node left;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // возвращаем высоту узла
        int getHeight(Node node) {
            return node == null ? 0 : node.height;
        }

        public Node find(K key) {
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

        void add(Node node) {
            int cmp = node.key.compareTo(this.key);
            // если ключи совпадают, то меняем значение на новое
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
            // пересчитать высоты узлов начиная с последнего добавленного элемента
            node.recountOfHeight();
            node.searchDisbalance();
        }

        void delete() {
            if (left != null || right != null) {
                Node current;
                if (getBalance() > 0)
                    current = left.maxElem();
                else if (getBalance() < 0)
                    current = right.minElem();
                else {
                    if (left != null)
                        current = left.maxElem();
                    else
                        current = right.minElem();
                }

                current.left = left;
                if (left != null)
                    left.parent = current;
                current.right = right;
                if (right != null)
                    right.parent = current;
                current.parent = parent;

                if (parent != null) {
                    if (parent.left == this)
                        parent.left = current;
                    if (parent.right == this)
                        parent.right = current;
                } else
                    root = current;

                if (left != null)
                    left.recountOfHeight();
                else {
                    if (right != null)
                        right.recountOfHeight();
                }
            } else {
                if (parent != null) {
                    if (parent.left == this)
                        parent.left = null;
                    if (parent.right == this)
                        parent.right = null;
                    parent.height = 0;
                    parent.searchDisbalance();
                    parent = null;
                } else
                    root = null;
            }
        }

        void change(K key) {
            if (left != null) {
                if (right != null) {
                    if (key.compareTo(left.key) > 0 && key.compareTo(right.key) < 0)
                        this.key = key;
                    else
                        keyNotFit();
                } else {
                    if (key.compareTo(left.key) > 0)
                        this.key = key;
                    else
                        keyNotFit();
                }
            } else {
                if (right != null) {
                    if (key.compareTo(right.key) < 0 && key.compareTo(parent.key) > 0)
                        this.key = key;
                    else
                        keyNotFit();
                } else {
                    if (key.compareTo(parent.key) > 0)
                        this.key = key;
                    else
                        keyNotFit();
                }
            }
        }

        // метод находит максимальный элемент и удаляет его
        Node maxElem() {
            if (right == null) {
                if (parent.left == this) {
                    if (left == null)
                        parent.left = null;
                    else {
                        parent.left = left;
                        left.parent = parent;
                    }
                }
                if (parent.right == this) {
                    if (left == null)
                        parent.right = null;
                    else {
                        parent.right = left;
                        left.parent = parent;
                    }
                }

                parent.recountOfHeight();
                parent = null;
                return this;
            }
             return right.maxElem();
        }

        // метод находит минимальный элемент и удаляет его
        Node minElem() {
            if (left == null) {
                if (parent.right == this) {
                    if (right == null)
                        parent.right = null;
                    else {
                        parent.right = right;
                        right.parent = parent;
                    }
                }
                if (parent.left == this) {
                    if (right == null)
                        parent.left = null;
                    else {
                        parent.left = right;
                        right.parent = parent;
                    }
                }

                parent.recountOfHeight();
                parent = null;
                return this;
            }
            return left.minElem();
        }

        // метод пересчитывает высоты в узлах
        void recountOfHeight() {
            if (this.parent == null)
                return;
            Node current = this;
            Node parentElem = current.parent;
            parentElem.height = searchMaxOfHeight(getHeight(parentElem.left), getHeight(parentElem.right)) + 1;
            parentElem.recountOfHeight();
        }

        // возвращает
        int getBalance() {
            return getHeight(left) - getHeight(right);
        }

        void searchDisbalance() {
            if (getBalance() > 1 && getHeight(left.right) <= getHeight(left.left)) {
                smallRightRotation();
                return;
            }
            if (this.getBalance() < -1 && getHeight(right.left) <= getHeight(right.right)) {
                smallLeftRotation();
                return;
            }
            if (getBalance() > 1 && getHeight(left.right) > getHeight(left.left)) {
                largeRightRotation();
                return;
            }
            if (getBalance() < -1 && getHeight(right.left) > getHeight(right.right)) {
                largeLeftRotation();
                return;
            }
            if (this.parent != null)
                parent.searchDisbalance();
        }

        // малое правое вращение
        void smallRightRotation() {
            Node b = left;
            Node c = b.right;
            left = c;
            b.right = this;
            b.parent = parent;

            if (parent != null) {
                if (parent.right == this)
                    parent.right = b;
                else
                    parent.left = b;
            } else
                root = b;

            parent = b;
            if (c != null)
                c.parent = this;
            if (right != null)
                right.recountOfHeight();
            else if (left != null)
                left.recountOfHeight();
            else {
                this.height = 0;
                parent.recountOfHeight();
            }
        }

        // малое левое вращение
        void smallLeftRotation() {
            Node b = right;
            Node c = b.left;
            right = c;
            b.left = this;
            b.parent = parent;

            if (parent != null) {
                if (parent.right == this)
                    parent.right = b;
                else
                    parent.left = b;
            } else
                root = b;

            parent = b;
            if (c != null)
                c.parent = this;
            if (left != null)
                left.recountOfHeight();
            else if (right != null)
                right.recountOfHeight();
            else {
                this.height = 0;
                parent.recountOfHeight();
            }
        }

        // большое правое вращение
        void largeRightRotation() {
            Node b = left;
            Node c = b.right;
            Node n = c.right;
            Node m = c.left;
            left = n;
            b.right = m;
            c.right = this;
            c.left = b;
            c.parent = parent;

            if (parent != null) {
                if (parent.right == this)
                    parent.right = c;
                else
                    parent.left = c;
            } else
                root = c;

            parent = c;
            b.parent = c;
            if (n != null) {
                n.parent = this;
                left.recountOfHeight();
            } else if (right != null)
                right.recountOfHeight();
            else
                this.height = 0;

            if (m != null) {
                m.parent = b;
                m.recountOfHeight();
            } else if (b.left != null)
                b.left.recountOfHeight();
            else
                b.height = 0;
        }

        // большое левое вращение
        void largeLeftRotation() {
            Node b = right;
            Node c = b.left;
            Node n = c.left;
            Node m = c.right;
            right = n;
            b.left = m;
            c.left = this;
            c.right = b;
            c.parent = parent;

            if (parent != null) {
                if (parent.right == this)
                    parent.right = c;
                else
                    parent.left = c;
            } else
                root = c;

            parent = c;
            b.parent = c;
            if (n != null) {
                n.parent = this;
                right.recountOfHeight();
            } else if (left != null)
                left.recountOfHeight();
            else
                this.height = 0;

            if (m != null) {
                m.parent = b;
                m.recountOfHeight();
            } else if (b.right != null)
                b.right.recountOfHeight();
            else
                b.height = 0;
        }

        // метод возвращает максимальное значение из двух переданных значений
        int searchMaxOfHeight(int height1, int height2) {
            if (height1 > height2)
                return height1;
            if (height1 < height2)
                return height2;
            return height1;
        }

        void process(Consumer<Node> consumer) {
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

    public Node root;

    public void put(K key, V value) {
        if (key == null || value == null)
            return;
        Node node = new Node(key, value);
        if (root == null)
            root = node;
        else
            root.find(key).add(node);
    }

    public void delete(K key) {
        if (root == null)
            keyNotExist();

        Node found = root.find(key);
        int cmp = found.key.compareTo(key);
        if (cmp != 0) {
            System.out.println(found);
            System.out.println("Не найден элемент с ключом " + key);
            keyNotExist();
        }

        found.delete();
    }

    public V find(K key) {
        if (root == null)
            keyNotExist();

        Node found = root.find(key);
        int cmp = found.key.compareTo(key);
        if (cmp != 0) {
            keyNotExist();
        }
        return found.value;
    }

    public void change(K oldKey, K newKey) {
        if (root == null)
            keyNotExist();

        Node found = root.find(oldKey);
        int cmp = found.key.compareTo(oldKey);
        if (cmp != 0) {
            keyNotExist();
        }

        if (found.parent == null) {
            if (root.left != null) {
                if (root.right != null) {
                    if (newKey.compareTo(root.left.key) > 0 && newKey.compareTo(root.right.key) < 0)
                        root.key = newKey;
                    else
                        keyNotFit();
                } else {
                    if (newKey.compareTo(root.left.key) > 0)
                        root.key = newKey;
                    else
                        keyNotFit();
                }
            } else {
                if (root.right != null) {
                    if (newKey.compareTo(root.right.key) < 0)
                        root.key = newKey;
                    else
                        keyNotFit();
                } else
                    root.key = newKey;
            }
        } else
            found.change(newKey);
    }

    public void process(Consumer<Node> consumer) {
        root.process(consumer);
    }

    private void keyNotExist() {
        try {
            throw new TreeException(KEY_NOT_EXIST);
        } catch (TreeException e) {
            e.printStackTrace();
        }
    }

    private void keyNotFit() {
        try {
            throw new TreeException(KEY_NOT_FIT);
        } catch (TreeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AvlTree<Integer, Integer> tree = new AvlTree<>();
        tree.put(15, 1);
        tree.put(10, 1);
        tree.put(20, 1);
        tree.put(18, 1);
        tree.put(27, 1);
        tree.put(5, 1);
        tree.put(12, 1);
        tree.put(6, 1);
        tree.put(2, 1);
        tree.put(1, 1);

//        tree.put(15, 1);
//        tree.put(10, 1);
//        tree.put(20, 1);
//        tree.put(17, 1);
//        tree.put(19, 1);

        tree.delete(5);

        tree.process(System.out::println);
    }
}
