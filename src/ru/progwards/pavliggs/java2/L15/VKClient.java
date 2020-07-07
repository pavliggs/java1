package ru.progwards.pavliggs.java2.L15;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class VKClient {
    public static void main(String[] args) {
        try(Socket client = new Socket("vk.com", 80)) {
            String request = "GET / HTTP/1.1 \n" +
                            "host:vk.com\n\n";

            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

            PrintWriter pw = new PrintWriter(os);
            pw.println(request);
            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";

            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

//        try(Socket client2 = new Socket("time-A.timefreq.bldrdoc.gov", 13)) {
//            // принимаем данные
//            InputStream is = client2.getInputStream();
//            // отправляем данные
//            OutputStream os = client2.getOutputStream();
//
//            Scanner scanner = new Scanner(is);
//
////            while (scanner.hasNextLine()) {
////                System.out.println(scanner.nextLine());
////            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String str = "";
//
//            while ((str = br.readLine()) != null) {
//                System.out.println(str);
//            }
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }
}
