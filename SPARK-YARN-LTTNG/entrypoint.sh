#!/bin/bash
#set -x  # Enable debugging

# Set default values
EXECUTOR_MEMORY="4G"
EXECUTOR_CORES=3

SPARK_WORKLOAD=$1

# Check if input arguments are provided
if [ $# -eq 2 ]; then
    EXECUTOR_MEMORY=$2
    EXECUTOR_CORES=$3
fi

echo "SPARK_WORKLOAD: $SPARK_WORKLOAD"
echo "EXECUTOR_MEMORY: $EXECUTOR_MEMORY"  
echo "EXECUTOR_CORES: $EXECUTOR_CORES"    


# Run the spark-submit command with the provided input arguments
# ${SPARK_HOME}/bin/spark-submit \
# --verbose \
# --class org.apache.spark.examples.ml.JavaKMeansExample \
# --master spark://Rezghool:7077 \
# --conf spark.executor.memory=$EXECUTOR_MEMORY \
# --conf spark.executor.cores=$EXECUTOR_CORES \
# /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar $INPUT
#!/bin/bash

export JAVA_OPTS="-Djava.library.path=.:/usr/local/lib/"
export LD_PRELOAD=/usr/local/lib/liblttng-ust-fork.so

echo "Java Opts: $JAVA_OPTS"

#/usr/lib/jvm/java-11-openjdk-amd64/bin/java -cp /opt/spark/conf/:/opt/spark/assembly/target/scala-2.12/jars/*:/etc/hadoop:/opt/spark/core/target/jars/lttng-agent/* -Xmx1g org.apache.spark.deploy.worker.Worker  --webui-port 8081 --cores 4 --memory 4g spark://127.0.1.1:7077 

if [ "$SPARK_WORKLOAD" == "master" ];
then
    #exec /usr/lib/jvm/java-11-openjdk-amd64/bin/java -cp /opt/spark/conf/:/opt/spark/assembly/target/scala-2.12/jars/*:/etc/hadoop:/opt/spark/core/target/jars/lttng-agent/* -Xmx1g org.apache.spark.deploy.master.Master --host Rezghool --port 7077 --webui-port 8080 -p 7077
    LD_PRELOAD=/usr/local/lib/liblttng-ust-fork.so /opt/spark/sbin/start-master.sh -p 7077
elif [ "$SPARK_WORKLOAD" == "worker" ];
then
  LD_PRELOAD=/usr/local/lib/liblttng-ust-fork.so  /opt/spark/sbin/start-worker.sh --cores EXECUTOR_CORES --memory EXECUTOR_MEMORY spark://127.0.1.1:7077
elif [ "$SPARK_WORKLOAD" == "history" ]
then
  LD_PRELOAD=/usr/local/lib/liblttng-ust-fork.so  /opt/spark/sbin/start-history-server.sh
fi