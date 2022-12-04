package homework01;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Нахождение минимально присутствующих в массиве чисел
        Integer[] elemArray = {-5, 4, 4, 8,-5, 9, 4, 8, 9, 9, -5, -1,-5};
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

    private static void minArrayEl(Integer[] elemArray) {
        System.out.println("//////////Нахождение минимально присутствующих в массиве чисел:");
        System.out.println("Начальный массив: " + Arrays.toString(elemArray));
        HashMap<Integer, Integer> elCount = new HashMap<>();
        int count=0;
        for (int i = 0; elemArray[i] != -1; i++) {
            if(elemArray[i]!=400) {
                elCount.put(elemArray[i], ++count);
            }
            for (int j =i + 1; j<elemArray.length; j++) {
                if(elemArray[i]==400) break;
                else if(elemArray[j]==-1) {
                    elemArray[i] = 400;
                    count=0;
                    break;
                }else if (elemArray[i] == elemArray[j]) {
                    elCount.put(elemArray[i], ++count);
                    elemArray[j]=400;
                }
            }
        }
        int minElCount = Collections.min(elCount.values());
        System.out.println("Минимально повторяемые элементы в массиве:");
        for (Map.Entry<Integer, Integer> entry : elCount.entrySet()) {
            if (entry.getValue().equals(minElCount)) {
                System.out.println(entry.getKey());
            }
        }
    }
}

