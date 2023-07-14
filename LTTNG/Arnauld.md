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

export CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar"

./configure --enable-java-agent-log4j CLASSPATH="/usr/share/java/log4j.jar"
./configure --enable-java-agent-log4j2 CLASSPATH="/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar"

make
sudo make install
sudo ldconfig


java -cp .:/usr/share/java/log4j.jar:lttng-ust-agent-jul-2.13.1.jar:/usr/share/java/lttng-ust-agent-jul.jar:/usr/share/java/lttng-ust-agent-log4j.jar:/usr/share/java/lttng-ust-agent-jul-2.13.1.jar HelloLog4j
```

