import java.util.Random;

public class Fork{
    private int forkId;
    private boolean status;

    public int getForkId() {
        return forkId;
    }

    Fork(int forkId){
        this.forkId=forkId;
        this.status=true; //if true avaliable for use. if false fork is in use
    }

    public synchronized void free() throws InterruptedException{
        status=true;
    }

    public synchronized boolean pick(int philosopherId) throws InterruptedException{
        int counter=0;
        int waitUntil=new Random().nextInt(10)+5;

        while(!status){
            Thread.sleep(new Random().nextInt(100)+50);
            counter++;

            if (counter>waitUntil){
                return false;
            }
        }
        status=false;
        return true;

    }
}