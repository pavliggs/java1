package ru.progwards.pavliggs.java2.N8dot1;

import java.util.function.Consumer;

/**
 * Класс двоичного дерева поиска для хранения данных
 * В классе содержатся объекты класса {@link BinaryTree.TreeLeaf}
 * @param <K> Тип данных ключа в классе
 * @param <V> Тип данных значения в классе
 * @author Progwards
 * @version 1.0
 */
public class BinaryTree<K extends Comparable<K>,V> {
    /**
     * Статическая константа содержащая сообщение о том, что такой ключ уже существует
     */
    public static final String KEY_EXIST = "Key already exist";
    /**
     * Статическая константа содержащая сообщение о том, что такого ключа не существует
     */
    public static final String KEY_NOT_EXIST = "Key not exist";

    /**
     * Класс листа - элемента двоичного дерева поиска {@link BinaryTree}
     * @version 1.0
     */
    class TreeLeaf {
        /**
         * Свойство - ключ
         */
        K key;
        /**
         * Свойство - значение
         */
        V value;
        /**
         * Свойство - ссылка на родитель объекта
         */
        TreeLeaf parent;
        /**
         * Свойство - ссылка на левый элемент объекта
         */
        TreeLeaf left;
        /**
         * Свойство - ссылка на правый элемент объекта
         */
        TreeLeaf right;

        /**
         * Конструктор с параметрами
         * @param key ключ элемента
         * @param value значение элемента
         */
        public TreeLeaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Метод возвращает объект класса TreeLeaf
         * @param key переданный ключ для поиска объекта класса TreeLeaf
         * @return возвращает объект класса TreeLeaf
         */
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

        /**
         * Метод добавляет к узлу дерева узел leaf и переприсваивает ссылки.
         * Если объект с таким ключом уже существует, то бросаем исключение с сообщением "Key already exist"
         * @param leaf добавляемый объект в дерево
         */
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

        /**
         * Метод удаляет узел из дерева и переприсваивает ссылки
         */
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

        /**
         * Метод обходит элементы дерева от элемента с меньшим ключом до элемента с наибольшим ключом
         * @param consumer параметром может быть функциональный интерфейс Consumer
         */
        void process(Consumer<TreeLeaf> consumer) {
            if (left != null)
                left.process(consumer);
            consumer.accept(this);
            if (right != null)
                right.process(consumer);
        }

        /**
         *
         * @return возвращаем строковое представление объекта TreeLeaf
         */
        @Override
        public String toString() {
            return key + " = " + value;
        }
    }

    /**
     * Свойство - корень дерева
     */
    private TreeLeaf root;

    /**
     * Метод возвращает значение элемента по ключу. Если такого ключа не существует, то возвращается null
     * @param key ключ объекта
     * @return возвращаем значение объекта с ключом key
     */
    public V find(K key) {
        if (root == null)
            return null;
        TreeLeaf found = root.find(key);
        return found.key.compareTo(key) == 0 ? found.value : null;
    }

    // метод добавляет узел

    /**
     * Метод добавляет в дерево элемент с ключом key и значением value
     * @param key ключ добавляемого элемента
     * @param value значение добавляемого элемента
     */
    public void add(K key, V value) {
        TreeLeaf leaf = new TreeLeaf(key, value);
        if (root == null)
            root = leaf;
        else
            root.find(key).add(leaf);
    }

    /**
     * Метод удаляет из дерева элемент с ключом key
     * @param key ключ удаляемого элемента
     */
    public void delete(K key) {
        internalDelete(key);
    }

    /**
     * Метод возвращает элемент с ключом key и удаляет этот элемент
     * @param key ключ удаляемого элемента
     * @return возвращаем удаляемый элемент
     */
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

    /**
     * Метод меняет у элемента старое значение ключа на новое.
     * Метод находит элемент дерева с ключом oldKey и удаляет его.
     * Затем меняет у найденного элемента значение ключа на newKey и добавляет этот элемент в дерево
     * @param oldKey старый ключ элемента
     * @param newKey новый ключ элемента
     */
    public void change(K oldKey, K newKey) {
        TreeLeaf current = internalDelete(oldKey);
        current.key = newKey;
        root.find(newKey).add(current);
    }

    /**
     * Метод обходит все элементы дерева
     * @param consumer параметром может быть функциональный интерфейс Consumer
     */
    public void process(Consumer<TreeLeaf> consumer) {
        root.process(consumer);
    }
}
