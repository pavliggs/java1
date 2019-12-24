package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    int a, b, a1, b1;

    public ComplexNum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    /*
    параметром метода будет новый созданный объект со своими параметрами, далее по формуле производятся
    действия параметров первого объекта с параметрами второго объекта, результат которых присваивается новым
    переменным, которые будут являтся параметрами нового объекта, который возвращается в методе
    (возвращается ссылка на объект)
    */
    public ComplexNum add(ComplexNum num) {
        a1 = a + num.a;
        b1 = b + num.b;
        return new ComplexNum(a1, b1);
    }

    public ComplexNum sub(ComplexNum num) {
        a1 = a - num.a;
        b1 = b - num.b;
        return new ComplexNum(a1, b1);
    }

    public ComplexNum mul(ComplexNum num) {
        a1 = a * num.a - b * num.b;
        b1 = b * num.a + a * num.b;
        return new ComplexNum(a1, b1);
    }

    public ComplexNum div(ComplexNum num) {
        a1 = (a * num.a + b * num.b) / (num.a * num.a + num.b * num.b);
        b1 = (b * num.a - a * num.b) / (num.a * num.a + num.b * num.b);
        return new ComplexNum(a1, b1);
    }

    @Override
    public String toString() {
        return a + "+" + b + "i";
    }

    public static void main(String[] args) {
        ComplexNum complexNum = new ComplexNum(99, 98);
        System.out.println(complexNum);
        System.out.println(new ComplexNum(500, 700));
        System.out.println(complexNum.add(new ComplexNum(99, 99)));
        System.out.println(complexNum.sub(new ComplexNum(1, 2)));
        System.out.println(complexNum.mul(new ComplexNum(99, 99)));
        System.out.println(complexNum.div(new ComplexNum(100, 100)));
    }
}
