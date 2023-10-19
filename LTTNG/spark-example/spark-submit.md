```bash
/opt/spark/bin/spark-submit \
--class JavaWordCount \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar \
/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/input.txt \
/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/output
```



find . -name "*.jar" -exec grep -Hls 'SparkConf' {} \;


./build/mvn -DskipTests clean package



after check 
/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar
/opt/spark/core/target/spark-core_2.12-3.4.0.jar


sparkconf
