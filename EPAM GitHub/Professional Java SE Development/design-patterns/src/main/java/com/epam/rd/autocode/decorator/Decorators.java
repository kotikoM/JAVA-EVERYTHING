package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        List<String> filteredList = new ArrayList<>();

        //filter out odd index elements and
        //add even indexed elements to filteredList
        for (int i = 0; i < sourceList.size(); i = i + 2) {
            filteredList.add(sourceList.get(i));
        }


        return filteredList;
    }


}
