from flask import Flask
from flask import request
import json
import pymysql
import sshtunnel
import random
from ping3 import ping
app = Flask(__name__)


master_Public_address = "ec2-44-203-180-30.compute-1.amazonaws.com"
master_Private_address = "ip-172-31-95-133.ec2.internal" # Hostname of the manager
slave1_private_address = "ip-172-31-88-0.ec2.internal" # Hostname/IP of the first data node
slave2_private_address = "ip-172-31-85-44.ec2.internal" # Hostname/IP of the second data node
slave3_private_address = "ip-172-31-86-165.ec2.internal" # Hostname/IP of the third data node
slaves = [slave1_private_address,slave2_private_address,slave3_private_address]
all_instances = {'master' : master_Private_address,
                 'slave1' : slave1_private_address,
                 'slave2' : slave2_private_address,
                 'slave3' : slave3_private_address}
proxy_private_address = "ip-172-31-80-175.ec2.internal" # Proxy private
proxy_private_address = "ec2-52-91-162-51.compute-1.amazonaws.com" # Proxy public

def connection_to_Master():
        #this function use pymysql to connect to the master. I use this function in first type of select
        conn = pymysql.connect(
        host= master_Private_address,
        port = 3306,
        user = 'reza',
        password = 'reza1234',
        db = 'sakila',
        )
        return conn
@app.route('/')
def default_route():
    return 'This is created to connect to Cluster. you can /update or /select '
@app.route('/delete/', methods = ['GET','POST'])
def delete():
    # in this function I run delete queries for delete a data from a table from sakila database
     if request.method == "POST":
        #First we catch the data from ui to get data we need. for example here we need table name and actor_id_old
        # so we save all data we need in key value foramat and access them here.
        jsondata = request.get_json()
        data = json.loads(jsondata)
        actor_id = data[0]['actor_id']
        table_name =  data[0]['table']
        #here we start a pymysql to our master.
        conn = connection_to_Master()
        cursor = conn.cursor()
        #here we edit out query to run.
        cursor.execute("delete from " + table_name + " WHERE actor_id = %s", (actor_id,))
        conn.commit()
        result = []
        #we get out result here and convert it a readable format.
        for row in cursor.fetchall():
            result.append(row)
        conn.close()
        if not result:
            result = 'SUCCESSFULL'
        return result
@app.route('/insert/', methods = ['GET','POST'])
def insert():
    #this function is cratead to insert a new data to the tables of sakila database.
     if request.method == "POST":
        jsondata = request.get_json()
        data = json.loads(jsondata)
        fname  = data[0]['fname']
        lname  = data[0]['lname']
        table_name =  data[0]['table']
        actor_id = data[0]['actor_id']
        conn = connection_to_Master()
        cursor = conn.cursor()
        sql = """insert into `actor` (actor_id,first_name, last_name) values (%s, %s, %s) """
        cursor.execute(sql,(actor_id,fname,lname))
        conn.commit()
        result = []
        for row in cursor.fetchall():
            result.append(row)
        conn.close()
        if not result:
            result = 'SUCCESSFULL'
        return result
@app.route('/update/', methods = ['GET','POST'])
def update():
      # this table is created to update a data from a table in sakila database
      if request.method == "POST":
        jsondata = request.get_json()
        data = json.loads(jsondata)
        new_value  = data[0]['new_value']
        table_name =  data[0]['table']
        fname  = data[0]['fname']
        lname = data[0]['lname']
        conn = connection_to_Master()
        cursor = conn.cursor()
        actor_id = data[0]['actor_id_old']
        cursor.execute("UPDATE " + table_name + " SET first_name = %s , last_name = %s WHERE actor_id = %s", (fname,lname, actor_id,))
        conn.commit()
        result = []
        for row in cursor.fetchall():
            result.append(row)
        conn.close()
        if not result:
            result = 'SUCCESSFULL'
        return result
@app.route('/selectall/', methods = ['GET','POST'])
def selectall():
    #this function select all data from a table whithout any condition.
    if request.method == "POST":
        jsondata = request.get_json()
        data = json.loads(jsondata)
        details = request.form
        table_name =  data[0]['table']
        actors = []
        conn = connection_to_Master()
        cursor = conn.cursor()
        sql = "select * from " + table_name ;
        cursor.execute(sql)
        for row in cursor.fetchall():
            actors.append({"actor_id": row[0], "name": row[1], "family": row[2]})
        conn.close()
        return actors
