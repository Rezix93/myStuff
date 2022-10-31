
# Running hadoop and spark

We did this assignement on an Azure Ubuntu VM

## Setup

Once in the Azure VM terminal, make sure you are in the user's home directory. By the below command you change your directory to the home directory.
```bash
user@localhost: cd ~
```

By switching to the root privilege mode, we don't need to sudo all the time and no permissions problem. 
```bash
sudo su
```

Go back to the root's home directory.
```bash
root@localhost: cd ~
```

Install latest Azure VM updates and java.
```bash
apt-get update
apt install default-jre -y
apt install default-jdk -y
```

To add the variables related to the JAVA packages, we append following lines at the end of the ```.profile``` file and reconfigure the bash variables. 
```bash
echo "export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64" >> ~/.profile
echo "export PATH=$JAVA_HOME/bin" >> ~/.profile
source ~/.profile
```

Next, get hadoop tar, decompress it and move it to a ```usr/local/hadoop``` folder.

```bash
wget "https://dlcdn.apache.org/hadoop/common/hadoop-3.3.4/hadoop-3.3.4.tar.gz"
tar -xf hadoop-3.3.4.tar.gz  -C /usr/local/
mv /usr/local/hadoop-* /usr/local/hadoop
```

To add the variables related to the Hadoop package, we append following lines at the end of the ```.profile``` file and reconfigure the bash variables. 
```bash
echo "export HADOOP_HOME=/usr/local/hadoop" >> ~/.profile
echo "export PATH=$PATH:$HADOOP_HOME/bin" >> ~/.profile
echo "export HADOOP_CONF_DIR=/usr/local/hadoop/etc/hadoop" >> ~/.profile
source ~/.profile
```

Define following parameters in the ```etc/hadoop/hadoop-env.sh``` file.
```bash
echo "export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64" >> /usr/local/hadoop/etc/hadoop/hadoop-env.sh
echo "export HADOOP_PREFIX=/usr/local/hadoop" >> /usr/local/hadoop/etc/hadoop/hadoop-env.sh
source /usr/local/hadoop/etc/hadoop/hadoop-env.sh
```

Try to type ```hadoop``` in terminal. If you don't get hadoop menu, then extend the PATH variable.
```bash
export PATH=$PATH:/usr/local/hadoop/bin/
```

## Running Hadoop
We have based our WordCount.java on these websites: [apache.org](<http://svn.apache.org/viewvc/hadoop/common/trunk/hadoop-mapreduce-project/hadoop-mapreduce-examples/src/main/java/org/apache/hadoop/examples/WordCount.java?view=log>) and [stackoverflow.com](<https://stackoverflow.com/questions/26700910/hadoop-java-error-exception-in-thread-main-java-lang-noclassdeffounderror-w>)


First, we want to make sure that ```WordCount.java``` and the dataset folder that contains ```pg4300.txt``` and the 9 target datasets from our github on the root's home directory. Then build ```WordCount.java```.
```bash
hadoop com.sun.tools.javac.Main WordCount.java
jar cf wc.jar WordCount*.class
```
Here is an example of command:
```bash
time hadoop jar wc.jar WordCount dataset/pg4300.txt output
```
> The time command is used to determine how long a given command takes to run. It is useful for testing the performance of our scripts and commands.

## Running Linux command

First, make sure you have the dataset folder from our github on the root's home directory.
```bash
root@localhost: cd ~
```

Compute the word frequency of a text with Linux, using Linux commands and pipes, as follows:
```bash
time cat dataset/pg4300.txt | tr ' ' '\n' | sort | uniq -c >> output.txt
```
We use ```>> output.txt``` so the output is put in a text file instead of saturating the terminal. If you want to check results, you can ```nano``` into it.
