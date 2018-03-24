package ro.usv.rf;


import java.awt.BasicStroke; 


public class MainClass {


   public static void main( String[ ] args ) {
	   
	   double[][] learningSet = FileUtils.readLearningSetFromFile("in.txt");
	    FileUtils.writeLearningSetToFile("out.csv", normalizeLearningSet(learningSet));
	   
   }
   
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
   
   

}
