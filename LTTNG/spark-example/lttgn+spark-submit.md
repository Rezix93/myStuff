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
--jars "/home/rezghool/research/lltn-ust2/lttng-ust-master/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar,/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar" \
--conf spark.executor.extraJavaOptions="-Dlog4j.configurationFile=log4j2.xml" \
--conf spark.driver.extraJavaOptions="-Dlog4j.configurationFile=log4j2.xml" \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 100
```

```bash
javac -classpath "/usr/local/share/java/*:/usr/local/share/java/log4j.jar:/usr/share/java/log4j.jar:/usr/local/share/java/lttng-ust-agent-log4j2.jar:/usr/share/java/lttng-ust-agent-log4j2.jar:/usr/local/share/java/lttng-ust-agent-common.jar:/usr/share/java/lttng-ust-agent-common.jar:/opt/spark/assembly/target/scala-2.12/jars/*" -g HelloLog4j2.java


Classpath: /usr/local/share/java/log4j-api.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar

library needed for lttng appeneder:
/home/rezghool/research/lltn-ust2/lttng-ust-master/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar
```
```bash
/usr/local/share/java/log4j-api.jar:
/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar
```


```bash

${SPARK_HOME}/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--verbose \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 100
```

```bash
${SPARK_HOME}/bin/spark-submit \
--class org.apache.spark.examples.sql.SparkSQLExample \
--master local[*] \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar

```
The SparkSQLExample is an example application included with Apache Spark that demonstrates how to use Spark's SQL capabilities. When you run this example with spark-submit, it performs the following tasks:

Initializes a Spark session: It sets up a Spark SQL session that allows you to interact with structured data using SQL queries.

Loads a sample dataset: The example typically loads a sample dataset, such as a Parquet file, into a DataFrame. DataFrames are a fundamental abstraction in Spark SQL that allow you to work with structured data.

Performs SQL queries: You can execute SQL queries against the loaded DataFrame. The example may demonstrate simple SQL queries, filtering, aggregation, and more.

Displays or saves results: Depending on the specific example, it may display the query results in the console or save them to an output location, such as a Parquet file.

Overall, running the SparkSQLExample with spark-submit is a way to demonstrate how to use Spark SQL to perform data manipulation and analysis tasks using SQL-like syntax within your Spark applications.

For the specific details of what this example does, you can refer to the Spark documentation or the source code of the example provided with your Spark distribution.



```bash
${SPARK_HOME}/bin/spark-submit \
--class org.apache.spark.examples.extensions.AgeExample \
--master local[*] \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar

```
