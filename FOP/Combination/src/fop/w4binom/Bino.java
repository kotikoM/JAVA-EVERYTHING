package fop.w4binom;

public class Bino extends MiniJava {

    static int bino(int n, int k) {
        if (k == 0) return 1;
        else if (k == n) return 1;
        else return bino(n - 1, k - 1) + bino(n - 1, k);

    }

    public static void main(String[] args) {
        int n = readInt("n:");
        int k = readInt("k:");
        if (n >= k && k >= 0) {
            System.out.println(bino(n, k));
        } else System.out.println("Invalid input");


    }
}
