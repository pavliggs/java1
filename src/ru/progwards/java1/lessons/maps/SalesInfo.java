package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.util.*;

public class SalesInfo {
    Map<Integer, BuyInfo> mapObj = new TreeMap<>();

    public static class BuyInfo {
        private String name;
        private String product;
        private int quantity;
        private double sumBuy;

        BuyInfo (String name, String product, int quantity, double sumBuy) {
            this.name = name;
            this.product = product;
            this.quantity = quantity;
            this.sumBuy = sumBuy;
        }

        public String getName() {
            return name;
        }

        public String getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getSumBuy() {
            return sumBuy;
        }

        @Override
        public String toString() {
            return "BuyInfo{" +
                    "name='" + name + '\'' +
                    ", product='" + product + '\'' +
                    ", quantity=" + quantity +
                    ", sumBuy=" + sumBuy +
                    '}';
        }
    }

    public int loadOrders(String fileName) {
        try (FileReader reader = new FileReader(fileName)){
            int count = 0;
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArr = getArray(line);
                if (lineArr.length == 4 &&
                        getInt(lineArr[2]).getClass() == Integer.class &&
                        getDouble(lineArr[3]).getClass() == Double.class) {
                    count++;
                    // добавляем в mapObj элементы с ключом count и значением - объектом BuyInfo
                    mapObj.put(count, new BuyInfo(lineArr[0], lineArr[1], getInt(lineArr[2]), getDouble(lineArr[3])));
                }
            }
            return count;
        } catch (Throwable t) {
            System.out.println(t);
        }
        return 0;
    }

    public Map<String, Double> getGoods() {
        Map<String, Double> treeMap = new TreeMap<>();

        // проходимся по всем элементам mapObj
        for (Map.Entry<Integer, BuyInfo> entry : mapObj.entrySet()) {
            String product = entry.getValue().getProduct();
            double sumBuy = entry.getValue().getSumBuy();
            /* если элемент с таким ключом уже имеется в treeMap, то к переменной sumBuy прибавляем уже имеющееся
             * значение элемента и перезаписываем элемент в treeMap с измененными данными */
            if (treeMap.containsKey(product)) {
                sumBuy += treeMap.get(product);
                treeMap.put(product, sumBuy);
            } else
                // если элемента с таким ключом нет, то записываем его в treeMap
                treeMap.put(product, sumBuy);
        }
        return treeMap;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> treeMap = new TreeMap<>();

        // проходимся по элементам mapObj
        for (Map.Entry<Integer, BuyInfo> entry : mapObj.entrySet()) {
            String name = entry.getValue().getName();
            double sumBuy = entry.getValue().getSumBuy();
            int count = 1;
            /* если элемент с таким ключом уже имеется, то к переменной sumBuy добавляем значение уже найденного
             * элемента и перезаписываем элемент в treeMap, где значением будет новый объект с новыми параметрами */
            if (treeMap.containsKey(name)) {
                sumBuy += treeMap.get(name).getKey();
                int quantity = treeMap.get(name).getValue() + 1;
                treeMap.put(name, new AbstractMap.SimpleEntry<>(sumBuy, quantity));
            } else
                // если элемента с таким ключом нет, то записываем его в treeMap
                treeMap.put(name, new AbstractMap.SimpleEntry<>(sumBuy, count));
        }
        return treeMap;
    }

    // метод, приводящий строку в массив слов
    private String[] getArray(String str) {
        str = str.replace(", ", ",");
        String[] strArr = str.split(",");
        return strArr;
    }

    private Integer getInt(String str) {
        return Integer.valueOf(str);
    }

    private Double getDouble(String str) {
        return Double.valueOf(str);
    }

    public static void main(String[] args) {
        SalesInfo object = new SalesInfo();
        System.out.println(object.loadOrders("salesInfo.txt"));
        System.out.println(object.getGoods());
        System.out.println(object.getCustomers());
    }
}
