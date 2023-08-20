public class Main {
    public static void main(String[] args) {
        int x, y;
        x=(4576);
        y=(48);
        while (x!=y)
            if (x<y)
                y=y-x;
            else
                x=x-y;
            System.out.println("gcd-" +x);
    }
}