@app.route('/select1/', methods = ['GET','POST'])
def select1():
    #this is the first type of select in project. it acesses directly to master and read the data.
    if request.method == "POST":
        jsondata = request.get_json()
        data = json.loads(jsondata)
        details = request.form
        table_name =  data[0]['table']
        actor_id = data[0]['actor_id']
        actors = []
        conn = connection_to_Master()
        cursor = conn.cursor()
        sql = "select * from " + table_name  + " where actor_id  like '%"+ str(actor_id) +"%' "
        cursor.execute(sql)
        for row in cursor.fetchall():
            actors.append({"actor_id": row[0], "name": row[1], "family": row[2]})
        conn.close()
        return actors

@app.route('/select2/', methods = ['GET','POST'])
def select2():
    # this is the second type of select. it connects to one of master randomly and then connect to master to use it mysql and catch data.
    if request.method == "POST":
        try:
            jsondata = request.get_json()
            data = json.loads(jsondata)
            details = request.form
            table_name =  data[0]['table']
            actor_id = data[0]['actor_id']

            sshtunnel.SSH_TIMEOUT = 10.0
            random_selected_slave_number = random.randint(0, 2)
            print("Slave #" + str(random_selected_slave_number+1) + " is RANDOMLY selected.")
            random_selected_slave_ip = slaves[random_selected_slave_number]

            with sshtunnel.SSHTunnelForwarder(
                   (random_selected_slave_ip,22),
                   ssh_host_key=None,
                   ssh_username='ubuntu',
                   ssh_password=None,
                   ssh_pkey='/home/ubuntu/LOG8415E2.pem',
                   remote_bind_address=(master_Private_address, 3306)) as server:
                   conn = pymysql.connect(
                        host= "localhost",
                        port=server.local_bind_port,
                        user='reza',
                        password='reza1234',
                        db='sakila',
                        charset="utf8")
                   sql = "select * from " + table_name  + " where actor_id  like '%"+ str(actor_id) +"%' "
                   cursor = conn.cursor()
                   cursor.execute(sql)
            actors = []
            for row in cursor.fetchall():
                 actors.append({"actor_id": row[0], "name": row[1], "family": row[2]})
            conn.close()
            return actors
        except Exception as e:
            print(e)
        return 'SSH error'
@app.route('/select3/', methods = ['GET','POST'])
def select3():
    # This function is same as prevoius one but adding ping. This meanse connect to fastest instances.
    if request.method == "POST":
        try:
            jsondata = request.get_json()
            data = json.loads(jsondata)
            details = request.form
            table_name =  data[0]['table']
            actor_id = data[0]['actor_id']
            sshtunnel.SSH_TIMEOUT = 10.0
            selected_instance_name = ''
            select_instance_ip = ''
            least_ping = 10000
            for host, ip in all_instances.items():
                tping = ping(ip)
                if tping < least_ping :
                    least_ping = tping
                    select_instance_ip = ip
                    selected_instance_name = host
                print (host , ' :: ' , ip , ' :: ', tping)

            print("Selected Instance: " + selected_instance_name + " ::  Ip: "  + select_instance_ip)

            with sshtunnel.SSHTunnelForwarder(
                   (select_instance_ip,22),
                   ssh_host_key=None,
                   ssh_username='ubuntu',
                   ssh_password=None,
                   ssh_pkey='/home/ubuntu/LOG8415E2.pem',
                   remote_bind_address=(master_Private_address, 3306)) as server:
                   conn = pymysql.connect(
                        host= "localhost",
                        port=server.local_bind_port,
                        user='reza',
                        password='reza1234',
                        db='sakila',
                        charset="utf8")
                   sql = "select * from " + table_name  + " where actor_id  like '%"+ str(actor_id) +"%' "
                   cursor = conn.cursor()
                   cursor.execute(sql)
            actors = []
            for row in cursor.fetchall():
                 actors.append({"actor_id": row[0], "name": row[1], "family": row[2]})
            conn.close()
            return actors
        except Exception as e:
            print(e)
        return 'SSH error'
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80)
