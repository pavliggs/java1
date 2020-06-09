package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {
    private byte[] bytes;

    private List<Block> freeBlocks;
    private List<Block> busyBlocks;

    private int quantityCompact;

    Heap(int maxHeapSize) {
        freeBlocks = new ArrayList<>();
        busyBlocks = new ArrayList<>();
        bytes = new byte[maxHeapSize];
        freeBlocks.add(new Block(0, bytes.length));
        quantityCompact = 0;
    }

    public static class Block implements Comparable<Block> {
        private int startIndex;
        private int size;

        Block(int startIndex, int size) {
            this.startIndex = startIndex;
            this.size = size;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public int getSize() {
            return size;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        @Override
        public String toString() {
            return "Block{" +
                    "startIndex=" + startIndex +
                    ", size=" + size +
                    '}';
        }

        @Override
        public int compareTo(Block o) {
            return Integer.compare(startIndex, o.startIndex);
        }
    }

    public int malloc(int size) {
        int startIndex = 0;
        for (int i = 0; i < freeBlocks.size(); i++) {
            Block freeBlock = freeBlocks.get(i);
            // если подходящий по размеру блок существует, то помечаем его ячейки как занятые
            if (size <= freeBlock.getSize()) {
                startIndex = freeBlock.getStartIndex();
                // добавляем блок в список занятых блоков
                busyBlocks.add(new Block(startIndex, size));
                // сортируем по значению свойства startIndex (объекты Block реализуют интерфейс Comparable)
                busyBlocks.sort(null);
                // создаём новый свободный блок с учётом уже занятых ячеек памяти
                Block newFreeBlock = new Block(freeBlock.getStartIndex() + size, freeBlock.getSize() - size);
                freeBlocks.set(i, newFreeBlock);
                break;
            }
            /*
            * если свободных блоков нужного размера не нашлось, то вызываем метод compact() и снова и ищем подходящий
            * свободный блок. Если во второй раз не нашлось свободных блоков, то значит свободной памяти не хватает
            * и бросаем OutOfMemoryException
            * */
            if (i == freeBlocks.size() - 1) {
                if (quantityCompact == 1)
                    throw new OutOfMemoryException();
                compact();
                ++quantityCompact;
                i = -1;
            }
        }
        return startIndex;
    }

    public void free(int ptr) {
        for (int i = 0; i < busyBlocks.size(); i++) {
            Block busyBlock = busyBlocks.get(i);
            // если блок с данным указателем найден, то добавляем его в список свободных блоков
            if (busyBlock.getStartIndex() == ptr) {
                freeBlocks.add(busyBlock);
                freeBlocks.sort(null);
                busyBlocks.remove(i);
                break;
            }
            // если занятый блок с начальным индексом = ptr не найден, то выдаём исключение
            if (i == busyBlocks.size() - 1)
                throw new InvalidPointerException();
        }
    }

    public void defrag() {
        int i = 0;
        int size = freeBlocks.size();
        for (int count = 0; count < size; count++) {
            if (i < freeBlocks.size() - 1) {
                Block freeBlockCurrent = freeBlocks.get(i);
                Block freeBlockNext = freeBlocks.get(i+1);
                /*
                * если конечный индекс блока freeBlockCurrent и начальный индекс блока freeBlockNext являются смежными,
                * то создаём новым блок newFreeBlock из блоков freeBlockCurrent и freeBlockNext и добавляем в список
                * */
                if (freeBlockCurrent.getStartIndex() + freeBlockCurrent.getSize() == freeBlockNext.getStartIndex()) {
                    Block newFreeBlock = new Block(freeBlockCurrent.getStartIndex(), freeBlockCurrent.getSize() +
                                            freeBlockNext.getSize());
                    freeBlocks.set(i, newFreeBlock);
                    freeBlocks.remove(freeBlockNext);
                } else
                    ++i;
            }
        }
    }

    public void compact() {
        int startIndex = 0;
        // переносим занятые блоки в начало кучи
        for (int i = 0; i < busyBlocks.size(); i++) {
            Block busyBlock = busyBlocks.get(i);
            busyBlock.setStartIndex(startIndex);
            startIndex += busyBlock.getSize();
        }
        // свободные блоки размещаем после занятых
        for (int i = 0; i < freeBlocks.size(); i++) {
            Block freeBlock = freeBlocks.get(i);
            freeBlock.setStartIndex(startIndex);
            startIndex += freeBlock.getSize();
        }
        // проводим дефрагментацию
        defrag();
    }

    public static void main(String[] args) {
        Heap heap = new Heap(50);

        System.out.println(heap.freeBlocks);

        heap.malloc(14);
        heap.malloc(7);

        System.out.println(heap.freeBlocks);
        System.out.println(heap.busyBlocks);

        heap.free(14);

        System.out.println(heap.freeBlocks);
        System.out.println(heap.busyBlocks);

//        heap.defrag();
//
//        System.out.println(heap.freeBlocks);
//        System.out.println(heap.busyBlocks);

        heap.malloc(5);

        System.out.println(heap.freeBlocks);
        System.out.println(heap.busyBlocks);

        heap.malloc(9);

        System.out.println(heap.freeBlocks);
        System.out.println(heap.busyBlocks);

        heap.free(0);

        System.out.println(heap.freeBlocks);
        System.out.println(heap.busyBlocks);

        heap.malloc(21);

        System.out.println(heap.freeBlocks);
        System.out.println(heap.busyBlocks);
//
        heap.malloc(16);

        System.out.println(heap.freeBlocks);
        System.out.println(heap.busyBlocks);

//        heap.compact();
//
//        System.out.println(heap.freeBlocks);
//        System.out.println(heap.busyBlocks);


    }
}
