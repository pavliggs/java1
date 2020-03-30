package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.*;

public class OrderProcessor {
    private Path directory;
    private int countFileInCorrect;
    private Set<Order> setOrder;

    public OrderProcessor(String startPath) {
        directory = Paths.get(startPath);
        countFileInCorrect = 0;
        setOrder = new HashSet<>();
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");

        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {
                        // если имя файла подходит заданному формату
                        if (isCorrectNameFile(path.getFileName().toString())) {
                          addSetOrder(path, start, finish, shopId);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path path, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return countFileInCorrect;
    }

    public List<Order> process(String shopId) {
        List<Order> orderList = new ArrayList<>();
        for (Order order : setOrder) {
            if (shopId == null)
                orderList.add(order);
            else if (order.shopId.equals(shopId))
                orderList.add(order);
        }

        Comparator<Order> comparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.datetime.compareTo(o2.datetime);
            }
        };

        orderList.sort(comparator);
        return orderList;
    }

    public Map<String, Double> statisticsByShop() {
        Map<String, Double> map = new TreeMap<>();
        for (Order order : setOrder) {
            if (map.containsKey(order.shopId)) {
                double sumNew = map.get(order.shopId) + order.sum;
                map.put(order.shopId, sumNew);
            }
            map.putIfAbsent(order.shopId, order.sum);
        }
        return map;
    }

    public Map<String, Double> statisticsByGoods() {
        Map<String, Double> map = new TreeMap<>();
        for (Order order : setOrder) {
            for (int i = 0; i < order.items.size(); i++) {
                String productName = order.items.get(i).googsName;
                double sumBuy = order.items.get(i).price * order.items.get(i).count;
                if (map.containsKey(productName)) {
                    double newSumBuy = map.get(productName) + sumBuy;
                    map.put(productName, newSumBuy);
                }
                map.putIfAbsent(productName, sumBuy);
            }
        }
        return map;
    }

    public Map<LocalDate, Double> statisticsByDay() {
        Map<LocalDate, Double> map = new TreeMap<>();
        for (Order order : setOrder) {
            LocalDate localDate = LocalDate.from(order.datetime);
            if (map.containsKey(localDate)) {
                double sumNew = map.get(localDate) + order.sum;
                map.put(localDate, sumNew);
            }
            map.putIfAbsent(localDate, order.sum);
        }
        return map;
    }

