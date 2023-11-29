   ```bash
${SPARK_HOME}/bin/spark-submit \
   --verbose \
   --class org.apache.spark.examples.ml.JavaKMeansExample \
   --deploy-mode client \
   /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 1


${SPARK_HOME}/bin/spark-submit \
   --verbose \
   --class org.apache.spark.examples.ml.JavaKMeansExample \
   --master local[2] \
   /opt/spark/examples/target/scala-2.12/jars/spark-examples_2.12-3.4.0.jar 4
     ```

1: 
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at org.apache.spark.examples.ml.JavaKMeansExample.main(JavaKMeansExample.java:41)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.apache.spark.deploy.JavaMainApplication.start(SparkApplication.scala:52)
	at org.apache.spark.deploy.SparkSubmit.org$apache$spark$deploy$SparkSubmit$$runMain(SparkSubmit.scala:1020)
	at org.apache.spark.deploy.SparkSubmit.doRunMain$1(SparkSubmit.scala:192)
	at org.apache.spark.deploy.SparkSubmit.submit(SparkSubmit.scala:215)
	at org.apache.spark.deploy.SparkSubmit.doSubmit(SparkSubmit.scala:91)
	at org.apache.spark.deploy.SparkSubmit$$anon$2.doSubmit(SparkSubmit.scala:1111)
	at org.apache.spark.deploy.SparkSubmit$.main(SparkSubmit.scala:1120)
	at org.apache.spark.deploy.SparkSubmit.main(SparkSubmit.scala)


----------------
2: No error
---------
3: java.lang.ArithmeticException: / by zero
-----
5: 
02:09:33.740 [Executor task launch worker for task 0.0 in stage 29.0 (TID 27)] ERROR org.apache.spark.executor.Executor - Exception in task 0.0 in stage 29.0 (TID 27) [] [ThreadID: 64] [ThreadName: Executor task launch worker for task 0.0 in stage 29.0 (TID 27)] [Caller: org.apache.spark.executor.Executoraller] [Class: org.apache.spark.internal.Logging] [Method: logError] [File: Logging.scala] [Line: 97]
org.apache.spark.SparkException: Intentional failure in stage
	at org.apache.spark.examples.ml.JavaKMeansExample.lambda$main$dc6d3fb3$1(JavaKMeansExample.java:92) ~[spark-examples_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2(Dataset.scala:3370) ~[spark-sql_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2$adapted(Dataset.scala:3370) ~[spark-sql_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2(RDD.scala:1027) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2$adapted(RDD.scala:1027) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.SparkContext.$anonfun$runJob$5(SparkContext.scala:2343) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.scheduler.ResultTask.runTask(ResultTask.scala:92) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.TaskContext.runTaskWithListeners(TaskContext.scala:161) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.scheduler.Task.run(Task.scala:139) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.executor.Executor$TaskRunner.$anonfun$run$3(Executor.scala:554) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.util.Utils$.tryWithSafeFinally(Utils.scala:1546) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:557) ~[spark-core_2.12-3.4.0.jar:3.4.0]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) ~[?:?]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628) ~[?:?]
	at java.lang.Thread.run(Thread.java:829) ~[?:?]
02:09:33.767 [task-result-getter-3] WARN org.apache.spark.scheduler.TaskSetManager - Lost task 0.0 in stage 29.0 (TID 27) (Rezghool.ht.home executor driver): org.apache.spark.SparkException: Intentional failure in stage
	at org.apache.spark.examples.ml.JavaKMeansExample.lambda$main$dc6d3fb3$1(JavaKMeansExample.java:92)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2(Dataset.scala:3370)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2$adapted(Dataset.scala:3370)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2(RDD.scala:1027)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2$adapted(RDD.scala:1027)
	at org.apache.spark.SparkContext.$anonfun$runJob$5(SparkContext.scala:2343)
	at org.apache.spark.scheduler.ResultTask.runTask(ResultTask.scala:92)
	at org.apache.spark.TaskContext.runTaskWithListeners(TaskContext.scala:161)
	at org.apache.spark.scheduler.Task.run(Task.scala:139)
	at org.apache.spark.executor.Executor$TaskRunner.$anonfun$run$3(Executor.scala:554)
	at org.apache.spark.util.Utils$.tryWithSafeFinally(Utils.scala:1546)
	at org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:557)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:829)
 [] [ThreadID: 103] [ThreadName: task-result-getter-3] [Caller: org.apache.spark.scheduler.TaskSetManageraller] [Class: org.apache.spark.internal.Logging] [Method: logWarning] [File: Logging.scala] [Line: 72]
