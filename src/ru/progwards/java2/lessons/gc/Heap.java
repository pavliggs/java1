package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {
    private byte[] bytes;

    private List<byte[]> freeBlocks;
    private List<byte[]> busyBlocks;
    private Comparator<byte[]> comparator;

    Heap(int maxHeapSize) {
        comparator = new Comparator<byte[]>() {
            @Override
            public int compare(byte[] o1, byte[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        };
        freeBlocks = new ArrayList<>();
        busyBlocks = new ArrayList<>();
        bytes = new byte[maxHeapSize];
//        for (int i = 0; i < maxHeapSize; i++) {
//            bytes[i] = i;
//        }
        freeBlocks.add(bytes);
    }

    public int malloc(int size) {
        byte startIndex = 0;
        for (int i = 0; i < freeBlocks.size(); i++) {
            byte[] freeBlock = freeBlocks.get(i);
            // если блок нужного размера найден
            if (freeBlock.length >= size) {
                startIndex = freeBlock[0];
                // создаём занятый блок
                byte[] busyBlock = new byte[size];
                int num = 0;
                // заполняем блок значениями
                for (byte j = startIndex; j < startIndex + size; j++) {
                    busyBlock[num] = j;
                    ++num;
                }
                busyBlocks.add(busyBlock);
                busyBlocks.sort(comparator);
                // перезаписываем свободный блок в список freeBlocks с новыми значениями
                byte[] freeBlockNew = new byte[freeBlock.length - busyBlock.length];
                System.arraycopy(freeBlock, size, freeBlockNew, 0, freeBlock.length - size);
                freeBlocks.set(i, freeBlockNew);
                break;
            }
            // свободный блок нужного размера не нашёлся
            if (i == freeBlocks.size() - 1)
                throw new OutOfMemoryException();
        }
        return startIndex;
    }

    public void free(int ptr) {
        for (int i = 0; i < busyBlocks.size(); i++) {
            byte[] busyBlock = busyBlocks.get(i);
            if (busyBlock[0] == ptr) {
                freeBlocks.add(busyBlock);
                freeBlocks.sort(comparator);
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
                byte[] freeBlock = freeBlocks.get(i);
                byte[] freeBlockNext = freeBlocks.get(i+1);
                /*
                * если конечный индекс массива freeBlock и начальный индекс массива freeBlockNext являются смежными,
                * то создаём новым массив newFreeBlock из массивов freeBlock и freeBlockNext
                * затем удаляем массивы freeBlock и freeBlockNext из списка freeBlocks и добавляем в список массив
                * newFreeBlock и сортируем список
                * */
                if (freeBlock[freeBlock.length - 1] + 1 == freeBlockNext[0]) {
                    byte[] newFreeBlock = new byte[freeBlock.length + freeBlockNext.length];
                    System.arraycopy(freeBlock, 0, newFreeBlock, 0, freeBlock.length);
                    System.arraycopy(freeBlockNext, 0, newFreeBlock, freeBlock.length, freeBlockNext.length);
                    freeBlocks.remove(freeBlock);
                    freeBlocks.remove(freeBlockNext);
                    freeBlocks.add(newFreeBlock);
                    freeBlocks.sort(comparator);
                } else
                    ++i;
            }
        }
    }

    public void compact() {
        for (int i = 0; i < busyBlocks.size(); i++) {
            byte[] busyBlock = busyBlocks.get(i);
            for (int j = 0; j < busyBlock.length; j++) {

            }
        }
    }

    public String printElements(List<byte[]> list) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0 && i == list.size() - 1) {
                str.append("{ " + Arrays.toString(list.get(i)) + " }");
                break;
            }
            if (i == 0) {
                str.append("{ " + Arrays.toString(list.get(i)) + ", ");
                continue;
            }
            if (i == list.size() - 1) {
                str.append(Arrays.toString(list.get(i)) + " }");
                continue;
            }
            str.append(Arrays.toString(list.get(i)) + ", ");
        }
        return str.toString();
    }

    public static void main(String[] args) {
//        Heap heap = new Heap(14);
//        System.out.println(heap.printElements(heap.freeBlocks));
//
//        System.out.println(heap.malloc(5));
//
//        System.out.println(heap.printElements(heap.freeBlocks));
//        System.out.println(heap.printElements(heap.busyBlocks));
//
//        System.out.println(heap.malloc(3));
//
//        System.out.println(heap.printElements(heap.freeBlocks));
//        System.out.println(heap.printElements(heap.busyBlocks));
//
//        heap.free(0);
//
//        System.out.println(heap.printElements(heap.freeBlocks));
//        System.out.println(heap.printElements(heap.busyBlocks));
//
//        heap.defrag();
//
//        System.out.println(heap.printElements(heap.freeBlocks));
//        System.out.println(heap.printElements(heap.busyBlocks));
    }
}
