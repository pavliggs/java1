package ru.progwards.java1.lessons.sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductAnalytics {
    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.products = products;
        this.shops = shops;
    }

    public Set<Product> existInAll() {
        // создаем множество на основании списка продуктов магазина c индексом 0 из списка магазинов shops
        Set<Product> productsResult = new HashSet<>(shops.get(0).getProducts());
        // проходим циклом по магазинам, начиная с магазина с индексом 1
        for (int i = 1; i < shops.size(); i++) {
            Shop shop = shops.get(i);
            /* оставляем во множестве productsResult продукты, которые содержаться и в productSet и в следующем по
            * списку магазине
            * в итоге, если в магазинах имеются общие продукты, то они окажутся во множестве productSet */
            productsResult.retainAll(shop.getProducts());
        }
        return productsResult;
    }

    public Set<Product> existAtListInOne() {
        Set<Product> productsResult = new HashSet<>(shops.get(0).getProducts());
        for (int i = 1; i < shops.size(); i++) {
            Shop shop = shops.get(i);
            // добавляем во множество productsResult продукты всех последующих магазинов
            productsResult.addAll(shop.getProducts());
        }
        return productsResult;
    }

    public Set<Product> notExistInShops() {
        /* создаем множество состоящее из продуктов, имеющихся во всех магазинах + могут быть продукты,
        * которых нет ни в одном магазине */
        Set<Product> productsResult = new HashSet<>(new ProductAnalytics(products, shops).products);
        for (int i = 0; i < shops.size(); i++) {
            Shop shop = shops.get(i);
            /* удаляем из множества productsResult продукты, котрые имеются в магазинах
            * в итоге, если в productsResult имеются продукты, которых нет ни в одном магазине, то они и
            * останутся в этом множестве, а все остальные уберутся */
            productsResult.removeAll(shop.getProducts());
        }
        return productsResult;
    }

    public Set<Product> existOnlyInOne() {
        /* чтобы получить список товаров, которые есть только в одном магазине я воспльзовался следующим способом:
         * 1. создаю пустое множество productsResult
         * 2. прохожу по внешнему циклу, где при каждой итерации создаю список продуктов productList на основании
         * списка продуктов магазина с текущим индексом в списке shops
         * 3. открываю внутренний цикл, где при каждой итерации буду удалять из списка productList те продукты,
         * которые повторяются в других оставшихся магазинах и после прохождения всех итераций записывать
         * содержимое списка productList в множество productsResult
         * 4. в итоге во множестве productsResult должны записаться те продукты, которые содержатся только
         * в одном магазине */
        Set<Product> productsResult = new HashSet<>();
        for (int i = 0; i < shops.size(); i++) {
            Shop shop = shops.get(i);
            List<Product> productList = new ArrayList<>(shop.getProducts());
            for (int j = 0; j < shops.size(); j++) {
                if (j == i && j == shops.size() - 1) {
                    productsResult.addAll(productList);
                }
                /* shops.get(1).getProducts().removeAll(shops.get(1).getProducts()) - недопустимо,
                * так как результат выполнения данного метода оставит список продуктов пустым */
                if (j == i)
                    continue;
                if (j == shops.size() - 1) {
                    productList.removeAll(shops.get(j).getProducts());
                    productsResult.addAll(productList);
                } else
                    productList.removeAll(shops.get(j).getProducts());
            }
        }
        return productsResult;
    }

    public static void main(String[] args) {
        Product product1 = new Product("Мясо");
        Product product2 = new Product("Рыба");
        Product product3 = new Product("Творог");
        Product product4 = new Product("Молоко");
        Product product5 = new Product("Крупа");
        Product product6 = new Product("Консервы");
        Product product7 = new Product("Посуда");
        Product product8 = new Product("Одежда");
        Product product9 = new Product("Фрукты");
        Product product10 = new Product("Соки");

        Product product11 = new Product("Стиральные порошки");
        Product product12 = new Product("Корма для животных");
        Product product13 = new Product("Хлеб");

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        productList1.add(product5);
        productList1.add(product7);
        productList1.add(product10);

        List<Product> productList2 = new ArrayList<>();
        productList2.add(product1);
        productList2.add(product2);
        productList2.add(product3);
        productList2.add(product6);
        productList2.add(product9);
        productList2.add(product10);

        List<Product> productList3 = new ArrayList<>();
        productList3.add(product1);
        productList3.add(product2);
        productList3.add(product4);
        productList3.add(product6);
        productList3.add(product8);


        Shop shop1 = new Shop(productList1);
        Shop shop2 = new Shop(productList2);
        Shop shop3 = new Shop(productList3);

//        System.out.println(shop1);
//        System.out.println(shop2);
//        System.out.println(shop3);

        List<Shop> shops = new ArrayList<>();
        shops.add(shop1);
        shops.add(shop2);
        shops.add(shop3);

        List<Product> allProducts = new ArrayList<>(shops.get(0).getProducts());
        for (int i = 1; i < shops.size(); i++) {
            allProducts.addAll(shops.get(i).getProducts());
        }

        allProducts.add(product11);
        allProducts.add(product12);
        allProducts.add(product13);
        allProducts.add(product8);

        System.out.println(new ProductAnalytics(allProducts, shops).products);
//
        System.out.println(new ProductAnalytics(allProducts, shops).existInAll());
        System.out.println(new ProductAnalytics(allProducts, shops).existAtListInOne());
        System.out.println(new ProductAnalytics(allProducts, shops).notExistInShops());
        System.out.println(new ProductAnalytics(allProducts, shops).existOnlyInOne());
    }
}
