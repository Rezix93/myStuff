
---

### **Java Threads Overview with Examples**

- **Thread Basics:** Threads allow concurrent execution within a program.

- **Creating Threads:**
  - **Extend `Thread`:**
    ```java
    class MyThread extends Thread {
        public void run() {
            System.out.println("Thread is running.");
        }
    }

    public class Main {
        public static void main(String[] args) {
            MyThread thread = new MyThread();
            thread.start(); // Starts the thread, invoking run()
        }
    }
    ```
  - **Implement `Runnable`:**
    ```java
    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("Thread is running.");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Thread thread = new Thread(new MyRunnable());
            thread.start(); // Starts the thread, invoking run()
        }
    }
    ```

- **Thread Lifecycle:** 
  - **New:** Thread is created.
  - **Runnable:** Thread is ready to run.
  - **Blocked/Waiting:** Thread is waiting for resources or conditions.
  - **Terminated:** Thread has finished execution.

- **Key Methods:**
  - **`start()`:** Begins thread execution.
  - **`run()`:** Contains the code to be executed by the thread.
  - **`sleep(long millis)`:** Pauses thread for specified time.
  - **`join()`:** Waits for the thread to finish.
    ```java
    Thread t1 = new Thread(() -> {
        System.out.println("Thread 1 running");
    });
    t1.start();
    t1.join(); // Waits for t1 to complete before continuing
    System.out.println("Main thread continues");
    ```

- **Synchronization:** Prevents race conditions by locking shared resources.
  - **Example:**
    ```java
    class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public class Main {
        public static void main(String[] args) {
            Counter counter = new Counter();

            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("Final count: " + counter.getCount()); // 2000
        }
    }
    ```

- **Thread Pools:** Use the `Executor` framework to manage multiple threads efficiently.
  - **Example:**
    ```java
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    public class ThreadPoolExample {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(3);

            for (int i = 0; i < 5; i++) {
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " is running");
                });
            }

            executor.shutdown(); // Initiates an orderly shutdown
        }
    }
    ```

---
