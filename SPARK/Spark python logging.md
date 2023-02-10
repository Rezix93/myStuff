First you create the traces then use Use the Babeltrace 2 Python bindings to do whatever: 

Create a recording session to write LTTng traces to /tmp/my-kernel-trace:

lttng create my-kernel-session --output=/tmp/my-kernel-trace
List the available kernel tracepoints and system calls:

```bash
lttng list --kernel
lttng list --kernel --syscall
```

Create recording event rules which match events having the desired names, for example the sched_switch and sched_process_fork tracepoints, and the open(2) and close(2) system calls:

```bash
lttng enable-event --kernel sched_switch,sched_process_fork
lttng enable-event --kernel --syscall open,close
```

Create a recording event rule which matches all the Linux kernel tracepoint events with the --all option (recording with such a recording event rule generates a lot of data):
```bash
lttng enable-event --kernel --all
```
Start recording:
```bash
lttng start
```
Do some operation on your system for a few seconds. For example, load a website, or list the files of a directory.

Destroy the current recording session:
```bash
lttng destroy

```
The lttng-destroy(1) command doesn’t destroy the trace data; it only destroys the state of the recording session.




in fact it is giving your trace file and analyse it with whatever you need:
1. python3 top5proc.py /tmp/my-kernel-trace/kernel


**Instrument a Python application

```bash
import lttngust
```
```bash
import lttngust
import logging
import time


def example():
    logging.basicConfig()
    logger = logging.getLogger('my-logger')

    while True:
        logger.debug('debug message')
        logger.info('info message')
        logger.warn('warn message')
        logger.error('error message')
        logger.critical('critical message')
        time.sleep(1)


if __name__ == '__main__':
    example()
```


Create a recording session, create a recording event rule matching Python logging events named my-logger, and start recording:

```bash
lttng create
lttng enable-event --python my-logger
lttng start
```

Run the Python script:

```bash
python test.py
```
Stop recording and inspect the recorded events:
```bash
lttng stop
lttng view
```

In the resulting trace, an event record which a Python application generated is named lttng_python:event and has the following fields:





**When an application imports the LTTng-UST Python agent, the agent tries to register to a session daemon. Note that you must start the session daemon before you run the Python application.


diffrent between error info and ...
DEBUG: Information interesting for Developers, when trying to debug a problem.
INFO: Information interesting for Support staff trying to figure out the context of a given error
WARN to FATAL: Problems and Errors depending on level of damage.

```diff
+asctime
Logging time (string).

+msg
Log record message.

+logger_name
Logger name.

+funcName
Name of the function in which the log statement was executed.

+lineno
Line number at which the log statement was executed.

+int_loglevel
Log level integer value.

+thread
ID of the Python thread in which the log statement was executed.

+threadName
Name of the Python thread in which the log statement was executed.


**spark


```bash
/opt/spark/bin/pyspark 
```
```bash

```

