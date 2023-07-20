https://github.com/lttng/lttng-ust/tree/master/doc/examples


My idea is :
spark uses log4j as a logging facility. So this week I was trying to use the LTTng-UST Java agent in the spark which uses Apache log4j.

Arnaud:
It should be very easy to do. You need to change the Log configuration and it should work with lttng



```bash
./configure --enable-java-agent-log4j CLASSPATH="/usr/share/java/log4j.jar"

make
sudo make install
sudo ldconfig
```

```bash
-classpath /usr/local/share/java/*:. -Djava.library.path=/usr/local/lib Test
jar tf /path/to/log4j-core-x.x.x.jar | grep org/lttng/ust/agent/log4j2/LttngLog4j2Api

export CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar"

./configure --enable-java-agent-log4j CLASSPATH="/usr/share/java/log4j.jar"
./configure --enable-java-agent-log4j2 CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar"

./configure --enable-java-agent-log4j2 CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar:/usr/local/share/java/*:."
LIBS ="/usr/local/lib"


make
sudo make install
sudo ldconfig

```

**example HelloLog4j.java 
```bash
cd ~/research/lttng-ust/lttng-ust/doc/examples/java-log4
lttng create test1
lttng enable-event -u -a
lttng start
java -cp .:/usr/share/java/log4j.jar:lttng-ust-agent-jul-2.13.1.jar:/usr/share/java/lttng-ust-agent-jul.jar:/usr/share/java/lttng-ust-agent-log4j.jar:/usr/share/java/lttng-ust-agent-jul-2.13.1.jar HelloLog4j
lttng stop
lttng view
```


java -cp "java-log4j2-basic-1.jar:/usr/share/java/lttng-ust-agent-jul.jar:/usr/share/java/lttng-ust-agent-log4j.jar:/usr/share/java/lttng-ust-agent-jul-2.13.1.jar:/path/to/log4j-core.jar:/path/to/log4j-api.jar" HelloLog4j2



Me: 
https://lttng.org/docs/v2.13/#doc-log4j

So like here I have to import the
org.lttng.ust.agent.log4j.LttngLogAppender
Or it is not related?

Arnaud: 
If you look at the examples some do not use the LttngLogAppender


the error: 
make[5]: Entering directory '/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-common'
/usr/bin/javah -classpath /usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar:. -d ../../jni/common  org.lttng.ust.agent.context.LttngContextApi && \



