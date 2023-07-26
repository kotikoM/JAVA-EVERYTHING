package com.epam.rd.autocode.hashtableopen816;


public class HashNode {
    public int key;
    public Object value;

    public HashNode(int key, Object value){
        this.key = key;
        this.value = value;
    }

    public String toString(){
        return "{Key: " + this.key + ", Value: " + value.toString() + "}\n";
    }
}
