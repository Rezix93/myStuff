
docker exec da-spark-master lttng create \
lttng enable-event -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener" || logger_name == "org.apache.spark.executor"' \
docker exec da-spark-master lttng enable-event -k -a \
docker exec da-spark-master lttng start \
docker exec da-spark-master \
/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--master spark://spark-master:7077 \
--conf "spark.executor.extraJavaOptions=-Djava.library.path=/usr/lib/x86_64-linux-gnu/jni" \
--conf "spark.driver.extraJavaOptions=-Djava.library.path=/usr/lib/x86_64-linux-gnu/jni" \
--conf spark.driver.extraClassPath=/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-common.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j2.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar \
--conf spark.executor.extraClassPath=/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-common.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j2.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6 \
docker exec da-spark-master lttng stop \
docker exec da-spark-master lttng view \
docker exec da-spark-master lttng destroy 
