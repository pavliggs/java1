package ru.progwards.pavliggs.java2.N4dot3;

public class TestBinaryHeap {
    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        for (int i = 0; i < 17; i++) {
            heap.add(i);
            System.out.println(heap);
        }

        System.out.println(heap.sort());
    }
}
