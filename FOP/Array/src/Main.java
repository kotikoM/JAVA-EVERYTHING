public class Main extends fop.w2Five.MiniJava {
    public static void main(String[] args) {
        int n = readInt("Input the Size: ");
        int[] a = new int[n];


        {
            for (int i = 0; i < n; i++) {
                {
                    a[i] = readInt();                                //reading array
                }
            }


            int x = readInt("Choose X: ");
            writeConsole("Multiples of X:");
            for (int t = 0; t < n; t++) {
                if (a[t] % x == 0) {

                    writeConsole(a[t] + " ");             //multiple of X
                }
            }
            write("|");


            int max = a[0];
            int b = 1;
            while (b < a.length) {
                if (a[b] > max)
                    max = a[b];
                b++;
            }
            writeConsole("Max:" + max + " ");       //max


            int min = a[0];
            int c = 1;
            while (c < a.length) {
                if (a[c] < min)
                    min = a[c];
                c++;
            }
            writeConsole("Min:" + min + " ");   //min


            int sum = a[0];
            int d = 1;
            while (d < a.length) {
                sum = sum + a[d];
                d++;
            }
            writeConsole("Sum:" + sum + " ");   //sum


            System.out.println("|");


            int A[] = new int[n];
            int T = n - 1;
            writeConsole("Reversed Array: ");
            for (int k = 0; k < n; k++) {
                A[k] = a[T];                                      //Reversing
                T = T - 1;
                writeConsole(A[k] + " ");

            }

            System.out.println("|");

            writeConsole("Values on odd positions: ");

            int N = 0;
            if (n % 2 == 0) {
                N = n / 2;
            }

            if (n % 2 == 1) {
                N = (n - 1) / 2;
            }

            int[] F = new int[N];
            int U = 1;
            for (int G = 0; G < N; G = G + 1) {
                F[G] = a[U];                              //odd values
                while (U < N) {
                    U = U + 2;
                }
                writeConsole(F[G] + " ");
            }


        }
        System.out.println("|");


    }
}
