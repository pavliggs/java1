package ru.progwards.java2.lessons.http;

import ru.progwards.java2.lessons.http.model.Account;
import ru.progwards.java2.lessons.http.service.AccountService;
import ru.progwards.java2.lessons.http.service.StoreService;
import ru.progwards.java2.lessons.http.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.http.service.impl.StoreServiceImpl;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ATMClient implements AccountService {
      private Socket socket;

    public ATMClient() {
        try {
            socket = new Socket("localhost", 50000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // метод пишет запросы на сервер для каждого из методов
    private void writeRequest(String method, Account account1, Account account2, double amount, PrintWriter pw) {
        switch (method) {
            case "balance":
                pw.println("GET/balance?account=" + account1.getId() + " HTTP/1.1");
                break;
            case "deposit":
                pw.println("GET/deposit?account=" + account1.getId() + "&amount=" + amount + " HTTP/1.1");
                break;
            case "withdraw":
                pw.println("GET/withdraw?account=" + account1.getId() + "&amount=" + amount + " HTTP/1.1");
                break;
            default:
                pw.println("GET/transfer?account=" + account1.getId() + "&account=" + account2.getId() +
                        "&amount=" + amount + " HTTP/1.1");
        }
        pw.println("hostname: localhost");
        pw.println("");
        pw.println("exit");
        pw.flush();
    }

    // метод печатает на консоли ответ от сервера
    private void printMessage(InputStream is) {
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }

    @Override
    public double balance(Account account) {
        try(InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
            PrintWriter pw = new PrintWriter(os);
            writeRequest("balance", account, null, 0, pw);
            printMessage(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public void deposit(Account account, double amount) {
        try(InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
            PrintWriter pw = new PrintWriter(os);
            writeRequest("deposit", account, null, amount, pw);
            printMessage(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void withdraw(Account account, double amount) {
        try(InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
            PrintWriter pw = new PrintWriter(os);
            writeRequest("withdraw", account, null, amount, pw);
            printMessage(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void transfer(Account from, Account to, double amount) {
        try(InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
            PrintWriter pw = new PrintWriter(os);
            writeRequest("transfer", from, to, amount, pw);
            printMessage(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StoreService storeService = new StoreServiceImpl();
        AccountServiceImpl accService = new AccountServiceImpl(storeService);
        ATMClient atmClient = new ATMClient();
//        atmClient.balance(accService.getService().get("100"));
//        atmClient.deposit(accService.getService().get("100"), 100_000);
        atmClient.balance(accService.getService().get("100"));
//        atmClient.balance(accService.getService().get("103"));
//        atmClient.transfer(accService.getService().get("100"), accService.getService().get("103"), 200_000);
    }
}
