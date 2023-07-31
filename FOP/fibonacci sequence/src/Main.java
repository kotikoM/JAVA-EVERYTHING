public class Main {

    public static long[] fibonacciCache;
    public static void main(String[] args) {
        int n = 92;
        fibonacciCache = new long[n + 1];

        for (int i = 0; i <= n; i++) {
            System.out.print(fibonacci(i)+" ");
    }
    }

    private static long fibonacci(int n) {
        if (n<=1){
            return n;
        }

        if (fibonacciCache[n] !=0){
            return fibonacciCache[n];
        }

        long nthfibonaccinumber=(fibonacci(n-1)+fibonacci(n-2));
        fibonacciCache[n]=nthfibonaccinumber;
        return (fibonacci(n-1)+fibonacci(n-2));

    }
}