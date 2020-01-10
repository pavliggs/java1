package ru.progwards.java1.lessons.interfaces;

public class Food implements CompareWeight{
    private int weight;

    Food(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Food food = (Food) smthHasWeigt;
        if (getWeight() < food.getWeight())
            return CompareResult.LESS;
        else if (getWeight() == food.getWeight())
            return CompareResult.EQUAL;
        else
            return CompareResult.GREATER;
    }

    public static void main(String[] args) {
        System.out.println(new Cow(3000).compareWeight(new Hamster(200)));
    }
}
