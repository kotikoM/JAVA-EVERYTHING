import java.util.Random;

public class Second extends fop.w3simple.MiniJava {
    public static int Searcher(int[][] RandomNums, int x, int a, int I, int J) {

        if (J == -1 & I == -1)
            return a;
        if (J == -1)
            return Searcher(RandomNums, x, a, I - 1, J);
        if (RandomNums[I][J] == x)
            return Searcher(RandomNums, x, a + 1, I, J - 1);


        else return Searcher(RandomNums, x, a, I, J - 1);


    }

    public static void main(String[] args) {
        int n = readInt("Rows:");
        int m = readInt("Columns");
        int[][] RandomNums = new int[n][m];
        int x = readInt("Number to Search For:");
        int a = 0; //counter
        int I = n - 1;
        int J = m - 1;

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                RandomNums[i][j] = rand.nextInt(1, 11);
                System.out.print(RandomNums[i][j] + " ");
            }
            System.out.println();

        }
        System.out.println();
        System.out.println(Searcher(RandomNums, x, a, I, J));


    }
}
