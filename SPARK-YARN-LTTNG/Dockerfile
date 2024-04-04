# Start with a base image that has Java installed
FROM openjdk:11-jdk

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
      sudo \
      curl \
      vim \
      unzip \
      rsync \
      openjdk-11-jdk \
      build-essential \
      software-properties-common \
      ssh && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

#RUN apt-add-repository -y ppa:lttng/stable-2.13 && \
#    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys F4A7DFFC33739778

RUN apt-get update

#RUN  apt-get install -y lttng-tools \
#       liblttng-ust-dev 


RUN apt-get -qq -y install liblttng-ust-java-jni liblttng-ust-java 
#liblttng-ust-agent-java  liblttng-ust-agent-java-jni 
#liblttng-ust-dev

RUN apt-get install -y --no-install-recommends \
    automake \
    autoconf \
    libtool \
    pkg-config \
    build-essential \
    liburcu* \
    liblog4j* \
    libnuma-dev  # Add libnuma-dev here

# Set the JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64

# Add the Spark and Hadoop bin and sbin to the PATH variable.
# Also add $JAVA_HOME/bin to the PATH
ENV PATH="$SPARK_HOME/sbin:/opt/spark/bin:${PATH}"
ENV PATH="$HADOOP_HOME/bin:$HADOOP_HOME/sbin:${PATH}"
ENV PATH="${PATH}:${JAVA_HOME}/bin"

# Setup Spark related environment variables
ENV SPARK_MASTER="spark://spark-yarn-master:7077"
ENV SPARK_MASTER_HOST spark-yarn-master
ENV SPARK_MASTER_PORT 7077
ENV PYSPARK_PYTHON python3
ENV HADOOP_CONF_DIR="$HADOOP_HOME/etc/hadoop"


# Set user for HDFS and Yarn (for production probably not smart to put root)
ENV HDFS_NAMENODE_USER="root"
ENV HDFS_DATANODE_USER="root"
ENV HDFS_SECONDARYNAMENODE_USER="root"
ENV YARN_RESOURCEMANAGER_USER="root"
ENV YARN_NODEMANAGER_USER="root"


# Add JAVA_HOME to haddop-env.sh
RUN echo "export JAVA_HOME=${JAVA_HOME}" >> \
    "$HADOOP_HOME/etc/hadoop/hadoop-env.sh"

# COPY the appropriate configuration files to their appropriate locations
COPY yarn/spark-defaults.conf "$SPARK_HOME/conf/"
COPY yarn/*.xml "$HADOOP_HOME/etc/hadoop/"

# Make the binaries and scripts executable and set the PYTHONPATH environment variable
RUN chmod u+x /opt/spark/sbin/* && \
    chmod u+x /opt/spark/bin/*

RUN ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa && \
  cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys && \
  chmod 600 ~/.ssh/authorized_keys

COPY ssh_config ~/.ssh/config


# Add Hadoop native library path to the dynamic link library path
ENV LD_LIBRARY_PATH="$HADOOP_HOME/lib/native:${LD_LIBRARY_PATH}"

RUN dpkg -L liblog4j1.2-java
RUN find / -name log4*.jar

# Download and extract LTTng
RUN wget https://lttng.org/files/lttng-ust/lttng-ust-latest-2.13.tar.bz2
RUN tar -xf lttng-ust-latest-2.13.tar.bz2 && ls -l && \
    rm lttng-ust-latest-2.13.tar.bz2

# Set Spark home environment variable
ENV SPARK_HOME=${SPARK_HOME:-"/opt/spark"}
ENV HADOOP_HOME=${HADOOP_HOME:-"/opt/hadoop"}

RUN mkdir -p ${HADOOP_HOME} && mkdir -p ${SPARK_HOME}
WORKDIR ${SPARK_HOME}

#ENV SPARK_MASTER="spark://spark-master:7077"
#ENV SPARK_MASTER_HOST spark-master
ENV SPARK_MASTER_PORT 7077

# Set the CLASSPATH environment variable
#ENV CLASSPATH /opt/spark/core/target/jars/lttng-ust-files/log4j-core-2.20.0.jar:/opt/spark/core/target/jars/lttng-ust-files/log4j-api-2.20.0.jar:/opt/spark/core/target/jars/lttng-ust-files/log4j.jar

#ENV CLASSPATH .:/usr/local/lib/:/usr/share/java/

ENV CLASSPATH $CLASSPATH:/usr/share/java/log4j.jar:/usr/share/java/log4j-core.jar:/usr/share/java/log4j-api.jar

#RUN export CLASSPATH=".:/usr/share/java/"

# Configure and install LTTng
RUN  cd lttng-ust-2.13.7 && \
    CLASSPATH="/usr/share/java/*"  ./configure --enable-java-agent-log4j --enable-java-agent-all --enable-java-agent-log4j2 && \
    make && \
    make install && \
    ldconfig


# Cleanup
RUN apt-get clean && \
    apt-get autoremove && \
    rm -rf /lttng-ust-2.13.*


# Download and install Hadoop
RUN curl https://dlcdn.apache.org/hadoop/common/hadoop-3.3.1/hadoop-3.3.1.tar.gz -o hadoop-3.3.1-bin.tar.gz \
 && tar xfz hadoop-3.3.1-bin.tar.gz --directory /opt/hadoop --strip-components 1 \
 && rm -rf hadoop-3.3.1-bin.gz


# Define the entrypoint/command, adjust according to your needs
CMD ["bash"]

# Set PATH to include Spark's bin directory
ENV PATH=$SPARK_HOME/bin:$PATH
 
#ENV LD_PRELOAD=/usr/local/lib/x86_64-linux-gnu/liblttng-ust-fork.so
ENV LD_PRELOAD=/usr/local/lib/liblttng-ust-fork.so
#ENV JAVA_TOOL_OPTIONS "-Djava.library.path=.:/usr/lib/x86_64-linux-gnu/jni:/usr/lib/x86_64-linux-gnu"
ENV JAVA_TOOL_OPTIONS "-Djava.library.path=.:/usr/local/lib/"

# Use a placeholder for the host IP address
ENV HOST_IP=host.docker.internal


COPY spark/ $SPARK_HOME/


# Create and set permissions for the Spark event log directory
RUN mkdir -p /var/spark/events && \
    chmod -R 777 /var/spark/events

#COPY conf/spark-defaults.conf "$SPARK_HOME/conf"

RUN chmod u+x /opt/spark/sbin/* && \
    chmod u+x /opt/spark/bin/*

RUN apt-get update && apt-get install -y rsync

# Expose Spark UI port, Master, Worker, and other necessary ports
EXPOSE 8080 7077 6066 4040 22

#CMD ["java", "-Djava.library.path=.:/usr/lib/x86_64-linux-gnu/jni", "-jar", "/app/your-application.jar"]
CMD ["java", "-Djava.library.path=.:/usr/local/lib", "-jar", "/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar"]

# Since your Dockerfile and Spark are both in /opt/, this COPY command should work as expected
    
# Copy the entrypoint script into the image
COPY entrypoint.sh /entrypoint.sh

# Make the entrypoint script executable
RUN chmod +x /entrypoint.sh

# Default command to run Spark shell, adjust as needed
ENTRYPOINT ["/entrypoint.sh"]
