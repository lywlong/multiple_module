package com.wl.throughput.demo;

import lombok.AllArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/18 10:49
 */
@AllArgsConstructor
public class AskThread implements Runnable{

    private CompletableFuture<Integer> re = null;

    @Override
    public void run() {
        int customRe = 0;
        try {
            customRe = re.get() * re.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("custom re:"+customRe);
    }

    public static void main(String[] args) {

        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        try {
            Thread.sleep(1000);
            future.complete(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
