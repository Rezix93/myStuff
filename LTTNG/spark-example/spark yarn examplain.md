https://medium.com/@MarinAgli1/setting-up-hadoop-yarn-to-run-spark-applications-6ea1158287af

https://sujithjay.com/spark/with-yarn


The ApplicationMaster, which is a non-executor container, requires CPU that must
be accounted for.
• When we run spark application, there will be several daemons that will run in the
background (Hadoop and YARN daemons). So, we must leave at least 1 core per
node for these daemons.
• When the number of cores is too high, the spark application spends more time and
this is because of switching between threads, consequently, it required more com-
puting and eventually more processing time.
• The HDFS client has difficulty processing many concurrent threads. Increasing num-
ber of cores per executor can lead to bad HDFS I/O throughput.




Spark yarn cluster vs client - how to choose which one to use?

https://stackoverflow.com/questions/41124428/spark-yarn-cluster-vs-client-how-to-choose-which-one-to-use\

Spark Application UI: http://localhost:4040/

Resource Manager: http://localhost:9870

Spark JobTracker: http://localhost:8088/

Node Specific Info: http://localhost:8042/

What I understand from this is that both strategies use the cluster to distribute tasks; the difference is where the "driver program" runs: locally with spark-submit, or, also in the cluster.


Apache Spark can be run with various cluster managers, and YARN (Yet Another Resource Negotiator) is one of the most commonly used ones. Running Spark "with YARN" versus "without YARN" (i.e., using another cluster manager or in standalone mode) can significantly impact how Spark integrates with your cluster, manages resources, and interacts with other applications. Here's an overview of the differences:

### Spark with YARN

YARN is a cluster management technology that is part of the Hadoop ecosystem. When running Spark on YARN:

1. **Integration with Hadoop**: Spark integrates seamlessly with the Hadoop ecosystem and can read from/write to HDFS, HBase, and other Hadoop-supported systems.

2. **Resource Management**: YARN manages the allocation of compute resources to Spark applications. It efficiently shares resources between multiple applications running on the same cluster.

3. **Scalability and Efficiency**: YARN allows Spark to scale dynamically by acquiring and releasing resources as needed, which can lead to better utilization of cluster resources.

4. **Multi-Tenancy Support**: YARN supports multi-tenancy, enabling multiple users and applications to share the same cluster, with resource isolation and management.

5. **Job Scheduling**: Spark jobs are scheduled alongside other Hadoop ecosystem jobs, and YARN handles the prioritization and queuing of these jobs.

### Spark without YARN

Running Spark without YARN means using either Spark's standalone cluster manager or another external cluster manager like Apache Mesos or Kubernetes. In standalone mode or with non-YARN managers:

1. **Simplified Setup**: Spark’s standalone mode is easy to set up and is a good choice for learning and testing Spark applications.

2. **Dedicated Spark Cluster**: In standalone mode, the Spark cluster is dedicated to running Spark applications only. This can lead to simpler resource allocation and management but might not be as efficient as YARN in a multi-tenant environment.

3. **Resource Allocation**: Resource allocation in standalone mode is static. Each Spark application reserves a fixed amount of resources, and they are not released until the application completes.

4. **No Hadoop Integration Required**: Running Spark without YARN does not require a Hadoop environment. This can be beneficial if you don't need Hadoop or are running Spark in a non-Hadoop environment.

5. **Flexibility with Other Resource Managers**: Using Kubernetes or Mesos can offer additional features like containerized deployment and dynamic scaling, which are particularly useful in certain cloud environments or with complex workflows.

### Summary

- **Spark with YARN** is ideal for environments where Spark is part of a larger Hadoop ecosystem, and there is a need for efficient resource sharing and management across different applications.
- **Spark without YARN** (in standalone mode, or with Mesos or Kubernetes) is suitable for dedicated Spark clusters, simpler setups, or specific environments where features of Kubernetes or Mesos are desired.

Your choice will depend on your specific requirements, existing infrastructure, and the goals of your Spark deployment.

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
