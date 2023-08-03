package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("CanBeFinal")
public class ThreadUnionImpl implements ThreadUnion {

    private final String name;

    private AtomicInteger threadCounter;
    private List<FinishedThreadResult> finishedThreads;
    private List<Thread> threads;
    private boolean isShutDown;


    public ThreadUnionImpl(String name) {
        this.name = name;
        this.threadCounter = new AtomicInteger(0);
        this.finishedThreads = new ArrayList<>();
        this.threads = new ArrayList<>();
        this.isShutDown = false;
    }

    @Override
    public int totalSize() {
        return threadCounter.get();
    }

    @Override
    public int activeSize() {
        int counter = 0;
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public void shutdown() {
        this.isShutDown = true;
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutDown;
    }

    @Override
    public void awaitTermination() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // Handle the interrupted exception if needed
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return isShutdown() && (activeSize() == 0);
    }

    @Override
    public List<FinishedThreadResult> results() {
        return finishedThreads;
    }


    @Override
    public Thread newThread(Runnable r) {
        if (isShutdown()) {
            throw new IllegalStateException("ThreadUnion has been shutdown.");
        }

        String threadName = (String.format("%s-worker-%d", name, threadCounter.getAndIncrement()));

        Thread thread = new Thread(() -> {
            try {
                r.run();
                finishedThreads.add(new FinishedThreadResult(threadName));
            } catch (Throwable throwable) {
                finishedThreads.add(new FinishedThreadResult(threadName, throwable));
            }
        }, threadName);

        threads.add(thread);
        return thread;
    }
}
