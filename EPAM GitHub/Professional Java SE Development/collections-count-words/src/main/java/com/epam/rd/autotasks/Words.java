package com.epam.rd.autotasks;


import java.util.*;

public class Words {


    public static List<String> linesToWords(List<String> linesUNFILTERED) {

        List<String> lines = new ArrayList<>();

        for (String line : linesUNFILTERED) {
            String[] lineWords = line.
                    toLowerCase().
                    replaceAll("[^0-9a-я]", " ").
                    replace("  ", " ").
                    split(" ");


            lines.addAll(Arrays.asList(lineWords));
        }

        return lines;
    }


    public static String countWords(List<String> linesUNFILTERED) {

        List<String> lines = linesToWords(linesUNFILTERED);

        HashMap<String, Integer> words = new HashMap<>();

        //create appropriate hashmap
        //short words requirement considered
        for (String currentWord : lines) {
            //proceed if word is 4 characters longer
            if (currentWord.length() >= 4) {

                if (words.containsKey(currentWord)) {
                    //add previous count 1
                    int counter = words.get(currentWord);
                    counter++;
                    words.put(currentWord, counter);
                } else {
                    //hashmap does not contain key
                    words.put(currentWord, 1);
                }

            }
        }


        // Sort keys based on value numbers
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(words.entrySet());

        // Sort the entries using a custom Comparator
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                int valueComparison = entry2.getValue().compareTo(entry1.getValue());
                if (valueComparison == 0) {
                    // If values are equal, sort based on the associated words in ascending order
                    return entry1.getKey().compareTo(entry2.getKey());
                }
                return valueComparison;
            }
        });


        StringBuilder wordsToString = new StringBuilder();

        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() >= 10) {
                wordsToString.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
            }
        }

        int lastIndex = wordsToString.lastIndexOf("\n");
        wordsToString.replace(lastIndex, lastIndex + 1, "");

        return wordsToString.toString();
    }

    public static void main(String[] args) {

        List<String> temp = new ArrayList<>();
        temp.add("манул красив.");
        temp.add("котенок игрив.");
        temp.add("тигруля в полете.");
        temp.add("Пушкин - наше все");

        System.out.println(linesToWords(temp));

    }
}
