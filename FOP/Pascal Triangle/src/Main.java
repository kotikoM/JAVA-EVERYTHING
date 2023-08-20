import java.io.Writer;

public class Main extends fop.w2Five.MiniJava {

    public static int digitCounter(int n) {
        int a = 1;
        while (n >= 10) {
            n = Math.round((float) n / 10);
            a++;
        }
        return a;
    }

    public static void main(String[] args) {
        int n = readInt();
        int b = n;   //b=spaces required to make it look like a triangle

        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j <= i; j++) {
                if (j == 0 | j == i)
                    a[i][j] = 1;              //first and last numbers == 1
                else
                    a[i][j] = a[i - 1][j - 1] + a[i - 1][j]; //adding 2 previous numbers
            }

        }


        for (int i = 0; i < n; i++) {
            writeConsole(i + "-> ");   //decorations


            for (int k = 0; k < b - i - 1; k++) {
                writeConsole(" ");                //triangle

            }


            for (int j = 0; j <= i; j++) {
                writeConsole(a[i][j]);              //printing matrix
                for (int q = 0; q < digitCounter(a[i + 1][q + 1]); q++) {
                    System.out.print(" ");
                }
            }
            writeLineConsole();

        }

    }
}
