```bash
/opt/spark/bin/spark-submit \
--class JavaWordCount \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar \
/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/input.txt \
/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/output
```


```bash
find . -name "*.jar" -exec grep -Hls 'SparkConf' {} \;
```

```bash
cd /opt/spark/
./build/mvn -DskipTests clean package
```


```bash
after check 
/opt/spark/assembly/target/scala-2.12/jars/spark-core_2.12-3.4.0.jar
/opt/spark/core/target/spark-core_2.12-3.4.0.jar
```

sparkconf

```bash
lttng-convert -i /home/rezghool/lttng-traces/auto-20231024-010206/ust/uid/1000 -o /home/rezghool/lttng-traces/auto-20231024-010206/ust/uid/1000/ctf
```
```bash

babeltrace /home/rezghool/lttng-traces/auto-20231024-010206

babeltrace2-text /home/rezghool/lttng-traces/auto-20231024-010206

babeltrace2 /home/rezghool/lttng-traces/auto-20231024-010206 --input-format text
babeltrace2 /path/to/ctf-output --component text --filter 'event.name == "my_event" && field_name == "message"'

--component auto-disc-source-ctf-fs:source.ctf.fs --params 'trace-name="ust/uid/1000/64-bit"' --params 'inputs=["/home/rezghool/lttng-traces/auto-20231024-010206/ust/uid/1000/64-bit"]' --component pretty:sink.text.pretty --component muxer:filter.utils.muxer --component debug-info:filter.lttng-utils.debug-info --connect auto-disc-source-ctf-fs:muxer --connect muxer:debug-info --connect debug-info:prettyrezghool@Rezghool:~/lttng-traces/auto-20231024-010206/ust/uid/1000/64-bit$ 

| grep 'reza'
```

slf4j slf4j

