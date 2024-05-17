package org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis;

import static org.eclipse.tracecompass.common.core.NonNullUtils.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.statesystem.core.ITmfStateSystemBuilder;
import org.eclipse.tracecompass.statesystem.core.StateSystemBuilderUtils;
import org.eclipse.tracecompass.statesystem.core.exceptions.AttributeNotFoundException;
import org.eclipse.tracecompass.tmf.core.event.ITmfEvent;
import org.eclipse.tracecompass.tmf.core.event.ITmfEventField;
import org.eclipse.tracecompass.tmf.core.statesystem.AbstractTmfStateProvider;
import org.eclipse.tracecompass.tmf.core.statesystem.ITmfStateProvider;
import org.eclipse.tracecompass.tmf.core.statesystem.TmfStateSystemAnalysisModule;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.core.trace.TmfTraceUtils;

public class SparkAnalysisModule  extends TmfStateSystemAnalysisModule{
    private HashSet<String> activeTasks = new HashSet<>();
    private final HashMap<String, HashSet<String>> activeTasksPerExecutor = new HashMap<>();
    private final HashMap<@Nullable String, @Nullable String> stageStartTimes = new HashMap<>();    // HashMap to store stage start times
    @NonNull private final HashSet<String> threads =  new HashSet<>();
    @NonNull private final HashMap<String, String> taskThread = new HashMap<>();
    @NonNull private final HashMap<String, String> task_kernelThread = new HashMap<>();
    public static final String ID = "mysparkanalysis"; //$NON-NLS-1$

    private final Pattern PATTERN = Pattern.compile("task_id=(\\d+).*thread_id=(\\d+)");

    //@NonNull private final HashSet<String> cpuLooking =  new HashSet<>();

    Map<String, Map<String, String>> CPU_spark_threadId_sparkVTID = new HashMap<>();
    List<List<String>> threadList = new ArrayList<>();
    @Override
    protected ITmfStateProvider createStateProvider() {
        // TODO Auto-generated method stub
           return new SparkStateProvider(getTrace(), "mysparkanalysis"); //$NON-NLS-1$
    }


    class SparkStateProvider extends AbstractTmfStateProvider {

        public SparkStateProvider(ITmfTrace trace, String id) {
            super(trace, id);
            // TODO Auto-generated constructor stub
        }

        @Override
        public int getVersion() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public @NonNull ITmfStateProvider getNewInstance() {
            // TODO Auto-generated method stub
            return new SparkStateProvider(getTrace(), "mysparkanalysis");
        }