02:09:33.769 [task-result-getter-3] ERROR org.apache.spark.scheduler.TaskSetManager - Task 0 in stage 29.0 failed 1 times; aborting job [] [ThreadID: 103] [ThreadName: task-result-getter-3] [Caller: org.apache.spark.scheduler.TaskSetManageraller] [Class: org.apache.spark.internal.Logging] [Method: logError] [File: Logging.scala] [Line: 76]
02:09:33.770 [task-result-getter-3] INFO org.apache.spark.scheduler.TaskSchedulerImpl - Removed TaskSet 29.0, whose tasks have all completed, from pool  [] [ThreadID: 103] [ThreadName: task-result-getter-3] [Caller: org.apache.spark.scheduler.TaskSchedulerImplaller] [Class: org.apache.spark.internal.Logging] [Method: logInfo] [File: Logging.scala] [Line: 60]
02:09:33.774 [dag-scheduler-event-loop] INFO org.apache.spark.scheduler.TaskSchedulerImpl - Cancelling stage 29 [] [ThreadID: 49] [ThreadName: dag-scheduler-event-loop] [Caller: org.apache.spark.scheduler.TaskSchedulerImplaller] [Class: org.apache.spark.internal.Logging] [Method: logInfo] [File: Logging.scala] [Line: 60]
02:09:33.775 [dag-scheduler-event-loop] INFO org.apache.spark.scheduler.TaskSchedulerImpl - Killing all running tasks in stage 29: Stage cancelled [] [ThreadID: 49] [ThreadName: dag-scheduler-event-loop] [Caller: org.apache.spark.scheduler.TaskSchedulerImplaller] [Class: org.apache.spark.internal.Logging] [Method: logInfo] [File: Logging.scala] [Line: 60]
02:09:33.776 [dag-scheduler-event-loop] INFO org.apache.spark.scheduler.DAGScheduler - ResultStage 29 (foreachPartition at JavaKMeansExample.java:90) failed in 0.074 s due to Job aborted due to stage failure: Task 0 in stage 29.0 failed 1 times, most recent failure: Lost task 0.0 in stage 29.0 (TID 27) (Rezghool.ht.home executor driver): org.apache.spark.SparkException: Intentional failure in stage
	at org.apache.spark.examples.ml.JavaKMeansExample.lambda$main$dc6d3fb3$1(JavaKMeansExample.java:92)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2(Dataset.scala:3370)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2$adapted(Dataset.scala:3370)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2(RDD.scala:1027)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2$adapted(RDD.scala:1027)
	at org.apache.spark.SparkContext.$anonfun$runJob$5(SparkContext.scala:2343)
	at org.apache.spark.scheduler.ResultTask.runTask(ResultTask.scala:92)
	at org.apache.spark.TaskContext.runTaskWithListeners(TaskContext.scala:161)
	at org.apache.spark.scheduler.Task.run(Task.scala:139)
	at org.apache.spark.executor.Executor$TaskRunner.$anonfun$run$3(Executor.scala:554)
	at org.apache.spark.util.Utils$.tryWithSafeFinally(Utils.scala:1546)
	at org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:557)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:829)

