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
