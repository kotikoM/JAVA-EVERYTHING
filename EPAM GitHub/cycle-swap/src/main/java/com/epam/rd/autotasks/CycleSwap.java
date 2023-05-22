package com.epam.rd.autotasks;

class CycleSwap {
    static void cycleSwap(int[] array) {
        if (array.length!=0) {
            int lastElem = array[array.length - 1];

            for (int i = array.length - 1; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = lastElem;
        }
    }

    static void cycleSwap(int[] array, int shift) {
        //repeat cycleSwap shift amount of times
        if (array.length!=0) {
            while (shift > 0) {
                int lastElem = array[array.length - 1];

                for (int i = array.length - 1; i > 0; i--) {
                    array[i] = array[i - 1];
                }
                array[0] = lastElem;

                shift--;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 7, 4};
        cycleSwap(array, 2);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }

    }
}
