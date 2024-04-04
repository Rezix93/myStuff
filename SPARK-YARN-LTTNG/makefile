
.PHONY: build build-nc build-progress down run run-scaled run-d stop submit

build:
	docker-compose build
image: 
	docker build -t  spark-custom-image:latest --progress=plain .

build-nc:
	docker-compose build --no-cache

build-progress:
	docker-compose build --no-cache --progress=plain

down:
	docker-compose down --volumes

run:
	make down && docker-compose up

run-scaled:
	make down && docker-compose up --scale spark-worker=2

run-d:
	make down && docker-compose up -d

stop:
	docker-compose stop

submit:
	docker exec da-spark-master spark-submit --master spark://spark-master:7077 --deploy-mode client ./apps/$(app)

dc:
	docker system df && \
	docker builder prune
dd:
	docker system df && \
	docker system prune --all

ex1:
	docker exec da-spark-master lttng create && \
	docker exec da-spark-master lttng enable-event -u -a && \
	docker exec da-spark-master lttng start && \
	docker exec da-spark-master opt/spark/bin/spark-submit --verbose --class org.apache.spark.examples.ml.JavaKMeansExample --master spark://127.0.1.1:7077 \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6 && \
	docker exec da-spark-master lttng stop && \
	docker exec da-spark-master lttng view
ex2:
		docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaKMeansExample --master spark://127.0.1.1:7077 \
  --conf "spark.executor.extraJavaOptions=-Djava.library.path=.:/usr/lib/x86_64-linux-gnu/jni/" \
  --conf "spark.driver.extraJavaOptions=-Djava.library.path=.:/usr/lib/x86_64-linux-gnu/jni/" \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6


ex3:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	sudo docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaKMeansExample --master spark://127.0.1.1:7077 \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6 && \
	lttng stop && \
 	lttng view

ex4:
	cd /opt/spark && \
	./bin/spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaKMeansExample --master spark://Rezghool:7077 \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6
ex5:
	cd /opt/spark && \
	./sbin/start-all.sh && \
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	./bin/spark-submit --verbose \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6 && \
	lttng stop && \
 	lttng view
lttng: 
	docker exec -it da-spark-master \
	lttng
bash:
	docker exec -it da-spark-master /bin/sh
cd:
	docker exec -it da-spark-master /bin/sh -c "cd /root/lttng-traces/auto-20240401-201315/ust/uid/0/64-bit && ls"