Driver stacktrace: [] [ThreadID: 49] [ThreadName: dag-scheduler-event-loop] [Caller: org.apache.spark.scheduler.DAGScheduleraller] [Class: org.apache.spark.internal.Logging] [Method: logInfo] [File: Logging.scala] [Line: 60]
02:09:33.779 [main] INFO org.apache.spark.scheduler.DAGScheduler - Job 19 failed: foreachPartition at JavaKMeansExample.java:90, took 0.079816 s [] [ThreadID: 1] [ThreadName: main] [Caller: org.apache.spark.scheduler.DAGScheduleraller] [Class: org.apache.spark.internal.Logging] [Method: logInfo] [File: Logging.scala] [Line: 60]
Exception in thread "main" org.apache.spark.SparkException: Job aborted due to stage failure: Task 0 in stage 29.0 failed 1 times, most recent failure: Lost task 0.0 in stage 29.0 (TID 27) (Rezghool.ht.home executor driver): org.apache.spark.SparkException: Intentional failure in stage
	at org.apache.spark.examples.ml.JavaKMeansExample.lambda$main$dc6d3fb3$1(JavaKMeansExample.java:92)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2(Dataset.scala:3370)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2$adapted(Dataset.scala:3370)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2(RDD.scala:1027)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2$adapted(RDD.scala:1027)
	at org.apache.spark.SparkContext.$anonfun$runJob$5(SparkContext.scala:2343)
	at org.apache.spark.scheduler.ResultTask.runTask(ResultTask.scala:92)
	at org.apache.spark.TaskContext.runTaskWithListeners(TaskContext.scala:161)
	at org.apache.spark.scheduler.Task.run(Task.scala:139)
	at org.apache.spark.executor.Executor$TaskRunner.$anonfun$run$3(Executor.scala:554)
	at org.apache.spark.util.Utils$.tryWithSafeFinally(Utils.scala:1546)
	at org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:557)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:829)

