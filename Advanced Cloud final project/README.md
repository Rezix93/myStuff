

**Common steps**

```bash
ip-172-31-95-133.ec2.internal # Hostname of the manager 
ip-172-31-88-0.ec2.internal # Hostname/IP of the first data node 
ip-172-31-85-44.ec2.internal # Hostname/IP of the second data node
ip-172-31-86-165.ec2.internal # Hostname/IP of the third data node

ip-172-31-80-175.ec2.internal # Proxy private
ec2-52-91-162-51.compute-1.amazonaws.com # Proxy public
```


```bash
sudo su 
```
```bash
mkdir -p /opt/mysqlcluster/home
cd /opt/mysqlcluster/home
wget http://dev.mysql.com/get/Downloads/MySQL-Cluster-7.2/mysql-cluster-gpl-7.2.1-linux2.6-x86_64.tar.gz
tar xvf mysql-cluster-gpl-7.2.1-linux2.6-x86_64.tar.gz
ln -s mysql-cluster-gpl-7.2.1-linux2.6-x86_64/ mysqlc
echo export MYSQLC_HOME=/opt/mysqlcluster/home/mysqlc’ > /etc/profile.d/mysqlc.sh
echo export PATH=$MYSQLC_HOME/bin:$PATH’ >> /etc/profile.d/mysqlc.sh
source /etc/profile.d/mysqlc.sh

mkdir -p /opt/mysqlcluster/deploy
cd /opt/mysqlcluster/deploy
mkdir conf
mkdir mysqld_data
mkdir ndb_data
cd conf
nano /opt/mysqlcluster/deploy/conf/my.cnf #and enter the following
```

```bash
[mysqld]
ndbcluster
datadir=/opt/mysqlcluster/deploy/mysqld_data
basedir=/opt/mysqlcluster/home/mysqlc
port=3306
# default IPv6，so change to ipv4 
bind-address = 0.0.0.0
```

```bash
nano /opt/mysqlcluster/deploy/conf/config.ini
```


ip-172-31-95-133.ec2.internal # Hostname of the manager 
ip-172-31-88-0.ec2.internal # Hostname/IP of the first data node 
ip-172-31-85-44.ec2.internal # Hostname/IP of the second data node
ip-172-31-86-165.ec2.internal # Hostname/IP of the third data node

and enter the following
NOTE: REPLACE the hostname entries below with names of the SQL/MGMT Node and Data Nodes.
```bash
[ndb_mgmd]
hostname=ip-172-31-95-133.ec2.internal
datadir=/opt/mysqlcluster/deploy/ndb_data
nodeid=1

[ndbd default]
Noofreplicas=3  # Number of replicas
datadir=/opt/mysqlcluster/deploy/ndb_data

[ndbd]
hostname=ip-172-31-88-0.ec2.internal
nodeid=3

[ndbd]
hostname=ip-172-31-85-44.ec2.internal
nodeid=4

[ndbd]
hostname=ip-172-31-86-165.ec2.internal
nodeid=5

[mysqld]
nodeid=50

```

Initialize the Database
```bash
 cd /opt/mysqlcluster/home/mysqlc
 sudo scripts/mysql_install_db --no-defaults --datadir=/opt/mysqlcluster/deploy/mysqld_data
 ```
 
  ```bash
 source /etc/profile.d/mysqlc.sh
 sudo apt-get update && sudo apt-get -y install libncurses5
```
**MASTER**

** We can now start the manager by executing the ndb_mgmd binary and specifying its config file using the -f flag:

 ```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgmd -f /opt/mysqlcluster/deploy/conf/config.ini --initial --configdir=/opt/mysqlcluster/deploy/conf/
```
Once inside the console enter the command SHOW and hit ENTER:

```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgm -e show
```

 ```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/mysqld --defaults-file=/opt/mysqlcluster/deploy/conf/my.cnf --user=root &
```

 ```bash
 cd  /opt/mysqlcluster/home/mysqlc/
sudo /opt/mysqlcluster/home/mysqlc/bin/mysql_secure_installation

sudo /opt/mysqlcluster/home/mysqlc/bin/mysql -h 127.0.0.1 -uroot -p reza1234 

CREATE USER 'reza'@'%' IDENTIFIED BY 'reza1234';
GRANT ALL PRIVILEGES ON *.* TO 'reza'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

sudo /opt/mysqlcluster/home/mysqlc/bin/mysql -h 127.0.0.1 -ureza -preza1234 

/opt/mysqlcluster/home/mysqlc/bin/mysqladmin -u root password

```
**On Data Nodes host:**

