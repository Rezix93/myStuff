

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
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 0

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
