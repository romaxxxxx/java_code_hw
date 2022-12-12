package homework02.people;

import java.lang.reflect.Array;

public class People {
    String name;
    Double weight;

    People(String name, Double weight) {
        this.name = name;
        this.weight = weight;
    }

    static void sort(People[] people) {
        People[] temp = new People[1];
        for (int i = 0; i < people.length - 1; i++) {
            for (int j = 0; j < people.length - 1; j++) {
                if (people[i].weight > people[i + 1].weight) {
                    temp[0] = people[j + 1];
                    people[j + 1] = people[j];
                    people[j] = temp[0];
                }
            }
        }
        System.out.println("Отсортированные люди по возрастанию веса:");
        for (People men : people) {
            System.out.println(men.name + " " + men.weight);
        }
    }
}