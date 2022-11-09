
I‌ was working on Spark dashboard based on this link: "https://github.com/cerndb/spark-dashboard". 
This dashboard is creating by Apache Spark Metric System + InfluxDB + Grafana:
![Spark_metrics_dashboard_arch](https://user-images.githubusercontent.com/80580733/199869697-568e7c8e-044e-4abc-bc7b-bf98e8c887ea.png)

After installing and work on Grafana, I thought I could go deeper on grafana and import our trace file there to see the result. SO here I‌ exaplain What I have done.

# Introduction
Grafana is an open source interactive data-visualization platform, developed by Grafana Labs, which allows users to see their data via charts and graphs that are unified into one dashboard (or multiple dashboards!) for easier interpretation and understanding. Installing process is from "https://grafana.com/tutorials/grafana-fundamentals/?utm_source=grafana_gettingstarted"

1. Clone the github.com/grafana/tutorial-environment repository.
```bash
git clone https://github.com/grafana/tutorial-environment.git
cd tutorial-environment
```
2. Make sure docer is running:
```bash
docker ps
```
3. Now start the sample application:
```bash
cd /home/rezghool2/Desktop/grafana/tutorial-environment
docker-compose up -d
```
 **Warning** Note: If you already have Grafana, Loki, or Prometheus running on your system, then you might see errors because the Docker image is trying to use ports that your local installations are already using. Stop the services, then run the command again. 
 
4. Ensure all services are up-and-running:
```bash
docker-compose ps
```
You shoud see somthing like this: 
![Screenshot from 2022-11-03 22-27-59](https://user-images.githubusercontent.com/80580733/199872253-ad776fbd-7f9f-4222-8a84-8e28d76cd16b.png)

5.Browse to the sample application on http://localhost:8081.

# Grafana news
The sample application, Grafana News, lets you post links and vote for the ones you like.

To add a link:

1. In Title, enter Example.

2. In URL, enter https://example.com.

3. Click Submit to add the link.

# Login to Grafana

Open a new tab.

1. Browse to http://localhost:3000.

2. In email or username, enter admin.

3. In password, enter admin.

4. Click Log In.

# Add a metrics data source

The sample application exposes metrics which are stored in Prometheus, a popular time series database (TSDB).

To be able to visualize the metrics from Prometheus, you first need to add it as a data source in Grafana.

1. In the sidebar, hover your cursor over the Configuration (gear) icon, and then click Data sources.

2. Click Add data source.

3. In the list of data sources, click Prometheus.

4. In the URL box, enter http://prometheus:9090.

5. Click Save & test.

Prometheus is now available as a data source in Grafana.

# What is Prometheus? 
based on google: Prometheus is a monitoring solution for storing time series data like metrics. Grafana allows to visualize the data stored in Prometheus (and other sources). This sample demonstrates how to capture NServiceBus metrics, storing these in Prometheus and visualizing these metrics using Grafana

# Waht is data source?
Grafana supports many different storage backends for your time series data (data source). Refer to Add a data source for instructions on how to add a data source to Grafana. Only users with the organization admin role can add data sources.

# Data source plugins
You can install additional data sources as plugins. To view available data source plugins, see the Grafana Plugins catalog. To build your own, see the “Build a data source plugin” tutorial and our documentation about building a plugin.

So First we need to build a simple plugin, then jump in to build a data soruce plugin.

# Build a data source plugin

1. Create a directory called grafana-plugins in your preferred workspace.
2. Find the plugins property in the Grafana configuration file and set the plugins property to the path of your grafana-plugins directory.
```bash
plugins = "/path/to/grafana-plugins"
```
For finding the file grafana.ini I run find command and there was in: 
```bash
nano /var/snap/docker/common/var-lib-docker/overlay2/516cbc3950f0b79c18ec947aa4323c66460261bd5ffb7ff646c56d2b16ecc9d2/diff/etc/grafana/grafana.ini
#plugins = "/path/to/grafana-plugins"
```
3. Restart Grafana if it’s already running, to load the new configuration.
4. 
# Create a new plugin
1. In the plugin directory, create a plugin from template using the plugin:create command:
```bash
sudo npx @grafana/toolkit plugin:create my-plugin
```
I got this error: Module.createRequire is not a function
```bash
 sudo systemctl restart docker.socket docker.service
```
After two hours I got that it should be created on root folder (~)

then somthin new happen. Docker stop did not work!
solution: 
```bash
sudo aa-remove-unknown
```
One liner to stop / remove all of Docker containers:


```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

2. Change directory.
```bash

```
cd my-plugin
3. Download necessary dependencies:
```bash
yarn install
```
4. Build the plugin:
```bash
yarn dev
```

4. Restart the Grafana server for Grafana to discover your plugin.

6. Open Grafana and go to Configuration -> Plugins. Make sure that your plugin is there

Grafana data source:‌
https://grafana.com/docs/grafana/latest/datasources/add-a-data-source/

Grafana plugins: 
https://grafana.com/docs/grafana/latest/developers/plugins/

```bash
docker run -d -p 3000:3000 -v "$(pwd)"/grafana-plugins:/var/lib/grafana/plugins --name=grafana grafana/grafana:7.0.0
```

```bash
docker restart grafana
```


