
spark.eventLog.enabled=false
When event logging is disabled, you won't have historical data available for Spark jobs that are completed, especially in cluster mode. The Spark UI uses these logs to display detailed diagnostics about past runs. However, for active jobs, the Spark UI will still display information in real-time; it just won’t retain that information after the application stops.
