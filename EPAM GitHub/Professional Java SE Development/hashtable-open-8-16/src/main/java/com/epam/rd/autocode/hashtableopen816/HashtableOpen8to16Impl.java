package com.epam.rd.autocode.hashtableopen816;

import java.util.*;

public class HashtableOpen8to16Impl implements HashtableOpen8to16 {

    private final int INITIAL_CAPACITY = 8;
    private final int MAXIMUM_CAPACITY = 16;

    private HashNode[] buckets;

    public HashtableOpen8to16Impl() {
        this.buckets = new HashNode[INITIAL_CAPACITY];
    }

    @Override
    public void insert(int key, Object value) {
        if (isFull(buckets) && buckets.length == MAXIMUM_CAPACITY && !containsKey(keys(), key)) {
            throw new IllegalStateException("Hashtable is at its maximum capacity");
        }

        if (isFull(buckets)) {
            if (!containsKey(keys(), key)) {
                // resizing
                HashNode[] oldTable = buckets;
                int newLength = Math.min(oldTable.length * 2, MAXIMUM_CAPACITY);
                buckets = new HashNode[newLength];

                for (int i = 0; i < oldTable.length; i++) {
                    // insert old elements
                    if (oldTable[i] != null) {
                        int index = linearProbe(oldTable[i].key);
                        buckets[index] = oldTable[i];
                    }
                }

                // recursively insert new element using the updated buckets array
                insert(key, value);
            } else {
                // update the old value
                int index = linearProbe(key);
                buckets[index] = new HashNode(key, value);
            }
        } else {
            // insert new element
            int index = linearProbe(key);
            buckets[index] = new HashNode(key, value);
        }
    }

    @Override
    public Object search(int key) {
        // Linear search for the key
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && buckets[i].key == key) {
                return buckets[i].value;
            }
        }

        return null; // Key not found
    }

    @Override
    public void remove(int key) {
        if (containsKey(keys(), key)) {
            int index = linearProbe(key);
            int startIndex = index;

            while (buckets[index] == null || (buckets[index] != null && buckets[index].key != key)) {
                index = (++index) % buckets.length;
                if (index == startIndex) {
                    return; // Key not found, exit the loop
                }
            }

            // Remove the element
            buckets[index] = null;

            int numOfNodes = 0;
            for (HashNode node : buckets) {
                if (node != null) {
                    numOfNodes++;
                }
            }

            double sizeToCapacityRatio = (double) numOfNodes / buckets.length;

            if (sizeToCapacityRatio <= 0.25) {
                // Resize the hashtable
                HashNode[] oldTable = buckets;
                int newLength = Math.max(buckets.length / 2, 2);
                buckets = new HashNode[newLength];

                for (HashNode hashNode : oldTable) {
                    if (hashNode != null && hashNode.key != key) {
                        insert(hashNode.key, hashNode.value);
                    }
                }
            }
        }
    }

    @Override
    public int size() {
        return buckets.length;
    }

    @Override
    public int[] keys() {
        int[] keys = new int[buckets.length];

        for (int i = 0; i < buckets.length; i++) {
            keys[i] = (buckets[i] == null) ? 0 : buckets[i].key;
        }

        return keys;
    }

    private boolean containsKey(int[] keys, int key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == key) {
                return true;
            }
        }

        return false;
    }

    private boolean isFull(HashNode[] buckets) {
        for (HashNode hashNode : buckets) {
            if (hashNode == null) {
                return false;
            }
        }
        return true;
    }

    private int linearProbe(int key) {
        int index = (Math.abs(key)) % buckets.length;

        // Linear probing
        while (buckets[index] != null && buckets[index].key != key) {
            index = (++index) % buckets.length;
        }

        return index;
    }

    public String toString() {
        return Arrays.toString(buckets);
    }
}