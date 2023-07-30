import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        BinaryTree tree = new BinaryTree();

        int[] elements3 = {405, 704, 320, 152, 230, 44, 52, 979, 781, 71, 881, 515, 170, 928,
                753, 437, 237, 522, 208, 9, 87, 157, 689, 5, 143, 345, 699, 386, 726, 650, 171, 229, 56, 615, 98};

//        for (int element : elements3) {
//            tree.insert(element);
//        }

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            tree.insert(random.nextInt(100000));
        }

        long start = System.currentTimeMillis();

        String s = (tree.prettyPrint());

        long end = System.currentTimeMillis();

        System.out.printf("it took %d ms", (end- start));

        FileWriter fw = new FileWriter("C:\\Users\\kotik\\OneDrive\\Desktop\\BinaryTreeRes.txt");
        fw.write(s);
        fw.flush();


    }
}