        @Override
        protected void eventHandle(@NonNull ITmfEvent event) {
            // TODO Auto-generated method stub

            //System.out.println("event is comming...");
            String eventName = event.getName();
            //String traceId = event.getContent().getFieldValue(String.class, IOpenTracingConstants.TRACE_ID);

            final ITmfEventField content = event.getContent();

            final String logger_name = content.getFieldValue(String.class, "logger_name");
            final String msgContent = content.getFieldValue(String.class, "msg");
            //final @NonNull String CPU = content.getFieldValue(String.class, "context.cpu_id");
            final String CPU = String.valueOf(TmfTraceUtils.resolveAspectOfNameForEvent(event.getTrace(), "CPU", event));

            final String Kernel_threadID = String.valueOf(TmfTraceUtils.resolveAspectOfNameForEvent(event.getTrace(), "TID", event));

            final String TID = event.getContent().getFieldValue(String.class, "context._tid");
            final String TID2 = event.getContent().getFieldValue(String.class, "context._vtid");
            final String TID3 = event.getContent().getFieldValue(String.class, "TID");
            final String TID4 = event.getContent().getFieldValue(String.class, "tid");

            //System.out.println(event);
            //String lastCpuVisited = null;
            //Boolean lookForKernelThreadId = false;

            //if (!"org.apache.spark.executor.Executor".equals(logger_name)) {
            //    System.out.println("reza");
            //    System.out.println(content.getFields());
            //}

            int quarkThreadTask;
            int quarkThreadNumber;

            final long ts = event.getTimestamp().toNanos();
            ITmfStateSystemBuilder ss = checkNotNull(getStateSystemBuilder());
            //System.out.println("-1" + eventName);

            if ("org.apache.spark.executor.Executor".equals(logger_name ) && msgContent != null && msgContent.startsWith("method=run")){
                Matcher matcher = PATTERN.matcher(msgContent);
                if (matcher.find()) {
                    String taskId= "";
                    String spark_threadId = "";
                    String sparkVTID = content.getFieldValue(String.class, "context._vtid");

                    taskId = matcher.group(1);
                    spark_threadId = matcher.group(2);
                    if (taskId == null || spark_threadId == null) {
                        return;
                     }
                    taskThread.put(taskId, spark_threadId);
                    threads.add(spark_threadId);

                    quarkThreadTask = ss.getQuarkAbsoluteAndAdd("Thread Task", "taskId: "+ taskId + " :: " + "threadId: "+ spark_threadId);
                    ss.modifyAttribute(ts, "taskId: "+ taskId + " :: " + "sprak threadId: "+ spark_threadId  , quarkThreadTask);

                    quarkThreadNumber = ss.getQuarkAbsoluteAndAdd("Tasks of Threads","spark threadId: " + spark_threadId);
                    StateSystemBuilderUtils.incrementAttributeInt(ss, ts, quarkThreadNumber, 1);


                    //System.out.println(taskId + ": " + spark_threadId + ": " + CPU + ": " + threadID + ": " + sparkVTID);

                    //find the next kernel events with same cpu
                    //cpuLooking.add(CPU);
                    // Adding initial data
                    Map<String, String> data = new HashMap<>();
                    //System.out.println(CPU + ": " + spark_threadId + ": " + sparkVTID);

                    data.put("ts", String.valueOf(ts));
                    data.put("taskId", taskId);
                    data.put("spark_threadID", spark_threadId);
                    data.put("sparkVTID", sparkVTID);
                    CPU_spark_threadId_sparkVTID.put(CPU, data);

                }
            }

            else if ("org.apache.spark.examples.MyCustomSparkListener".equals(logger_name)){
                if (msgContent == null) {
                    return;
                }
            // HashMap to store all variables and their values
            HashMap<String, String> variables = new HashMap<>();
            String[] keyValuePairs = msgContent.split("; "); //$NON-NLS-1$
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("="); //$NON-NLS-1$
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    variables.put(key, value); // Store key-value pair in the HashMap
                    //System.out.println(key + ": " + value);
                    }
                }
            String eventType = variables.get("eventType"); // //$NON-NLS-1$
            String taskId = variables.get("taskId"); // //$NON-NLS-1$
            String executorId = variables.get("executorId"); // //$NON-NLS-1$
            String jobId = variables.get("jobId"); // //$NON-NLS-1$
            String stageId = variables.get("stageId"); // //$NON-NLS-1$
            String stageIdsInjobs = variables.get("stageIds"); // //$NON-NLS-1$
            String numberoftaskinstages = variables.get("numTasks"); // //$NON-NLS-1$
            String taskEndSuccess = variables.get("successful"); // //$NON-NLS-1$

            String stageOperations = variables.get("stageOperations"); // //$NON-NLS-1$

