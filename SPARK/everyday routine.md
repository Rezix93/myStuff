```console
ssh $dorsal-server
```

```bash
spark-shell
```

https://www.alibabacloud.com/forum/read-471
https://lttng.org/docs/v2.13/#doc-java-application


January 23 2022: 

https://academic.oup.com/gigascience/article/7/8/giy098/5067872
1. Bioinformatics tools and algorithms based on Apache Spark


2. SparkCAD: Caching Anomalies Detector for Spark Applications

That sounds interesting but do you have results, an application running on Spack and generating traces?

Well now you can start looking at spark source code and insert some trace points at locations where you deem it necessary or interesting.
12:23
https://www.alibabacloud.com/forum/read-471
alibabacloud.comalibabacloud.com
Abstract: Before the Spark source code walkthrough, reading the Spark thesis by Matei Zaharia , Apache Spark source code walkthrough - Spark thesis reading notes and job submission and running.

This looks like a good starting point to find out the interesting steps in job computation


https://lttng.org/docs/v2.13/#doc-java-application
12:25
This is the documentation to start writing tracepoints in java source code



After installing brew: 


```bash
 echo '# Set PATH, MANPATH, etc., for Homebrew.' >> /home/rezghool/.profile
 echo 'eval "$(/home/linuxbrew/.linuxbrew/bin/brew shellenv)"' >> /home/rezghool/.profile
 eval "$(/home/linuxbrew/.linuxbrew/bin/brew shellenv)"
```

```bash
javac -cp /usr/share/java/jarpath/lttng-ust-agent-common.jar:/usr/share/java/jarpath/lttng-ust-agent-jul.jar Test.java
 ./configure --enable-java-agent-log4j CLASSPATH:/usr/local/apache-log4j-2.19.0-bin/log4j-1.2-api-2.19

```
