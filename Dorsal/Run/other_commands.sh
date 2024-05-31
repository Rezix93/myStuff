
.PHONY: build build-nc build-progress down run run-scaled run-d stop submit

build:
	docker-compose build

build-yarn:
	docker-compose -f docker-compose.yarn.yml build

build-yarn-nc:
	docker-compose -f docker-compose.yarn.yml build --no-cache
image: 
	docker build -t  spark-custom-image:latest .

yimage: 
	docker build -t  da-spark-yarn-image:latest .

build-nc:
	docker-compose build --no-cache

build-progress:
	docker-compose build --no-cache --progress=plain

down:
	docker-compose down --volumes
down-yarn:
	docker-compose -f docker-compose.yarn.yml down --volumes --remove-orphans

run:
	make down && docker-compose up

run-scaled:
	make down && docker-compose up --scale spark-worker=2
	

run-d:
	make down && docker-compose up -d

run-yarn:
	make down-yarn && docker-compose -f docker-compose.yarn.yml up

run-yarn-scaled:
	make down-yarn && docker-compose -f docker-compose.yarn.yml up --scale spark-yarn-worker=2

stop:
	docker-compose stop

stop-yarn:
	docker-compose -f docker-compose.yarn.yml stop

submit:
	docker exec da-spark-master spark-submit --master spark://spark-master:7077 --deploy-mode client ./apps/$(app)

submit-da-book:
	make submit app=data_analysis_book/$(app)

submit-yarn-test:
	docker exec da-spark-yarn-master spark-submit --master yarn --deploy-mode cluster ./examples/src/main/python/pi.py

submit-yarn-cluster:
	docker exec da-spark-yarn-master spark-submit --master yarn --deploy-mode cluster ./apps/$(app)

