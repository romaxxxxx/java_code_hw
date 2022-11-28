package homework01;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Нахождение минимально присутствующих в массиве чисел
        int[] elemArray = {-5, 4, -5, 8, 4, -5, 8, -1};
        minArrayEl(elemArray);

        //Сортировка по ASCII
        String elemLine = "№яzZ@гвб1а8+!";
        asciiSort(elemLine);
    }

    static void asciiSort(String elemLine) {
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

    private static void minArrayEl(int[] elemArray) {
        int count = 1;
        boolean bool = true;
        boolean bool1 = true;
        HashMap<Integer, Integer> elCount = new HashMap<>();
        while (bool) {
            bool = false;
            for (int i = 0; i < (elemArray.length - 2); i++) {
                if (elemArray[i] > elemArray[i + 1]) {
                    int d = elemArray[i + 1];
                    elemArray[i + 1] = elemArray[i];
                    elemArray[i] = d;
                    bool = true;
                }
            }
        }
        while (bool1) {
            bool1 = false;
            for (int i = 0; i < (elemArray.length - 1); i++) {
                if (elemArray[i + 1] == -1) {
                    bool1 = false;
                    break;
                } else if (elemArray[i] == elemArray[i + 1]) {
                    elCount.put(elemArray[i], ++count);
                } else {
                    count = 0;
                    elCount.put(elemArray[i + 1], ++count);
                    bool1 = true;
                }
            }
        }
        int minElCount = Collections.min(elCount.values());
        System.out.println("//////////Нахождение минимально присутствующих в массиве чисел:");
        System.out.println("Начальный массив: " + Arrays.toString(elemArray));
        System.out.println("Минимально повторяемые элементы в массиве:");
        for (Map.Entry<Integer, Integer> entry : elCount.entrySet()) {
            if (entry.getValue().equals(minElCount)) {
                System.out.println(entry.getKey());
            }
        }
    }
}
