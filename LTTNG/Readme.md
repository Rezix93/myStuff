Test  and run and everything.
**Use the LTTng-UST Java agent for java.util.logging

1. insert library 
2. Create an LTTng-UST java.util.logging log handler.
3. Add this handler to the java.util.logging loggers which should emit LTTng events.
4. Use java.util.logging log statements and configuration as usual.
5. Before exiting the application, remove the LTTng-UST log handler from the loggers attached to it and call its close() method.


java.util.logging (JUL) works by defining Logger and Handler objects. Loggers are part of the Java application: they receive log calls once the execution reaches pre-determined log points. Handlers are attached to loggers: they process the received log points to generate and save log events wherever they are configured to do so (to a file, to the console, over the network, or in our case, to an LTTng trace).


```bash
javac -cp /usr/lcoal/share/java/lttng-ust-agent-common.jar:/usr/lcoal/share/java/lttng-ust-agent-jul.jar:/usr/local/share/java/liblttng-ust-agent.jar Test.java
```


```bash
java -classpath /usr/local/share/java/*:. -Djava.library.path=/usr/local/lib Test
```


This is nice, but not sufficient for us: we want the results in an LTTng trace! Let's create a tracing session, enable LTTng events targetting the JUL (--jul) domain, and start it:

```bash
lttng create
lttng enable-event --jul --all
lttng start

java -classpath /usr/local/share/java/*:. -Djava.library.path=/usr/local/lib Test

lttng stop
lttng view
lttng destroy
```


Task 3: Understanding the Critical Path
The OS Execution Graph analysis that is the base of the critical path computes the dependencies between the threads only from the kernel events. It will try to explain the causes of a thread being blocked by following what triggered the wakeup of the thread. For example, the reception of a network packet will cause a wake-up event for the thread that was waiting for this packet. So we can infer that the thread was blocked waiting for the network.

The Critical Path analysis starts from the end of the thread and moves back through the dependency chain to get the longest path of waiting for resources. It would find out if a process was waiting for a semaphore owned by another thread or if it was waiting on disk, etc.

In the case of the wget critical path, there is no dependency with any other thread on the machine, as should be expected from such an application, so it looks like the line of the process of the Control Flow view, only that the blocked states are replaced by the reasons of the blocking. The following screenshot shows the legend of the Critical Path view.


**SPARK
https://linuxhint.com/install-apache-spark-ubuntu/
For running spark just do this

```bash
start-master.sh
```
Once you have started; run the address (https://localhost:8080) and you will notice that there is one worker added in “Workers” section. It is noticed that the worker is using “1” core of processor and 3.3GB of RAM by default:

```bash
spark-class org.apache.spark.deploy.worker.Worker  spark://localhost:7077 -c 1 -m 512M
```

```bash
spark-submit  --class org.apache.spark.examples.SparkPi   --master spark://localhost:7077  lib/spark-examples-1.2.1-hadoop2.4.0.jar 
```

org.apache.spark.deploy.worker.Worker running as process 17129.  Stop it first.

```bash
stop-master.sh
stop-worker.sh
stop-all.sh
```

```bash
echo $SPARK_HOME
export SPARK_HOME=/opt/spark
```

javac -cp /opt/spark/core/target/spark-core_2.12-3.4.0.jar:/opt/spark/build/scala-2.12.17/lib/scala-library.jar:/opt/spark/core/target/jars/guava-14.0.1.jar:/opt/spark/sql/core/target/spark-sql_2.12-3.4.0.jar JavaPageRank.java






and open http://localhost:8080/
