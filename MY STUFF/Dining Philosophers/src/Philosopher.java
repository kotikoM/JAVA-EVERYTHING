import java.util.Random;

public class Philosopher {

    private int philosopherId;
    private Fork left, right;


    Philosopher(int philosopherId){
        this.philosopherId=philosopherId;
    }

    public void start(Fork left, Fork right) throws InterruptedException{
        this.left=left;
        this.right=right;

        while(true){
            if (new Random().nextBoolean()){
                eat();
            }else {
                think();
            }

        }
    }

    public void think() throws InterruptedException{
        System.out.println("The philosopher:  "+philosopherId+"is thinking");
        Thread.sleep(new Random().nextInt(1000)+100);
        System.out.println("The philosopher:  "+philosopherId+"has STOPPED thinking");
    }

    public void eat() throws InterruptedException{
        boolean righPick=false;
        boolean leftPick=false;

        System.out.println("The philosopher:  "+philosopherId+"is hungry and wants to EAT");
        System.out.println("The philosopher:  "+philosopherId+"is picking up the left fork:  "+this.left.getForkId());

        leftPick=left.pick(philosopherId);
        if (!leftPick){
            return;
        }


        System.out.println("The philosopher:  "+philosopherId+"is picking up the right fork:  "+this.right.getForkId());

        righPick=right.pick(philosopherId);
        if (!righPick){
            return;
        }

        System.out.println("The philosopher:  "+philosopherId+"is now EATING");

        Thread.sleep(new Random().nextInt(1000)+100);

        left.free();
        right.free();

        System.out.println("The philosopher:  "+philosopherId+"has eaten and freed the forks");



    }
}
