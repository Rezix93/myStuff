

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
--num-executors 16 \
--conf spark.executor.cores=8 \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 0

lttng stop


 ${SPARK_HOME}/bin/spark-submit \
   --verbose \
   --class org.apache.spark.examples.JavaPageRank2 \
    --deploy-mode client \
   /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar \
   500 400 100

```

Cluster Manager allocates resources across the other applications. I think the issue is with bad optimized configuration. You need to configure Spark on the Dynamic Allocation. In this case Spark will analyze cluster resources and add changes to optimize work.

You can find all information about Spark resource allocation and how to configure it here: http://site.clairvoyantsoft.com/understanding-resource-allocation-configurations-spark-application/


#Attention: 
change java verison to 11 for spark 


The error message you're encountering from the YARN application state monitor indicates that the resource request for your Spark application exceeds the maximum allowed allocation in your YARN cluster. Specifically, the request for 8 virtual cores (vCores) per container is beyond the maximum allowed allocation of 4 vCores. The maximum allocation is determined by the YARN scheduler based on the resources of registered NodeManagers, which might be less than or equal to the configured maximum allocation.





