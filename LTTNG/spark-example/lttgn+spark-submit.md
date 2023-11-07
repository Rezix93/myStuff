```bash
java -cp "/usr/local/share/java/log4j-api.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/*:" -jar  HelloLog4j2.jar

java -cp "HelloLog4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/slf4j-api-2.0.6.jar:/opt/spark/assembly/target/scala-2.12/jars/*" HelloLog4j2

java -classpath  "HelloLog4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/slf4j-api-2.0.6.jar" -jar HelloLog4j2.jar

/opt/spark/bin/spark-submit --class org.apache.spark.examples.SparkPi --master local[2] /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar  100

/opt/spark/bin/spark-submit --class org.apache.spark.examples.SparkPi --master local[2] /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar  100


```
