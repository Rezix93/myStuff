package org.eclipse.tracecompass.incubator.internal.spark_test1.core.analysis;

import java.util.Set;

public class SparkStageTypeChecker {


    public static boolean isAction(String stageName) {
        final Set<String> actions = Set.of("reduce", "collect", "count", "first", "take", "takeSample", "takeOrdered", "saveAsTextFile", "saveAsSequenceFile", "saveAsObjectFile", "countByKey", "foreach", "max", "sum", "mean", "stdev", "aggregate");

        return actions.stream().anyMatch(stageName::contains);
    }

    public static boolean isTransformation(String stageName) {
        final Set<String> transformations = Set.of("map", "filter", "flatMap", "mapPartitions", "mapPartitionsWithIndex", "sample", "union", "intersection", "distinct", "groupByKey", "reduceByKey", "aggregateByKey", "sortByKey", "join", "cogroup", "cartesian", "pipe", "coalesce", "repartition", "repartitionAndSortWithinPartitions");
        return transformations.stream().anyMatch(stageName::contains);
    }

    public static String operationType(String operation) {
        String result = "Other";
        if (isAction(operation)) {
            result = "Action";
        }
        if (isTransformation(operation)) {
            result = "Transormation";
        }
        return result;
    }
}
