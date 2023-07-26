package com.epam.rd.autotasks;


import java.util.*;
import java.util.stream.Collectors;

public class Words {
    public String countWords(List<String> linesUNFILTERED) {
        Map<String, Integer> wordsToKey = linesUNFILTERED.stream()
                .flatMap(line -> Arrays.stream(line
                        .toLowerCase()
                        .replaceAll("[^0-9a-я]", " ")
                        .replace("  ", " ")
                        .split(" ")))
                .filter(word -> word.length() >= 4)
                .collect(Collectors.toConcurrentMap(
                        word -> word,
                        word -> 1,
                        Integer::sum
                ));

        return wordsToKey.entrySet().stream()
                .filter(entry -> entry.getValue() >= 10)
                .sorted(
                        Map.Entry.<String, Integer>comparingByValue()
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
