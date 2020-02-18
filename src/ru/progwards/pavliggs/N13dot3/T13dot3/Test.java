package ru.progwards.pavliggs.N13dot3.T13dot3;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Test {
    static class User {
        public Integer id;
        public String name;

        User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public TreeSet<User> createSet() {
        TreeSet<User> treeSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o2.id, o1.id);
            }
        });
        return treeSet;
    }

    public static void main(String[] args) {
        Test test = new Test();
        TreeSet<User> treeSet = test.createSet();
        treeSet.add(new User(23, "Pavel"));
        treeSet.add(new User(12, "Paul"));
        treeSet.add(new User(10, "Michael"));
        treeSet.add(new User(100, "Kris"));
        treeSet.add(new User(78, "Nicol"));
        System.out.println(treeSet);
    }
}
