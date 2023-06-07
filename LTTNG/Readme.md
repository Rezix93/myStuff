Test  and run and everything.
**Use the LTTng-UST Java agent for java.util.logging

1. insert library 
2. Create an LTTng-UST java.util.logging log handler.
3. Add this handler to the java.util.logging loggers which should emit LTTng events.
4. Use java.util.logging log statements and configuration as usual.
5. Before exiting the application, remove the LTTng-UST log handler from the loggers attached to it and call its close() method.

```bash
apt-get update
apt-get install python3-pip 
pip install pyspark
```
