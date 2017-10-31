package com.others;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SumByCallable {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int n = 10;//线程数
        long start=1L;//开始值
        long end=1000000000L;//结束值
        long sum = 0L;
        ExecutorService threads = Executors.newFixedThreadPool(n);
        CompletionService<Long> cs = new ExecutorCompletionService<Long>(threads);
        for(int i=0;i<n;i++){
            cs.submit(new SumCallable(start, end, n, i));
        }
        threads.shutdown();//关闭service
        for(int i=0;i<n;i++){
            try {
                sum += cs.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("和："+sum+",耗时："+(endTime-startTime)+"ms");
    }

}

class SumCallable implements Callable<Long>{
    private long start;
    private long end;
    public SumCallable(long start, long end, int n, int index){
        this.start = index * (end-start)/n;
        this.end = (index+1) * (end-start)/n-1;
        if(index==0){
            this.start = start;
        }
        if(index == n-1){
            this.end = end;
        }
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for(long i=start;i<=end;i++){
            sum +=i;
        }
    //    System.out.println(Thread.currentThread().getName()+":"+sum);
        return sum;
    }
    
}