
 javac 
-classpath /home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:
opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:
/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar 
JavaWordCount.java 


javac -classpath /opt/spark/build/scala-2.12.17/lib/scala-library.jar:/opt/spark/examples/target/scala-2.12/jars/*:/opt/spark/assembly/target/scala-2.12/jars/* JavaWordCount.java
javac  -classpath  /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar: JavaWordCount








java -classpath  /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar:./ JavaWordCount

javac  -classpath  /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar: JavaWordCount

java -cp /home/rezghool/Downloads/hadoop-3.3.6/share/hadoop/common/hadoop-common-3.3.6.jar:
/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:
/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar:
/opt/spark/assembly/target/scala-2.12/jars/scala-library-2.12.17.jar:path/to/log4j-core-2.x.jar:path/to/hadoop-common-x.x.x.jar:./ JavaWordCount
