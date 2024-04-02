
spark.executor.extraJavaOptions:   liblttng-ust-log4j-jni.so
spark.driver.extraClassPath        lttng-ust-agent-common.jar :: lttng-ust-agent-log4j2.jar :: lttng-ust-agent-log4j.jar


```
Question: 
you said liblttng-ust-fork.so but there is no in containers.
 docker exec -it opt-spark-worker-1 /bin/sh -c "locate liblttng-ust-fork.so"

liblttng-ust-log4j-jni.so inam vase sspark conf


./bin/spark-submit --class org.apache.spark.examples.ml.JavaKMeansExample --master spark://Rezghool:7078    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6



where is :  lttng-ust-agent-log4j.jar

 docker exec -it opt-spark-worker-1 /bin/sh -c "locate lttng-ust-agent-log4j.jar"


```


/usr/lib/x86_64-linux-gnu/jni

lttng session: 
lttng-ust-log4j-jni.so


```
sudo lttng-sessiond


netstat -patune|grep 7077
git clone https://github.com/lttng/lttng-modules.git
cd lttng-modules/
make -j4
 make modules_install

ls /lib/modules/6.5.0-26-generic/updates/

git checktout stable-2.13
```
-Djava.library.path=.:/usr/local/lib/

${SPARK_HOME}/bin/spark-submit \ \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--master spark://Rezghool:7077 \
--conf spark.driver.extraClassPath=/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-common.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j2.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar \
/op


for undestand which container is this from : 
lttng add-context --userspace  --type=pid_ns

```
Setting up lttng-modules-dkms (2.14~pre-0+git1901+202403260133~ubuntu22.04.1) ...
Removing old lttng-modules-2.14~pre DKMS files...
Deleting module lttng-modules-2.14~pre completely from the DKMS tree.
Loading new lttng-modules-2.14~pre DKMS files...
Building for 6.5.0-26-generic
Building initial module for 6.5.0-26-generic
```

```
sudo chown -R rezghool:rezghool /media/rezghool/7dde372b-b2c9-4ba8-b9f5-18f32a3685ec

./sbin/start-master.sh

./sbin/start-worker.sh --cores 4 --memory 4g spark://Rezghool:7077


./sbin/start-worker.sh --cores 8 --memory 16g spark://Rezghool:7077

http://localhost:8080/



sudo mv /var/lib/docker/* /media/rezghool/7dde372b-b2c9-4ba8-b9f5-18f32a3685ec/docker



ps -ef | grep spark
docker system df
docker builder prune
docker container prune -f
docker image prune -a -f
docker volume prune -f


docker build -t spark-custom-image:latest .

docker run --network="host" -v /home/rezghool/research/spark_example:/data/mllib spark-custom-image:latest 4G 2 6


docker run -it --name spark-master spark-custom-image /opt/spark/sbin/start-master.sh


if [ $# -eq 3 ]; then
    EXECUTOR_MEMORY=$1 # total excutor memory
    EXECUTOR_CORES=$2 # number of core for each core
    INPUT=$3 $ # input of my example 
fi


```
export JAVA_LIBRARY_PATH=$JAVA_LIBRARY_PATH:/usr/lib/hadoop/lib/native
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/lib/hadoop/lib/native

-Djava.library.path=/usr/local/lib


	docker exec da-spark-master lttng create \
	docker exec da-spark-master lttng enable-event -l -a  --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"' \
	docker exec da-spark-master lttng start \
	docker exec da-spark-master \
	/bin/spark-submit \
	--verbose \
	--class org.apache.spark.examples.ml.JavaKMeansExample \
	--master spark://spark-master:7077 \
	--conf "spark.executor.extraJavaOptions=-Djava.library.path=/usr/lib/x86_64-linux-gnu/jni" \
    --conf "spark.driver.extraJavaOptions=-Djava.library.path=/usr/lib/x86_64-linux-gnu/jni" \
	--conf spark.driver.extraClassPath=/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-common.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j2.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar \
	--conf spark.executor.extraClassPath=/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-common.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j2.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6 \
	docker exec da-spark-master lttng stop \
	docker exec da-spark-master lttng view \
	docker exec da-spark-master lttng destroy 




Check Docker Logs: View the Docker service logs to see if there are any specific error messages or clues about what caused the failure. You can view the logs using the following command:

bash
Copy code
journalctl -xeu docker