            String taskDuration = variables.getOrDefault("taskDuration","0");
            String executorRunTime = variables.getOrDefault("executorRunTime","0");
            String executorCpuTime = variables.getOrDefault("executorCpuTime","0"); // //$NON-NLS-1$ nanosecond
            String resultSerializationTime = variables.getOrDefault("resultSerializationTime","0"); // //$NON-NLS-1$
            String jvmGCTime = variables.getOrDefault("jvmGCTime","0"); // //$NON-NLS-1$
            String shuffleReadFetchWaitTime = variables.getOrDefault("shuffleReadFetchWaitTime","0"); // //$NON-NLS-1$
            String shufflesWrtietime = variables.getOrDefault("shufflesWrtietime","0"); // //$NON-NLS-1$ nanosecond
            String executorDeserializeCpuTime = variables.getOrDefault("executorDeserializeCpuTime","0"); // //$NON-NLS-1$ nanosecond
            String executorDeserializeTime = variables.getOrDefault("executorDeserializeTime","0"); // //$NON-NLS-1$

            String stageSubmissionTime = variables.get("stageSubmissionTime");
            //String taskStartTime = variables.get("taskStartTime");

            //String taskPendingTimebeoreStart;
            //String stageOfTaskStartTime;

            int quarkTaskExcecutorId;
            int quarkExecutorRunningTask;
            int quarkExecutorTaskTotal;
            int quarkExecutorTaskFailed;
            int quarkSatgesInJobs;
            int quarkTaskInstages;
            int quarkExecutorForTask;
            int quarkJob;
            int quarkStage;

            int quarkActiveTime;
            int quarkNotAtiveTime;
            int quarkActiveTimePerExecutor;

            int quarkTasksTime;


            if ("JobStart".equals(eventType)) {
                quarkJob = ss.getQuarkAbsoluteAndAdd("Jobs -> stages -> tasks","Job Id: " + jobId);
                ss.modifyAttribute(ts, "jobId:"+ jobId + "-> {" + stageIdsInjobs + "}" , quarkJob);
            }
            else if ("JobEnd".equals(eventType)) {
                quarkJob = ss.getQuarkAbsoluteAndAdd("Jobs -> stages -> tasks","Job Id: " + jobId);
                ss.removeAttribute(ts, quarkJob);
            }

