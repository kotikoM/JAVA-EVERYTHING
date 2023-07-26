package com.efimchick.ifmo.io.filetree;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileTreeImpl fileTree = new FileTreeImpl();
        Path test3 = Path.of(("C:\\Users\\kotik\\OneDrive\\Desktop\\JAVA\\EPAM\\Professional Java SE Development\\file-tree\\src\\test\\resources\\test1"));
        Path myDirectory = Path.of("C:\\Users\\kotik\\OneDrive\\Desktop\\test3");

//        Path temp = Path.of("C:\\Users\\kotik\\OneDrive\\Desktop\\file-tree");
//        String s = (fileTree.tree(test3)).get();
//        System.out.println(s);
        FileWriter fw = new FileWriter("C:\\Users\\kotik\\OneDrive\\Desktop\\FileTree.txt");
        fw.write( fileTree.tree(Path.of("C:\\Users\\kotik\\Onedrive\\Desktop")).get());
        fw.flush();
    }
}
