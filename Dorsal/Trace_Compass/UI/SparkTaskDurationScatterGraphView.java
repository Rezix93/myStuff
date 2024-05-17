package org.eclipse.tracecompass.incubator.internal.spark_test1.ui.views;

import static org.eclipse.tracecompass.common.core.NonNullUtils.nullToEmptyString;

import java.util.Objects;


import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.tracecompass.analysis.timing.ui.views.segmentstore.scatter.AbstractSegmentStoreScatterChartTreeViewer2;
import org.eclipse.tracecompass.common.core.NonNullUtils;
//import org.eclipse.tracecompass.incubator.internal.scripting.ui.views.xychart.ScriptedXYTreeViewer;
//import org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis.TaskXYDataProviderFactory;
import org.eclipse.tracecompass.tmf.ui.viewers.TmfViewer;
import org.eclipse.tracecompass.tmf.ui.viewers.xychart.TmfXYChartViewer;
//import org.eclipse.tracecompass.tmf.ui.viewers.xychart.linechart.TmfFilteredXYChartViewer;
//import org.eclipse.tracecompass.tmf.ui.viewers.xychart.linechart.TmfXYChartSettings;
import org.eclipse.tracecompass.tmf.ui.views.xychart.TmfChartView;

import org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis.SparkScatterAnalysisModule;


public class SparkTaskDurationScatterGraphView extends TmfChartView {
 // Attributes
    // ------------------------------------------------------------------------

    /** The view's ID */
    public static final String ID = "org.eclipse.tracecompass.incubator.spark_test1.ui.sparkviewscatter"; //$NON-NLS-1$

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    /**
     * Constructor
     */
    public SparkTaskDurationScatterGraphView() {
        super(ID);
    }

    // ------------------------------------------------------------------------
    // ViewPart
    // ------------------------------------------------------------------------

    @Override
    protected TmfXYChartViewer createChartViewer(@Nullable Composite parent) {

        return new SparkTaskDurationScatterGraphViewer(NonNullUtils.checkNotNull(parent),
                nullToEmptyString(Messages.SparkDurationTaskScatterView_title),
                nullToEmptyString(Messages.SparkDurationTaskScatterView_xAxis),
                nullToEmptyString(Messages.SparkDurationTaskScatterView_yAxis)
                );
    }

    @Override
    protected @NonNull TmfViewer createLeftChildViewer(@Nullable Composite parent) {
        return new AbstractSegmentStoreScatterChartTreeViewer2(Objects.requireNonNull(parent), SparkScatterAnalysisModule.ID);
    }

}