            else if ("StageSubmitted".equals(eventType)) {
                quarkStage = ss.getQuarkAbsoluteAndAdd("Stage -> Operations","Stage Id: " +  stageId);
                try {
                    if (stageSubmissionTime != "") {
                        stageSubmissionTime = stageSubmissionTime.replace("Some(", "").replace(")", ""); // Removing "Some(" and ")" //$NON-NLS-3$
                    }
                    stageStartTimes.put(stageId, stageSubmissionTime);
                    //quarkSatgesInJobs = ss.getQuarkAbsoluteAndAdd("jobs -> stages", quarkStage);
                    quarkJob = ss.getQuarkAbsolute("Jobs -> stages -> tasks", "Job Id: " + jobId);
                    quarkSatgesInJobs = ss.getQuarkRelativeAndAdd(quarkJob,"Stage Id: " + stageId);
                    ss.modifyAttribute(ts, "stageID:"+ stageId + "-> #"  , quarkSatgesInJobs);

                } catch (AttributeNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //ss.modifyAttribute(quarkStage, keyValuePairs, quarkJob);
                ss.modifyAttribute(ts, "#" , quarkStage);
            }
            else if ("StageCompleted".equals(eventType)) {

                stageStartTimes.remove(stageId);

                quarkStage = ss.getQuarkAbsoluteAndAdd("Stage -> Operations","Stage Id: " + stageId);
                ss.updateOngoingState("stageID:"+ stageId + " -> " + stageOperations, quarkStage);

                ss.removeAttribute(ts, quarkStage);

                try {
                    quarkJob = ss.getQuarkAbsolute("Jobs -> stages -> tasks", "Job Id: " + jobId);
                    quarkSatgesInJobs = ss.getQuarkRelative(quarkJob,"Stage Id: " + stageId);
                    ss.updateOngoingState("stageID:"+ stageId + "-> #Task: "+ numberoftaskinstages , quarkSatgesInJobs);
                    ss.removeAttribute(ts, quarkSatgesInJobs);
                } catch (AttributeNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            else if ("TaskStart".equals(eventType)) {
                activeTasks.add(taskId);
                activeTasksPerExecutor.putIfAbsent(executorId, new HashSet<>());
                activeTasksPerExecutor.get(executorId).add(taskId);

                quarkTaskExcecutorId = ss.getQuarkAbsoluteAndAdd("Task -> Executor ID", "Task Id: " + taskId);
                quarkExecutorRunningTask = ss.getQuarkAbsoluteAndAdd("ExecuterID -> Running Task", "Executor: " + executorId);
                quarkExecutorTaskTotal = ss.getQuarkAbsoluteAndAdd("ExecuterID -> Total Task", "Executor: " + executorId);
                //quarkExecutorTaskFailed = ss.getQuarkAbsoluteAndAdd("ExecuterID -> Total Failed Task", taskId);


                quarkActiveTime = ss.getQuarkAbsoluteAndAdd("Active Tasks Time","All Executors");
                ss.modifyAttribute(ts, "Active Tasks Time", quarkActiveTime);

                quarkActiveTimePerExecutor = ss.getQuarkAbsoluteAndAdd("Active Tasks Time Per Executor","Executor: " + executorId);
                ss.modifyAttribute(ts, "Active Tasks Time." , quarkActiveTimePerExecutor);


                StateSystemBuilderUtils.incrementAttributeInt(ss, ts, quarkExecutorRunningTask, 1);
                StateSystemBuilderUtils.incrementAttributeInt(ss, ts, quarkExecutorTaskTotal, 1);
                // Update state to "active" or add excuter id
                ss.modifyAttribute(ts, "success: " + executorId, quarkTaskExcecutorId);
                //ss.modifyAttribute(ts, "Start #" + taskId  , quarkExecutorTaskFailed);

                //String stageStartTime = stageStartTimes.get(stageId);
                //taskPendingTimebeoreStart = stageOfTaskStartTime - taskStartTime;
                // Convert to long
                // Calculate pending time (in nanoseconds)
                //long pendingTime = Long.parseLong(taskStartTime) - Long.parseLong(stageStartTime);
                //System.out.print("pendingTime: " + pendingTime);
                try {
                    quarkNotAtiveTime = ss.getQuarkAbsoluteAndAdd("Not Active Tasks Time","All Executors");
                    ss.removeAttribute(ts, quarkNotAtiveTime);

                    quarkJob = ss.getQuarkAbsolute("Jobs -> stages -> tasks", "Job Id: " + jobId);
                    quarkSatgesInJobs = ss.getQuarkRelativeAndAdd(quarkJob, "Stage Id: " + stageId);
                    quarkTaskInstages = ss.getQuarkRelativeAndAdd(quarkSatgesInJobs, "Task Id: " + taskId);
                    quarkExecutorForTask = ss.getQuarkRelativeAndAdd(quarkTaskInstages, "Executor Id: " + executorId );

                    String thisTaskThread = taskThread.get(taskId);
                    ss.modifyAttribute(ts, "TaskId: " + taskId ,quarkTaskInstages);
                    ss.modifyAttribute(ts, "executorId: " + executorId + " :: " + thisTaskThread , quarkExecutorForTask);


                } catch (AttributeNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else if ("TaskEnd".equals(eventType)) {

                /*
                 End the kernel events for this thread*/
                if (task_kernelThread.containsKey(taskId)) {
                    int quarkKernelThread_task;
                    try {
                        quarkKernelThread_task = ss.getQuarkAbsolute("Kernel Thread Task", task_kernelThread.get(taskId));
                        //ss.removeAttribute(ts, quarkKernelThread_task);
                        ss.modifyAttribute(ts, null, quarkKernelThread_task);

                        task_kernelThread.remove(taskId);
                    } catch (AttributeNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }



                quarkTaskExcecutorId = ss.getQuarkAbsoluteAndAdd("Task -> Executor ID", "Task Id: " + taskId);
                quarkExecutorRunningTask = ss.getQuarkAbsoluteAndAdd("ExecuterID -> Running Task", "Executor: " + executorId);
                quarkExecutorTaskFailed = ss.getQuarkAbsoluteAndAdd("ExecuterID -> Total Failed Task","Task Id: " + taskId);
                activeTasks.remove(taskId);


                quarkTasksTime = ss.getQuarkAbsoluteAndAdd("Time of Tasks","executorRunTime");
                double executorRunTimeDouble = Double.parseDouble(executorRunTime);
                ss.modifyAttribute(ts, executorRunTimeDouble, quarkTasksTime);

                 // Convert executorCpuTime
                int quarkExecutorCpuTime = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "executorCpuTime");
                double executorCpuTimeDouble = Double.parseDouble(executorCpuTime) / 1000000;
                ss.modifyAttribute(ts, executorCpuTimeDouble, quarkExecutorCpuTime);

                // Convert resultSerializationTime
                int quarkResultSerializationTime = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "resultSerializationTime");
                double resultSerializationTimeDouble = Double.parseDouble(resultSerializationTime);
                ss.modifyAttribute(ts, resultSerializationTimeDouble, quarkResultSerializationTime);

                // Convert jvmGCTime
                int quarkJvmGCTime = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "jvmGCTime");
                double jvmGCTimeDouble = Double.parseDouble(jvmGCTime);
                ss.modifyAttribute(ts, jvmGCTimeDouble, quarkJvmGCTime);

                // Convert shuffleReadFetchWaitTime
                int quarkShuffleReadFetchWaitTime = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "shuffleReadFetchWaitTime");
                double shuffleReadFetchWaitTimeDouble = Double.parseDouble(shuffleReadFetchWaitTime);
                ss.modifyAttribute(ts, shuffleReadFetchWaitTimeDouble, quarkShuffleReadFetchWaitTime);

                // Convert shuffleWriteTime
                int quarkShuffleWriteTime = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "shuffleWriteTime");
                double shuffleWriteTimeDouble = Double.parseDouble(shufflesWrtietime) / 1000000;
                ss.modifyAttribute(ts, shuffleWriteTimeDouble, quarkShuffleWriteTime);

                // Convert executorDeserializeCpuTime
                int quarkExecutorDeserializeCpuTime = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "executorDeserializeCpuTime");
                double executorDeserializeCpuTimeDouble = Double.parseDouble(executorDeserializeCpuTime) / 1000000;

                ss.modifyAttribute(ts, executorDeserializeCpuTimeDouble, quarkExecutorDeserializeCpuTime);

                // Convert executorDeserializeTime
                int quarkexecutorDeserializeTime = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "executorDeserializeTime");
                double executorDeserializeTimeDouble = Double.parseDouble(executorDeserializeTime);
                ss.modifyAttribute(ts, executorDeserializeTimeDouble, quarkexecutorDeserializeTime);

