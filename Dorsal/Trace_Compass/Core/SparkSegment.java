package org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.analysis.os.linux.core.model.OsStrings;
import org.eclipse.tracecompass.datastore.core.interval.IHTIntervalReader;
import org.eclipse.tracecompass.datastore.core.serialization.ISafeByteBufferWriter;
import org.eclipse.tracecompass.datastore.core.serialization.SafeByteBufferFactory;
import org.eclipse.tracecompass.internal.analysis.os.linux.core.latency.SystemCallLatencyAnalysis;
import org.eclipse.tracecompass.segmentstore.core.ISegment;
import org.eclipse.tracecompass.segmentstore.core.segment.interfaces.INamedSegment;
import org.eclipse.tracecompass.tmf.core.event.lookup.ITmfCallsite;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class SparkSegment implements INamedSegment {

    private static final long serialVersionUID = 1922703039441359595L;


    /**
     * The reader for this segment class
     */
    public static final IHTIntervalReader<ISegment> READER = buffer ->
    new SparkSegment(
            buffer.getLong(),
            buffer.getString(),
            buffer.getInt(),
            buffer.getInt());

    /**
     * The subset of information that is available from the Spark entry event.
     */
    public static class InitialInfo {

        private long eventStartTime;
        private String eventName;
        private int eventTaskid;

        /**
         * @param eventStartTime
         *            Start time of the event
         * @param eventName
         *            Name of the evnt
         * @param eventTaskid
         *            The Task Id of the event
         */
        public InitialInfo(
                long startTime,
                String name,
                int tid) {
            eventStartTime = startTime;
            eventName = name.intern();
            eventTaskid = tid;
        }
    }

    private final long eventStartTime;
    private final String eventName;
    private final int eventTaskid;
    private final int eventTaskDuration;
    /**
     * @param info
     *            Initial information of the system call
     * @param endTime
     *            End time of the system call
     * @param ret
     *            The return value of the system call
     */
    public SparkSegment(
            InitialInfo info,
            int duration) {
        eventStartTime = info.eventStartTime;
        eventName = info.eventName;
        eventTaskid = info.eventTaskid;
        eventTaskDuration = duration;
    }

    private SparkSegment(long startTime, String name, int tid, int duration) {
        eventStartTime = startTime;
        eventName = name;
        eventTaskid = tid;
        eventTaskDuration = duration;
    }
    @Override
    public long getStart() {
        return eventStartTime;
    }

    @Override
    public String getName() {
        return eventName;
    }
    @Override
    public long getLength() {
        return eventTaskDuration;
    }

    /**
     * Get the Task ID for this event
     *
     * @return The ID of the Task
     */
    public int getTid() {
        return eventTaskid;
    }

    /**
     * Get the return value of the Task. Right now i put task duration here
     *
     * @return The return value of this Task
     */
    public int getReturnValue() {
        return 100;
    }

    @Override
    public int getSizeOnDisk() {
        return 2 * Long.BYTES + SafeByteBufferFactory.getStringSizeInBuffer(eventName) + 2 * Integer.BYTES;
    }

    @Override
    public void writeSegment(ISafeByteBufferWriter buffer) {
        buffer.putLong(eventStartTime);
        buffer.putString(eventName);
        buffer.putInt(eventTaskid);
        buffer.putInt(eventTaskDuration);
    }
    @Override
    public int compareTo(ISegment o) {
        int ret = INamedSegment.super.compareTo(o);
        if (ret != 0) {
            return ret;
        }
        return toString().compareTo(o.toString());
    }
    @Override
    public String toString() {
        return "Start Time = " + getStart() + //$NON-NLS-1$
                "; Duration = " + getLength() + //$NON-NLS-1$
                "; Name = " + getName(); //$NON-NLS-1$
    }

    public @Nullable ITmfCallsite getCallsite() {
        return (ITmfCallsite) SystemCallLatencyAnalysis.SyscallCallsiteAspect.INSTANCE.resolve(this);
    }

    public Multimap<String, Object> getMetadata() {
        Multimap<String, Object> map = HashMultimap.create();
        map.put(OsStrings.tid(), eventTaskid);
        return map;
    }

    @Override
    public long getEnd() {
        // TODO Auto-generated method stub
        return eventStartTime;
    }
}
