package org.apache.spark.examples;


import org.apache.log4j.Logger;
import org.apache.spark.scheduler.*;
import org.apache.spark.executor.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.spark.storage.RDDInfo;
import scala.collection.JavaConversions;
//import org.lttng.ust.agent.log4j.LttngLogAppender;
import org.apache.log4j.Appender;
import java.io.IOException;


public class MyCustomSparkListener extends SparkListener {
    private final Map<Integer, Integer> stageToJobMap = new HashMap<>();
    Logger logger_log4j = Logger.getLogger(MyCustomSparkListener.class);

    @Override
    public void onExecutorAdded(SparkListenerExecutorAdded executorAdded) {
        Map<String, Object> data = new HashMap<>();
        data.put("executorId", executorAdded.executorId());
        data.put("addedTime", executorAdded.time());
        data.put("executorInfo", executorAdded.executorInfo().toString());
    
        logData("ExecutorAdded", data);
    }
    @Override
    public void onExecutorRemoved(SparkListenerExecutorRemoved executorRemoved) {
        Map<String, Object> data = new HashMap<>();
        data.put("executorId", executorRemoved.executorId());
        data.put("removedTime", executorRemoved.time());
        data.put("reason", executorRemoved.reason());
    
        logData("ExecutorRemoved", data);
    }    
    @Override
    public void onApplicationStart(SparkListenerApplicationStart applicationStart) {
        Map<String, Object> data = new HashMap<>();
        data.put("appName", applicationStart.appName());
        data.put("appId", applicationStart.appId().isDefined() ? applicationStart.appId().get() : null);

        // You can include more information as needed
        // Example: User, Spark User, appAttemptId, etc.

        logData("ApplicationStart", data);
    }

    @Override
    public void onApplicationEnd(SparkListenerApplicationEnd applicationEnd) {
        Map<String, Object> data = new HashMap<>();
    
        logData("ApplicationEnd", data);
    }

    @Override
    public void onJobStart(SparkListenerJobStart jobStart) {
        Map<String, Object> data = new HashMap<>(); 
        List<StageInfo> stageInfos = scala.collection.JavaConversions.seqAsJavaList(jobStart.stageInfos());
        List<Integer> stageIdList = new ArrayList<>();
        int numberOfStages = jobStart.stageIds().size(); // Number of stages in this job
        for (StageInfo stageInfo : stageInfos) {
            stageToJobMap.put(stageInfo.stageId(), jobStart.jobId());
            stageIdList.add(stageInfo.stageId());
        }
        data.put("jobId", jobStart.jobId());
        data.put("submissionTime", jobStart.time());
        data.put("numberOfStages", numberOfStages);
        data.put("stageIds", stageIdList.stream().map(Object::toString).collect(Collectors.joining(",")));
        data.put("properties", jobStart.properties().toString());
        logData("JobStart", data);
    }
    @Override
    public void onJobEnd(SparkListenerJobEnd jobEnd) {
        Map<String, Object> data = new HashMap<>();
        data.put("jobId", jobEnd.jobId());
        data.put("endTime", jobEnd.time());
        data.put("jobResult", jobEnd.jobResult().toString());
    
        // Log the data
        logData("JobEnd", data);
    }    


    @Override
    public void onStageSubmitted(SparkListenerStageSubmitted stageSubmitted) {
        int stageId = stageSubmitted.stageInfo().stageId();
        Integer jobId = stageToJobMap.getOrDefault(stageId, -1);

        Map<String, Object> data = new HashMap<>();
        data.put("jobId", jobId);
        data.put("stageId", stageId);
        data.put("stageSubmissionTime", stageSubmitted.stageInfo().submissionTime());
        data.put("stageAttemptId", stageSubmitted.stageInfo().attemptNumber());
        data.put("numTasks", stageSubmitted.stageInfo().numTasks());

        logData("StageSubmitted", data);
    }

