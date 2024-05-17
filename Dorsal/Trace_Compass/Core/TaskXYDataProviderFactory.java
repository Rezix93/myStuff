package org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.internal.tmf.analysis.xml.core.fsm.model.DataDrivenStateSystemPath;
import org.eclipse.tracecompass.internal.tmf.analysis.xml.core.output.DataDrivenOutputEntry;
import org.eclipse.tracecompass.internal.tmf.analysis.xml.core.output.DataDrivenXYDataProvider.DisplayType;
import org.eclipse.tracecompass.internal.tmf.analysis.xml.core.output.DataDrivenXYProviderFactory;
import org.eclipse.tracecompass.statesystem.core.ITmfStateSystem;
import org.eclipse.tracecompass.tmf.core.dataprovider.IDataProviderFactory;
import org.eclipse.tracecompass.tmf.core.model.tree.ITmfTreeDataModel;
import org.eclipse.tracecompass.tmf.core.model.tree.ITmfTreeDataProvider;
import org.eclipse.tracecompass.tmf.core.model.xy.ITmfTreeXYDataProvider;
import org.eclipse.tracecompass.tmf.core.model.xy.TmfTreeXYCompositeDataProvider;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.core.trace.TmfTraceManager;
import org.eclipse.tracecompass.tmf.core.trace.TmfTraceUtils;

public class TaskXYDataProviderFactory implements IDataProviderFactory {

    public static String ID = "org.eclipse.tracecompass.incubator.spark.taskxy.dataprovider"; //$NON-NLS-1$

    @Override
    public @Nullable ITmfTreeXYDataProvider<? extends ITmfTreeDataModel> createProvider(@NonNull ITmfTrace trace, @NonNull String secondaryId) {
        Collection<ITmfTrace> traces = TmfTraceManager.getTraceSet(trace);
        // traces.add(trace);
        // traces.addAll(traces);
        System.out.println(traces.size());

        if (traces.size() == 1) {
            String StateSystem = "1";
            // TODO Auto-generated method stub

            if (secondaryId.equals("1")) {
                StateSystem = "ExecuterID -> Running Task/*";
            }
            if (secondaryId.equals("2")) {
                StateSystem = "ExecuterID -> Total Task/*";
            }
            if (secondaryId.equals("3")) {
                StateSystem = "Time of Tasks/*";
            }
            SparkAnalysisModule module = TmfTraceUtils.getAnalysisModuleOfClass(trace, SparkAnalysisModule.class, "mysparkanalysis"); //$NON-NLS-1$

            if (module != null) {
                module.schedule();
                ITmfStateSystem stateSystem = module.getStateSystem();
                if (stateSystem == null) {
                    return null;
                }
                DataDrivenStateSystemPath display = new DataDrivenStateSystemPath(Collections.emptyList());
                DisplayType displayType = DisplayType.ABSOLUTE;

                DataDrivenOutputEntry entry = new DataDrivenOutputEntry(Collections.emptyList(), StateSystem, null, true, // $NON-NLS-1$
                        display, null, null, null, displayType);

                ITmfTreeXYDataProvider<@NonNull ITmfTreeDataModel> provider = DataDrivenXYProviderFactory.create(trace, Collections.singletonList(stateSystem), Collections.singletonList(entry), ID + ":" + secondaryId);

                return provider;
            }
        }
        return TmfTreeXYCompositeDataProvider.create(traces, "Reza", ID + ":" + secondaryId);
        // return IDataProviderFactory.super.createProvider(trace, secondaryId);
    }

    private static ITmfTreeDataProvider<? extends ITmfTreeDataModel> createDataProvider(String stateSystemPath, String secondaryId, ITmfStateSystem stateSystem, ITmfTrace trace) {
        DataDrivenStateSystemPath display = new DataDrivenStateSystemPath(Collections.emptyList());
        DisplayType displayType = DisplayType.ABSOLUTE;

        DataDrivenOutputEntry entry = new DataDrivenOutputEntry(Collections.emptyList(), stateSystemPath, null, true, // $NON-NLS-1$
                display, null, null, null, displayType);

        ITmfTreeXYDataProvider<@NonNull ITmfTreeDataModel> provider = DataDrivenXYProviderFactory.create(trace, Collections.singletonList(stateSystem), Collections.singletonList(entry), ID + ":" + secondaryId);

        return provider;
    }

    @Override
    public @Nullable ITmfTreeDataProvider<? extends ITmfTreeDataModel> createProvider(@NonNull ITmfTrace trace) {
        Collection<ITmfTrace> traces = TmfTraceManager.getTraceSet(trace);
        System.out.println("yeah khai!");

        if (traces.size() == 1) {
            System.out.println(" one trace!");
            SparkAnalysisModule module = TmfTraceUtils.getAnalysisModuleOfClass(trace, SparkAnalysisModule.class, "mysparkanalysis"); //$NON-NLS-1$
            if (module != null) {
                module.schedule();
                ITmfStateSystem stateSystem = module.getStateSystem();
                if (stateSystem == null) {
                    return null;
                }
                DataDrivenStateSystemPath display = new DataDrivenStateSystemPath(Collections.emptyList());
                DisplayType displayType = DisplayType.ABSOLUTE;
                DataDrivenOutputEntry entry = new DataDrivenOutputEntry(Collections.emptyList(), "ExecuterID -> Total Task/*", null, true, //$NON-NLS-1$
                        display, null, null, null, displayType);
                ITmfTreeXYDataProvider<@NonNull ITmfTreeDataModel> provider = DataDrivenXYProviderFactory.create(trace, Collections.singletonList(stateSystem), Collections.singletonList(entry), ID);

                return provider;
            }
        }
        System.out.println("yeah traces!");

        return TmfTreeXYCompositeDataProvider.create(traces, "Reza", "mysparkanalysis");
        // return null;
    }

}
