
# This is a simpel example just for starting work in spark 

```console
spark-shell
var Data = sc.textFile("/opt/spark/CHANGES.txt")
var tokens = Data.flatMap(s => s.split(" ")) #: To tokenize each line of the input file to individual words
var tokens_1 = tokens.map(s => (s,1)) # To append 1 with each word
var sum_each = tokens_1.reduceByKey((a, b) => a + b) # To aggregate each unigue word together and adding the appended one’s with each to give the count of each word.
sum_each.collect() # To dump the output on console
sum_each.saveAsTextFile("/tmp/spark_out") # To save the output in file

```
