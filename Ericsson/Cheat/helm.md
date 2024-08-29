**Helm** is a package manager for Kubernetes that simplifies the deployment and management of applications. It uses **Helm charts**,
which are pre-configured templates containing all the necessary resources
(like deployments, services, etc.) to run an application on Kubernetes. 
With Helm, you can easily install, upgrade, and roll back applications,
making Kubernetes deployments more manageable and repeatable.
It handles dependencies and configuration, helping teams automate and standardize their Kubernetes workflows.



<p align = "center">
<img src = "https://github.com/user-attachments/assets/3245ffbe-4928-431d-92b3-02bf828d0832"  style = "width : 70%">
</p>


### Helm chart

In Helm, a chart 
is a collection of files that describe a related set of Kubernetes resources.
It acts like a package that can be used to deploy and manage applications on Kubernetes clusters.
Each chart contains templates, configuration values, and dependencies required for deploying an application.

A Helm chart for Prometheus would be a collection of Kubernetes manifests that define how to deploy Prometheus, an open-source monitoring and alerting tool, in a Kubernetes environment. The chart would include configurations for setting up Prometheus servers, data storage, alert managers, and service discovery components within the cluster.


**Helm templating** allows you to create dynamic Kubernetes manifests using a template language. It uses the **Go templating** engine to make templates reusable and configurable.

### Key Elements of Helm Templating:
- **`{{ .Values }}`:** Access values from the `values.yaml` file.
- **`{{ .Release.Name }}`:** Access release-specific data like the release name.
- **`if/else` Statements:** Add conditional logic to templates.
- **Loops:** Iterate over lists or maps to dynamically generate configuration.

Templates are defined in the `templates/` directory of a Helm chart, and values are provided through `values.yaml` to customize deployments. This approach makes your Kubernetes resources flexible and manageable.
