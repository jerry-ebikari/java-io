package wordList;

import java.io.*;
import java.util.*;

public class Main {
    // gets words and frequency of occurence from a file
    static TreeMap<String, Integer> getWordsFromFile(String filePath) {
        File file = new File(filePath);
        TreeMap<String, Integer> wordMap = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // remove specific punctuation
                line = line.replaceAll("[,.?!]", " ").trim().toLowerCase();
                // increment frequency if word exists, set to 1 if it doesn't
                for (String word: line.split("\\s+")) {
                    wordMap.merge(word, 1, Integer::sum);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to read file");
        }
        return wordMap;
    }

    // sorts map by value in descending order
    static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> map) {
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, Comparator.reverseOrder());
        // put elements in sorted map in the order that they appear in the list
        for (Integer num: list) {
            for (Map.Entry<String, Integer> entry: map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        return sortedMap;
    }

    // writes content of map to a file
    static void writeMapToFile(Map<String, Integer> map, String filePath) {
        File file = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // write word and frequency to file
            for (String word: map.keySet()) {
                bw.write(word + " " + map.get(word));
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filePath);
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        TreeMap<String, Integer> wordMap = getWordsFromFile("words.txt");
        writeMapToFile(wordMap, "wordsListedAlphabetically.txt");
        writeMapToFile(sortMapByValue(wordMap), "wordsSortedByFrequency.txt");
    }
}
