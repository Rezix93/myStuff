
```
docker ps

docker ps -a 

docker cp -L test.py cluster_container-spark-master-1:/opt/bitnami/spark/test.py

docker logs cluster_container-spark-master-1

docker-compose exec spark-master spark-submit --master spark://spark-master:7077 /opt/bitnami/spark/test.py





```
docker cp -L your_program.py spark_spark-master_1:/opt/bitnami/spark/anyfilename.py


To run Docker with the provided `docker-compose.yml` configuration, you'll first need to make sure Docker and Docker Compose are installed on your system. Then, follow these steps:

1. **Save the Configuration**: Copy the configuration you've posted into a file named `docker-compose.yml`. Ensure you correct any syntax errors or misalignments, especially with the dashes and indentation, which are crucial for YAML files. Here's a corrected version of your configuration for clarity:

    ```yaml
    version: '3.7'

    services:
      spark-master:
        image: bitnami/spark:latest
        command: bin/spark-class org.apache.spark.deploy.master.Master
        ports:
          - "9090:8080"
          - "7077:7077"

      spark-worker-1:
        image: bitnami/spark:latest
        command: bin/spark-class org.apache.spark.deploy.worker.Worker spark://spark-master:7077
        depends_on:
          - spark-master
        environment:
          SPARK_MODE: worker
          SPARK_WORKER_CORES: 2
          SPARK_WORKER_MEMORY: 2g
          SPARK_MASTER_URL: spark://spark-master:7077

      spark-worker-2:
        image: bitnami/spark:latest
        command: bin/spark-class org.apache.spark.deploy.worker.Worker spark://spark-master:7077
        depends_on:
          - spark-master
        environment:
          SPARK_MODE: worker
          SPARK_WORKER_CORES: 2
          SPARK_WORKER_MEMORY: 2g
          SPARK_MASTER_URL: spark://spark-master:7077
    ```

2. **Run Docker Compose**: Open a terminal or command prompt, navigate to the directory where you saved the `docker-compose.yml` file, and run the following command:

    ```sh
    docker-compose up -d
    ```

    This command will start all the services defined in your `docker-compose.yml` file in detached mode, meaning they'll run in the background.

3. **Verify the Services**: To check that the Spark master and worker nodes are running, you can use:

    ```sh
    docker-compose ps
    ```

    This command lists the running services. You should see your Spark master and worker services listed.

4. **Accessing Spark Master UI**: Since you've mapped the Spark master's 8080 port to 9090 on your host, you can access the Spark master's web UI by opening a web browser and navigating to `http://localhost:9090`. Here, you should see the Spark master's dashboard, which will list the connected workers.

5. **Scaling Workers**: If you decide you need more workers, Docker Compose makes it easy to scale your services. For example, to increase the number of workers to 3, you could run:

    ```sh
    docker-compose up -d --scale spark-worker-1=3
    ```

    However, this command will only scale the `spark-worker-1` service. Since your file defines separate services for each worker, you'd typically add more worker services in the `docker-compose.yml` for static scaling or explore dynamic scaling solutions.

6. **Stopping the Cluster**: When you're done, you can stop your Docker Compose services by running:

    ```sh
    docker-compose down
    ```

    This command stops and removes the containers created by Docker Compose, based on the services defined in your `docker-compose.yml` file. If you want to also remove the volumes and networks, you can add the `-v` option.

Remember, each service in your Docker Compose file is a separate container, and by using Docker Compose, you're orchestrating these containers to work together as a unified system.
