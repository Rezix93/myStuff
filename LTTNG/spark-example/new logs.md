

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

lttng create

lttng enable-event -l -a

lttng start

${SPARK_HOME}/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--master local[2] \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 

lttng stop

```
