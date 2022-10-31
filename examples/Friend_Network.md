

## Running mapper and reducer for “People You Might Know"
Make sure you have mapper.py, reducer.py and soc-LiveJournal1Adj.txt from our github on the root's home directory.

Install python 2.7, because our mapper and reducer have ```#!/usr/bin/env python```
```bash
apt-get install python
```

Here is the command to run
```bash
hadoop jar  /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-3.3.4.jar  -file mapper.py -mapper mapper.py -file reducer.py -reducer reducer.py -input soc-LiveJournal1Adj.txt -output output
```
If you get an error like ```‘python3\r’: No such file or directory```, then it means the formatting of the mapper and reducer are Windows/DOS-style instead of Linux style

There is two options to fix it...

### Option 1:
You install dos2unix
```bash
apt install dos2unix
```

And convert the mapper and reducer to unix with a command like this.
```bash
dos2unix mapper_OR_reducer.py
```

### Option 2:
The second option is to simply deleting the mapper.py and reducer.py that are in the home directory.
```bash
rm -r mapper.py
rm -r reducer.py
```

Touch them
```bash
touch mapper.py
touch reducer.py 
```

Nano mapper.py and copy-paste its respective code into it
```bash
nano mapper.py
```

Nano reducer.py and copy-paste its respective code into it
```bash
nano reducer.py 
```
