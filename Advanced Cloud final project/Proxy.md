Master: 

```bash
PS1='\u:\W\$ '
```

```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgmd -f /opt/mysqlcluster/deploy/conf/config.ini --initial --configdir=/opt/mysqlcluster/deploy/conf/
sudo /opt/mysqlcluster/home/mysqlc/bin/mysqld --defaults-file=/opt/mysqlcluster/deploy/conf/my.cnf --user=root &
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgm -e show
/opt/mysqlcluster/home/mysqlc/bin/mysql -u cuser

```
 
 NODE‌S:‌
 
```bash
 sudo /opt/mysqlcluster/home/mysqlc/bin/ndbd -c "ip-172-31-92-148.ec2.internal"
 /opt/mysqlcluster/home/mysqlc/bin/mysql -h ip-172-31-92-148.ec2.internal -u cuser -preza1234
```

pymysql.err.OperationalError: (2003, "Can't connect to MySQL server on 'ip-172-31-83-82.ec2.internal' ([Errno 111] Connection refused)"):
SOLVED: 

```bash
 sudo /opt/mysqlcluster/home/mysqlc/bin/mysqladmin -u root -p variables | grep port
 
 Otherwise, I added "sudo ufw allow from {private_ips}" from all my nodes and it worked !


```





