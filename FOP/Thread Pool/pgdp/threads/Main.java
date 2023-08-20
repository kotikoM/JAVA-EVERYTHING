package pgdp.threads;

public class Main {
    public static void main(String[] args) {
        Threadpool threadpool=new Threadpool((Runtime.getRuntime().availableProcessors()));

        Future[] futures = new Future[1000];

        for (int i = 0; i < futures.length ; i++) {
            final int isFinal=i;
            try{
                futures[i]=threadpool.submit(()-> System.out.println(Thread.currentThread().getName()+":"+isFinal));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        for(Future f: futures){
            try{
                f.get();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        threadpool.shutdownNow();
        System.out.println("Shut Down!");

    }
}
