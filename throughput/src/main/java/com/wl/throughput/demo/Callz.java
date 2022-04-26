package com.wl.throughput.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/18 11:17
 */
public class Callz {

    public static Integer calc(Integer param){
        //模拟一个长时间的执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param * param;
    }

    public static void main(String[] args) {

        final CompletableFuture<Void> future = CompletableFuture.supplyAsync(
                ()-> calc(60))
                .thenApply((i)-> Integer.toString(i))
                .thenApply((str) -> "\""+str+"\"")
                .thenAccept(System.out::println);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
