package org.eclipse.tracecompass.incubator.internal.spark_test1.ui.views;

import java.util.Objects;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.tracecompass.incubator.internal.scripting.ui.views.xychart.ScriptedXYTreeViewer;
import org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis.TaskXYDataProviderFactory;
import org.eclipse.tracecompass.tmf.ui.viewers.TmfViewer;
import org.eclipse.tracecompass.tmf.ui.viewers.xychart.TmfXYChartViewer;
import org.eclipse.tracecompass.tmf.ui.viewers.xychart.linechart.TmfFilteredXYChartViewer;
import org.eclipse.tracecompass.tmf.ui.viewers.xychart.linechart.TmfXYChartSettings;
import org.eclipse.tracecompass.tmf.ui.views.xychart.TmfChartView;

public class TaskPerExecutorView extends TmfChartView {
    /** The view's ID */
    public static final String ID = "org.eclipse.tracecompass.incubator.spark_test1.ui.runningTasks"; //$NON-NLS-1$


    /**
     * Default constructor
     */
    public TaskPerExecutorView() {
        super("Tasks Per Executor"); //$NON-NLS-1$
    }

    @Override
    public void createPartControl(@Nullable Composite parent) {
        super.createPartControl(parent);
        setPartName(getDataProviderID());
    }

    @Override
    protected TmfXYChartViewer createChartViewer(@Nullable Composite parent) {
        TmfXYChartSettings settings = new TmfXYChartSettings("Tasks Per Executor", //$NON-NLS-1$
                "TIME", "Number of tasks running", 1); //$NON-NLS-1$ //$NON-NLS-2$
        return new TmfFilteredXYChartViewer(parent, settings, getDataProviderID());
    }

    @Override
    protected TmfViewer createLeftChildViewer(@Nullable Composite parent) {
        return new ScriptedXYTreeViewer(Objects.requireNonNull(parent), getDataProviderID());
    }

    private static String getDataProviderID() {
        return TaskXYDataProviderFactory.ID + ":1";
    }

}