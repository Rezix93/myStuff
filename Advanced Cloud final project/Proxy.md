Master: 

```bash
PS1='\u:\W\$ '
```

```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgmd -f /opt/mysqlcluster/deploy/conf/config.ini --initial --configdir=/opt/mysqlcluster/deploy/conf/
sudo /opt/mysqlcluster/home/mysqlc/bin/mysqld --defaults-file=/opt/mysqlcluster/deploy/conf/my.cnf --user=root &
sudo /opt/mysqlcluster/home/mysqlc/bin/ndb_mgm -e show
sudo /opt/mysqlcluster/home/mysqlc/bin/mysql -h 127.0.0.1 -uroot -preza1234 
/opt/mysqlcluster/home/mysqlc/bin/mysql -u reza

ssh -i labsuser.pem ubuntu@ec2-54-227-117-122.compute-1.amazonaws.com

sudo chmod 700 /home/ubuntu /home/ubuntu/.ssh
sudo chmod 600 /home/ubuntu/.ssh/authorized_keys

```
 
 NODE‌S:‌
 
```bash
 sudo /opt/mysqlcluster/home/mysqlc/bin/ndbd -c "ip-172-31-95-133.ec2.internal"
 /opt/mysqlcluster/home/mysqlc/bin/mysql -h ip-172-31-95-133.ec2.internal -u reza -preza1234
```

```bash
 apt update;
apt -y install python3-pip;
pip3 install flask;
sudo apt-get install python3-pymysql
pip install sshtunnel
```


pymysql.err.OperationalError: (2003, "Can't connect to MySQL server on 'ip-172-31-83-82.ec2.internal' ([Errno 111] Connection refused)"):
SOLVED: 

```bash

from sshtunnel import SSHTunnelForwarder
import pymysql
import pandas as pd

tunnel = SSHTunnelForwarder(('SSH_HOST', 22), ssh_password=SSH_PASS, ssh_username=SSH_UNAME,
     remote_bind_address=(DB_HOST, 3306)) 
tunnel.start()
conn = pymysql.connect(host='127.0.0.1', user=DB_UNAME, passwd=DB_PASS, port=tunnel.local_bind_port)
data = pd.read_sql_query("SHOW DATABASES;", conn)

```

```bash
sudo /opt/mysqlcluster/home/mysqlc/bin/mysqladmin -u root -p variables | grep port
 ```
Otherwise, I added "sudo ufw allow from {private_ips}" from all my nodes and it worked !

If you are asking for tunnelling, I followed this stackoverflow link 

https://stackoverflow.com/questions/21903411/enable-python-to-connect-to-mysql-via-ssh-tunnelling (edited) 


To choose the data node, you can use the library random from python and for the customized I used the subprocess library to make a ping command

The link is for tunnelling to the data node and get the API in the data node because you cannot connect to the data node directly

You can't connect to the data nodes using pymysql alone since mysql isn't installed on them in the first place. First tunnel into the master with something like sshtunnelforwarder and then use pymysql to run the query on the data nodes.



For those using sshtunnelforwarder, you must use the RSA key from the keypair you used to create your instances in the ssh_pkey field.

you can get that key at creation


If the nodes still show the "starting" state, then they won't work because they have not finished to start. Look if the ports are open so they can communicate. As for mysqld idk


Vahid Majdinasab
**You can't connect to the data nodes using pymysql alone since mysql isn't installed on them in the first place. First tunnel into the master with something like sshtunnelforwarder and then use pymysql to run the query on the data nodes.


```bash
try:
    sshtunnel.SSH_TIMEOUT = 10.0

    with sshtunnel.SSHTunnelForwarder(
            ('ec2-**-**-***-***.eu-central-1.compute.amazonaws.com', 22),
            ssh_host_key=None,
            ssh_username='ubuntu',
            ssh_password=None,
            ssh_pkey='./XXXXXXXXX.pem',
            remote_bind_address=('127.0.0.1', 3306)
    ) as tunnel:
        conn = mysql.connector.connect(
                host='127.0.0.1',
                port=tunnel.local_bind_port,
                user='admin',
                db='XXXXXX',
                passwd='XXXXXX',
                charset="utf8"
        )

        c = conn.cursor()
        print(c)
        c.execute("SELECT * FROM XXXXXX ORDER BY Rand() LIMIT 50")

except Exception as e:
    print(e)

```



