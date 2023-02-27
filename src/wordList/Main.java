package wordList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    static TreeMap<String, Integer> getWordsFromFile(String filePath) {
        File file = new File(filePath);
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[,\\.\\?\\!]", " ").trim().toLowerCase();
                for (String word: line.split("\\s+")) {
                    if (wordMap.get(word) != null) {
                        wordMap.put(word, wordMap.get(word) + 1);
                    } else {
                        wordMap.put(word, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Failed to read file");
        }
        return wordMap;
    }

    // sort map by value
    static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> map) {
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            list.add(null);
        }
        return sortedMap;
    }

    public static void main(String[] args) {
        TreeMap<String, Integer> wordMap = getWordsFromFile("./src/wordList/words.txt");
        System.out.println(wordMap);
    }
}
