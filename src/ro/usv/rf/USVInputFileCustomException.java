package ro.usv.rf;

import java.text.DecimalFormat;

public class USVInputFileCustomException extends RuntimeException  {

    public USVInputFileCustomException(String message) {
    	
        super(message);
    }
    
    protected static void Euclidan(double[][] learningSet, double n, double m)
    {
    	
    	double x, y;
    	double dist;
    	DecimalFormat df = new DecimalFormat("####0.00");
    	/*for(int i=1; i<patterns; i++)//linii
    	{
    		for(int j=0; j<features; j++)//coloane
    		{
    		double dif1=learningSet[0][j]-learningSet[i][j];
    		double dif2= learningSet[0][j+1]-learningSet[i][j+1];
    		dist=Math.sqrt(dif1*dif1+dif2*dif2);
    		System.out.print(dist+" ");
    		}
    		System.out.println();
    	}*/
    	
    	
    	x=learningSet[0][0];
    	y=learningSet[0][1];
    	
    	for(int i=1; i<n; i++)
    	{
    		
    			double dif1=x-learningSet[i][0];
    			double dif2=y-learningSet[i][1];
    			dist=Math.sqrt(dif1*dif1+dif2*dif2);
        		System.out.println("Distanta Euclidiana: "+df.format(dist)+" ");
    	}
    	
    	
     	
    }
   
   public static void Cebisev(double[][] learningSet, int n, int p)
   {
	  /* double[] maxim=p;
	   int j=0;
	   for(int i=0; i<p; i++)
		   maxim[j]=Math.abs(learningSet[0][i]-learningSet[0][i+1]);
	  */
	   double x1=learningSet[0][0];
	   double y1= learningSet[0][1];
	   double x2, y2;
	   double distC;
	   for(int i=1; i<n; i++)
	   {
		   x2 =x1-learningSet[i][0];
		  y2 =y1-learningSet[i][1];
		  distC= Math.max(Math.abs(x1-x2),Math.abs(y1- y2));
		  System.out.println("Distance Cebisev: " + distC);
	   }  
   }
   
   public static void Cebisev2(double[][] learningSet, int p, int f, int a)
   {
	   double x1=learningSet[0][a];
	   double y1= learningSet[0][a+1];
	   double max = Math.abs(x1-y1);
       
       for (int j = 1; j < p; j++){
           double abs = Math.abs(learningSet[j][a]-learningSet[j][a+1]);
           if (abs > max) max = abs;
       }
       
       System.out.println("Distanta Cebisev: "+ max);
   }
   
   public static void Cebisev3(double[][] learningSet, int p, int f)
   {
	   for(int i=0; i<f-1; i++)
	   {
		   Cebisev2(learningSet, p,f, i);
	   }
   }
   
   public static void CityBlock(double[][] learningSet, int p, int f, int a)
   {
	
	   double x1=learningSet[0][a];
	   double y1= learningSet[0][a+1];
	   double sum= Math.abs(x1-y1);
       double x2, y2;
	   
       for (int j = 1; j < p; j++)
       {
    	   x2=learningSet[j][a];
    	   y2=learningSet[j][a+1];
    	   sum+=Math.abs(x2-y2);
       }
       
       System.out.println("Distanta CityBlock: "+ sum);
   }
   
   public static void CityBlock2(double[][] learningSet, int p, int f)
   {
	   for(int i=0; i<f-1; i++)
	   {
		   CityBlock(learningSet, p,f, i);
	   }
   }
   
   public static void  Mahalanobis(double[][] learningSet, int p, int f, int a)
   {
	   
	   double x1=learningSet[0][a];
	   double y1= learningSet[0][a+1];
	   double sum= Math.pow((x1-y1), f);
       double x2, y2;
	   
       for (int j = 1; j < p; j++)
       {
    	   x2=learningSet[j][a];
    	   y2=learningSet[j][a+1];
    	   sum+=Math.pow((x2-y2), f);
       }
       
       System.out.println("Distanta CityBlock: "+Math.pow(sum, f));
   }
   public static void Mahalanobis2(double[][] learningSet, int p, int f)
   {
	   for(int i=0; i<f-1; i++)
	   {
		   Mahalanobis(learningSet, p,f, i);
	   }
   }
   
	protected static void Euclidian(double[] p,double[] p2 ) {
		// TODO Auto-generated method stub
		double sum=0.00;
    	double d=0.00;
    	for(int k=0; k<p.length; k++)
    	{
    		sum+=(p[k]-p2[k])*(p[k]-p2[k]);
    	}
    	d=Math.sqrt(sum);
    	System.out.print(d+" ");;
	}
	protected static double Euclidian1(double[] p,double[] p2 ) {
		// TODO Auto-generated method stub
		double sum=0.00;
    	double d=0.00;
    	for(int k=0; k<p.length-1; k++)
    	{
    		sum+=(p[k]-p2[k])*(p[k]-p2[k]);
    	}
    	d=Math.sqrt(sum);
    	return d;
	}

}