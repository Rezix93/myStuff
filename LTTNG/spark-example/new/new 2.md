```
sudo chown -R rezghool:rezghool /media/rezghool/7dde372b-b2c9-4ba8-b9f5-18f32a3685ec

./sbin/start-master.sh

./sbin/start-worker.sh --cores 4 --memory 4g spark://Rezghool:7077

http://localhost:8080/

ps -ef | grep spark

```

```
docker system df
docker builder prune


docker rmi -f spark-custom-image:latest

docker build -t spark-custom-image .

docker run -it --name spark-container -p 8080:8080 spark-custom-image:latest


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
