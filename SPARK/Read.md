

## Running Spark
We have based our sparkWordCount.py from this [link](<https://github.com/apache/spark/blob/master/examples/src/main/python/wordcount.py>).

Again, make sure you have ```sparkWordCount.py``` and the dataset folder from our github on the root's home directory.

Get the spark dependencies.
```bash
apt-get update
apt-get install python3-pip 
pip install pyspark
```

Example of spark command:
```bash
time spark-submit sparkWordCount.py dataset/buchanj-midwinter-00-t.txt
```

If you get something like spark-submit command not found, you can also do:
```bash
 time /usr/local/bin/spark-submit sparkWordCount.py dataset/buchanj-midwinter-00-t.txt
```
------------------------------------------------------------------
```bash
spark-shell
```
For install it on server I have done these steps from this link: https://www.projectpro.io/apache-spark-tutorial/apache-spark-installation-tutorial

```bash
SPARK_HOME=/opt/spark
export PATH=$SPARK_HOME/bin:$PATH
source  ~/.bashrc
spark-shell
```


**install from source:**

https://spark.apache.org/downloads.html