    // метод добавляет объекты во множество учитывая некоторые условия
    public void addSetOrder(Path path, LocalDate start, LocalDate finish, String shopId) {
        try {
            // поместим содержимое файла в переменную
            String content = Files.readString(path);
            // isNotContainError будет true, если содержимое не содержит ошибку
            boolean isNotContainError = !content.contains("Error");
            // isNotEmpty будет true, если содержимое не пустое
            boolean isNotEmpty = !content.isEmpty();
            // время последнего изменения файла
            LocalDate localDate = LocalDate.from(getLocalDateTime(Files.getLastModifiedTime(path)));
            // имя файла
            String fileName = path.getFileName().toString();
            // shopId файла
            String shopIdFile = getSubString(fileName, 0 ,3);
            if (start == null && finish == null) {
                if (shopId == null) {
                    // если содержимое файла не содержит ошибку и файл не пустой
                    if (isNotContainError && isNotEmpty)
                        setOrder.add(createOrder(path));
                    else {
                        ++countFileInCorrect;
                        Files.writeString(path, "");
                    }
                }
                else if (shopIdFile.equals(shopId)) {
                    if (isNotContainError && isNotEmpty)
                        setOrder.add(createOrder(path));
                    else {
                        ++countFileInCorrect;
                        Files.writeString(path, "");
                    }
                }
            } else if (start == null) {
                if (localDate.isBefore(finish) || localDate.equals(finish)) {
                    if (shopId == null) {
                        if (isNotContainError && isNotEmpty)
                            setOrder.add(createOrder(path));
                        else {
                            ++countFileInCorrect;
                            Files.writeString(path, "");
                        }
                    }
                    else if (shopIdFile.equals(shopId)) {
                        if (isNotContainError && isNotEmpty)
                            setOrder.add(createOrder(path));
                        else {
                            ++countFileInCorrect;
                            Files.writeString(path, "");
                        }
                    }
                }
            } else if (finish == null) {
                if (localDate.isAfter(start) || localDate.equals(start)) {
                    if (shopId == null) {
                        if (isNotContainError && isNotEmpty)
                            setOrder.add(createOrder(path));
                        else {
                            ++countFileInCorrect;
                            Files.writeString(path, "");
                        }
                    }
                    else if (shopIdFile.equals(shopId)) {
                        if (isNotContainError && isNotEmpty)
                            setOrder.add(createOrder(path));
                        else {
                            ++countFileInCorrect;
                            Files.writeString(path, "");
                        }
                    }
                }
            } else if ((localDate.isAfter(start) && localDate.isBefore(finish)) ||
                    localDate.equals(start) || localDate.equals(finish)) {
                if (shopId == null) {
                    if (isNotContainError && isNotEmpty)
                        setOrder.add(createOrder(path));
                    else {
                        ++countFileInCorrect;
                        Files.writeString(path, "");
                    }
                }
                else if (shopIdFile.equals(shopId)) {
                    if (isNotContainError && isNotEmpty)
                        setOrder.add(createOrder(path));
                    else {
                        ++countFileInCorrect;
                        Files.writeString(path, "");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // метод создаёт объект Order
    public Order createOrder(Path path) {
        Order order = new Order();
        try {
            String fileName = path.getFileName().toString();
            List<String> stringList = Files.readAllLines(path);
            order.shopId = getSubString(fileName, 0, 3);
            order.orderId = getSubString(fileName, 4, 10);
            order.customerId = getSubString(fileName, 11, 15);
            order.datetime = getLocalDateTime(Files.getLastModifiedTime(path));
            order.items = createListOrderItem(stringList);
            order.sum = getSumBuy(createListOrderItem(stringList));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    // метод проверяет корректность формата переданной строки
    public boolean isCorrectNameFile(String str) {
        return str.matches("[A-Z0-9]{3}-[A-Z0-9]{6}-[0-9]{4}[.][a-z]{3}");
    }

    // из определенной строки создаём объект OrderItem
    public OrderItem createOrderItem(String str) {
        str = str.replace(", ", ",");
        String[] strArr = str.split(",");
        OrderItem orderItem = new OrderItem();
        orderItem.googsName = strArr[0];
        orderItem.count = Integer.parseInt(strArr[1]);
        orderItem.price = Double.parseDouble(strArr[2]);
        return orderItem;
    }

    // из списка строк создаём список объектов OrderItem
    public List<OrderItem> createListOrderItem(List<String> list) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            orderItemList.add(createOrderItem(list.get(i)));
        }
        Comparator<OrderItem> comparator = new Comparator<OrderItem>() {
            @Override
            public int compare(OrderItem o1, OrderItem o2) {
                return o1.googsName.compareTo(o2.googsName);
            }
        };
        orderItemList.sort(comparator);
        return orderItemList;
    }

    // считаем суммы всех покупок в заказе
    public double getSumBuy(List<OrderItem> orderItemList) {
        double result = 0;
        for (int i = 0; i < orderItemList.size(); i++) {
            int count = orderItemList.get(i).count;
            double price = orderItemList.get(i).price;
            result += count * price;
        }
        return result;
    }

    // из строки получаем подстроку
    public String getSubString(String str, int fromInt, int toInt) {
        return str.substring(fromInt, toInt);
    }

    // из объекта FileTime получаем объект LocalDateTime
    public LocalDateTime getLocalDateTime(FileTime fileTime) {
        Instant instant = Instant.ofEpochMilli(fileTime.toMillis());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime;
    }

    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor("C:\\Users\\Эльдорадо\\inFolder");
        System.out.println(orderProcessor.loadOrders(LocalDate.of(2020, Month.MARCH, 30), null, null));
        System.out.println(orderProcessor.setOrder);
        System.out.println(orderProcessor.process("S02"));
        System.out.println(orderProcessor.statisticsByShop());
        System.out.println(orderProcessor.statisticsByGoods());
        System.out.println(orderProcessor.statisticsByDay());
    }
}