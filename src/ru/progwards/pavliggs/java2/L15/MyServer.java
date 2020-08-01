package ru.progwards.pavliggs.java2.L15;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(40000)) {

            while (true) {
                Socket server = serverSocket.accept();
                new Thread(new RequestHandler(server)).start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class RequestHandler implements Runnable {

    Socket server;

    public RequestHandler(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {
        try (InputStream is = server.getInputStream(); OutputStream os = server.getOutputStream()) {
            Scanner scanner = new Scanner(is);
//            boolean done = false;
//
//            while (!done && scanner.hasNextLine()) {
                String str = scanner.nextLine();

                PrintWriter pw = new PrintWriter(os);
                pw.println("Echo:" + str);

                pw.flush();

//                if (str.toLowerCase().equals("exit"))
//                    done = true;
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
