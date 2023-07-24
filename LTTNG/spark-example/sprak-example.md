


this works well: 






```bash
javac -cp "/opt/spark/assembly/target/scala-2.12/jars/*:." JavaWordCount.java
java -cp "/opt/spark/assembly/target/scala-2.12/jars/*:." JavaWordCount

```
















```bash

javac  -classpath  /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar: JavaWordCount.java
```


javac -cp /opt/spark/assembly/target/scala-2.12/jars/* JavaWordCount.java

javac -cp /opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar JavaWordCount.java


javac -cp "/opt/spark/assembly/target/scala-2.12/jars/*:." JavaWordCount.java
java -cp "/opt/spark/assembly/target/scala-2.12/jars/*:." JavaWordCount


java  /opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar JavaWordCount


javac  -classpath  JavaWordCount.java /opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar JavaWordCount.java

java -cp    /opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar JavaWordCount



```bash
java -cp /opt/spark/build/scala-2.12.17/lib/scala-library.jar:/opt/spark/examples/target/scala-2.12/jars/*:/opt/spark/assembly/target/scala-2.12/jars/* JavaWordCount
```


export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre/
export PATH=$JAVA_HOME/bin:$PATH


sudo update-java-alternatives --list
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
java -version

fin java path:
readlink -f /usr/bin/java | sed "s:bin/java::"

export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH











javac -classpath /opt/spark/build/scala-2.12.17/lib/scala-library.jar:/opt/spark/examples/target/scala-2.12/jars/*:/opt/spark/assembly/target/scala-2.12/jars/* JavaWordCount.java


javac  -classpath  /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar:./ JavaWordCount


















others: 
 javac 
-classpath /home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:
opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:
/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar 
JavaWordCount.java 

java -classpath  /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar:./ JavaWordCount

javac  -classpath  /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar: JavaWordCount

java -cp /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:
/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:
/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:
/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar:./ JavaWordCount


java.lang.ClassCastException: cannot assign instance of java.lang.invoke.SerializedLambda to field org.apache.spark.rdd.MapPartitionsRDD.f of type scala.Function3 in instance of org.apache.spark.rdd.MapPartitionsRDD


apache spark cannot access class sun.nio.ch.DirectBuffer 
java.lang.IllegalAccessError: class org.apache.spark.storage.StorageUtils$ (in unnamed module @0xd1f74b8) cannot access class sun.nio.ch.DirectBuffer (in module java.base) because module java.base does not export sun.nio.ch to unnamed module @0xd1f74b8
