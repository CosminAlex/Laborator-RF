package ro.usv.rf;


import java.awt.BasicStroke;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import usv.FileUtils;
import usv.USVInputFileCustomException;




public class MainClass {

	   
	   private static double[][] normalizeLearningSet(double[][] learningSet)  
		 {   
			 double[][] normalizedLearningSet = new double[learningSet.length][];
			 int n = learningSet.length;
			 int m = learningSet[0].length;
			 double[] minn = new double[learningSet.length];
			 double[] maxx = new double[learningSet.length];
		
			 System.out.println(n + " linii si " + m + " coloane");
			 for(int i=0;i<normalizedLearningSet.length;i++)
				 normalizedLearningSet[i] = new double[normalizedLearningSet.length];
			 for(int i=0;i<n;i++) {
				 System.out.println();
				 for(int j=0;j<m;j++)
					 System.out.print(learningSet[i][j]+" ");
			 }
			 for(int i=0;i<m;i++)
			 {
				 double min = 999999;
				 double max = -999999;
				 for(int j=0;j<n;j++)
				 {
					 if(learningSet[j][i] < min)
						 min = learningSet[j][i];
					 if(learningSet[j][i] > max)
						 max = learningSet[j][i];
				 }
				 minn[i] = min;
				 maxx[i] = max;
			 }
			 
			 for(int i=0;i<m;i++)
				 for(int j=0; j<n;j++)
					 normalizedLearningSet[j][i] = (learningSet[j][i] - minn[i]) / (maxx[i] - minn[i]);
			 System.out.println("Matricea normalizata este:");
			 for(int i=0;i<n;i++) {
				 System.out.println();
				 for(int j=0;j<m;j++)
					 System.out.print(normalizedLearningSet[i][j]+" ");
			 }
			 
			 return normalizedLearningSet;  
		 }
	   
	   private static double[][] autoscaleLearningSet(double[][] learningSet, double[] featureWeightedAverages) {
			double[][] autoscaledLearningSet = new double[learningSet.length][];
			int n = learningSet.length;
			int m = learningSet[0].length - 1;
			for(int i=0;i<n;i++)
				autoscaledLearningSet[i] = new double[m];
			for(int j=0;j<m;j++)
			{
				double partial_sum = 0;
				for(int i=0;i<n;i++)
					partial_sum += Math.pow(learningSet[j][i] - featureWeightedAverages[j], 2);
				for(int i=0;i<n;i++)
					autoscaledLearningSet[i][j] = (learningSet[j][i] - featureWeightedAverages[j])/partial_sum;
			}
			return autoscaledLearningSet;
		}
	   
