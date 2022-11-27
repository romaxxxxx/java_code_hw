package homework01;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Нахождение минимально присутствующих в массиве чисел
        int[] mass = {-5, 4, -5, 8, 4, -5, 8, -1};
        int count = 1;
        boolean bool = true;
        boolean bool1 = true;
        HashMap<Integer, Integer> elCount = new HashMap<>();
        while (bool) {
            bool = false;
            for (int i = 0; i < (mass.length - 2); i++) {
                if (mass[i] > mass[i + 1]) {
                    int d = mass[i + 1];
                    mass[i + 1] = mass[i];
                    mass[i] = d;
                    bool = true;
                }
            }
        }
        while (bool1) {
            bool1 = false;
            for (int i = 0; i < (mass.length - 1); i++) {
                if (mass[i + 1] == -1) {
                    bool1 = false;
                    break;
                } else if (mass[i] == mass[i + 1]) {
                    elCount.put(mass[i], ++count);
                } else {
                    count = 0;
                    elCount.put(mass[i + 1], ++count);
                    bool1 = true;
                }
            }
        }
        int minElCount = Collections.min(elCount.values());
        System.out.println("//////////Нахождение минимально присутствующих в массиве чисел:");
        System.out.println("Начальный массив: " + Arrays.toString(mass));
        System.out.println("Минимально повторяемые элементы в массиве:");
        for (Map.Entry<Integer, Integer> entry : elCount.entrySet()) {
            if (entry.getValue().equals(minElCount)) {
                System.out.println(entry.getKey());
            }
        }

        //Сортировка по ASCII
        String elemLine = "№яzZ@гвб1а8+!";
        int len = elemLine.length();
        char[] charArray = new char[len];
        for (int i = 0; i < elemLine.length(); i++) {
            charArray[i] = elemLine.charAt(i);
        }
        Arrays.sort(charArray);
        System.out.println("///////////Сортировка по ASCII");
        System.out.println("Строка для сортировки:" + " " + elemLine);
        System.out.println("Отсортированный массив по ASCII:" + Arrays.toString(charArray));
    }
}
