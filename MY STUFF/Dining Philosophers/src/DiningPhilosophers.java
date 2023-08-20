import java.util.Random;
import java.util.Scanner;

public class DiningPhilosophers {

    Philosopher[] philosophers;
    Fork[] forks;
    Thread[] threads;
    Scanner scan;
    int number;



    public void init(){
        scan=new Scanner(System.in);
        try{
            System.out.println("Num of Philosopher?");
            number=scan.nextInt();
            if (number<2){
                System.out.println("greater than 1");
                return;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        philosophers=new Philosopher[number];
        forks=new Fork[number];
        threads=new Thread[number];

        for (int i = 0; i <number ; i++) {
            philosophers[i]=new Philosopher(i+1);
            forks[i]=new Fork(i+1);
        }
    }


    public void startThinkingEating(){
        for (int i = 0; i < number; i++) {
            final int index=i;
            threads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                        try{
                            philosophers[index].start(forks[index], (index-1>0)?forks[index-1]:forks[number-1]);
                        }
                        catch (Exception e){
                            e.printStackTrace();

                        }
                }
            });

            threads[i].start();
        }
    }







    public static void main(String[] args) {
        DiningPhilosophers diningPhilosophers=new DiningPhilosophers();
        diningPhilosophers.init();
        diningPhilosophers.startThinkingEating();
    }
}