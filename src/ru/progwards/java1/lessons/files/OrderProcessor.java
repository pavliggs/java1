package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class OrderProcessor {
    private Path directory;
    private int countFileInCorrect;
    private Set<Order> setOrder = new HashSet<>();

    public OrderProcessor(String startPath) {
        directory = Paths.get(startPath);
        countFileInCorrect = 0;
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");

        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    try {
                        if (pathMatcher.matches(path)) {
                            // если имя файла подходит заданному формату
                            if (isCorrectNameFile(path.getFileName().toString())) {
                                String fileName = path.getFileName().toString();
                                List<String> stringList = Files.readAllLines(path);
                                // создаём объект
//                                Order order = new Order(getSubString(fileName, 0, 3),
//                                        getSubString(fileName, 4, 10),
//                                        getSubString(fileName, 11, 15),
//                                        getLocalDateTime(attrs.lastModifiedTime()),
//                                        createListOrderItem(stringList),
//                                        getSumBuy(createListOrderItem(stringList)));
                                Order order = new Order();
                                order.shopId = getSubString(fileName, 0, 3);
                                order.orderId = getSubString(fileName, 4, 10);
                                order.customerId = getSubString(fileName, 11, 15);
                                order.datetime = getLocalDateTime(attrs.lastModifiedTime());
                                order.items = createListOrderItem(stringList);
                                order.sum = getSumBuy(createListOrderItem(stringList));
                                // добавляем заказы во множество, учитывая переданные параметры метода
                                addSetOrder(order, setOrder, start, finish, shopId);
                            } else {
                                /* если имя файла некорректно, то увеличиваем countFileInCorrect на 1
                                 * и очищаем этот файл */
                                ++countFileInCorrect;
                                Files.writeString(path, "");
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
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
    public void addSetOrder(Order order, Set<Order> setOrder, LocalDate start, LocalDate finish, String shopId) {
        LocalDate localDate = LocalDate.from(order.datetime);
        if (start == null && finish == null) {
            if (shopId == null)
                setOrder.add(order);
            else if (order.shopId.equals(shopId))
                setOrder.add(order);
        } else if (start == null) {
            if (localDate.isBefore(finish) || localDate.equals(finish)) {
                if (shopId == null)
                    setOrder.add(order);
                else if (order.shopId.equals(shopId))
                    setOrder.add(order);
            }
        } else if (finish == null) {
            if (localDate.isAfter(start) || localDate.equals(start)) {
                if (shopId == null)
                    setOrder.add(order);
                else if (order.shopId.equals(shopId))
                    setOrder.add(order);
            }
        } else if ((localDate.isAfter(start) && localDate.isBefore(finish)) ||
                localDate.equals(start) || localDate.equals(finish)) {
            if (shopId == null)
                setOrder.add(order);
            else if (order.shopId.equals(shopId))
                setOrder.add(order);
        }
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
//        return new OrderItem(strArr[0], Integer.parseInt(strArr[1]), Double.parseDouble(strArr[2]));
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
        System.out.println(orderProcessor.loadOrders(null, null, null));
        System.out.println(orderProcessor.statisticsByShop());
        System.out.println(orderProcessor.statisticsByGoods());
        System.out.println(orderProcessor.statisticsByDay());
        System.out.println(orderProcessor.process(null));
    }
}