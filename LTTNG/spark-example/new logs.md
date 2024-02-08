

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

bash stop-dfs.sh 
bash stop-yarn.sh

lttng view > output-lttng.log 2>&1
/usr/local/hadoop/bin/yarn node -list


/usr/local/hadoop/bin/hdfs dfsadmin -report
```


## Enable everything

```bash


lttng create

lttng enable-event -l -a  --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'

lttng start

${SPARK_HOME}/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--deploy-mode client \
--num-executors 4 \
--conf spark.executor.cores=4 \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 8

lttng stop



${SPARK_HOME}/bin/spark-submit \
--verbose \
  --class org.apache.spark.examples.SparkPi \
--deploy-mode client \
--num-executors 16 \
--conf spark.executor.cores=4 \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 1000


${SPARK_HOME}/bin/spark-submit \
  --class org.apache.spark.examples.graphx.AggregateMessagesExample \
--deploy-mode client \
--num-executors 16 \
--conf spark.executor.cores=4 \
/opt/spark/examples/target/original-spark-examples_2.12-3.4.0.jar


${SPARK_HOME}/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--deploy-mode client \
--num-executors 16 \
--conf spark.executor.cores=4 \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 1

```





Result: Successed
--num-executors 16 \
--conf spark.executor.cores=4 \

Result: failed
--num-executors 16 \
--conf spark.executor.cores=8 \



Result: failed
--num-executors 8 \
--conf spark.executor.cores=8 \


Result: failed
--num-executors 2 \
--conf spark.executor.cores=8 \



Cluster Manager allocates resources across the other applications. I think the issue is with bad optimized configuration. You need to configure Spark on the Dynamic Allocation. In this case Spark will analyze cluster resources and add changes to optimize work.

You can find all information about Spark resource allocation and how to configure it here: http://site.clairvoyantsoft.com/understanding-resource-allocation-configurations-spark-application/


#Attention: 
change java verison to 11 for spark 


The error message you're encountering from the YARN application state monitor indicates that the resource request for your Spark application exceeds the maximum allowed allocation in your YARN cluster. Specifically, the request for 8 virtual cores (vCores) per container is beyond the maximum allowed allocation of 4 vCores. The maximum allocation is determined by the YARN scheduler based on the resources of registered NodeManagers, which might be less than or equal to the configured maximum allocation.


## For adding a new graph to Trace compass:
### Core
/org.eclipse.tracecompass.incubator.spark_test1.core

/org.eclipse.tracecompass.incubator.spark_test1.core/src/org/eclipse/tracecompass/incubator/internal/spark_test1/core/analysis/SparkAnalysisModule.java
/org.eclipse.tracecompass.incubator.spark_test1.core/src/org/eclipse/tracecompass/incubator/internal/spark_test1/core/analysis/TaskXYDataProviderFactory.java




### Ui

/org.eclipse.tracecompass.incubator.spark_test1.ui

here add a new class for view and import the data provider from core

 in /org.eclipse.tracecompass.incubator.spark_test1.ui/META-INF/MANIFEST.MF add veiws then brows class org.eclipse.tracecompass.incubator.internal.spark_test1.ui.views.TaskPerExecutorView
 

### shuffleReadFetchWaitTime
The shuffleReadFetchWaitTime parameter in Spark measures the total time spent waiting for shuffle data to be fetched from remote locations. It is a critical metric for understanding the performance of shuffling operations in Spark applications, as longer wait times can indicate bottlenecks in network communication or issues with data distribution across the cluster. Optimizing this metric can lead to more efficient data processing by reducing idle time waiting for data.

### broadcast
In Spark, a "broadcast" refers to the process of distributing a read-only variable to all nodes in the cluster to optimize the efficiency of joins and data processing. The log message indicates that a broadcast variable (identified by "broadcast 61") was created, likely to share data across tasks without requiring shuffle operations, thus reducing network I/O and improving job execution time. This operation is managed by Spark's DAGScheduler and is a key optimization technique for distributed computing in Spark.

### Shuffle data
Shuffle data in Apache Spark refers to the data that needs to be transferred across different nodes in a cluster for completing operations that involve grouping or aggregating data across partitions. This happens, for example, during operations like reduceByKey or groupBy. The shuffle process is a key phase in distributed computations, where data is redistributed across the cluster so that each node can work on the data it needs to process. The efficiency of shuffling can significantly affect the performance of Spark applications due to network and disk I/O involved.

### executorRunTime vs taskDuration
executorRunTime measures the total time the executor spent running the task, including both computation time and reading from/writing to HDFS or other storage systems. taskDuration, on the other hand, is the total time from when the task was launched until it completed, which includes not only the executorRunTime but also time spent scheduling the task, fetching shuffle data if necessary, and any other overhead before and after execution. Essentially, taskDuration encompasses executorRunTime along with all other overheads involved in task execution.

### executorDeserializeCPU
`executorDeserializeCPU` measures the CPU time taken specifically for deserializing task data on the executor before the task can actually start running its computation. It's a part of the overhead before the actual execution (`executorRunTime`) begins and is not included in the `executorRunTime`. Therefore, `executorDeserializeCPU` contributes to the `taskDuration` by adding to the pre-execution overhead. In summary, `taskDuration` includes all phases of a task's lifecycle, including deserialization (`executorDeserializeCPU`), execution (`executorRunTime`), and any other overheads, making it a comprehensive measure of task time.


### jvmGCTime
jvmGCTime refers to the amount of time the Java Virtual Machine (JVM) spent performing garbage collection (GC) during the execution of a task. Garbage collection is the process of identifying and disposing of objects that are no longer needed by the application, which helps in managing memory more efficiently. High jvmGCTime values can indicate that the task involved heavy object creation and disposal, potentially impacting performance by increasing overall task execution time.


### executorCPUTime
`executorCPUTime` measures the total CPU time consumed by the executor for running the task. This includes the time spent on executing the task's code and any other CPU-bound operations it performs. It's a measure of the actual CPU resources consumed by the task, distinct from wall-clock time (`executorRunTime`), which includes idle waits. Monitoring `executorCPUTime` can help in understanding the computational complexity of the tasks and optimizing resource utilization.



### executorRunTime vs executorCPUTime
`executorRunTime` measures the total time taken by a task from start to finish, including both computation time and waiting time for resources or data. It's the wall-clock time for task execution. `executorCPUTime`, on the other hand, measures only the CPU time consumed by the task for computation, excluding any I/O waiting time, data shuffle time, or time spent waiting for other resources. It provides a measure of how much CPU work the task did, offering insights into the task's computational efficiency.


### resultSerializationTime vs executorDeserializeTime

`resultSerializationTime` measures the time taken to serialize the result of a task before sending it back to the driver, indicating how long it takes to prepare the result for transmission over the network. `executorDeserializeTime`, in contrast, tracks the time spent to deserialize the task data at the executor side before the task execution begins. Essentially, `resultSerializationTime` is about converting task output to a network-transmittable form, while `executorDeserializeTime` is about converting received data into a form that the executor can process for task execution.

### so executorDeserializeTime is not part of executorRunTime?
Yes, `executorDeserializeTime` is a separate metric that measures the time taken to deserialize task data before execution can begin on the executor. It does not directly contribute to `executorRunTime`, which measures the actual running time of the task execution itself. The deserialization time is part of the overhead incurred before the task's computation starts, while the execution time is focused solely on the computation part of the task.

### Why taskDuration > executorRunTime
The `executorRunTime` from Spark metrics and the task duration reported by the Spark listener may differ due to various factors involved in task execution. The `executorRunTime` specifically measures the time spent executing the task code on the executor, excluding overheads like scheduling delays, data serialization/deserialization, and network I/O. In contrast, task duration from the Spark listener includes all overheads, from the moment the task was scheduled to its completion, hence offering a more comprehensive view of the task's lifecycle. This broader scope is why task duration can be longer than `executorRunTime`.


### General
executorRunTime measures the total time a task spends running on the executor, including computation and reading from or writing to HDFS or other storage systems. executorDeserializeTime, on the other hand, measures the time taken to deserialize the task data sent from the driver to the executor before the task can begin execution. While executorRunTime encompasses the core computation and I/O time, executorDeserializeTime is specifically about the overhead before the actual computation starts.
jvmGCTime refers to the amount of time the Java Virtual Machine (JVM) spent performing garbage collection (GC) during the execution of a task. Garbage collection is the process of identifying and disposing of objects that are no longer needed by the application, which helps in managing memory more efficiently. High jvmGCTime values can indicate that the task involved heavy object creation and disposal, potentially impacting performance by increasing overall task execution time.


#### Example: 

#### From task metric
shuffleReadFetchWaitTime=0;

shuffleWriteBytesWrtietime=0;

jvmGCTime=249; 

executorDeserializeCPUTime=709177696;

executorDeserializeTime=1844;

executorCPUTime=3060031425;

executorRunTime=7172; 

resultSerializationTime=1;


#### From task Info           
gettingResultTime=0;

taskDuration=9227;



These metrics provide detailed insights into the performance and behavior of a Spark task:

- `shuffleReadFetchWaitTime`: Time spent waiting for shuffle data, indicative of network or IO bottlenecks in shuffle operations. Here, no time was spent waiting, suggesting efficient shuffle data retrieval.
- `shuffleWriteBytesWrtietime` & `gettingResultTime`: These show the time to write shuffle data and retrieve results, respectively. Both are zero, indicating no significant shuffle write time or delay in getting results.
- `jvmGCTime`: Time the JVM spent in garbage collection during this task, which can impact task performance.
- `executorDeserializeTime` & `executorDeserializeCPUTime`: These represent the time and CPU time spent on deserializing task data on the executor, impacting task startup time.
- `executorCPUTime`: Total CPU time consumed by the task, reflecting the actual CPU resources used.
- `executorRunTime`: The execution time of the task within the executor, excluding JVM GC time or any overhead introduced by Spark's execution engine.
- `taskDuration`: Total duration of the task from start to end, including all overheads and processing time.
- `launchTime` & `taskEndTime`: Timestamps marking the start and end of the task execution.
- `resultSerializationTime`: Time taken to serialize the task's result before sending it back to the driver.

`taskDuration` is the overall time from the task's start to its completion, potentially including time spent waiting for resources or other overheads not directly related to computation. It's usually the broadest measure of task time, encompassing all other time metrics.


cpuRate = executorCpuTime / executorRunTime
serRate = resultSerializationTime / executorRunTime
gcRate = jvmGCTime / executorRunTime
shuffleFetchRate = shuffleReadFetchWaitTime / executorRunTime
shuffleWriteRate = shufflesWrtietime / executorRunTime


### TASK STATUS:

Each task in Spark represents a unit of work sent to the executor. Spark tasks themselves do not inherently have states like "map," "reduce," or "group by" because these are transformations or actions applied on RDDs (Resilient Distributed Datasets) or DataFrames

# New challenge: 
## Waitng time for tasks
## Pending time for tasks
## Diffrent state of task in running (map, reduce, groupby)


#### Complex example: 










As an example, the Alternating Least Squares (ALS) implementation in MLlib computes an approximate product of two factor matrices iteratively. This involves a series of map, join, groupByKey operations under the hood.