   public static void main( String[ ] args ) {
	   
	   double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
	    FileUtils.writeLearningSetToFile("out.csv", normalizeLearningSet(learningSet));
	    
	    int numberOfForms = learningSet.length;
		int numberOfFeatures = learningSet[0].length;
	    //LAb2 b)
	    int n = learningSet.length;
		int m = learningSet[0].length;
	    for (int j = 0; j < m; j++) {
			Double[] feature = new Double[n];
			for (int i = 0; i < n; i++) {
				feature[i] = learningSet[i][j];
			}
			double featureAverage = StatisticsUtils.calculateFeatureAverage(feature);
			System.out.println("Feature average is : " + featureAverage);
		}
	 // get weights from matrix - only after you add the new line !!!
	 		// since the last column represent the weights, the number of features is now
	 		// learningSet[0].length - 1
	 		numberOfFeatures = learningSet[0].length - 1;

	 		Double[] weights = new Double[numberOfForms];
	 		for (int formIndex = 0; formIndex < numberOfForms; formIndex++) {
	 			// weights are always located on the last column
	 			int weightColumnIndex = learningSet[formIndex].length - 1;
	 			weights[formIndex] = learningSet[formIndex][weightColumnIndex];
	 		}
	 // calculate Feature Weighted Average and feature dispersion
	 		List<Double[]> featureList = new ArrayList<Double[]>();
	 		double[] featureWeightedAverages = new double[numberOfFeatures];
	 		double[] featureDispersions = new double[numberOfFeatures];
	 		double[] averageSquareDeviations = new double[numberOfFeatures];
	 		for (int featureIndex = 0; featureIndex < numberOfFeatures; featureIndex++) {

	 			Double[] feature = new Double[numberOfForms];
	 			for (int formIndex = 0; formIndex < numberOfForms; formIndex++) {
	 				feature[formIndex] = learningSet[formIndex][featureIndex];
	 			}
	 			featureList.add(feature);

	 			featureWeightedAverages[featureIndex] = StatisticsUtils.calculateFeatureWeightedAverage(feature, weights);
	 			System.out.println("Feature weighted average is : " + featureWeightedAverages[featureIndex]);

	 			featureDispersions[featureIndex] = StatisticsUtils.calculateFeatureDispersion(feature,featureWeightedAverages[featureIndex]);
	 			System.out.println("Feature dispersion is : " + featureDispersions[featureIndex]);

	 			averageSquareDeviations[featureIndex] = StatisticsUtils.calculateAverageSquareDeviation(featureDispersions[featureIndex]);
	 			System.out.println("average Square Deviations is : " + averageSquareDeviations[featureIndex]);
	 		}

	 		// we select a feature and an element to calculate frequency of occurence

	 		int featureIndex = 0;
	 		int elementIndex = 2;
	 		Map<Double, Integer> featureDistincElementsCounterMap = StatisticsUtils
	 				.getFeatureDistincElementsCounterMap(featureList.get(featureIndex));
	 		double frequencyOfOccurence = StatisticsUtils.calculateFrequencyOfOccurence(featureDistincElementsCounterMap,
	 				learningSet[elementIndex][featureIndex]);
	 		System.out.println("frequency Of Occurence is : " + frequencyOfOccurence);

	 		// we select two features to calculate covariance and corelation
	 		int feature1Index = 0;
	 		int feature2Index = 1;

	 		double covariance = StatisticsUtils.calculateCovariance(featureList.get(feature1Index),
	 				featureList.get(feature2Index), featureWeightedAverages[feature1Index], featureWeightedAverages[1]);
	 		System.out.println("covariance is : " + covariance);

	 		double corelation = StatisticsUtils.calculateCorrelationCoefficient(covariance,
	 				featureDispersions[feature1Index], featureDispersions[feature2Index]);
	 		System.out.println("corelation is : " + corelation);

	 		//FileUtils.writeLearningSetToFile("scaledSet.csv", autoscaleLearningSet(learningSet, featureWeightedAverages));
	    
	 		//Lab3
	 		System.out.println("Laboratorul 3");
	 		int numberOfPatterns = learningSet.length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
	 		USVInputFileCustomException.Euclidan(learningSet,numberOfPatterns,numberOfFeatures);
			USVInputFileCustomException.Cebisev3(learningSet,numberOfPatterns,numberOfFeatures);
			USVInputFileCustomException.CityBlock2(learningSet,numberOfPatterns,numberOfFeatures);
			USVInputFileCustomException.Mahalanobis2(learningSet,numberOfPatterns,numberOfFeatures);
			
			//Laborator4
			System.out.println("LAB--4");
			try {
				learningSet = FileUtils.readLearningSetFromFile("in.txt");
				int numberOfPatterns1 = learningSet.length;
				int numberOfFeatures1 = learningSet[0].length -1;
				System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns1,
						numberOfFeatures1));
				double[][] matrix = new double[numberOfPatterns1][numberOfPatterns1];
				// USVInputFileCustomException.calculateEuclidian(learningSet[0],learningSet[1]);
				int counter = 0;
				DecimalFormat df = new DecimalFormat("#.##");
				for (int i = 0; i < numberOfPatterns1; i++) {
					for (int j = i+1; j < numberOfPatterns1; j++) {
						double euclidianDistance = USVInputFileCustomException.Euclidian1(learningSet[i], learningSet[j]);
						counter++;
						matrix[i][j] = euclidianDistance;
						matrix[j][i] = euclidianDistance;

					}

				}
				System.out.println(" --- > " + counter);
				for (int i = 0; i < numberOfPatterns1; i++) {
					for (int j = 0; j < numberOfPatterns1; j++) {
						double value = Math.floor(matrix[i][j] * 100) / 100;
						System.out.print(value + " ");

					}
					System.out.println();
				}
				System.out.println("\n\n");
				double minDistance = Double.MAX_VALUE;
				int lineToCalculateClass = learningSet.length-1;
				int line=-1;
				for (int j = 0; j<numberOfPatterns1; j++)
				{if (j == lineToCalculateClass)
					{
						continue;
					}
					if(matrix[lineToCalculateClass][j] < minDistance )
					{
						minDistance= matrix[lineToCalculateClass][j];
						line= j;
					
					}
					
				}
				System.out.println("Class "+minDistance);
				System.out.println("Class "+learningSet[line][learningSet[0].length -1]);
				
			} catch (USVInputFileCustomException e) {
				System.out.println(e.getMessage());
			} finally {
				System.out.println("Finished learning set operations");
			}
		
   }
   
}
