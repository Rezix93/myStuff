Test  and run and everything.
**Use the LTTng-UST Java agent for java.util.logging

1. insert library 
2. Create an LTTng-UST java.util.logging log handler.
3. Add this handler to the java.util.logging loggers which should emit LTTng events.
4. Use java.util.logging log statements and configuration as usual.
5. Before exiting the application, remove the LTTng-UST log handler from the loggers attached to it and call its close() method.


java.util.logging (JUL) works by defining Logger and Handler objects. Loggers are part of the Java application: they receive log calls once the execution reaches pre-determined log points. Handlers are attached to loggers: they process the received log points to generate and save log events wherever they are configured to do so (to a file, to the console, over the network, or in our case, to an LTTng trace).


```bash
apt-get update
apt-get install python3-pip 
pip install pyspark
```
