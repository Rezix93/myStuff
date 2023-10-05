```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/jre/
export PATH=$JAVA_HOME/bin:$PATH


sudo update-java-alternatives --list
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
java -version


sudo update-alternatives --config java
sudo update-alternatives --set java /usr/lib/jvm/java-11-openjdk-amd64/bin/java
source ~/.bashrc   # or source ~/.profile or source ~/.bash_profile
```

```bash
pgrep -a lttng-sessiond
sudo kill xxx
```

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
```

```bash
lttng enable-event -k sched_switch,sched_waking,sched_pi_setprio,sched_process_fork,sched_process_exit,sched_process_free,sched_wakeup,\
irq_softirq_entry,irq_softirq_raise,irq_softirq_exit,irq_handler_entry,irq_handler_exit,\
lttng_statedump_process_state,lttng_statedump_start,lttng_statedump_end,lttng_statedump_network_interface,lttng_statedump_block_device,\
block_rq_complete,block_rq_insert,block_rq_issue,\
block_bio_frontmerge,sched_migrate,sched_migrate_task,power_cpu_frequency,\
net_dev_queue,netif_receive_skb,net_if_receive_skb,\
timer_hrtimer_start,timer_hrtimer_cancel,timer_hrtimer_expire_entry,timer_hrtimer_expire_exit
```

```bash
lttng enable-event -l -a
```

```bash
lttng start
./run
lttng stop
lttng view
```






./bin/run-example \
--driver-java-options "-Dlog4j.configuration=file:/opt/spark/conf/log4j.properties" \
--conf "spark.executor.extraJavaOptions=-Dlog4j.configuration=file:/opt/spark/confnflog4j.properties" \
SparkPi 10





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

set -x in run file print somthing
if did not link the libaray logs not appear
if change name of xml file , logs did not appaer
```bash

javac -classpath /usr/local/share/java/log4j.jar:/usr/share/java/log4j.jar:/usr/local/share/java/lttng-ust-agent-log4j2.jar:/usr/share/java/lttng-ust-agent-log4j2.jar:/usr/local/share/java/lttng-ust-agent-common.jar:/usr/share/java/lttng-ust-agent-common.jar test.java

javac -classpath '/usr/local/share/java/log4j-core.jar:/usr/share/java/log4j-core.jar:/usr/local/share/java/log4j-api.jar:/usr/share/java/log4j-api.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/*:' -Djava.library.path=../../../src/lib/lttng-ust-java-agent/jni/log4j/.libs test.java

java -classpath '/usr/local/share/java/log4j-core.jar:/usr/share/java/log4j-core.jar:/usr/local/share/java/log4j-api.jar:/usr/share/java/log4j-api.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common/lttng-ust-agent-common.jar:../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/*:' -Djava.library.path=../../../src/lib/lttng-ust-java-agent/jni/log4j/.libs test

java -classpath '../../../src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/lttng-ust-agent-log4j2.jar:/opt/spark/assembly/target/scala-2.12/jars/*:' HelloLog4j2



```
