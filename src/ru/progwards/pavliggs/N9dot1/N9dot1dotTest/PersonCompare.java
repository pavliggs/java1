package ru.progwards.pavliggs.N9dot1.N9dot1dotTest;

abstract class PersonCompare {
    public int compare(Person p1, Person p2) {
        return 0;
    }

    public static void main(String[] args) {
        Person person1 = new Person("Maria");
        Person person2 = new Person("Pavel");

        //инициализация экземпляра абстрактного класса при помощи анонимного класса, который переопределяет метод compare
        PersonCompare personCompare = new PersonCompare() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.name.compareTo(p2.name);
            }
        };

        System.out.println(personCompare.compare(person1, person2));
    }
}