Now we can start the data node using the following command:

```bash
mkdir -p /opt/mysqlcluster/deploy/ndb_data
```
 
 ```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/ndbd -c "ip-172-31-95-133.ec2.internal"
```

START SQL NODE

 ```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgm -e show
```
It is finished when you see:‌ NDB Binlog: ndb tables writable


 ```bash
 /opt/mysqlcluster/home/mysqlc/bin/mysql -u reza
```

On nodes just run this to ceonnect to database:
 ```bash
  /opt/mysqlcluster/home/mysqlc/bin/mysql -h ip-172-31-95-133.ec2.internal -ureza -preza1234

```



**Download and install sakila**

[1] Install sakila database. C



```bash
wget https://downloads.mysql.com/docs/sakila-db.tar.gz
tar -xvzf sakila-db.tar.gz
cd sakila-db 
sudo /opt/mysqlcluster/home/mysqlc/bin/mysql -h 127.0.0.1 -uroot -p
#mysql -u root 
SOURCE sakila-schema.sql;
SOURCE sakila-data.sql;
USE sakila;
SHOW FULL TABLES;
SELECT COUNT(*) FROM film;
SELECT COUNT(*) FROM film_text;
```

**Stand alone: **

```bash
sudo apt-get install mysql-server
```

```bash
sudo mysql_secure_installation
mysql -u root -p
```



**Sysbench:**

```bash
apt-get install sysbench
```

For cluster : 
```bash
 sysbench --db-driver=mysql --mysql-user=root --mysql-password=reza1234 \
  --mysql-socket=/tmp/mysql.sock --mysql-db=dbtest --range_size=100 \
  --table_size=10000 --tables=2 --threads=1 --events=0 --time=60 \
  --rand-type=uniform /usr/share/sysbench/oltp_read_only.lua prepare
```

 ```bash
sysbench --db-driver=mysql --mysql-user=root --mysql-password=reza1234 \
  --mysql-socket=/tmp/mysql.sock --mysql-db=dbtest --range_size=100 \
  --table_size=10000 --tables=2 --threads=1 --events=0 --time=60 \
  --rand-type=uniform /usr/share/sysbench/oltp_read_only.lua run
```

For Standalone: 
```bash
 sysbench --db-driver=mysql --mysql-user=root --mysql-password=reza1234 \
   --mysql-db=dbtest --range_size=100 \
  --table_size=10000 --tables=2 --threads=1 --events=0 --time=60 \
  --rand-type=uniform /usr/share/sysbench/oltp_read_only.lua prepare
```

 ```bash
sysbench --db-driver=mysql --mysql-user=root --mysql-password=reza1234 \
   --mysql-db=dbtest --range_size=100 \
  --table_size=10000 --tables=2 --threads=1 --events=0 --time=60 \
  --rand-type=uniform /usr/share/sysbench/oltp_read_only.lua run
```




**ALL‌CHALENGES:**

 ```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/drop   -u root -preza1234 
sudo /opt/mysqlcluster/home/mysqlc/bin/mysql -h 127.0.0.1 -u root 

sudo /opt/mysqlcluster/home/mysqlc/bin/mysql -h 127.0.0.1 -u cluser

/opt/mysqlcluster/home/mysqlc/bin/mysql -u root -p -h 127.0.0.1 -e "select @@socket"
/opt/mysqlcluster/home/mysqlc/bin/mysql -u root -p -S /tmp/mysql.sock

```
socket adress: /tmp/mysql.sock 




In mysql: 

