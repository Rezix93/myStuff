**Kubernetes** is an open-source platform that automates deploying, scaling, and managing containerized applications. It provides a framework to run distributed systems resiliently, handling failover, scaling, service discovery, load balancing, storage orchestration, and more.

Kubernetes clusters consist of **master nodes** that control and manage the cluster and **worker nodes** that run applications in containers. It helps manage complex microservices architectures, enabling consistent and efficient application deployment across multiple environments (on-premises, cloud, hybrid).

<p align = center>
<img src = "https://github.com/user-attachments/assets/d4f89a15-be5b-4c6d-bf93-04a1524776e9" style = "width : 80%">
</p>
Here are additional **Kubernetes components**:

Here's a complete list of **Kubernetes components**:

### **Master Node Components:**
1. **API Server:** The front-end of the Kubernetes control plane, exposes the Kubernetes API.
2. **etcd:** A key-value store that holds all cluster data and state.
3. **Scheduler:** Assigns workloads to nodes based on resource availability and policies.
4. **Controller Manager:**
   - Runs controllers to regulate the state of the cluster (e.g., Node Controller, Deployment Controller).
5. **Cloud Controller Manager:** Interacts with cloud provider APIs for cloud-specific operations.

### **Worker Node Components:**
1. **Kubelet:** An agent that runs on each node, ensuring that containers are running as expected.
2. **Kube-proxy:** Manages networking, service discovery, and load balancing for pods.
3. **Container Runtime:** The software that runs containers (e.g., Docker, containerd, CRI-O).

These components work together to provide a robust and scalable platform for deploying, scaling, and managing containerized applications.


### Workers: 
![image](https://github.com/user-attachments/assets/4c81a6d4-7703-460f-980d-04386b5ccabe)



## Docker vs Kubernetes:




Here is an overview of the three major managed Kubernetes services:

1. **Google Kubernetes Engine (GKE):**
   - Managed Kubernetes service on Google Cloud.
   - Offers auto-scaling, auto-upgrades, and integrated monitoring.

2. **Amazon EKS (Elastic Kubernetes Service):**
   - Managed Kubernetes service on AWS.
   - Provides a secure, scalable Kubernetes control plane and integrates with AWS services.

3. **Azure Kubernetes Service (AKS):**
   - Managed Kubernetes service on Microsoft Azure.
   - Offers automated upgrades, patching, and built-in CI/CD integration with Azure DevOps.

All three services simplify Kubernetes management in cloud environments.


### POD 

A Pod is the smallest deployable unit in Kubernete


### Docker vs. Kubernetes


Docker and Kubernetes are both important tools in the containerization ecosystem. Docker is used for creating and running containers, while Kubernetes is used for managing and automating the deployment, scaling, and operation of containers across clusters of hosts. Docker provides a simple and efficient way to run and manage containers, while Kubernetes provides more advanced features like automatic container deployment, scaling, and self-healing.

### Using Kubernetes with Docker
When using Kubernetes with Docker, Kubernetes acts as an orchestrator for the Docker containers. This means that Kubernetes can manage and automate the deployment, scaling, and operation of Docker containers.
