```bash
pgrep -a lttng-sessiond
```

```bash
java -cp "/usr/local/share/java/log4j-api.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/*:" -jar  HelloLog4j2.jar

java -cp "HelloLog4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/slf4j-api-2.0.6.jar:/opt/spark/assembly/target/scala-2.12/jars/*" HelloLog4j2

java -classpath  "HelloLog4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/slf4j-api-2.0.6.jar" -jar HelloLog4j2.jar

/opt/spark/bin/spark-submit --class org.apache.spark.examples.SparkPi --master local[2] /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar  100

/opt/spark/bin/spark-submit --class org.apache.spark.examples.SparkPi --master local[2] /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar  100
```

```bash

/opt/spark/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master local[2] \
--conf "spark.driver.extraJavaOptions=-Dlog4j.configuration=file:/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic/properties/log4j2.properties" \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 100


/opt/spark/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master local[*] --files  ~/log4j.properties \
--conf spark.sql.catalogImplementation=hive \
--conf spark.driver.extraJavaOptions=-Dlog4j.configuration=file:///home/rezghool/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic/properties/log4j2.properties \ /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 100



/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic/log4j2.properties

/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic/properties



/opt/spark/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master local[2] --files log4j2.properties \
--conf spark.driver.extraJavaOptions=-Dlog4j.configuration=file:///home/rezghool/research/lttng-ust/lttng-us/doc/examples/java-log4j2-basic/properties/log4j2.properties \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 100


/home/rezghool/research/lttng-ust/lttng-us/doc/examples/java-log4j2-basic/properties/log4j2.properties
/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic/properties



file:/home/rezghool/research/lttng-ust/lttng-us/doc/examples/java-log4j2-basic/properties/log4j2.properties


/opt/spark/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master local[2] \
--files /home/rezghool/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic/properties/log4j2.properties \
--conf "spark.executor.extraJavaOptions=-Dlog4j.configuration=log4j2.xml" \
--driver-java-options "-Dlog4j.configuration=file:/home/rezghool/research/lttng-ust/lttng-us/doc/examples/java-log4j2-basic/log4j2.xml" \
--driver-java-options "-Dlog4j.configurationFile=file:/home/rezghool/research/lttng-ust/lttng-us/doc/examples/java-log4j2-basic/log4j2.xml" \
--conf spark.driver.extraJavaOptions="-Dlog4j.configurationFile=file:/home/rezghool/research/lttng-ust/lttng-us/doc/examples/java-log4j2-basic/properties/log4j2.properties2" \
--conf spark.executor.extraJavaOptions="-Dlog4j.configurationFile=file:/home/rezghool/research/lttng-ust/lttng-us/doc/examples/java-log4j2-basic/properties/log4j2.properties2" \--conf 
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 100


--conf "spark.driver.extraJavaOptions=-Dlog4j.configuration=log4j-spark.properties" 
--conf "spark.executor.extraJavaOptions=-Dlog4j.configuration=log4j-spark.properties"


javac -classpath "/usr/local/share/java/*:/usr/local/share/java/log4j.jar:/usr/share/java/log4j.jar:/usr/local/share/java/lttng-ust-agent-log4j2.jar:/usr/share/java/lttng-ust-agent-log4j2.jar:/usr/local/share/java/lttng-ust-agent-common.jar:/usr/share/java/lttng-ust-agent-common.jar:/opt/spark/assembly/target/scala-2.12/jars/*" -g HelloLog4j2.java


${SPARK_HOME}/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--verbose \
--master 'local[*]' \
--files "log4j2.xml" \
--conf spark.executor.extraJavaOptions="-Dlog4j.configurationFile=log4j2.xml" \
--conf spark.driver.extraJavaOptions="-Dlog4j.configurationFile=log4j2.xml" \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 100
```



javac -classpath "/usr/local/share/java/*:/usr/local/share/java/log4j.jar:/usr/share/java/log4j.jar:/usr/local/share/java/lttng-ust-agent-log4j2.jar:/usr/share/java/lttng-ust-agent-log4j2.jar:/usr/local/share/java/lttng-ust-agent-common.jar:/usr/share/java/lttng-ust-agent-common.jar:/opt/spark/assembly/target/scala-2.12/jars/*" -g HelloLog4j2.java


/home/rezghool/research/lltn-ust2/lttng-ust-master/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar


Classpath: /usr/local/share/java/log4j-api.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar

