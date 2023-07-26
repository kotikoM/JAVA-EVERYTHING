package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){

        return new Iterator<Integer>() {
            private int currentIndex = 0;
            private int clock = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < array.length;
            }

            @Override
            public Integer next() {


                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (clock == 0) {
                    int currentElement = array[currentIndex];
                    clock ++;
                    return currentElement;
                }
                if (clock == 1) {
                    int currentElement = array[currentIndex++];
                    clock = 0;
                    return currentElement;
                }


                return null;
            }
        };

    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int currentIndex = 0;
            private int clock = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < array.length;
            }

            @Override
            public Integer next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (clock == 0) {
                    int currentElement = array[currentIndex];
                    clock ++;
                    return currentElement;
                }
                if (clock == 1) {
                    int currentElement = array[currentIndex];
                    clock ++;
                    return currentElement;
                }
                if (clock == 2) {
                    int currentElement = array[currentIndex++];
                    clock = 0;
                    return currentElement;
                }



                return null;
            }
        };
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int currentIndex = 0;
            private int clock = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < array.length;
            }

            @Override
            public Integer next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (clock == 0) {
                    int currentElement = array[currentIndex];
                    clock ++;
                    return currentElement;
                }
                if (clock == 1) {
                    int currentElement = array[currentIndex];
                    clock ++;
                    return currentElement;
                }
                if (clock == 2) {
                    int currentElement = array[currentIndex];
                    clock ++;
                    return currentElement;
                }
                if (clock == 3) {
                    int currentElement = array[currentIndex];
                    clock ++;
                    return currentElement;
                }
                if (clock == 4) {
                    int currentElement = array[currentIndex++];
                    clock = 0;
                    return currentElement;
                }



                return null;
            }
        };
    }

    public static Iterable<String> table(String[] columns, int[] rows){
        return () -> new Iterator<String>() {
            private int columnsIndex = 0;
            private int rowsIndex = 0;
            @Override
            public boolean hasNext() {
                return columnsIndex < columns.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                String result = columns[columnsIndex] + rows[rowsIndex];

                rowsIndex++;

                if (rowsIndex >= rows.length) {
                    rowsIndex = 0;
                    columnsIndex++;
                }

                return result;
            }
        };
    }



}
