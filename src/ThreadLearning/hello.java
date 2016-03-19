package ThreadLearning;

/**
 * Created by admin on 2016/3/19.
 */
import  java.lang.Runnable;

// 代理模式？？
// 如果一个类继承Thread 不适合资源共享，
// Runnable Thread
// 避免单继承的限制；增强代码和数据的分离
// main 也是一个线程，线程是同时启动的
/*
*   程序至少有两个线程 main 垃圾收集线程
*
* Thread   isAlive
* join() 强制执行？
* sleep(2000) 休眠  --2s??
* yield 礼让
*
* 同步和死锁
* synchronized (同步对象){
*  需要同步的代码
* }
* */

public class hello implements  Runnable{
    private int ticket=5;
    public  void run(){
        for(int i=0;i<=20;i++){
            if(this.ticket>0)
            System.out.println(Thread.currentThread().getName()+" "+this.ticket--);
            if(i==3)
                Thread.currentThread().yield(); // 线程礼让
            sale();
        }
    }
    public  synchronized  void  sale(){
        if(ticket>0){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(ticket--);
        }
    }
    public  static  void  main(String args[]){
        hello my =new hello();
        new Thread(my,"1hao").start();
        new Thread(my,"2").start();

        Thread demo=new Thread(my,"thread");
        demo.start();
        for(int i=0;i<50;i++){
            if(i>10){
                try{
                    demo.join();  // force wait
                }catch (Exception e){

                }
            }
        }
        try{
        Thread.sleep(2000);
        } catch (Exception e){

        }
        demo.interrupt();



    }
}
