public class Main extends fop.intro.MiniJava {
    public static void main(String[] args) {
        int N, x, i, a, b;
        N=readInt("Enter N");
        x=readInt("Enter X");
        i=0;
        a=0;

        if (x == 1) {

            while (x <= N) {
                if (N % x == 0) {                   //factors
                    i = i + 1;                      //number of factors
                    a = x + a;                      //sum of factors
//                    System.out.println("factors-");
                    System.out.print(x+" ");
                }
                x = x + 1;
            }
            System.out.println();
            System.out.println("number of factors-"+i);
            System.out.println("sum of factors-"+ a);

        }
        else {
            b=0;
            while (b<=N){
                if (b % x==0)
                    System.out.println("Numbers divisible by x-"+ b);
                b=b+1;                                                 //numbers divisible by X
            }

        }






    }
}