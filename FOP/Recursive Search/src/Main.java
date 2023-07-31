
import java.util.Random;
public class Main extends fop.w3simple.MiniJava {
    public static int Searcher(int [] RandomNums, int x, int a, int j){
        if (j==-1)
            return a;
        if (RandomNums[j]==x)
            return Searcher(RandomNums, x, a+1 , j - 1);

        else return Searcher(RandomNums, x, a, j-1);
    }

    public static void main(String[] args) {
       int n = readInt("Array Size:") ;
        int [] RandomNums=new int[n];
        int a=0; //counter
        int j=n-1;
        int x=readInt("Number to Search For:");
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            RandomNums[i]= rand.nextInt(1,11);
            System.out.print(RandomNums[i]+" ");
        }
        System.out.println();
        System.out.println(Searcher(RandomNums, x, a, j));



    }
}