public class Main {
    public static void main(String[] args) {
        int[] a= new int [100];
        int x=0;
        while (x<100){
            a[x]=x; x=x+1;
        }

        System.out.println(a[5]);
    }
}