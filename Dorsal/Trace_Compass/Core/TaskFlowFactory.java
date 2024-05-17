package org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis;


import java.util.Collection;
import java.util.Collections;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.tmf.core.dataprovider.IDataProviderDescriptor;
import org.eclipse.tracecompass.tmf.core.dataprovider.IDataProviderDescriptor.ProviderType;
import org.eclipse.tracecompass.tmf.core.dataprovider.IDataProviderFactory;
import org.eclipse.tracecompass.tmf.core.model.DataProviderDescriptor;
import org.eclipse.tracecompass.tmf.core.model.tree.ITmfTreeDataModel;
import org.eclipse.tracecompass.tmf.core.model.tree.ITmfTreeDataProvider;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.core.trace.TmfTraceManager;
import org.eclipse.tracecompass.tmf.core.trace.TmfTraceUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class TaskFlowFactory implements IDataProviderFactory {
    public static final String ID = "org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis.TaskFlowFactory";

    private static final Predicate<? super ITmfTrace> PREDICATE = t -> TmfTraceUtils.getAnalysisModuleOfClass(t, SparkAnalysisModule.class, "mysparkanalysis") != null;

    private static final IDataProviderDescriptor DESCRIPTOR = new DataProviderDescriptor.Builder()
            .setId(TaskFlowProvider.ID)
            .setName("Task Life") //$NON-NLS-1$
            .setDescription("Show Tasks over time") //$NON-NLS-1$
            .setProviderType(ProviderType.TIME_GRAPH)
            .build();

    @Override
    public @Nullable ITmfTreeDataProvider<? extends ITmfTreeDataModel> createProvider(@NonNull ITmfTrace trace) {
        /**
        SparkAnalysisModule module = TmfTraceUtils.getAnalysisModuleOfClass(trace, SparkAnalysisModule.class, "mysparkanalysis"); //$NON-NLS-1$
        //ITmfStateSystem stateSystem = module.getStateSystem();
        System.out.println("reza createProvider");
        if (module != null) {
            module.schedule();
            return new TaskFlowProvider(trace, module);
        }
        return null;
         */
        Collection<@NonNull ITmfTrace> traces = TmfTraceManager.getTraceSet(trace);
        if (traces.size() == 1) {
            return TaskFlowProvider.create(trace);
        }

        return TaskFlowProvider.create(trace);
//        return TmfTreeXYCompositeDataProvider.create(traces, "Example time graph data provider", TaskFlowProvider.ID); //$NON-NLS-1$

    }

    @Override
    public Collection<IDataProviderDescriptor> getDescriptors(@NonNull ITmfTrace trace) {
        System.out.println("reza getDescriptors");

        Collection<ITmfTrace> traces = TmfTraceManager.getTraceSet(trace);
        return Iterables.any(traces, PREDICATE) ? Collections.singletonList(DESCRIPTOR) : Collections.emptyList();
    }

}