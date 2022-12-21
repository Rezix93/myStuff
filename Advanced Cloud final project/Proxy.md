Master: 

```bash
  
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgmd -f /opt/mysqlcluster/deploy/conf/config.ini --initial --configdir=/opt/mysqlcluster/deploy/conf/
sudo /opt/mysqlcluster/home/mysqlc/bin/mysqld --defaults-file=/opt/mysqlcluster/deploy/conf/my.cnf --user=root &
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgm -e show

```
 
 NODE‌S:‌
 
```bash
 sudo /opt/mysqlcluster/home/mysqlc/bin/ndbd -c "ip-172-31-92-148.ec2.internal"
 /opt/mysqlcluster/home/mysqlc/bin/mysql -h ip-172-31-92-148.ec2.internal -u cuser -preza1234

```
