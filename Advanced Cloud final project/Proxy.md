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

If you are asking for tunnelling, I followed this stackoverflow link 

https://stackoverflow.com/questions/21903411/enable-python-to-connect-to-mysql-via-ssh-tunnelling (edited) 


To choose the data node, you can use the library random from python and for the customized I used the subprocess library to make a ping command

The link is for tunnelling to the data node and get the API in the data node because you cannot connect to the data node directly

You can't connect to the data nodes using pymysql alone since mysql isn't installed on them in the first place. First tunnel into the master with something like sshtunnelforwarder and then use pymysql to run the query on the data nodes.



For those using sshtunnelforwarder, you must use the RSA key from the keypair you used to create your instances in the ssh_pkey field.

you can get that key at creation


If the nodes still show the "starting" state, then they won't work because they have not finished to start. Look if the ports are open so they can communicate. As for mysqld idk




```





