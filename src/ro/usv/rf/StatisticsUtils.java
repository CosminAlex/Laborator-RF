package ro.usv.rf;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.*;

public class StatisticsUtils {
	protected static double calculateFeatureAverage(Double[] feature) {
		/*Map<Double, Integer> counterMap = getFeatureDistincElementsCounterMap(feature);
		double featureAverage = 0;

		double sum1 = 0;
		double sum2 = 0;

		sum1 = counterMap.keySet().stream()
				.mapToDouble(x -> calculateSum1(x, counterMap.get(x))).sum();
		sum2 = counterMap.values().stream()
				.mapToInt(x -> x).sum();
		featureAverage = sum1 / sum2;
		System.out.println("The feature average is: " +  featureAverage);*/
		double featureAverage = 0;
		for(int i=0;i<feature.length;i++)
			System.out.println(feature[i]+" ");
		
		return featureAverage;
}

}