    @Override
    public void onStageCompleted(SparkListenerStageCompleted stageCompleted) {
        int stageId = stageCompleted.stageInfo().stageId();
        Integer jobId = stageToJobMap.getOrDefault(stageId, -1);
        String stageOperations = "[" + stageCompleted.stageInfo().name();
        Map<String, Object> data = new HashMap<>();
        data.put("jobId", jobId);
        data.put("stageId", stageId);
        data.put("stageCompletionTime",stageCompleted.stageInfo().completionTime());
        data.put("stageSubmissionTime",stageCompleted.stageInfo().submissionTime());
        //data.put("rddInfos",stageCompleted.stageInfo().rddInfos());
        data.put("stageName",stageCompleted.stageInfo().name());
        data.put("stageAttemptId", stageCompleted.stageInfo().attemptNumber());
        data.put("numTasks", stageCompleted.stageInfo().numTasks());

        // New code to add RDD information
        //List<rddInfos> rddInfos = JavaConversions.seqAsJavaList(onStageCompleted.stageInfo().rddInfos());
        //List<RDDInfo> rddInfos = ScalaConversionUtils.fromSeq(stageInfo.rddInfos());
        List<RDDInfo> rddInfos = scala.collection.JavaConversions.seqAsJavaList(stageCompleted.stageInfo().rddInfos());

        List<Map<String, Object>> rddDetailsList = new ArrayList<>();
        for (RDDInfo rddInfo : rddInfos) {
            Map<String, Object> rddDetails = new HashMap<>();

            rddDetails.put("RDD ID", rddInfo.id());
            rddDetails.put("Name", rddInfo.name());
            // Scope might need special handling if it's an optional value
            if (rddInfo.scope().isDefined()) {
            rddDetails.put("Scope", rddInfo.scope().get().toJson());
            }
            rddDetails.put("Callsite", rddInfo.callSite());
            stageOperations = stageOperations + "] [" + rddInfo.callSite() ;
            // Parent IDs
            //List<RDDInfo> parentIds = scala.collection.JavaConversions.seqAsJavaList(rddInfo.parentIds());
            rddDetails.put("Parent IDs", rddInfo.parentIds());
            // Storage Level
            rddDetails.put("Storage Level", rddInfo.storageLevel().description());
            rddDetails.put("Barrier", rddInfo.isBarrier());
            rddDetails.put("DeterministicLevel", rddInfo.outputDeterministicLevel().toString());
            rddDetails.put("Number of Partitions", rddInfo.numPartitions());
            rddDetails.put("Number of Cached Partitions", rddInfo.numCachedPartitions());
            rddDetails.put("Memory Size", rddInfo.memSize());
            rddDetails.put("Disk Size", rddInfo.diskSize());

            // Add more details as needed
            rddDetailsList.add(rddDetails);
            
        }
        stageOperations = stageOperations + "]";
        data.put("stageOperations", stageOperations);

        data.put("RDDInfos", rddDetailsList);

        logData("StageCompleted", data);
    }

    @Override
    public void onTaskStart(SparkListenerTaskStart taskStart) {
        
        Thread currentThread = Thread.currentThread();
        long threadId = currentThread.getId();
        String threadName = currentThread.getName();
        
        int stageId = taskStart.stageId();
        Integer jobId = stageToJobMap.getOrDefault(stageId, -1);

        Map<String, Object> data = new HashMap<>();
        data.put("jobId", jobId);
        data.put("stageId", stageId);
        data.put("taskId", taskStart.taskInfo().taskId());
        data.put("executorId", taskStart.taskInfo().executorId());
        data.put("host", taskStart.taskInfo().host());
        data.put("taskStartTime", taskStart.taskInfo().launchTime());
        data.put("speculative", taskStart.taskInfo().speculative());
        data.put("threadId", threadId);
        data.put("threadName", threadId);

        logData("TaskStart", data);
    }

