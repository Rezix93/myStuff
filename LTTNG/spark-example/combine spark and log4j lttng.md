


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

first if you have error :

```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/jre/
export PATH=$JAVA_HOME/bin:$PATH

sudo update-java-alternatives --list
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
java -version
export HADOOP_HOME=/usr/local/hadoop
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin
export HADOOP_MAPRED_HOME=$HADOOP_HOME
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_HDFS_HOME=$HADOOP_HOME
export YARN_HOME=$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib/native"

source ~/.bashrc
source ~/.profile
```
