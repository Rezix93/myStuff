


https://stackoverflow.com/questions/75556273/slf4j-marker-when-used-with-fluent-apis-is-ignored-by-log4j2-configuration-but
Use Log4j2 API instead of SLF4J. Log4j2 API is basically a superset of SLF4J (cf. Log4j2 API Logger and SLF4J Logger) and except a package change it is mostly source compatible with SLF4J.

Using the Log4j2 API, a call to atTrace() will return a real log builder if you use global filters and a no-op log builder if you don't (for improved performance, cf. the benchmarks in GH PR #1203).

You can keep using SLF4J, but you need to replace:

log.atTrace().addMarker(marker).log("This is logged using fluent api");
with

log.makeLoggingEventBuilder(Level.TRACE)
   .addMarker(marker)
   .log("This is logged using fluent api");
(cf. SLF4J-560). This solution will incur in a performance penalty for disabled loggers (whether you use global filters or not) and will always create a temporary object (that need to be GC-ed).


## Before start
```bash
./build/mvn -DskipTests clean package -rf :spark-examples_2.12


pgrep -a lttng-sessiond
sudo lttng-sessiond --daemonize

cd /opt/spark/
 
./sbin/start-history-server.sh

PATH=$PATH:/usr/local/hadoop/sbin
bash start-dfs.sh
bash start-yarn.sh

lttng view > output-lttng.log 2>&1

```

## Enable everything

```bash

lttng create kmenas_8

lttng enable-event -l -a

lttng enable-event -k sched_switch,sched_waking,sched_pi_setprio,sched_process_fork,sched_process_exit,sched_process_free,sched_wakeup,\
irq_softirq_entry,irq_softirq_raise,irq_softirq_exit,irq_handler_entry,irq_handler_exit,\
lttng_statedump_process_state,lttng_statedump_start,lttng_statedump_end,lttng_statedump_network_interface,lttng_statedump_block_device,\
block_rq_complete,block_rq_insert,block_rq_issue,\
block_bio_frontmerge,sched_migrate,sched_migrate_task,power_cpu_frequency,\
net_dev_queue,netif_receive_skb,net_if_receive_skb,\
timer_hrtimer_start,timer_hrtimer_cancel,timer_hrtimer_expire_entry,timer_hrtimer_expire_exit

lttng enable-event -k --syscall --all

lttng start

${SPARK_HOME}/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--master local[2] \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 8

lttng stop

```


## Running code with error: 

```bash
${SPARK_HOME}/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--deploy-mode client \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 1


${SPARK_HOME}/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--master local[2] \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 3






```



## Change java version 11
```bash

export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

sudo update-alternatives --set java /usr/lib/jvm/java-11-openjdk-amd64/bin/java
source ~/.bashrc   # or source ~/.profile or source ~/.bash_profile
java -version
```

## Change java version 18

```bash

export JAVA_HOME=/usr/lib/jvm/java-1.18.0-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

sudo update-alternatives --set java  /usr/lib/jvm/java-18-openjdk-amd64/bin/java 
source ~/.bashrc   # or source ~/.profile or source ~/.bash_profile
java -version

```
# Trace Compass

press the / key. It will open a small dialog with a looking glass at the bottom of the view.

wget|lttng will highligh threads containing wget or lttng

TID contains 56 will highlight all threads whose TID contains 56

TID matches 5628 will highlight the thread with ID 5628

System_call matches .* will highlight all state with system calls (they are visible only when zoomed)

@octocat :+1: This PR looks great - it's ready to merge! :shipit:


> [!IMPORTANT]
> Crucial information necessary for users to succeed.

## different states in kmenas example

### state 1: 

```bash
for (int i = 0; i < 10000; i++) {
  leakyList.add(new int[1000000]);  // Allocate large arrays
}
 ```             
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space


### state 3:

Exception in thread "main" java.lang.ArithmeticException: / by zero


### state 5:

```bash
if (Math.random() < 0.4){ // 50% chance to fail for each partition
              throw new SparkException("Intentional failure in stage");
            }
  ```             
            
org.apache.spark.SparkException: Intentional failure in stage


### state 8:

```bash
if (Math.random() < 0.4){ // 50% chance to fail for each partition
              throw new SparkException("Intentional failure in stage");
            }
 ```  
java.lang.NegativeArraySizeException: -727379968




### state 10:
```bash
// Use MapFunction to introduce a memory-intensive operation
                  Dataset<Row> mappedPredictions = predictions.map(new MapFunction<Row, Row>() {
                    @Override
                    public Row call(Row row) throws Exception {
                        // Memory-intensive operation
                        byte[] largeArray = new byte[10000 * 10000 * 10000]; // Allocate 1GB
                        return row;
                    }
                }, Encoders.bean(Row.class));

                // Trigger an action to execute the map transformation
                mappedPredictions.collect(); // or any other action like count(), show(), et
``` 
java.lang.NegativeArraySizeException: -727379968



https://www.loggly.com/blog/benchmarking-java-logging-frameworks/
log4j is better thatn jul


https://stackoverflow.com/questions/75556273/slf4j-marker-when-used-with-fluent-apis-is-ignored-by-log4j2-configuration-but





