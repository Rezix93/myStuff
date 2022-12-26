1. Create a t2.micro instance and install MySQL stand-alone on it. To install MySQL, please follow the
instructions described in [2].

2. To install the MySQL Cluster, you must create four t2.micro instance, including one master and three slaves.
You can follow the instructions described in [3].

3. Once the setup for MySQL server is complete, you must install Sakila database on your stand-alone server
and cluster. To know more about Sakila and how to install it, please refer to [1] 


**References**

[1] Install sakila database. https://dev.mysql.com/doc/sakila/en/sakila-installation.html



```bash
scp -i LOG8415E2.pem LOG8415E2.pem ubuntu@ec2-52-91-162-51.compute-1.amazonaws.com:~/opt

mysql -u root -p
SOURCE sakila-schema.sql;
SOURCE sakila-data.sql;```
USE sakila;
SHOW FULL TABLES;
SELECT COUNT(*) FROM film;
SELECT COUNT(*) FROM film_text;
```

sudo ndb_mgmd --initial --config-file=/your_config_directory/config.ini

killall ndb_mgmd

sudo ndb_mgmd --initial --config-file=/var/lib/mysql-cluster/config.ini


[2] Installing mysql. https://www.linode.com/docs/databases/mysql/install-mysql-on-ubuntu-14-04/

[3] Installing mysql cluster. https://stansantiago.wordpress.com/2012/


instead of [3] someone introduced this one : 
[3.1] https://cloudinfrastructureservices.co.uk/how-to-create-a-multi-node-mysql-cluster-on-ubuntu-20-04/
and I found this link: 
3.1:‌ https://www.digitalocean.com/community/tutorials/how-to-create-a-multi-node-mysql-cluster-on-ubuntu-18-04

```bash
hostname=ip-172-31-84-235.ec2.internal # Hostname of the manager 172.31.84.235
hostname=ip-172-31-84-194.ec2.internal # Hostname/IP of the first data node 172.31.84.194
hostname=ip-172-31-95-48.ec2.internal # Hostname/IP of the second data node 172.31.95.48
```


```bash
[ndbd default]
# Options affecting ndbd processes on all data nodes:
NoOfReplicas=2	# Number of replicas

[ndb_mgmd]
# Management process options:
hostname= ip-172-31-84-235.ec2.internal # Hostname of the manager
datadir=/var/lib/mysql-cluster # Directory for the log files

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
killall ndb_mgmd
sof -P | grep '1186' | awk '{print $2}' | xargs kill -9
 ndb_mgmd --initial --skip-config-cache -f /var/lib/mysql-cluster/config.ini  
sudo service mysql restart
sudo systemctl enable mysql
ndb_mgm -e show
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
netstat -ntl | grep 80
Now you got the process name and kill the process with the killall command:

killall -9 process name
ndb_mgmd >> master
ndbd >>‌ salve (data node)


wget https://dev.mysql.com/get/Downloads/MySQL-Cluster-8.0/mysql-cluster_8.0.31-1ubuntu22.04_amd64.deb-bundle.tar

tar -xvf mysql-cluster_7.6.6-1ubuntu18.04_amd64.deb-bundle.tar -C install/

sudo dpkg -i mysql-common_8.0.31-1ubuntu22.04_amd64.deb
sudo dpkg -i mysql-cluster-community-client_8.0.31-1ubuntu22.04_amd64.deb
sudo dpkg -i mysql-client_8.0.31-1ubuntu22.04_amd64.deb   
sudo dpkg -i mysql-cluster-community-server_8.0.31-1ubuntu22.04_amd64.deb


https://dba.stackexchange.com/questions/23881/setting-up-mysql-cluster-on-two-systems

```bash
wget https://dev.mysql.com/get/Downloads/MySQL-Cluster-8.0/mysql-cluster-community-management-server_8.0.31-1ubuntu22.04_amd64.deb
sudo dpkg -i mysql-cluster-community-management-server_7.6.6-1ubuntu18.04_amd64.deb
```

ndbd_mgm not connected, accepting connect from i
ndbd_mgm not connected, accepting connect from i
ndbd_mgm not connected, accepting connect from i
ndbd_mgm not connected, accepting connect from indbd_mgm not connected, accepting connect from i
[4] Sysbench benchmark. http://www.jamescoyle.net/how-to/1131-benchmark-mysql-server-performancewith-sysbench


https://stansantiago.wordpress.com/2012/
install MYSQL on instances:

```bash
sudo apt-get install mysql-server


sudo mysql_secure_installation

sudo mysql

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'reza1234';
```
```bash
wget https://downloads.mysql.com/docs/sakila-db.tar.gz
tar -xf  sakila-db.tar.gz
cd sakila-db/

/opt/mysqlcluster/home/mysqlc/bin/mysql -h 127.0.0.1 -u root -preza1234

/opt/mysqlcluster/home/mysqlc/bin/mysql -h ip-172-31-92-148.ec2.internal -u cuser -preza1234

SOURCE sakila-schema.sql;
SOURCE sakila-data.sql;
USE sakila;
SHOW FULL TABLES;
SELECT COUNT(*) FROM film;
SELECT COUNT(*) FROM film_text;

```

```bash
Private host name:  
Master:  ip-172-31-92-148.ec2.internal
Slave1:  ip-172-31-83-82.ec2.internal
Slave2:  ip-172-31-91-1.ec2.internal
Slave3:  ip-172-31-82-132.ec2.internal
```

```bash
wget https://downloads.mysql.com/archives/get/p/14/file/mysql-cluster-gpl-7.2.1-linux2.6-i686.tar.gz
groupadd mysql
useradd -r -g mysql mysql
cd /usr/local
tar zxvf /path/to/mysql-VERSION-OS.tar.gz
ln -s full-path-to-mysql-VERSION-OS mysql
cd mysql
chown -R mysql .
chgrp -R mysql .
scripts/mysql_install_db --user=mysql
chown -R root .
chown -R mysql data
# Next command is optional
cp support-files/my-medium.cnf /etc/my.cnf
 bin/mysqld_safe --user=mysql & 
# Next command is optional
cp support-files/mysql.server /etc/init.d/mysql.server
```
To start mysqld at boot time you have to copy
support-files/mysql.server to the right place for your system

PLEASE REMEMBER TO SET A PASSWORD FOR THE MySQL root USER !
To do so, start the server, then issue the following commands:

./bin/mysqladmin -u root password 'new-password'
./bin/mysqladmin -u root -h ip-172-31-22-215 password 'new-password'

Alternatively you can run:
./bin/mysql_secure_installation

which will also give you the option of removing the test
databases and anonymous user created by default.  This is
strongly recommended for production servers.

See the manual for more instructions.

You can start the MySQL daemon with:
cd . ; ./bin/mysqld_safe &

You can test the MySQL daemon with mysql-test-run.pl
cd ./mysql-test ; perl mysql-test-run.pl



