```bash

${SPARK_HOME}/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--verbose \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 100

lttng view > output-lttng.log 2>&1
```

then look at logs: 

