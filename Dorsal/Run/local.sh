  ```bash
  	cd /opt/spark && \
	./sbin/start-all.sh && \
  	lttng create && \
	lttng enable-event -l -a  && \
 	lttng enable-event -k -a  && \
	lttng start && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.mllib.JavaALS  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar && \
	lttng stop && \
 	lttng view 
```