                // Convert taskduration
                int quarktaskDuration = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "taskDuration");
                double taskDurationDouble = Double.parseDouble(taskDuration);
                ss.modifyAttribute(ts, taskDurationDouble, quarktaskDuration);

             // Convert taskduration
                int quarkTaskTimeSum = ss.getQuarkAbsoluteAndAdd("Time of Tasks", "taskTimeSum");
                double taskTimeSum = executorRunTimeDouble + executorDeserializeTimeDouble + resultSerializationTimeDouble;
                ss.modifyAttribute(ts, taskTimeSum, quarkTaskTimeSum);

             // To remove a task
                if (activeTasksPerExecutor.containsKey(executorId)) {
                    activeTasksPerExecutor.get(executorId).remove(taskId);
                    quarkActiveTimePerExecutor = ss.getQuarkAbsoluteAndAdd("Active Tasks Time Per Executor","Executor: " + executorId);
                    ss.removeAttribute(ts, quarkActiveTimePerExecutor);
                    if (activeTasksPerExecutor.get(executorId).isEmpty()) {
                        activeTasksPerExecutor.remove(executorId); // Optional: remove the executor entry if no more active tasks
                    }
                }

                try {
                    if (activeTasks.isEmpty()) {
                        // Perform the desired action because the set is empty
                        quarkActiveTime = ss.getQuarkAbsoluteAndAdd("Active Tasks Time","All Executors");
                        ss.removeAttribute(ts, quarkActiveTime);
                        quarkNotAtiveTime = ss.getQuarkAbsoluteAndAdd("Not Active Tasks Time","All Executors");
                        ss.modifyAttribute(ts, "Not Active Tasks Time", quarkNotAtiveTime);
                        }

                    quarkJob = ss.getQuarkAbsolute("Jobs -> stages -> tasks", "Job Id: " + jobId);
                    quarkSatgesInJobs = ss.getQuarkRelativeAndAdd(quarkJob, "Stage Id: " + stageId);
                    quarkTaskInstages = ss.getQuarkRelativeAndAdd(quarkSatgesInJobs, "Task Id: " + taskId);
                    quarkExecutorForTask = ss.getQuarkRelativeAndAdd(quarkTaskInstages, "Executor Id: " + executorId);

                    ss.removeAttribute(ts, quarkTaskInstages);
                } catch (AttributeNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                if (!("true".equals(taskEndSuccess))) {
                    ss.updateOngoingState("Failed! #" + taskId, quarkExecutorTaskFailed);
                    ss.updateOngoingState( "Failed!: " + executorId, quarkTaskExcecutorId);
                }
                 ss.removeAttribute(ts, quarkExecutorTaskFailed);

                //System.out.println(eventType + ": " + executorId);
                // Update state to "inactive"
                //ss.modifyAttribute(ts, "inactive", quark);
                // Instead of setting to "inactive", delete or reset the quark
                StateSystemBuilderUtils.incrementAttributeInt(ss, ts, quarkExecutorRunningTask, -1);

                ss.removeAttribute(ts, quarkTaskExcecutorId);

            }
            /* Number of events of each type, globally */
            //quark = ss.getQuarkAbsoluteAndAdd(Attributes.EVENT_TYPES, eventName);
            //StateSystemBuilderUtils.incrementAttributeInt(ss, ts, quark, 1);
            //System.out.println(eventType);
        }



            else if (!"lttng_log4j:event".equals(eventName) && Kernel_threadID != null && CPU_spark_threadId_sparkVTID.containsKey(CPU)) {

            if (Kernel_threadID.length() != 0) {

                System.out.println( CPU_spark_threadId_sparkVTID.get(CPU).get("spark_threadID") + " :: "+
                       CPU_spark_threadId_sparkVTID.get(CPU).get("sparkVTID") + " :: " +
                      CPU_spark_threadId_sparkVTID.get(CPU).get("taskId") + " :: " +
                       eventName + " :: " +
                       //content + " :: "+
                     Kernel_threadID + " :: "
                      );
            }



            CPU_spark_threadId_sparkVTID.get(CPU).put("Kernel_threadID",Kernel_threadID);
            String taskId = CPU_spark_threadId_sparkVTID.get(CPU).get("taskId");
            String spark_thradID = CPU_spark_threadId_sparkVTID.get(CPU).get("spark_threadID");
            task_kernelThread.put(taskId,Kernel_threadID);
            long ts2 = Long.parseLong(CPU_spark_threadId_sparkVTID.get(CPU).get("ts"));


            int quarkKernelThread_task = ss.getQuarkAbsoluteAndAdd("Kernel Thread Task", Kernel_threadID);
            ss.modifyAttribute(ts2, "taskId: "+ taskId + " :: " + "sprak threadId: " + spark_thradID  + " :: CPU: " + CPU , quarkKernelThread_task );

            CPU_spark_threadId_sparkVTID.remove(CPU);
        }





        }


    }

}