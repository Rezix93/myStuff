/*******************************************************************************
 * Copyright (c) 2015, 2016 EfficiOS Inc., Ericsson
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.analysis.os.linux.core.model.OsStrings;
import org.eclipse.tracecompass.analysis.os.linux.core.trace.IKernelAnalysisEventLayout;
import org.eclipse.tracecompass.analysis.timing.core.segmentstore.AbstractSegmentStoreAnalysisEventBasedModule;
import org.eclipse.tracecompass.analysis.timing.core.segmentstore.IGroupingSegmentAspect;
import org.eclipse.tracecompass.datastore.core.interval.IHTIntervalReader;
import org.eclipse.tracecompass.internal.analysis.os.linux.core.SyscallLookup;
import org.eclipse.tracecompass.segmentstore.core.ISegment;
import org.eclipse.tracecompass.segmentstore.core.ISegmentStore;
import org.eclipse.tracecompass.segmentstore.core.SegmentComparators;
import org.eclipse.tracecompass.segmentstore.core.SegmentStoreFactory.SegmentStoreType;
import org.eclipse.tracecompass.tmf.core.analysis.IAnalysisModule;
import org.eclipse.tracecompass.tmf.core.event.ITmfEvent;
import org.eclipse.tracecompass.tmf.core.event.ITmfEventField;
import org.eclipse.tracecompass.tmf.core.event.lookup.TmfCallsite;
import org.eclipse.tracecompass.tmf.core.segment.ISegmentAspect;

import com.google.common.collect.ImmutableList;

 /**
  * @author Alexandre Montplaisir
  * @since 2.0
  */
 public class SparkScatterAnalysisModule extends AbstractSegmentStoreAnalysisEventBasedModule {

     /**
      * The ID of this analysis
      */
     public static final String ID = "org.eclipse.tracecompass.analysis.os.linux.latency.sparkanalysisscatter"; //$NON-NLS-1$
     private static final String RET_FIELD = "ret"; //$NON-NLS-1$
     private static final int VERSION = 1;

     private static final Collection<ISegmentAspect> BASE_ASPECTS = ImmutableList.of(SyscallNameAspect.INSTANCE, SyscallTidAspect.INSTANCE, SyscallRetAspect.INSTANCE, SyscallComponentAspect.INSTANCE, SyscallFileAspect.INSTANCE);


     /*else if (transformations.contains(stageName)) {
         System.out.println(stageName + " is a transformation.");
     } else {
         System.out.println(stageName + " is not recognized as an action or transformation.");
     }*/

      /**
      * Constructor
      */
     public SparkScatterAnalysisModule() {
         // do nothing
     }

     @Override
     public String getId() {
         return ID;
     }

     @Override
     protected Iterable<IAnalysisModule> getDependentAnalyses() {
         /*ITmfTrace trace = getTrace();
         if (trace == null) {
             throw new IllegalStateException();
         }
         IAnalysisModule module = trace.getAnalysisModule(TidAnalysisModule.ID);
         SparkAnalysisModule module2 = TmfTraceUtils.getAnalysisModuleOfClass(trace, SparkAnalysisModule.class, "mysparkanalysis"); //$NON-NLS-1$
         if (module2 != null) {
             return ImmutableSet.of(module2);
         }
         if (module == null) {
             return Collections.emptySet();
         }
         return ImmutableSet.of(module);*/
         return Collections.emptySet();
     }

     @Override
     public Iterable<ISegmentAspect> getSegmentAspects() {
         return BASE_ASPECTS;
     }

     @Override
     protected int getVersion() {
         return VERSION;
     }

     @Override
     protected @NonNull SegmentStoreType getSegmentStoreType() {
         return SegmentStoreType.OnDisk;
     }

     @Override
     protected AbstractSegmentStoreAnalysisRequest createAnalysisRequest(ISegmentStore<ISegment> syscalls, IProgressMonitor monitor) {
         return new SparkDurationTaskAnalysisRequest(syscalls, monitor);
     }

     @Override
     protected @NonNull IHTIntervalReader<ISegment> getSegmentReader() {
         return SparkSegment.READER;
     }

     private class SparkDurationTaskAnalysisRequest extends AbstractSegmentStoreAnalysisRequest {

         private final Map<Integer, SparkSegment.InitialInfo> fOngoingSystemCalls = new HashMap<>();
         private @Nullable IKernelAnalysisEventLayout fLayout;
         private final IProgressMonitor fMonitor;


         public SparkDurationTaskAnalysisRequest(ISegmentStore<ISegment> events, IProgressMonitor monitor) {
             super(events);
             fMonitor = monitor;
         }

         @Override
         public void handleData(final ITmfEvent event) {
             super.handleData(event);

          // Now you can access the data in jsonObject
          // HashMap to store all variables and their values
             HashMap<String, String> variables = new HashMap<>();
             final ITmfEventField content = event.getContent();
             final String msgContent = content.getFieldValue(String.class, "msg");
             if (msgContent == null) {
                 return;
             }
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
             String taskDuration = variables.get("taskDuration");
             //String startTime = variables.get("taskStartTime");
             long time = event.getTimestamp().toNanos();
             String taskId = variables.get("taskId"); // //$NON-NLS-1$
             //String taskStartTime = variables.get("launchTime");

             int taskDurationInt = -1; // Initialize taskDurationInt as an integer
             int taskIdInt = 0; // Initialize taskId as an integer
             //Long taskStartTimeLong; //Initialize taskStartTime as an long

             String stageId = variables.get("stageId"); // //$NON-NLS-1$
             String stageSubmissionTime = variables.get("stageSubmissionTime"); // //$NON-NLS-1$
             String stageCompletionTime = variables.get("stageCompletionTime"); // //$NON-NLS-1$
             String stageName = variables.get("stageName"); // //$NON-NLS-1$

             int stageIdInt;
             int stageDuration;

             try {
                 SparkSegment.InitialInfo timeMeanInfo = new SparkSegment.InitialInfo(time, "average", 1);
                 SparkSegment meanSegment = new SparkSegment(timeMeanInfo , 10);
                 getSegmentStore().add(meanSegment);
             } catch(NumberFormatException e) {

             }
             if ("TaskEnd".equals(eventType)) {
                 try {
                     taskIdInt = Integer.parseInt(taskId); // Attempt to parse taskId as an integer
                     //taskStartTimeLong = Long.parseLong(taskStartTime) * 1000000;
                     taskDurationInt = Integer.parseInt(taskDuration);
                     SparkSegment.InitialInfo taskInfo = new SparkSegment.InitialInfo(time, eventType.intern(), taskIdInt);
                     SparkSegment taskSegment = new SparkSegment(taskInfo , taskDurationInt);
                     getSegmentStore().add(taskSegment);
                 } catch (NumberFormatException e) {
                     System.err.println("Error parsing. taskId: '" + taskId + "' as integer: " + e.getMessage());
                     // Handle the error or skip processing this event
                 }
             }
             if ("StageCompleted".equals(eventType)) {
                 try {
                     //String stageName = "reduce at MLUtils.scala:94";
                     String stageOperation = stageName.split(" ")[0]; // Extracts "reduce"
                     //boolean isAction = SparkStageTypeChecker.isAction(operation);
                     //boolean isTransformation = SparkStageTypeChecker.isTransformation(operation);
                     String operationType = SparkStageTypeChecker.operationType(stageOperation);
                     if (operationType == "Other") {
                        System.out.println("operationType: " + " :: " + stageName);
                    }
                     stageIdInt = Integer.parseInt(stageId); // Attempt to parse taskId as an integer
                     stageSubmissionTime = stageSubmissionTime.replace("Some(", "").replace(")", ""); // Removing "Some(" and ")"
                     stageCompletionTime = stageCompletionTime.replace("Some(", "").replace(")", ""); // Removing "Some(" and ")"
                     stageDuration = (int) (Long.parseLong(stageCompletionTime) - Long.parseLong(stageSubmissionTime));

                     SparkSegment.InitialInfo stageinfo = new SparkSegment.InitialInfo(time, "stage " + operationType.intern(), stageIdInt);
                     SparkSegment stageSegment = new SparkSegment(stageinfo , stageDuration);
                     getSegmentStore().add(stageSegment);


                     SparkSegment.InitialInfo stageOperationInfo = new SparkSegment.InitialInfo(time, stageOperation, stageIdInt);
                     SparkSegment stageOperationSegment = new SparkSegment(stageOperationInfo , stageDuration);
                     getSegmentStore().add(stageOperationSegment);


                 } catch (NumberFormatException e) {
                     System.err.println("Error parsing" + stageSubmissionTime + " :: "  +  stageCompletionTime +  e.getMessage());
                     // Handle the error or skip processing this event
                 }
             }
         }

         @Override
         public void handleCompleted() {
             fOngoingSystemCalls.clear();
             super.handleCompleted();
         }

         @Override
         public void handleCancel() {
             fMonitor.setCanceled(true);
             super.handleCancel();
         }
     }

     private static final class SyscallNameAspect implements ISegmentAspect {
         public static final ISegmentAspect INSTANCE = new SyscallNameAspect();

         private SyscallNameAspect() {
             // Do nothing
         }

         @Override
         public String getHelpText() {
             return Messages.getMessage(Messages.SegmentAspectHelpText_SystemCall);
         }

         @Override
         public String getName() {
             return Messages.getMessage(Messages.SegmentAspectName_SystemCall);
         }

         @Override
         public @Nullable Comparator<?> getComparator() {
             return (ISegment segment1, ISegment segment2) -> {
                 if (segment1 == null) {
                     return 1;
                 }
                 if (segment2 == null) {
                     return -1;
                 }
                 if (segment1 instanceof SparkSegment && segment2 instanceof SparkSegment) {
                     int res = ((SparkSegment) segment1).getName().compareTo(((SparkSegment) segment2).getName());
                     return (res != 0 ? res : SegmentComparators.INTERVAL_START_COMPARATOR.thenComparing(SegmentComparators.INTERVAL_END_COMPARATOR).compare(segment1, segment2));
                 }
                 return 1;
             };
         }

         @Override
         public @Nullable String resolve(ISegment segment) {
             if (segment instanceof SparkSegment) {
                 return ((SparkSegment) segment).getName();
             }
             return EMPTY_STRING;
         }
     }

     private static final class SyscallTidAspect implements ISegmentAspect {
         public static final ISegmentAspect INSTANCE = new SyscallTidAspect();

         private SyscallTidAspect() {
             // Do nothing
         }

         @Override
         public String getHelpText() {
             return Messages.getMessage(Messages.SegmentAspectHelpText_SystemCallTid);
         }

         @Override
         public String getName() {
             return OsStrings.tid();
         }

         @Override
         public @Nullable Comparator<?> getComparator() {
             return (ISegment segment1, ISegment segment2) -> {
                 if (segment1 == null) {
                     return 1;
                 }
                 if (segment2 == null) {
                     return -1;
                 }
                 if (segment1 instanceof SparkSegment && segment2 instanceof SparkSegment) {
                     int res = Integer.compare(((SparkSegment) segment1).getTid(), ((SparkSegment) segment2).getTid());
                     return (res != 0 ? res : SegmentComparators.INTERVAL_START_COMPARATOR.thenComparing(SegmentComparators.INTERVAL_END_COMPARATOR).compare(segment1, segment2));
                 }
                 return 1;
             };
         }

         @Override
         public @Nullable Integer resolve(ISegment segment) {
             if (segment instanceof SparkSegment) {
                 return ((SparkSegment) segment).getTid();
             }
             return -1;
         }
     }

     private static final class SyscallComponentAspect implements IGroupingSegmentAspect {
         public static final ISegmentAspect INSTANCE = new SyscallComponentAspect();

         private SyscallComponentAspect() {
             // Do nothing
         }

         @Override
         public String getName() {
             return Messages.getMessage(Messages.SystemCallLatencyAnalysis_componentName);
         }

         @Override
         public String getHelpText() {
             return Messages.getMessage(Messages.SystemCallLatencyAnalysis_componentDescription);
         }

         @Override
         public @Nullable Comparator<?> getComparator() {
             return (ISegment segment1, ISegment segment2) -> {
                 if (segment1 == null) {
                     return 1;
                 }
                 if (segment2 == null) {
                     return -1;
                 }
                 if (segment1 instanceof SparkSegment && segment2 instanceof SparkSegment) {
                     int res = SyscallLookup.getInstance().getComponent(((SparkSegment) segment1).getName()).compareTo(SyscallLookup.getInstance().getComponent(((SparkSegment) segment2).getName()));
                     return (res != 0 ? res : SegmentComparators.INTERVAL_START_COMPARATOR.thenComparing(SegmentComparators.INTERVAL_END_COMPARATOR).compare(segment1, segment2));
                 }
                 return 1;
             };
         }

         @Override
         public @Nullable Object resolve(ISegment segment) {
             if (segment instanceof SparkSegment) {
                 return SyscallLookup.getInstance().getComponent(((SparkSegment) segment).getName());
             }
             return EMPTY_STRING;
         }
     }

     private static final class SyscallRetAspect implements ISegmentAspect {
         public static final ISegmentAspect INSTANCE = new SyscallRetAspect();

         private SyscallRetAspect() {
             // Do nothing
         }

         @Override
         public String getHelpText() {
             return Messages.getMessage(Messages.SegmentAspectHelpText_SystemCallRet);
         }

         @Override
         public String getName() {
             return Messages.getMessage(Messages.SegmentAspectName_SystemCallRet);
         }

         @Override
         public @Nullable Object resolve(ISegment segment) {
             if (segment instanceof SparkSegment) {
                 return ((SparkSegment) segment).getReturnValue();
             }
             return EMPTY_STRING;
         }

         @Override
         public @Nullable Comparator<?> getComparator() {
             return (ISegment segment1, ISegment segment2) -> {
                 if (segment1 == null) {
                     return 1;
                 }
                 if (segment2 == null) {
                     return -1;
                 }
                 if (segment1 instanceof SparkSegment && segment2 instanceof SparkSegment) {
                     int res = Integer.compare(((SparkSegment) segment1).getReturnValue(), ((SparkSegment) segment2).getReturnValue());
                     return (res != 0 ? res : SegmentComparators.INTERVAL_START_COMPARATOR.thenComparing(SegmentComparators.INTERVAL_END_COMPARATOR).compare(segment1, segment2));
                 }
                 return 1;
             };
         }

     }

     private static final class SyscallFileAspect implements ISegmentAspect {
         public static final ISegmentAspect INSTANCE = new SyscallFileAspect();

         private SyscallFileAspect() {
             // Do nothing
         }

         @Override
         public String getName() {
             return Messages.getMessage(Messages.SystemCallLatencyAnalysis_fileName);
         }

         @Override
         public String getHelpText() {
             return Messages.getMessage(Messages.SystemCallLatencyAnalysis_fileDescription);
         }

         @Override
         public @Nullable Comparator<?> getComparator() {
             return (ISegment segment1, ISegment segment2) -> {
                 if (segment1 == null) {
                     return 1;
                 }
                 if (segment2 == null) {
                     return -1;
                 }
                 if (segment1 instanceof SparkSegment && segment2 instanceof SparkSegment) {
                     int res = SyscallLookup.getInstance().getFile(((SparkSegment) segment1).getName()).compareTo(SyscallLookup.getInstance().getFile(((SparkSegment) segment2).getName()));
                     return (res != 0 ? res : SegmentComparators.INTERVAL_START_COMPARATOR.thenComparing(SegmentComparators.INTERVAL_END_COMPARATOR).compare(segment1, segment2));
                 }
                 return 1;
             };
         }

         @Override
         public @Nullable Object resolve(ISegment segment) {
             if (segment instanceof SparkSegment) {
                 return SyscallLookup.getInstance().getFile(((SparkSegment) segment).getName());
             }
             return EMPTY_STRING;
         }
     }

     /**
      * Callsite aspect for system calls
      */
     public static final class SyscallCallsiteAspect implements ISegmentAspect {

         /**
          * Instance
          */
         public static final ISegmentAspect INSTANCE = new SyscallCallsiteAspect();

         private SyscallCallsiteAspect() {
             // Do nothing
         }

         @Override
         public String getName() {
             return Messages.getMessage(Messages.SystemCallLatencyAnalysis_sourceLookupName);
         }

         @Override
         public String getHelpText() {
             return Messages.getMessage(Messages.SystemCallLatencyAnalysis_sourceLookupDescription);
         }

         @Override
         public @Nullable Comparator<?> getComparator() {
             return null;
         }

         @Override
         public @Nullable Object resolve(ISegment segment) {
             String file = (String) SyscallFileAspect.INSTANCE.resolve(segment);
             if (file == null || file.isEmpty()) {
                 return null;
             }
             return new TmfCallsite(file, 0L);
         }

     }
 }
