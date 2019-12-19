package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    int a, b;

    public ComplexNum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public String toString() {
        return a + "+" + b + "i";
    }

    public ComplexNum add(ComplexNum num) {
        System.out.println((a + num.a) + " + " + (b + num.b) + "i");
        return num;
    }

    public ComplexNum sub(ComplexNum num) {
        System.out.println((a - num.a) + " + " + (b - num.b) + "i");
        return num;
    }

    public ComplexNum mul(ComplexNum num) {
        System.out.println((a * num.a - b * num.b) + " + " + (b * num.a + a * num.b) + "i");
        return num;
    }

    public ComplexNum div(ComplexNum num) {
        System.out.println((double)(a * num.a + b * num.b) / (num.a * num.a + num.b * num.b) +
                " + " + ((double)(b * num.a - a * num.b) / (num.a * num.a + num.b * num.b)) + "i");
        return num;
    }

    public static void main(String[] args) {
        ComplexNum complexNum = new ComplexNum(2, 5);
        complexNum.add(new ComplexNum(5, 8));
        complexNum.sub(new ComplexNum(5, 8));
        complexNum.mul(new ComplexNum(5, 8));
        complexNum.div(new ComplexNum(6, 2));
    }
}
