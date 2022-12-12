package homework02.people;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        People[] people = new People[10];
        double weight = 0;
        String name = "";
        for (int i = 0; i < people.length; i++) {
            System.out.println("Введите имя");
            Scanner nameScanner = new Scanner(System.in);
            name = nameScanner.nextLine();
            while (true) {
                System.out.println("Введите вес для" + " " + name);
                Scanner weightScanner = new Scanner(System.in);

                try {
                    weight = Double.parseDouble(weightScanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Руки правят за углом");

                }
            }
            people[i] = new People(name, weight);
        }
        People.sort(people);
    }
}