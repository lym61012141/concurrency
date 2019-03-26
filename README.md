**《Java 并发编程之美》**
- 用户线程与守护线程的区别
- TreadExtends Runnable实现类的区别,单个实例前者不能重复执行,后者可以重复执行

**DeadLock**
* 死锁原因:相互持有各自的资源不释放
* 使持有顺序一致解决问题

**InterruptedMethod**
 * interrupted() 会清除中断标志, isInterrupted()不会,
 * 所以设计时使用Thread.interrupted(),这样被中断的线程再次尝试重启时不会因为上次中断标志是false而终止业务
 * Thread.interrupted() 其实获取的时当前线程的中断标志
 
 **InterruptMethod**
 
 * 根据中断标志优雅退出线程
 
 **JoinMethod**
 * 使用join 会让子线程运行结束再执行后面的
 
 **MethodTest**
 
* 当t1 调用了o.wait() ,Thread.sleep(),或者join() 在被t2 调用t1.interrupt() 时t1线程停止抛出中断异常
  否者在被t2 调用t1.interrupt()时只是被标记了中断,即设置isInterrupt()=true,但t1线程还继续执行

**thread.consumerproducer.Main**
* 生产者消费者模型,生产一个消费一个,通过flag控制

**thread.consumerproducer.otherdemo.ConPro**
- jdk 文档给与的生产者消费者模型

**atomic.AtomicLongTest**

 * 多线程下测试AtomicLong 原子性
 
**lock.LockSupportTest**
* 测试park() unpark() 使用jstack 测试park(),park(this)区别

