```bash

wget [https://downloads.mysql.com/archives/get/p/14/file/mysql-cluster-gpl-7.2.1-linux2.6-i686.tar.gz](https://dev.mysql.com/get/Downloads/MySQL-Cluster-7.2/mysql-cluster-gpl-7.2.35-linux-glibc2.12-x86_64.tar.gz)
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
shell> cp support-files/mysql.server /etc/init.d/mysql.server



```
