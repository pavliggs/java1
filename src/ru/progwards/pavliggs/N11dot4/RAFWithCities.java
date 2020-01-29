package ru.progwards.pavliggs.N11dot4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RAFWithCities extends RandomAccessFile {
    //свойства класса
    private long start = 0;
    private long end;
    //в эту переменную будем подставлять город, который будет найден
    public CityGeoLocation cityGeoLocation = new CityGeoLocation();

    //в конструкторе используем конструктор родителя и используем режим чтения
    public RAFWithCities(String pathName) throws FileNotFoundException {
        super(pathName, "r");
    }

    //вложенный класс, в котором создаём объект на основании строки с наименованием города и координатами
    public static class CityGeoLocation {
        public String city;
        public double lat;
        public double lon;

        //конструктор по умолчанию
        public CityGeoLocation() {
            city = "";
        }

        //конструктор с параметром в виде строки
        public CityGeoLocation(String cityStr) {
            //если вдруг строка будет равна null
            if (cityStr == null)
                return;

            //делаем из строки массив
            String[] cityArr = cityStr.split(",");
            //нулевой элемент - это название города
            city = cityArr[0].trim().toUpperCase();
            if (cityArr.length > 1)
                lat = Double.parseDouble(cityArr[1].trim());
            if (cityArr.length > 2)
                lon = Double.parseDouble(cityArr[2].trim());
        }

        @Override
        public String toString() {
            return city + " (" + lat + ", " + lon + ")";
        }
    }

    //метод будет получать какую-то позицию в какой-то строке и переводить позицию на начало строки
    private long startStringPosition(long pos) throws IOException {
        //ищем символ конца строки 0x0D (\r) или 0x0A (\n)
        for (long i = pos; i >= 0 ; i--) {
            //устанавливаем курсор на текущую позицию
            seek(i);
            //читаем символ который стоит на этой позиции
            int byteChar = read();
            /*символ на котором стоит курсор равен символу обозначающему конец строки, то переводим курсор
            * на единицу вперед (то есть на начало строки, которая идёт ниже)*/
            if (byteChar == 0x0D || byteChar == 0x0A)
                return i + 1;
        }
        return 0;
    }

    //метод будет возвращать true, если определённый город будет найден
    public boolean findCity(String city) throws IOException {
        //приведём строку к установленному виду (убираем пробелы ДО и ПОСЛЕ и переводим в верхний регистр)
        city = city.trim().toUpperCase();

        start = 0;
        //переменной end присваиваем длину файла (это и будет координатой конца файла)
        end = length();

        //ищем переданный город в цикле
        while (start != end) {
            /*присваиваем current результат выполнения метода startStringPosition (выдаст позицию начала строки,
             * на которой лежит передаваемая позиция)*/
            long current = startStringPosition((start + end) / 2);
            seek(current);
            //читаем эту строку
            String currentLine = readLine();
            if (currentLine == null)
                return false;
            //переводим из старой кодировки в новую
            currentLine = new String(currentLine.getBytes("ISO-8859-1"), "UTF-8");
            //меняем значение переменной cityGeoLocation на объект класса CityGeoLocation с параметром найденной строки
            cityGeoLocation = new CityGeoLocation(currentLine);
            //сравниваем переданный город с городом, который определился в cityGeoLocation
            int compareResult = city.compareTo(cityGeoLocation.city);
            //если равны, то город найден
            if (compareResult == 0)
                return true;
                //если переданный город больше, то ищем его в блоке ниже и меняем координату start на текущую позицию из файла
            else if (compareResult > 0)
                start = getFilePointer();
                //если наоборот, то ищем город в блоке выше и меняем координату end на current
            else
                end = current;
        }
        return false;
    }

    public static void main(String[] args) {
        try(RAFWithCities rafWithCities = new RAFWithCities("C:/Users/Эльдорадо/Desktop/TestJava/CityGeoLocation.txt")) {
           if (rafWithCities.findCity("Котлас"))
               System.out.println(rafWithCities.cityGeoLocation);
            if (rafWithCities.findCity("Воронеж"))
                System.out.println(rafWithCities.cityGeoLocation);
            else {
                System.out.println("Такой город не найден");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
