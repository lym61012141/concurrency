package queue;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Liuym
 * @date 2019/1/21 0021
 */
public class PriorityBlockingQueueDemo {
    static class Task implements Comparable{
        private int priority=0;

        private String taskName;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public int compareTo(Object o) {
            if (this.priority >= ((Task)o).getPriority()) {
                return 1;
            }else{
                return -1;
            }
        }

        public void doSomething() {
            System.out.println(taskName + ":" + priority);
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName" + i);
            queue.offer(task);
        }
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            if (null != task) {
                task.doSomething();
            }
        }
    }
}
