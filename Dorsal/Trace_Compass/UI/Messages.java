/*******************************************************************************
 * Copyright (c) 2015 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   France Lapointe Nguyen - Initial API and implementation
 *******************************************************************************/

package org.eclipse.tracecompass.incubator.internal.spark_test1.ui.views;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.osgi.util.NLS;

/**
 * @author France Lapointe Nguyen
 */
@NonNullByDefault({})
public class Messages extends NLS {

    private static final String BUNDLE_NAME = "org.eclipse.tracecompass.incubator.internal.spark_test1.ui.views.messages"; //$NON-NLS-1$

    /**
     * Time vs Duration
     */
    public static String SparkDurationTaskScatterView_title;

    /**
     * Time axis
     */
    public static String SparkDurationTaskScatterView_xAxis;

    /**
     * Duration axis
     */
    public static String SparkDurationTaskScatterView_yAxis;

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
