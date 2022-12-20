package homework03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String str = "ааа ааа ббб ггг ддд ааа ддд ддд";
        wordCount(str);
        ArrayList<String> strList = new ArrayList<>();
        strList.add("ааа");
        strList.add("ббб");
        strList.add("ааа");
        strList.add("ааа");
        strList.add("ддд");
        strList.add("ааа");
        strList.add("ддд");
        strList.add("rrr");
        dublsRemovingAlg(strList);
        strList.add("ааа");
        strList.add("ббб");
        strList.add("ааа");
        strList.add("ааа");
        strList.add("ддд");
        strList.add("ааа");
        strList.add("ддд");
        strList.add("rrr");
        dublsRemovingSet(strList);
        dublsRemovingStream(strList);
    }

    static void wordCount(String str) {
        String[] wordArray = str.split(" ");
        HashMap<String, Integer> elCount = new HashMap<>();
        int count = 0;
        ArrayList<String> countedElements = new ArrayList<>();
        for (String word : wordArray) {
            if (!elCount.containsKey(word)) {
                elCount.put(word, 0);
            }
            elCount.put(word, elCount.get(word) + 1);
        }
        System.out.println("Счетчик слов: " + elCount.toString());
    }

    static void dublsRemovingAlg(ArrayList strList) {
        for (int i = 0; i < strList.size(); i++) {
            for (int j = i + 1; j < strList.size(); j++) {
                if (strList.get(i) == strList.get(j)) {
                    strList.remove(j);
                    j--;
                }
            }
        }
        System.out.println("Список без дублей (алгоритм): " + strList.toString());
    }

    static void dublsRemovingSet(ArrayList strList) {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(strList);
        System.out.println("Список без дублей (Set): " + hashSet.toString());
    }
    static void dublsRemovingStream(ArrayList strList){
        System.out.println("Список без дублей (stream): " + strList.stream().distinct().collect(Collectors.toList()).toString());
    }
}