    @Override
    public void onTaskEnd(SparkListenerTaskEnd taskEnd) {
        TaskMetrics metrics = taskEnd.taskMetrics();
        int stageId = taskEnd.stageId();
        Integer jobId = stageToJobMap.getOrDefault(stageId, -1);

        Map<String, Object> data = new HashMap<>();
        data.put("jobId", jobId);
        data.put("stageId", stageId);


        data.put("taskId", taskEnd.taskInfo().taskId());
        data.put("taskEndTime", taskEnd.taskInfo().finishTime());//The time when the task has completed successfully (including the time to remotely fetch results, if necessary).
        data.put("gettingResultTime", taskEnd.taskInfo().gettingResultTime());//The time when the task started remotely getting the result.
        data.put("taskIndex", taskEnd.taskInfo().index());//The index of this task within its task set.
        data.put("attemptNumber", taskEnd.taskInfo().attemptNumber());

        data.put("taskDuration", taskEnd.taskInfo().duration());
        data.put("executorId", taskEnd.taskInfo().executorId());
        data.put("host", taskEnd.taskInfo().host());
        data.put("successful", taskEnd.taskInfo().successful());
        data.put("launchTime", taskEnd.taskInfo().launchTime());
        data.put("taskLocality", taskEnd.taskInfo().taskLocality());
        data.put("id", taskEnd.taskInfo().id());
        data.put("status", taskEnd.taskInfo().status());

        data.put("failed", taskEnd.taskInfo().failed());
        data.put("finished", taskEnd.taskInfo().finished());
        data.put("killed", taskEnd.taskInfo().killed());

        data.put("reasonForFailure", taskEnd.reason() != null ? taskEnd.reason().toString() : "");

        data.put("diskBytesSpilled" , metrics.diskBytesSpilled());
        data.put("executorCpuTime" , metrics.executorCpuTime());
        data.put("executorDeserializeCpuTime" , metrics.executorDeserializeCpuTime());
        data.put("executorDeserializeTime" , metrics.executorDeserializeTime());
        data.put("executorRunTime" , metrics.executorRunTime());


        data.put("outputBytesWritten" , metrics.outputMetrics().bytesWritten());
        data.put("outputRecordsWritten" , metrics.outputMetrics().recordsWritten());

        data.put("jvmGCTime" , metrics.jvmGCTime());

        data.put("resultSerializationTime" , metrics.resultSerializationTime());
        data.put("resultSize" , metrics.resultSize());
      
        ShuffleReadMetrics shuffleReadMetrics = metrics.shuffleReadMetrics();
        // Adding ShuffleReadMetrics to the data map
        data.put("shuffleReadFetchWaitTime", shuffleReadMetrics.fetchWaitTime());
        data.put("shuffleReadLocalBlocksFetched", shuffleReadMetrics.localBlocksFetched());
        data.put("shuffleReadLocalBytesRead", shuffleReadMetrics.localBytesRead());
        data.put("shuffleReadRecordsRead", shuffleReadMetrics.recordsRead());
        data.put("shuffleReadRemoteBlocksFetched", shuffleReadMetrics.remoteBlocksFetched());
        data.put("shuffleReadRemoteBytesRead", shuffleReadMetrics.remoteBytesRead());
        data.put("shuffleReadRemoteReqsDuration", shuffleReadMetrics.remoteReqsDuration());

        ShuffleWriteMetrics shuffleWriteMetrics = metrics.shuffleWriteMetrics();
        // Adding shuffleWriteMetrics to the data map
        data.put("shuffleWriteBytesWritten" , shuffleWriteMetrics.bytesWritten());
        data.put("shuffleWriteRecordsWritten" , shuffleWriteMetrics.recordsWritten());
        data.put("shufflesWrtietime" , shuffleWriteMetrics.writeTime());
        
        // Memory
        data.put("memoryBytesSpilled" , metrics.memoryBytesSpilled());
        data.put("peakExecutionMemory" , metrics.peakExecutionMemory());
        data.put("shuffleReadRemoteBytesReadToDisk", shuffleReadMetrics.remoteBytesReadToDisk());

        logData("TaskEnd", data);
    }

    // ... (Other methods like onJobEnd, etc.)

    private void logData(String eventType, Map<String, Object> data) {
        String logMessage = formatLogMessage(eventType, data);
        boolean lttngUstLogAppendercheck = Boolean.parseBoolean(System.getProperty("lttngUstLogAppender.enabled", "false"));

        if (lttngUstLogAppendercheck){
            //try {
                Logger logger_lttng = Logger.getLogger("lttngUstLogAppender");
                //Appender lttngUstLogAppender = new LttngLogAppender();
                // Add the LTTng-UST log appender to our logger
                //logger_lttng.addAppender(lttngUstLogAppender);
                // Log at will!
                //logger_lttng.info(logMessage);
                // Not mandatory, but cleaner
                //logger_lttng.removeAppender(lttngUstLogAppender);
                //lttngUstLogAppender.close();
                   // } catch (IOException e) {
                        //data.put("lttngUstLogAppender", "exeption");
                        //logData("Jello", data);
                // Handle exception here
            //}
        }
        else {
            logger_log4j.info(logMessage);
        }
        
    }

    private String formatLogMessage(String eventType, Map<String, Object> data) {
        // Implement the logic to format the log message based on eventType and data
        // This is just a placeholder, you need to implement it according to your requirements
        StringBuilder sb = new StringBuilder();
        // Append eventType at the end of the log message
        sb.append("eventType=").append(eventType).append("; ");
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("; ");
        }
        // Remove the last semicolon if it exists
        if (sb.length() > 0 && sb.charAt(sb.length() - 2) == ';') {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
