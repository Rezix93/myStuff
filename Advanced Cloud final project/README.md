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

[3] Installing mysql cluster. https://stansantiago.wordpress.com/2012/01/04/installing-mysql-cluster-onec2/

[4] Sysbench benchmark. http://www.jamescoyle.net/how-to/1131-benchmark-mysql-server-performancewith-sysbench
