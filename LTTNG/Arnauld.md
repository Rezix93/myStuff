https://github.com/lttng/lttng-ust/tree/master/doc/examples


My idea is :
spark uses log4j as a logging facility. So this week I was trying to use the LTTng-UST Java agent in the spark which uses Apache log4j.

Arnaud:
It should be very easy to do. You need to change the Log configuration and it should work with lttng


```bash
cd ~/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic
lttng create test1
lttng enable-event -l -a
lttng start
./run
lttng stop
lttng view
```

```bash
cd ~/research/lttng-ust/lttng-ust
 export CLASSPATH="/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-core-2.20.0.jar:/home/rezghool/Downloads/apache-log4j-2.20.0-bin/log4j-api-2.20.0.jar"

./configure --enable-java-agent-log4j2
make
sudo make install
sudo ldconfig
```












```bash
-classpath /usr/local/share/java/*:. -Djava.library.path=/usr/local/lib Test
java -cp /usr/share/java/jarpath/lttng-ust-agent-common.jar:/usr/share/java/jarpath/lttng-ust-agent-log4j.jar:$LOG4JPATH:. Test

export CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar:/usr/share/java/log4j.jar"

find . -name "*.jar" -exec jar tf {} \; | grep "org.lttng.ust.agent.log4j*\.class"


jar tf /usr/share/java/log4j-core.jar | grep org/lttng/ust/agent/log4j/LttngLog4j2Api
jar tf /usr/share/java/log4j-api.jar | grep org/lttng/ust/agent/log4j2/LttngLog4j2Api

export CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar"

~/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2/org/lttng/ust/agent/log4j2

./configure --enable-java-agent-log4j CLASSPATH="/usr/share/java/*:."
./configure --enable-java-agent-log4j2 CLASSPATH="/usr/share/java/*:."

./configure --enable-java-agent-log4j2 CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar"


./configure --enable-java-agent-log4j --enable-java-agent-log4j2 CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar"
 CLASSPATH="/usr/share/java/*:."


after make log4j1:

###Making all in lttng-ust-agent-log4j
make[5]: Entering directory '/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j'
CLASSPATH=.:./.${CLASSPATH:+":$CLASSPATH"} javac -d . -classpath /usr/share/java/*:.:./../lttng-ust-agent-common/lttng-ust-agent-common.jar    org/lttng/ust/agent/log4j/LttngLog4jAgent.java org/lttng/ust/agent/log4j/LttngLog4jApi.java org/lttng/ust/agent/log4j/LttngLogAppender.java
echo timestamp > classnoinst.stamp
jar cfm  lttng-ust-agent-log4j-1.0.0.jar ./Manifest.txt org/lttng/ust/agent/log4j/*.class && rm -f lttng-ust-agent-log4j.jar && ln -s lttng-ust-agent-log4j-1.0.0.jar lttng-ust-agent-log4j.jar
/usr/bin/javah -classpath /usr/share/java/*:.:. -d ../../jni/log4j  org.lttng.ust.agent.log4j.LttngLog4jApi && \
echo "Log4j JNI header generated" > log4j-jni-header.stamp


make[5]: Leaving directory '/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j'

####Making all in lttng-ust-agent-log4j2:
make[5]: Entering directory '/home/rezghool/research/lttng-ust/lttng-ust/src/lib/lttng-ust-java-agent/java/lttng-ust-agent-log4j2'
/usr/bin/javah -classpath /usr/share/java/*:.:. -d ../../jni/log4j  org.lttng.ust.agent.log4j2.LttngLog4j2Api && \
echo "Log4j JNI header generated" > log4j-jni-header.stamp


Error: Could not find class file for 'org.lttng.ust.agent.log4j2.LttngLog4j2Api'.


log4j.core.config.plugins.processor.PluginProcessor    org/lttng/ust/agent/log4j2/LttngLog4j2Agent.java org/lttng/ust/agent/log4j2/LttngLog4j2Api.java org/lttng/ust/agent/log4j2/LttngLogAppender.java
Note: Processing Log4j annotations
Note: Annotations processed
Note: Processing Log4j annotations
Note: No elements to process
echo timestamp > classnoinst.stamp
jar cfm  lttng-ust-agent-log4j2-1.0.0.jar ./Manifest.txt META-INF org/lttng/ust/agent/log4j2/*.class && rm -f lttng-ust-agent-log4j2.jar && ln -s lttng-ust-agent-log4j2-1.0.0.jar lttng-ust-agent-log4j2.jar
/usr/bin/javah -classpath /usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar:. -d ../../jni/log4j  org.lttng.ust.agent.log4j2.LttngLog4j2Api && \
echo "Log4j JNI header generated" > log4j-jni-header.stamp
Error: Could not find class file for 'org.lttng.ust.agent.log4j2.LttngLog4j2Api'.




org/lttng/ust/agent/log4j2/LttngLog4j2Agent.java:46: error: cannot find symbol
			log4j2_instance = new LttngLog4j2Agent(Domain.LOG4J2);
			                                             ^
  symbol:   variable LOG4J2
  location: class Domain
org/lttng/ust/agent/log4j2/LttngLogAppender.java:96: error: cannot find symbol
		} else if (domain == LttngLog4j2Agent.Domain.LOG4J2) {
		                                            ^
  symbol:   variable LOG4J2
  location: class Domain
2 errors




./configure --enable-java-agent-log4j --enable-java-agent-log4j2 CLASSPATH="/usr/share/java/*:."


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
lttng enable-event -l -a
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