--jars /opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-common.jar,/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j2.jar,/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar \

```

docker exec da-spark-master \
opt/spark/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--master spark://spark-master:7077 \
--jars /opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-common.jar,/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j2.jar,/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar \
--deploy-mode client \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6


${SPARK_HOME}/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--master spark://Rezghool:7077 \
--conf spark.driver.extraClassPath=/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-common.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j2.jar:/opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6

docker exec -it da-spark-master /bin/sh -c "cd /usr/usr/lib/x86_64-linux-gnu/ && ls"


${SPARK_HOME}/bin/spark-submit \
--verbose \
--class org.apache.spark.examples.ml.JavaKMeansExample \
--master spark://Rezghool:7077 \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6

--conf spark.executor.memory="4G" \
--conf spark.executor.cores=3 \
/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6


docker exec 230621b82c3b ls /opt/spark/core/target/jars/lttng-agent/lttng-ust-agent-log4j.jar


--master yarn-client --executor-memory 4G --executor-cores 2 --num-executors 12 (less core, more executor)





docker build -t spark-custom-image .
docker build -t spark-custom-image:latest .


docker run -it --network="host" spark-custom-image:latest /bin/bash


docker run spark-custom-image:latest

```


```
docker system df
docker builder prune


docker rmi -f spark-custom-image:latest

docker build -t spark-custom-image .

docker run -it --name spark-container -p 8080:8080 spark-custom-image:latest

docker run --add-host=Rezghool:<Your_Ubuntu_Host_IP> spark-custom-image:latest
docker run --add-host=Rezghool:192.168.X.X spark-custom-image:latest

docker run --add-host=Rezghool:10.200.27.70 spark-custom-image:latest

docker run spark-custom-image:latest


docker stop spark-container

docker rm spark-container

docker rmi a1b2c3d4e5f6

docker start spark-container-new

docker attach spark-container-new

docker logs spark-container-new

```


Considering the issues you've encountered, let's create a simplified and new version of the Dockerfile that assumes your local Spark directory (`/opt/spark`) can be successfully copied into the Docker image. This version will include basic instructions and assumes that you're building the Docker image from a directory where you have the appropriate permissions to access and copy `/opt/spark`.

**Important Note:** Before proceeding, ensure that you have a copy of your Spark directory accessible in the same directory as your Dockerfile or adjust the paths accordingly if you're using a different setup.

### Simplified Dockerfile

```Dockerfile
# Start with a base image that has Java installed
FROM openjdk:11-jdk

# Set the working directory in the Docker image
WORKDIR /opt/spark

# Assuming the Spark directory is now within the build context,
# adjust the COPY source path accordingly
COPY ./spark /opt/spark

# Set environment variables for Spark
ENV SPARK_HOME=/opt/spark
ENV PATH=$PATH:$SPARK_HOME/bin

# Expose ports for Spark UI and Spark Master
EXPOSE 8080 7077 6066 4040

# Default command to run when starting the container
CMD ["/opt/spark/bin/spark-shell"]
```

### Steps to Use This Dockerfile

1. **Prepare Your Spark Directory**: Make sure to place a copy of your Spark directory (`/opt/spark`) within the same directory as this Dockerfile or adjust the `COPY` command's source path to where you've placed your Spark directory relative to the Dockerfile.

2. **Build the Docker Image**:
   Navigate to the directory containing your Dockerfile and the Spark directory copy. Then, build your Docker image using the following command:

   ```bash
   docker build -t my-custom-spark-image .
   ```

   This command builds a Docker image named `my-custom-spark-image` based on the instructions in your Dockerfile.

3. **Run Your Docker Container**:
   After successfully building the image, you can run a container based on your new image:

   ```bash
   docker run -it --name spark-container -p 8080:8080 spark-custom-image:latest
   ```

   This command starts a container named `my-spark-container` in interactive mode, maps port 8080 from the container to port 8080 on your host (allowing you to access the Spark web UI), and uses the image `my-custom-spark-image`.

### Final Notes

- Ensure that the `./spark` path in the `COPY` command accurately reflects the location of your Spark directory within the Docker build context.
- Adjust the `EXPOSE` and `CMD` instructions as needed based on your specific Spark configuration and the components you wish to run within the container.
- This approach assumes that your Docker build context has access to the Spark directory. If you encounter permissions issues or context-related errors, you may need to review Docker's documentation on build contexts and file permissions.
