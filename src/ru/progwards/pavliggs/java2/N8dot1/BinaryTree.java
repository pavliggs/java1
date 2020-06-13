package ru.progwards.pavliggs.java2.N8dot1;

public class BinaryTree<K extends Comparable<K>,V> {
    public static final String KEY_EXIST = "Key already exist";
    public static final String KEY_NOT_EXIST = "Key not exist";

    class TreeLeaf<K extends Comparable<K>,V> {
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
        private TreeLeaf<K,V> find(K key) {
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
        void add(TreeLeaf<K,V> leaf) throws TreeException {
            int cmp = leaf.key.compareTo(this.key);
            if (cmp == 0)
                throw new TreeException(KEY_EXIST);
            if (cmp > 0) {
                right = leaf;
                leaf.parent = this;
            } else {
                left = leaf;
                leaf.parent = this;
            }
        }

        // метод удаляет узел
        void delete() throws TreeException {
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
    }

    private TreeLeaf<K,V> root;

    // метод находит значение по ключу
    public V find(K key) {
        if (root == null)
            return null;
        TreeLeaf found = root.find(key);
        return found.key.compareTo(key) == 0 ? (V)found.value : null;
    }

    // метод добавляет узел
    public void add(TreeLeaf<K,V> leaf) throws TreeException {
        if (root == null)
            root = leaf;
        else
            root.find(leaf.key).add(leaf);
    }

    public void delete(K key) throws TreeException {
        internalDelete(key);
    }

    // метод проверяет не удаляем ли мы корень дерева
    private TreeLeaf<K,V> internalDelete(K key) throws TreeException {
        if (root == null)
            throw new TreeException(KEY_NOT_EXIST);

        TreeLeaf found = root.find(key);
        int cmp = found.key.compareTo(key);
        if (cmp != 0)
            throw new TreeException(KEY_NOT_EXIST);
        if (found.parent == null) {
            if (found.right != null) {
                root = found.right;
                if (found.left != null)
                    add(found.left);
            } else if (found.left != null)
                root = found.left;
            else
                root = null;
        } else
            found.delete();
        return found;
    }
}
