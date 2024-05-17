package org.eclipse.tracecompass.incubator.internal.spark_test1.ui.views;


import java.util.Collections;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis.TaskFlowProvider;
import org.eclipse.tracecompass.internal.provisional.tmf.ui.widgets.timegraph.BaseDataProviderTimeGraphPresentationProvider;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.ui.views.timegraph.BaseDataProviderTimeGraphView;

public class SparkTimeFlowView extends BaseDataProviderTimeGraphView {


    public static final String ID = "org.eclipse.tracecompass.incubator.spark_test1.ui.timeflow"; //$NON-NLS-1$
    /**
     * Default constructor
     */
    public SparkTimeFlowView() {
       super(ID, new BaseDataProviderTimeGraphPresentationProvider(), TaskFlowProvider.ID);
    }

    /**
     * Constructs a SparkTimeFlowView instance.
     *
     * @param id the ID of the view
     * @param pres the presentation provider for the time graph
     * @param providerId the ID for the data provider to use to populate this view
     */
    @Override
    protected @NonNull Iterable<ITmfTrace> getTracesToBuild(@Nullable ITmfTrace trace) {
        return Collections.singletonList(trace);
    }

}
