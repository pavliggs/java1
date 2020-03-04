package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SalesInfo {
    Map<Integer, BuyInfo> mapObj = new TreeMap<>();

    private static class BuyInfo {
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
                        isNumber(lineArr[2]) &&
                        isNumber(lineArr[3])) {
                    count++;
                    // добавляем в mapObj элементы с ключом count и значением - объектом BuyInfo
                    mapObj.put(count, new BuyInfo(lineArr[0], lineArr[1], getInt(lineArr[2]), getDouble(lineArr[3])));
                }
            }
            return count;
        } catch (IOException e) {
            System.out.println(e);
            return 0;
        }
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
            int quantity = entry.getValue().getQuantity();
            /* если элемент с таким ключом уже имеется, то к переменным sumBuy и quantity добавляем значения уже найденного
             * элемента и перезаписываем элемент в treeMap, где значением будет новый объект с новыми параметрами */
            if (treeMap.containsKey(name)) {
                sumBuy += treeMap.get(name).getKey();
                quantity += treeMap.get(name).getValue();
                treeMap.put(name, new AbstractMap.SimpleEntry<>(sumBuy, quantity));
            } else
                // если элемента с таким ключом нет, то записываем его в treeMap
                treeMap.put(name, new AbstractMap.SimpleEntry<>(sumBuy, quantity));
        }
        return treeMap;
    }

    // метод, приводящий строку в массив слов
    private String[] getArray(String str) {
        str = str.replace(", ", ",");
        String[] strArr = str.split(",");
        return strArr;
    }

    // метод приводит тип данных String к Integer
    private Integer getInt(String str) {
        return Integer.valueOf(str);
    }

    // метод приводит тип данных String к Double
    private Double getDouble(String str) {
        return Double.valueOf(str);
    }

    // метод возвращает true, если переданная строка является числом типа int или double
    private boolean isNumber(String str) {
        StringBuilder newStr = new StringBuilder();
        /* проходимся по символам строки и если символ не является символом алфавита, то добавляем его в newStr
         * (подходит для числа типа int и double (если вдруг число содержит точку)) */
        for (char symbol : str.toCharArray()) {
            if (!Character.isAlphabetic(symbol))
                newStr.append(symbol);
        }
        return str.length() == newStr.length();
    }


    public static void main(String[] args) {
        SalesInfo object = new SalesInfo();
        System.out.println(object.loadOrders("salesInfo.txt"));
        System.out.println(object.getGoods());
        System.out.println(object.getCustomers());
    }
}
