```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/jre/
export PATH=$JAVA_HOME/bin:$PATH


sudo update-java-alternatives --list
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
java -version


sudo update-alternatives --config java
sudo update-alternatives --set java /usr/lib/jvm/java-11-openjdk-amd64/bin/java
source ~/.bashrc   # or source ~/.profile or source ~/.bash_profile

```

```bash
sudo pgrep -a lttng-sessiond
kill xxx
```

```bash
cd ~/research/lttng-ust/lttng-ust/doc/examples/java-log4j2-basic
lttng create
```

```bash
lttng enable-event -k sched_switch,sched_waking,sched_pi_setprio,sched_process_fork,sched_process_exit,sched_process_free,sched_wakeup,\
irq_softirq_entry,irq_softirq_raise,irq_softirq_exit,irq_handler_entry,irq_handler_exit,\
lttng_statedump_process_state,lttng_statedump_start,lttng_statedump_end,lttng_statedump_network_interface,lttng_statedump_block_device,\
block_rq_complete,block_rq_insert,block_rq_issue,\
block_bio_frontmerge,sched_migrate,sched_migrate_task,power_cpu_frequency,\
net_dev_queue,netif_receive_skb,net_if_receive_skb,\
timer_hrtimer_start,timer_hrtimer_cancel,timer_hrtimer_expire_entry,timer_hrtimer_expire_exit
```

```bash
lttng enable-event -l -a
```

```bash
lttng enable-event -j -a
```

```bash
lttng enable-channel  --subbuf-size 2000M
```

```bash
lttng start
./run
lttng stop
lttng view
```

Error: Events: Tracing the kernel requires a root lttng-sessiond daemon, as well as "tracing" group membership or root user ID for the lttng client (channel channel0, session auto-20230803-151404)

```bash
sudo lttng-sessiond --daemonize
```bash



```bash
git clone https://github.com/lttng/lttng-modules.git
cd lttng-modules
make
sudo make modules_install
```




