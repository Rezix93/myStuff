https://stansantiago.wordpress.com/2012/

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

Please report any problems at http://bugs.mysql.com/
---------------------------------------------------------------

Create the Deployment Directory and Setup Config Files
```bash
sudo su 

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
nano my.cnf #and enter the following
```

```bash
[mysqld]
ndbcluster
datadir=/opt/mysqlcluster/deploy/mysqld_data
basedir=/opt/mysqlcluster/home/mysqlc
port=3306
```

```bash
nano config.ini
```

and enter the following
NOTE: REPLACE the hostname entries below with names of the SQL/MGMT Node and Data Nodes.
```bash
[ndb_mgmd]
hostname=ip-172-31-83-188.ec2.internal
datadir=/opt/mysqlcluster/deploy/ndb_data
nodeid=1

[ndbd default]
noofreplicas=2
datadir=/opt/mysqlcluster/deploy/ndb_data

[ndbd]
hostname=ip-172-31-24-0.ec2.internal
nodeid=3

[ndbd]
hostname=ip-172-31-20-208.ec2.internal
nodeid=4

[mysqld]
nodeid=50
```
```bash
 cd /opt/mysqlcluster/home/mysqlc
 scripts/mysql_install_db –no-defaults –datadir=/opt/mysqlcluster/deploy/mysqld_data
 
 source /etc/profile.d/mysqlc.sh
 sudo apt-get update && sudo apt-get -y install libncurses5
 ```
 
 ```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgmd  -f /opt/mysqlcluster/deploy/conf/config.ini --initial --configdir=/opt/mysqlcluster/deploy/conf/
```

 ```bash
ndbd -c ip-172-31-83-188.ec2.internal
```

 ```bash
mkdir -p /opt/mysqlcluster/deploy
cd /opt/mysqlcluster/deploy
mkdir conf
mkdir mysqld_data
mkdir ndb_data
cd conf
```
