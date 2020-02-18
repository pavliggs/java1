package ru.progwards.pavliggs.N13dot3;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetWithComparator {

    public static class GeoPoint implements Comparable<GeoPoint>{
        double lat;
        double lon;

        public GeoPoint(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        public int compareTo(GeoPoint o) {
            return Double.compare(dist2Zero(lat, lon), dist2Zero(o.lat, o.lon));
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    '}';
        }
    }

    // метод вычисляет расстояние от точки 0,0 до точки с переданными координатами
    public static double dist2Zero(double lat, double lon) {
        return 111.111*Math.sqrt(lon*lon + lat*Math.cos(Math.PI*lon/180)*lat*Math.cos(Math.PI*lon/180));
    }

    public static void main(String[] args) {
        TreeSet<GeoPoint> treeSet = new TreeSet<>(new Comparator<GeoPoint>() {
            @Override
            public int compare(GeoPoint o1, GeoPoint o2) {
                return Double.compare(o1.lat, o2.lat);
            }
        });
        treeSet.add(new GeoPoint(55.751091, 37.6135763)); // Москва
        treeSet.add(new GeoPoint(43.1736206, 131.8957547)); // Владивосток
        treeSet.add(new GeoPoint(54.969655, 82.6692294)); // Новосибирск

        System.out.println(treeSet);
    }
}
