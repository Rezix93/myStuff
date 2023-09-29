

```bash

ajc -source 1.8 -classpath 
"/usr/local/share/java/log4j.jar:
/usr/share/java/log4j.jar:
/usr/local/share/java/lttng-ust-agent-log4j2.jar:/usr/share/java/lttng-ust-agent-log4j2.jar:
/usr/local/share/java/lttng-ust-agent-common.jar:/usr/share/java/lttng-ust-agent-common.jar:
/usr/share/java/aspectjrt.jar:
/opt/spark/assembly/target/scala-2.12/jars/*" -g 
HelloLog4j2.java LoggingAspect.java

```
