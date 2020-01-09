package ru.progwards.pavliggs;

//<T> - обобщение для классов (Generic)
public class GenericExample<D> {
    D object;

    public GenericExample(D object) {
        this.object = object;
    }

    public String getObjectInfo() {
        //возращаем имя вот этого класса <D>
        return object.getClass().getName();
    }

    public static void main(String[] args) {
        System.out.println(new GenericExample<String>("Hello!").getObjectInfo());
    }
}
