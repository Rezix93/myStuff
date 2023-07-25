
## Run spark java example

```bash
cd  ~/research/spark\ example/
javac -cp "/opt/spark/assembly/target/scala-2.12/jars/*:." JavaWordCount.java
java -cp "/opt/spark/assembly/target/scala-2.12/jars/*:." JavaWordCount
```

## Run a simple example for using the LTTng-UST Java agent for Apache log4j
```bash
cd ~/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic
export CLASSPATH="/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-api-2.20.0.jar"
make

lttng create test1
lttng enable-event -l -a
lttng start
./run
lttng stop
lttng view
```

## Now I need a java example using apache spark and also  LTTng-UST Java agent
