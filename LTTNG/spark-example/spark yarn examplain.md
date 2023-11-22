https://medium.com/@MarinAgli1/setting-up-hadoop-yarn-to-run-spark-applications-6ea1158287af

https://sujithjay.com/spark/with-yarn



Spark yarn cluster vs client - how to choose which one to use?

https://stackoverflow.com/questions/41124428/spark-yarn-cluster-vs-client-how-to-choose-which-one-to-use\

Spark Application UI: http://localhost:4040/

Resource Manager: http://localhost:9870

Spark JobTracker: http://localhost:8088/

Node Specific Info: http://localhost:8042/

What I understand from this is that both strategies use the cluster to distribute tasks; the difference is where the "driver program" runs: locally with spark-submit, or, also in the cluster.




. The map function iterates over the elements of a collection and applies a transformation function on each element. The reduce function converts the input array into an array of a different dimension or single value. The combination of these functions proved to be a very powerful programming model.


In Apache Spark, when you submit a job using `spark-submit`, you can choose between two modes for deploying your application: `cluster` mode and `client` mode. These modes define where the driver program runs.

### Cluster Mode

In **cluster** mode, the Spark driver runs inside an application master process which is managed by YARN, Kubernetes, Mesos, or the standalone scheduler. This mode is fully managed by the cluster.

- **Advantages**:
  - The driver is managed by the cluster, which can help in resource optimization.
  - Better for long-running jobs, as it's less susceptible to network issues between your client machine and the cluster.
  - Useful when submitting jobs from a machine with limited resources, as the driver runs on a cluster node.

- **Usage Scenarios**:
  - When you submit jobs from a machine that is not always connected to the cluster.
  - For automated workflows and scripts, typically in production environments.

- **Considerations**:
  - You can't directly see the output of the Spark job on your console. You have to rely on logs or the Spark UI.

### Client Mode

In **client** mode, the Spark driver runs on the machine where the `spark-submit` command is executed. The executors run on the nodes in the cluster.

- **Advantages**:
  - Immediate feedback and direct access to the Spark job’s output in the console where you submit the job.
  - Useful for interactive and debugging purposes.

- **Usage Scenarios**:
  - Ideal for interactive analysis and debugging.
  - When you need to directly see the output of your Spark job as it happens.

- **Considerations**:
  - The machine where you're running `spark-submit` must be network-accessible from the worker nodes.
  - The driver can be a single point of failure if the client machine is disconnected from the cluster or has issues.

### Summary

- Use **cluster mode** when you want the entire application to run within the cluster. This mode is often used in production environments, especially for automated job submissions.
- Use **client mode** for interactive and debugging purposes, or when you need to directly monitor the output of your application.

In both modes, the executors that run the actual tasks are always distributed across the cluster nodes. The primary difference lies in where the driver program, which coordinates the Spark job, runs.