rm-results:
	rm -r book_data/results/*

dc:
	docker system df && \
	docker builder prune
dd:
	docker system df && \
	docker system prune --all && \
	docker system prune -af --volumes

ex0: 
	yarn node -list
	docker exec -it opt-spark-yarn-worker-1 /bin/sh
	docker exec -it da-spark-yarn-master /bin/sh
	

	docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.SparkPi --master yarn \
	--conf "spark.executor.extraJavaOptions=-Dlog4j.configurationFile=log4j2.xml" \
	--files /opt/spark/conf/log4j.xml \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 1


	docker exec da-spark-yarn-master spark-submit --verbose \
	--class org.apache.spark.examples.SparkPi --master yarn \
	--conf "spark.executor.extraJavaOptions=-Dlog4j.configurationFile=log4j2.xml" \
	--conf "spark.driver.extraJavaOptions=-Dlog4j.configurationFile=log4j2.xml" \
	--conf spark.executor.memory=512m \
	--conf spark.driver.memory=512m \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 1


	docker exec da-spark-yarn-master spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaKMeansExample \
	--master yarn  --deploy-mode client\
	--conf spark.driver.extraJavaOptions="-Dlog4j.configurationFile=log4j2.xml" \
	--conf spark.executor.extraJavaOptions="-Dlog4j.configurationFile=log42.xml" \
	--conf spark.extraListeners=org.apache.spark.examples.MyCustomSparkListener \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6

ex1:
	docker exec da-spark-master lttng create && \
	docker exec da-spark-master lttng enable-event -u -a && \
	docker exec da-spark-master lttng start && \
	docker exec da-spark-master /opt/spark/bin/spark-submit --verbose --class org.apache.spark.examples.ml.JavaKMeansExample --master spark://127.0.1.1:7077 \
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
	--class org.apache.spark.examples.ml.JavaKMeansExample --master spark://Rezghool:7077 \
	--conf spark.executor.cores=8\
	--conf spark.executor.memory=8g \
	--conf spark.shuffle.useOldFetchProtocol=true \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6 && \
	lttng stop && \
 	lttng view

ex64:
	@echo "Starting the ex6 target... least time because eveythin is off"
	lttng create && \
	lttng enable-event -l -a  && \
	lttng start
	@# Use the Unix time command to measure the docker execution
	@time docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf "spark.executor.extraJavaOptions=-Dlog4j.configurationFile=log4j2.xml" \
	--files /opt/spark/conf/log4j.xml \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10
	lttng stop && \
	lttng view
	@echo "ex6 target completed."

ex61:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.driver.extraJavaOptions="-Dlog4j.configurationFile=log4j.xml" \
	--conf spark.executor.extraJavaOptions="-Dlog4j.configurationFile=log4j2.xml" \
	--conf "spark.executor.extraJavaOptions=-Dlog4j.configurationFile=log4j.properties" \
	--conf spark.driver.extraJavaOptions="-DlttngUstLogAppender.enabled=true" \
   --conf spark.executor.extraJavaOptions="-DlttngUstLogAppender.enabled=true" \
       /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10 && \
	lttng stop && \
 	lttng view

ex62:
	lttng create && \
	lttng enable-event -l -a   && \
	lttng start && \
	docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.eventLog.enabled=true \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10 && \
	lttng stop && \
 	lttng view

ex63:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.eventLog.enabled=true \
	--conf spark.extraListeners=org.apache.spark.examples.MyCustomSparkListener \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10 && \
	lttng stop && \
 	lttng view




ex8:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "lttngUstLogAppender"'  && \
	lttng start && \
	docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.driver.extraJavaOptions="-DlttngUstLogAppender.enabled=true" \
   --conf spark.executor.extraJavaOptions="-DlttngUstLogAppender.enabled=true" \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10 && \
	lttng stop && \
 	lttng view


ex4:
	cd /opt/spark && \
	./bin/spark-submit --verbose \
	--conf spark.shuffle.service.enabled=true \
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
	docker exec da-spark-yarn-master spark-submit --master yarn \
	--deploy-mode client ./examples/src/main/python/pi.py
	docker exec da-spark-yarn-master spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaKMeansExample \
	--master yarn  --deploy-mode client\
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6 \
	./hdfs dfs -put /opt/spark/data/mllib/sample_kmeans_data.txt /user/root/data/mllib/ \
    ./hdfs dfs -mkdir -p /user/root/data/mllib \
	hdfs://spark-yarn-master:8080/user/root/data/mllib/sample_kmeans_data.txt \
	docker exec -it da-spark-master /bin/sh
	docker exec -it opt-spark-worker-1 /bin/sh
	docker exec -it da-spark-yarn-history /bin/sh



bash-yarn:
	docker exec -it da-spark-yarn-master /bin/sh
	docker exec -it opt-spark-yarn-worker-1 /bin/sh

cd:
	docker exec -it da-spark-master /bin/sh -c "cd /root/lttng-traces/auto-20240401-201315/ust/uid/0/64-bit && ls"
move: 
	sudo chmod -R 755 /root/lttng-traces  & \
	sudo rsync -av /root/lttng-traces/ /home/rezghool/lttng-traces/ & \
	sudo chmod -R 755 /home/rezghool/lttng-traces/  
ex7:
	sudo docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
    --class org.apache.spark.examples.ml.JavaKMeansExample --master spark://Rezghool:7077 \
    --conf spark.executor.memory=4g \
    --conf spark.executor.cores=4 \
    --conf spark.network.timeout=800s \
    --conf spark.speculation=true \
	--conf spark.shuffle.useOldFetchProtocol=true \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6


ex_normal:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10000 && \
	lttng stop && \
 	lttng view

ex_no_extraListeners:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	docker exec da-spark-master opt/spark/bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.extraListeners= \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10 && \
	lttng stop && \
 	lttng view

ex6:
	@echo "Starting the ex6 target..."
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"' && \
	lttng start
	@# Use the Unix time command to measure the docker execution
	@time docker exec da-spark-master ./bin/spark-submit --verbose \
	--class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10 && \
	lttng stop && \
	lttng view
	@echo "ex6 target completed."


ex65:
	./bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.driver.extraJavaOptions="-Dlog4j.configurationFile=log4j.xml" \
	--conf spark.executor.extraJavaOptions="-Dlog4j.configurationFile=log4j.xml" \
       /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10

ex66: @echo "everythin is off. both worker and execcutor should be off" 
	@time ./bin/spark-submit --verbose \
	--class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf "spark.driver.extraJavaOptions=-Dlog4j.configurationFile=log4j.properties" \
		--conf "spark.executor.extraJavaOptions=-Dlog4j.configurationFile=log4j.properties" \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar

ex67: 
	lttng create && \
	lttng enable-event -l -a  --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"' && \
	lttng start && \
	./spark/bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 1 && \
	lttng stop && \
	lttng view

ex68: 
	lttng create && \ 
	lttng enable-event -l -a  && \
	lttng start && \
	./bin/spark-submit --verbose \
   --class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 1 & \
	lttng stop && \
	lttng view

ex70: 
	./bin/spark-submit --verbose \
   --class org.apache.spark.examples.ml.JavaGradientBoostedTreeClassifierExample --master spark://Rezghool:7077 \
   	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
    /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar



ex_pi:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	./spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.SparkPi --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 10 && \
	lttng stop && \
	lttng view

ex_PageRank:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	./spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.JavaPageRank --master spark://Rezghool:7077 \
	--conf spark.executor.cores=4 \
	--conf spark.executor.memory=4g \
	--conf spark.driver.cores=4 \
	--conf spark.driver.memory=4g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar \
	/home/rezghool/Downloads/spakinput/rangpage2.txt 100 \
	lttng stop && \
	lttng view

ex_PageRank2:
	./spark/bin/spark-submit --verbose \
		--jars /opt/spark/graphx/target/spark-graphx_2.12-3.4.0.jar \
	--class org.apache.spark.graphx.lib.PageRank --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/graphx/target/original-spark-graphx_2.12-3.4.0.jar


ex_JavaGradientBoostingClassificationExample:
	cd spark && \
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.mllib.JavaGradientBoostingClassificationExample  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar && \
	lttng stop && \
	lttng view

ex_JavaMultilayerPerceptronClassifierExample:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	cd spark && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaMultilayerPerceptronClassifierExample  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar && \
	lttng stop && \
	lttng view

ex_JavaKMeansExample:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	cd spark && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaKMeansExample  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 6 && \
	lttng stop && \
	lttng view

ex_JavaGradientBoostedTreeRegressorExample:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	cd spark && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaGradientBoostedTreeRegressorExample  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar && \
	lttng stop && \
	lttng view

ex_JavaALSExample:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	cd spark && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaALSExample  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar && \
	lttng stop && \
	lttng view

ex_JavaALS:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	cd spark && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.mllib.JavaALS  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar && \
	lttng stop && \
	lttng view

ex_JavaLatentDirichletAllocationExample:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	cd spark && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.mllib.JavaLatentDirichletAllocationExample  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=4 \
	--conf spark.executor.memory=8g \
	--conf spark.driver.cores=4 \
	--conf spark.driver.memory=8g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar && \
	lttng stop && \
	lttng view

ex_JavaLDAExample:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	lttng start && \
	cd spark && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.ml.JavaLDAExample  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=4 \
	--conf spark.executor.memory=4g \
	--conf spark.driver.cores=4 \
	--conf spark.driver.memory=8g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar && \
	lttng stop && \
	lttng view


ex_wordcount:
	# Check if the directory exists and remove it if it does
	if [ -d "/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/output" ]; then \
		rm -r "/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/output"; \
	fi
	# Additional commands can be added herelt
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	sudo lttng start && \
	/opt/spark/bin/spark-submit --verbose \
	--class JavaWordCount --master spark://Rezghool:7077 \
	--conf spark.executor.cores=4 \
	--conf spark.executor.memory=8g \
	--conf spark.driver.cores=4 \
	--conf spark.driver.memory=8g \
	--conf spark.executor.instances=2 \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar \
	/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/cebwiki-20240420-pages-articles-multistream-index.txt\
	/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/output  && \
	lttng stop && \
	lttng view



ex_w:
	# Check if the directory exists and remove it if it does
	if [ -d "/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/output" ]; then \
		rm -r "/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/output"; \
	fi
	/opt/spark/bin/spark-submit --verbose \
	--class JavaWordCount --master yarn \
	--deploy-mode client \
	--conf spark.executor.instances=2 \
	--conf spark.executor.cores=4 \
	--conf spark.executor.memory=4g \
    --conf spark.dynamicAllocation.enabled=false \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar \
	/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/input.txt \
	/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/output 

ex_JavaHdfsLR:
	# Additional commands can be added herelt
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	sudo lttng start && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.JavaHdfsLR  --master spark://Rezghool:7077 \
	--conf spark.executor.cores=4 \
	--conf spark.executor.memory=8g \
	--conf spark.driver.cores=4 \
	--conf spark.driver.memory=8g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar \
	/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/JavaHdfsLR.txt 1  && \
	sudo lttng stop && \
	sudo lttng view

ex_JavaNetworkWordCount:
	lttng create && \
	lttng enable-event -l -a --filter 'logger_name == "org.apache.spark.examples.MyCustomSparkListener"'  && \
	sudo lttng start && \
	/opt/spark/bin/spark-submit --verbose \
	--class org.apache.spark.examples.streaming.JavaNetworkWordCount --master spark://Rezghool:7077 \
	--conf spark.executor.cores=1 \
	--conf spark.executor.memory=1g \
	--conf spark.driver.cores=1 \
	--conf spark.driver.memory=1g \
	/opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar \
	localhost 9999 &&  \
	lttng stop && \
	lttng view
bspark:
	cd spark && \
	mvn install:install-file \
	-Dfile=/usr/local/share/java/lttng-ust-agent-log4j-1.0.0.jar \
	-DgroupId=org.lttng.ust.agent \
	-DartifactId=lttng-ust-agent-log4j \
	-Dversion=1.0.0 \
	-Dpackaging=jar

	mvn install:install-file \
	-Dfile=/usr/local/share/java/lttng-ust-agent-log4j2-1.0.0.jar \
	-DgroupId=org.lttng.ust.agent \
	-DartifactId=lttng-ust-agent-log4j2 \
	-Dversion=2.1.0 \
	-Dpackaging=jar

	./build/mvn -Pyarn -Dhadoop.version=3.3.4 -DskipTests clean package  -rf :spark-examples_2.12
	./build/mvn -DskipTests clean package -rf :spark-examples_2.12
	./build/mvn -T 1C -DskipTests clean package -rf :spark-examples_2.12
	 ./build/mvn -Pyarn -Phadoop-3.4 -Dhadoop.version=3.4.0 -DskipTests clean package  
	 -rf :spark-sql_2.12

	curl https://dlcdn.apache.org/hadoop/common/hadoop-3.34/hadoop-3.3.4.tar.gz \

	-o hadoop-${HADOOP_VERSION}-bin.tar.gz \
	&& tar xfz hadoop-${HADOOP_VERSION}-bin.tar.gz --directory /opt/hadoop --strip-components 1 \
	&& rm -rf hadoop-${HADOOP_VERSION}-bin.tar.gz
	docker cp /home/username/research/data/input.txt da-spark-master:/home/rezghool/research/lttng-ust/lttng-ust/doc/examples/exmaple-submit/input.txt

mv_trace: 
	mv /root/lttng-traces/auto-20240418-161902 /home/rezghool/lttng-traces
	sudo chmod -R 777 /home/rezghool/lttng-traces


eclipse: 
	export GDK_BACKEND=x11 && \
	./eclipse -clean -safeMode
