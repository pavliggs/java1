package ru.progwards.java1.lessons.basics;

public class Astronomy {
    public static final double PI = 3.14;

    public static Double sphereSquare(Double r) {
        return 4 * PI * Math.pow(r, 2);
    }

    public static Double earthSquare() {
        final double EARTH_RADIUS = 6371.2;
        return sphereSquare(EARTH_RADIUS);
    }

    public static Double mercurySquare() {
        final double MERCURY_RADIUS = 2439.7;
        return sphereSquare(MERCURY_RADIUS);
    }

    public static Double jupiterSquare() {
        final double JUPITER_RADIUS = 71492;
        return sphereSquare(JUPITER_RADIUS);
    }

    public static Double earthVsMercury() {
        return earthSquare() / mercurySquare();
    }

    public static Double earthVsJupiter() {
        return earthSquare() / jupiterSquare();
    }

    public static void main(String[] args) {
        System.out.println(earthSquare());
        System.out.println(mercurySquare());
        System.out.println(jupiterSquare());
        System.out.println(earthVsMercury());
        System.out.println(earthVsJupiter());
    }
}
