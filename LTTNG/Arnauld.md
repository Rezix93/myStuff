https://github.com/lttng/lttng-ust/tree/master/doc/examples


My idea is :
spark uses log4j as a logging facility. So this week I was trying to use the LTTng-UST Java agent in the spark which uses Apache log4j.

Arnaud:
It should be very easy to do. You need to change the Log configuration and it should work with lttng