Driver stacktrace:
	at org.apache.spark.scheduler.DAGScheduler.failJobAndIndependentStages(DAGScheduler.scala:2785)
	at org.apache.spark.scheduler.DAGScheduler.$anonfun$abortStage$2(DAGScheduler.scala:2721)
	at org.apache.spark.scheduler.DAGScheduler.$anonfun$abortStage$2$adapted(DAGScheduler.scala:2720)
	at scala.collection.mutable.ResizableArray.foreach(ResizableArray.scala:62)
	at scala.collection.mutable.ResizableArray.foreach$(ResizableArray.scala:55)
	at scala.collection.mutable.ArrayBuffer.foreach(ArrayBuffer.scala:49)
	at org.apache.spark.scheduler.DAGScheduler.abortStage(DAGScheduler.scala:2720)
	at org.apache.spark.scheduler.DAGScheduler.$anonfun$handleTaskSetFailed$1(DAGScheduler.scala:1206)
	at org.apache.spark.scheduler.DAGScheduler.$anonfun$handleTaskSetFailed$1$adapted(DAGScheduler.scala:1206)
	at scala.Option.foreach(Option.scala:407)
	at org.apache.spark.scheduler.DAGScheduler.handleTaskSetFailed(DAGScheduler.scala:1206)
	at org.apache.spark.scheduler.DAGSchedulerEventProcessLoop.doOnReceive(DAGScheduler.scala:2984)
	at org.apache.spark.scheduler.DAGSchedulerEventProcessLoop.onReceive(DAGScheduler.scala:2923)
	at org.apache.spark.scheduler.DAGSchedulerEventProcessLoop.onReceive(DAGScheduler.scala:2912)
	at org.apache.spark.util.EventLoop$$anon$1.run(EventLoop.scala:49)
	at org.apache.spark.scheduler.DAGScheduler.runJob(DAGScheduler.scala:971)
	at org.apache.spark.SparkContext.runJob(SparkContext.scala:2288)
	at org.apache.spark.SparkContext.runJob(SparkContext.scala:2316)
	at org.apache.spark.SparkContext.runJob(SparkContext.scala:2343)
	at org.apache.spark.SparkContext.runJob(SparkContext.scala:2386)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$1(RDD.scala:1027)
	at org.apache.spark.rdd.RDDOperationScope$.withScope(RDDOperationScope.scala:151)
	at org.apache.spark.rdd.RDDOperationScope$.withScope(RDDOperationScope.scala:112)
	at org.apache.spark.rdd.RDD.withScope(RDD.scala:417)
	at org.apache.spark.rdd.RDD.foreachPartition(RDD.scala:1025)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$1(Dataset.scala:3359)
	at scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.java:23)
	at org.apache.spark.sql.Dataset.$anonfun$withNewRDDExecutionId$1(Dataset.scala:4154)
	at org.apache.spark.sql.execution.SQLExecution$.$anonfun$withNewExecutionId$6(SQLExecution.scala:118)
	at org.apache.spark.sql.execution.SQLExecution$.withSQLConfPropagated(SQLExecution.scala:195)
	at org.apache.spark.sql.execution.SQLExecution$.$anonfun$withNewExecutionId$1(SQLExecution.scala:103)
	at org.apache.spark.sql.SparkSession.withActive(SparkSession.scala:849)
	at org.apache.spark.sql.execution.SQLExecution$.withNewExecutionId(SQLExecution.scala:65)
	at org.apache.spark.sql.Dataset.withNewRDDExecutionId(Dataset.scala:4152)
	at org.apache.spark.sql.Dataset.foreachPartition(Dataset.scala:3359)
	at org.apache.spark.sql.Dataset.foreachPartition(Dataset.scala:3370)
	at org.apache.spark.examples.ml.JavaKMeansExample.main(JavaKMeansExample.java:90)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.apache.spark.deploy.JavaMainApplication.start(SparkApplication.scala:52)
	at org.apache.spark.deploy.SparkSubmit.org$apache$spark$deploy$SparkSubmit$$runMain(SparkSubmit.scala:1020)
	at org.apache.spark.deploy.SparkSubmit.doRunMain$1(SparkSubmit.scala:192)
	at org.apache.spark.deploy.SparkSubmit.submit(SparkSubmit.scala:215)
	at org.apache.spark.deploy.SparkSubmit.doSubmit(SparkSubmit.scala:91)
	at org.apache.spark.deploy.SparkSubmit$$anon$2.doSubmit(SparkSubmit.scala:1111)
	at org.apache.spark.deploy.SparkSubmit$.main(SparkSubmit.scala:1120)
	at org.apache.spark.deploy.SparkSubmit.main(SparkSubmit.scala)
Caused by: org.apache.spark.SparkException: Intentional failure in stage
	at org.apache.spark.examples.ml.JavaKMeansExample.lambda$main$dc6d3fb3$1(JavaKMeansExample.java:92)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2(Dataset.scala:3370)
	at org.apache.spark.sql.Dataset.$anonfun$foreachPartition$2$adapted(Dataset.scala:3370)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2(RDD.scala:1027)
	at org.apache.spark.rdd.RDD.$anonfun$foreachPartition$2$adapted(RDD.scala:1027)
	at org.apache.spark.SparkContext.$anonfun$runJob$5(SparkContext.scala:2343)
	at org.apache.spark.scheduler.ResultTask.runTask(ResultTask.scala:92)
	at org.apache.spark.TaskContext.runTaskWithListeners(TaskContext.scala:161)
	at org.apache.spark.scheduler.Task.run(Task.scala:139)
	at org.apache.spark.executor.Executor$TaskRunner.$anonfun$run$3(Executor.scala:554)
	at org.apache.spark.util.Utils$.tryWithSafeFinally(Utils.scala:1546)
	at org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:557)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:829)
