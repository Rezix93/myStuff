1. Create a t2.micro instance and install MySQL stand-alone on it. To install MySQL, please follow the
instructions described in [2].

2. To install the MySQL Cluster, you must create four t2.micro instance, including one master and three slaves.
You can follow the instructions described in [3].

3. Once the setup for MySQL server is complete, you must install Sakila database on your stand-alone server
and cluster. To know more about Sakila and how to install it, please refer to [1] 


**References**

[1] Install sakila database. https://dev.mysql.com/doc/sakila/en/sakila-installation.html



```bash
mysql -u root -p
SOURCE sakila-schema.sql;
SOURCE sakila-data.sql;```
USE sakila;
SHOW FULL TABLES;
SELECT COUNT(*) FROM film;
SELECT COUNT(*) FROM film_text;
```


[2] Installing mysql. https://www.linode.com/docs/databases/mysql/install-mysql-on-ubuntu-14-04/

[3] Installing mysql cluster. https://stansantiago.wordpress.com/2012/

instead of [3] someone introduced this one : 

[3.1] https://cloudinfrastructureservices.co.uk/how-to-create-a-multi-node-mysql-cluster-on-ubuntu-20-04/



```bash

```
```bash
[ndbd default]
# Options affecting ndbd processes on all data nodes:
NoOfReplicas=2	# Number of replicas

[ndb_mgmd]
# Management process options:
hostname= ip-172-31-84-235.ec2.internal # Hostname of the manager
datadir=/var/lib/mysql-cluster 	# Directory for the log files

[ndbd]
hostname=ip-172-31-84-194.ec2.internal # Hostname/IP of the first data node
NodeId=2			# Node ID for this data node
datadir=/usr/local/mysql/data	# Remote directory for the data files

[ndbd]
hostname=ip-172-31-95-48.ec2.internal # Hostname/IP of the second data node
NodeId=3			# Node ID for this data node
datadir=/usr/local/mysql/data	# Remote directory for the data files

[mysqld]
# SQL node options:
hostname=ip-172-31-84-235.ec2.internal # In our case the MySQL server/client is on the same Droplet as the cluster manager
```



```bash

sudo ufw allow from 172.31.84.235
sudo ufw allow from 172.31.84.194
sudo ufw allow from 172.31.95.48
```
A firewall like UFW is running at the OS level, while Amazon Security Groups are running at the instance level. Traffic coming into the EC2 would first pass through the SG, and then be evaluated by UFW. Take a scenario where traffic is explicitly allowed to pass through the SG but UFW denies it -- in this case UFW would sort of 'override' the settings in the SG.


for slave1 and slave2:
```bash
wget https://dev.mysql.com/get/Downloads/MySQL-Cluster-8.0/mysql-cluster-community-data-node_8.0.31-1ubuntu22.04_amd64.deb

[mysql_cluster]
# Options for NDB Cluster processes:
ndb-connectstring=ip-172-31-84-235.ec2.internal  # location of cluster manager

```

ndb_mgmd >> master
ndbd >>‌ salve (data node)


wget https://dev.mysql.com/get/Downloads/MySQL-Cluster-8.0/mysql-cluster_8.0.31-1ubuntu22.04_amd64.deb-bundle.tar

tar -xvf mysql-cluster_7.6.6-1ubuntu18.04_amd64.deb-bundle.tar -C install/

sudo dpkg -i mysql-common_8.0.31-1ubuntu22.04_amd64.deb
sudo dpkg -i mysql-cluster-community-client_8.0.31-1ubuntu22.04_amd64.deb
sudo dpkg -i mysql-client_8.0.31-1ubuntu22.04_amd64.deb   
sudo dpkg -i mysql-cluster-community-server_8.0.31-1ubuntu22.04_amd64.deb


and I found this link: 
3.1:‌ https://www.digitalocean.com/community/tutorials/how-to-create-a-multi-node-mysql-cluster-on-ubuntu-18-04

```bash
wget https://dev.mysql.com/get/Downloads/MySQL-Cluster-8.0/mysql-cluster-community-management-server_8.0.31-1ubuntu22.04_amd64.deb
sudo dpkg -i mysql-cluster-community-management-server_7.6.6-1ubuntu18.04_amd64.deb
```

[4] Sysbench benchmark. http://www.jamescoyle.net/how-to/1131-benchmark-mysql-server-performancewith-sysbench
