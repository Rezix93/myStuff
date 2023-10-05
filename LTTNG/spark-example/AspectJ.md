
Use aspect-oriented programming (AOP) extension
Because Scala, the
Because Scala, the native program language used by Spark, is a JVM language. So we can AspectJ to intercept Spark code. In this way, we can modify the Spark APIs without modifying Spark source code directly.

For those not familiar with AspectJ, here is a brief introduction. AspectJ is an aspect-oriented programming (AOP) extension created at PARC for the Java programming language. AspectJ has become a widely used de facto standard for AOP by emphasizing simplicity and usability for end users.

```bash

ajc -source 1.8 -classpath "/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-api-2.20.0.jar:/usr/share/java/aspectjrt.jar:/opt/spark/assembly/target/scala-2.12/jars/*" -g HelloLog4j2.java LoggingAspect.java

```

```bash

java -classpath ".:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-api-2.20.0.jar:/usr/share/java/aspectjrt.jar:/opt/spark/assembly/target/scala-2.12/jars/*" HelloLog4j2

```

make
then .run the file 

```bash

ajc -source 1.8 -classpath "/home/rezghool/.m2/repository/org/scala-lang/scala-library/scala-library-2.13.8.jar:/usr/local/share/java/log4j.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/usr/local/share/java/lttng-ust-agent-log4j2.jar:/usr/local/share/java/lttng-ust-agent-common.jar:/usr/share/java/lttng-ust-agent-common.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-api-2.20.0.jar:/usr/share/java/aspectjrt.jar:/opt/spark/assembly/target/scala-2.12/jars/*" -g HelloLog4j2.java LoggingAspect.java

```

