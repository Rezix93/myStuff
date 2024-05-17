

# Custom Spark Listener and Instrumentation

This repository contains the latest version of the code for my custom Apache Spark listener and additional instrumentation. The project captures and customizes logs from Spark applications to provide detailed runtime information.

## Features
- **Custom Spark Listener**: Captures and customizes logs from Apache Spark applications.
- **Additional Instrumentation**: Includes extra logging in various classes to provide detailed insights.

## Example Instrumentation
In `executor.scala`:
```scala
logInfo(s"method=run task_id=$taskId thread_id=$threadId thread_name=$threadName")
```

Feel free to explore the code and use it for your projects. Contributions and feedback are welcome!
