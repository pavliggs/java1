package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    int a, b;

    public ComplexNum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public ComplexNum add(ComplexNum num) {
        a += num.a;
        b += num.b;
        return new ComplexNum(a, b);
    }

    public ComplexNum sub(ComplexNum num) {
        a -= num.a;
        b -= num.b;
        return new ComplexNum(a, b);
    }

    public ComplexNum mul(ComplexNum num) {
        a = a * num.a - b * num.b;
        b = b * num.a + a * num.b;
        return new ComplexNum(a, b);
    }

    public ComplexNum div(ComplexNum num) {
        a = (a * num.a + b * num.b) / (num.a * num.a + num.b * num.b);
        b = (b * num.a - a * num.b) / (num.a * num.a + num.b * num.b);
        return new ComplexNum(a, b);
    }

    @Override
    public String toString() {
        return a + "+" + b + "i";
    }

    public static void main(String[] args) {
        ComplexNum complexNum = new ComplexNum(1000, 1000);
        System.out.println(complexNum.add(new ComplexNum(99, 99)));
        System.out.println(complexNum.sub(new ComplexNum(1, 1)));
        System.out.println(complexNum.mul(new ComplexNum(99, 99)));
        System.out.println(complexNum.div(new ComplexNum(100, 100)));
    }
}