02:09:33.907 [shutdown-hook-0] INFO org.apache.spark.SparkContext - Invoking stop() from shutdown hook [] [ThreadID: 277] [ThreadName: shutdown-hook-0] [Caller: org.apache.spark.SparkContextaller] [Class: org.apache.spark.internal.Logging] [Method: logInfo] [File: Logging.scala] [Line: 60]




-------------------
6: Exception in thread "main" java.lang.IllegalArgumentException: features does not exist. Available: 
	at org.apache.spark.sql.types.StructType.$anonfun$apply$1(StructType.scala:285)
	at scala.collection.immutable.Map$EmptyMap$.getOrElse(Map.scala:110)
	at org.apache.spark.sql.types.StructType.apply(StructType.scala:284)
	at org.apache.spark.ml.util.SchemaUtils$.checkColumnTypes(SchemaUtils.scala:59)
	at org.apache.spark.ml.util.SchemaUtils$.validateVectorCompatibleColumn(SchemaUtils.scala:205)
	at org.apache.spark.ml.clustering.KMeansParams.validateAndTransformSchema(KMeans.scala:121)
	at org.apache.spark.ml.clustering.KMeansParams.validateAndTransformSchema$(KMeans.scala:120)
	at org.apache.spark.ml.clustering.KMeansModel.validateAndTransformSchema(KMeans.scala:132)
	at org.apache.spark.ml.clustering.KMeansModel.transformSchema(KMeans.scala:168)
	at org.apache.spark.ml.PipelineStage.transformSchema(Pipeline.scala:71)
	at org.apache.spark.ml.clustering.KMeansModel.transform(KMeans.scala:157)
	at org.apache.spark.examples.ml.JavaKMeansExample.main(JavaKMeansExample.java:133)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.apache.spark.deploy.JavaMainApplication.start(SparkApplication.scala:52)
	at org.apache.spark.deploy.SparkSubmit.org$apache$spark$deploy$SparkSubmit$$runMain(SparkSubmit.scala:1020)
	at org.apache.spark.deploy.SparkSubmit.doRunMain$1(SparkSubmit.scala:192)
	at org.apache.spark.deploy.SparkSubmit.submit(SparkSubmit.scala:215)
	at org.apache.spark.deploy.SparkSubmit.doSubmit(SparkSubmit.scala:91)
	at org.apache.spark.deploy.SparkSubmit$$anon$2.doSubmit(SparkSubmit.scala:1111)
	at org.apache.spark.deploy.SparkSubmit$.main(SparkSubmit.scala:1120)
	at org.apache.spark.deploy.SparkSubmit.main(SparkSubmit.scala)
02:25:38.194 [shutdown-hook-0] INFO org.apache.spark.SparkContext - Invoking stop() from shutdown hook [] [ThreadID: 276] [ThreadName: shutdown-hook-0] [Caller: org.apache.spark.SparkContextaller] [Class: org.apache.spark.internal.Logging] [Method: logInfo] [File: Logging.scala] [Line: 60]

-----------------------------------------
change 6 and 7 : 

The error you're encountering (java.lang.IllegalArgumentException: features does not exist) is due to the transformation applied to the dataset after the KMeans model fitting. The map transformation in the code is altering the structure of the dataset, which is causing an issue when the transform method of the KMeans model is called later.

In Spark MLlib, when you fit a model (like KMeans), the model expects the input dataset for prediction (or transformation) to have the same schema as the dataset used for training. The map operation in your code is changing the schema of the dataset, leading to this error.

To fix this, you need to ensure that the schema of the dataset remains consistent after any transformation. Here's a revised version of the code that addresses this issue:

Dataset<Row> transformedDataset = dataset.map((MapFunction<Row, Row>) row -> {
        // Allocate a large amount of memory (this could lead to OutOfMemoryError)
        byte[] largeArray = new byte[1000 * 1000 * 1000]; // 1GB
        return row;
    }, Encoders.bean(Row.class));

    
-----------------------------
new 6,7 : no error 
------------------