UPDATE mysql.user SET password=password('reza1234')
WHERE user='cluser' AND host='localhost';
exit
 
 ```bash
 SET PASSWORD FOR 'root'@'localhost' = PASSWORD('reza1234');
 SET PASSWORD FOR 'cuser'@'localhost' = PASSWORD('reza1234');
 CREATE USER 'cuser'@'%' IDENTIFIED BY 'reza1234';
 GRANT ALL PRIVILEGES ON *.* TO 'cuser'@'%' WITH GRANT OPTION;
 
 CREATE USER 'cluser'@'%' IDENTIFIED BY 'reza1234';
GRANT ALL PRIVILEGES ON *.* TO 'cluser'@'%' WITH GRANT OPTION;


FLUSH PRIVILEGES;
/opt/mysqlcluster/home/mysqlc/bin/mysql -u root -p
/opt/mysqlcluster/home/mysqlc/bin/mysql -u cuser -preza1234
/opt/mysqlcluster/home/mysqlc/bin/mysql -u cluser -preza1234

```

 ```bash
GRANT ALL PRIVILEGES ON * . * TO 'reza'@'%' IDENTIFIED BY 'reza' WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;
```

 ```bash
 use clusterdb;
 select * from simples;

```



 ```bash
UPDATE mysql.user SET Password=PASSWORD('testpwd') WHERE user='myapp';

telnet 127.0.0.1 3306
/opt/mysqlcluster/home/mysqlc/bin/ndb_mgm -e shutdown
/opt/mysqlcluster/home/mysqlc/bin/mysqladmin -u root -h 127.0.0.1 -preza1234 shutdown
/opt/mysqlcluster/home/mysqlc/bin/mysqladmin -u cluser -h 127.0.0.1 -preza1234 shutdown
```

 ```bash
CREATE USER 'cuser'@'%' IDENTIFIED BY 'reza1234';
GRANT ALL PRIVILEGES ON *.* TO 'cuser'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'cuser'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

create database testdb;
GRANT ALL PRIVILEGES ON testdb.* TO 'reza'@'%' WITH GRANT OPTION;

mysql -u cuser -p

use testdb;
create table customers (customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, first_name TEXT, last_name TEXT);
```

 ```bash
CREATE USER 'reza'@'%' IDENTIFIED BY 'reza1234';
GRANT ALL PRIVILEGES ON *.* TO 'reza'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

create database testdb;
GRANT ALL PRIVILEGES ON testdb.* TO 'reza'@'%' WITH GRANT OPTION;

CREATE DATABASE sbtest;
CREATE USER 'sbtest'@'localhost';
GRANT ALL PRIVILEGES ON * . * TO 'sbtest'@'localhost';
FLUSH PRIVILEGES;
quit;

mysql -u reza -p

use testdb;
create table customers (customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, first_name TEXT, last_name TEXT);
```

 ```bash
apt-get install sysbench
```

 ```bash
 /opt/mysqlcluster/home/mysqlc/bin/mysql  -u root -preza1234
 mysql -u root -p
 create database dbtest;

 use dbtest;
 SELECT COUNT(*) FROM sbtest;
 show tables;
 drop database dbtest;

 
 netstat -lnp | grep 3306
 #tcp        0      0 0.0.0.0:3306            0.0.0.0:*               LISTEN      1734/mysqld         
  netstat -lnp | grep 1186
  #tcp        0      0 0.0.0.0:1186            0.0.0.0:*               LISTEN      1356/ndb_mgmd       
netstat -a -n
```

For cluster : 
```bash
 sysbench --db-driver=mysql --mysql-user=root --mysql-password=reza1234 \
  --mysql-socket=/tmp/mysql.sock --mysql-db=dbtest --range_size=100 \
  --table_size=10000 --tables=2 --threads=1 --events=0 --time=60 \
  --rand-type=uniform /usr/share/sysbench/oltp_read_only.lua prepare
```

 ```bash
sysbench --db-driver=mysql --mysql-user=root --mysql-password=reza1234 \
  --mysql-socket=/tmp/mysql.sock --mysql-db=dbtest --range_size=100 \
  --table_size=10000 --tables=2 --threads=1 --events=0 --time=60 \
  --rand-type=uniform /usr/share/sysbench/oltp_read_only.lua run
```

For Standalone: 
```bash
 sysbench --db-driver=mysql --mysql-user=root --mysql-password=reza1234 \
   --mysql-db=dbtest --range_size=100 \
  --table_size=10000 --tables=2 --threads=1 --events=0 --time=60 \
  --rand-type=uniform /usr/share/sysbench/oltp_read_only.lua prepare
```

 ```bash
sysbench --db-driver=mysql --mysql-user=root --mysql-password=reza1234 \
   --mysql-db=dbtest --range_size=100 \
  --table_size=10000 --tables=2 --threads=1 --events=0 --time=60 \
  --rand-type=uniform /usr/share/sysbench/oltp_read_only.lua run
```
