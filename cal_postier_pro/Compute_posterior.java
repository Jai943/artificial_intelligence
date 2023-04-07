
//IMPORTS FOR EXCEPTIONROUNDING OUTOPUT AND WRITING FILE

import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Compute_posterior 
{
		public static void main(String[] args) 
	{   
		String argsx = ""; //args to be stored in empty string
		if (args.length > 0)
			argsx = args[0];

		//assigning values to the list for hypothesis and pro.. of cherry and lime values
		double[]H_values = {0.1, 0.2, 0.4, 0.2, 0.1};
		double[]CL_pvalues = {1, 0.75, 0.5, 0.25, 0};
        display("\nObservation sequence Q: "+ argsx);
		display("\nLength of Q: " + argsx.length() + "\n");
		double calcpro_values = 0;
		int i = 0;

		//loops up and gives out the intial probability as we already have the first hypothesis
		while (i < H_values.length) 
		{   
			//gives out the proper intial probability
			calcpro_values = calcpro_values+(CL_pvalues[i] * H_values[i]);
			i++;
		}
		if (argsx.length() < 1)  
		//to cal args to first probabilty, ew can use direct given probailty in question
		{   
			int j = 0;
			while (j < H_values.length) 
			{ 
				display("P(h" + (j + 1) + " | Q) = " + round(H_values[j], 5) + "\n");
				j++;
		    }
			display("\nProbability that the next candy we pick will be C, given Q: " + round(calcpro_values, 5));
			display("\nProbability that the next candy we pick will be L, given Q: " + round(1 - calcpro_values, 5) + "\n\n");
			return;
		}
		calc_probability(H_values, CL_pvalues, calcpro_values, (1 - calcpro_values), argsx);
		display("-----------------------------------------------------------------------------------\n");
	}
    //calculates the postier probabilty 
	private static void calc_probability(double[]H_Cal_values, double[]CL_pvalues, double calcpro_values, double H_LICH_VALUES, String LICH_SEQOBS) 
	{
		if (LICH_SEQOBS != null && LICH_SEQOBS.isEmpty())
			return;
		String seq = LICH_SEQOBS.substring(0, 1);
		double[]H_Cal_values_next = new double[H_Cal_values.length];
		//sets up after obesrvation for the each nodes in as bring down to single node
		display("\nAfter Observation " + LICH_SEQOBS.charAt(0) + " = " + LICH_SEQOBS.substring(1, LICH_SEQOBS.length()) + "\n\n");
		int i=0;
		int j=0;
		//helps on to calculate the probabilty to each hypothesis after teh intail one
		//while loop was used to get these values

		while(i < H_Cal_values.length) 
		{
			H_Cal_values_next[i] = ((seq.equals("C") ? CL_pvalues[i] : ( 1 - CL_pvalues[i] )) * H_Cal_values[i]) / (seq.equals("C") ? calcpro_values : H_LICH_VALUES);
			display("P(h"+ (i + 1) + " | Q) = " + round(H_Cal_values_next[i], 5) + "\n");
			i++;
		}
		double calcpro_values_next = 0, Pro_LICH_values_next = 0;

		//used to give out the probabilioty on based on what we pick for all other expect for intail one

		while(j < H_Cal_values.length) 
		{
			calcpro_values_next += CL_pvalues[j] * H_Cal_values_next[j];
			Pro_LICH_values_next += (1 - CL_pvalues[j]) * H_Cal_values_next[j];
			j++;
		}
		display("\nProbability that the next candy we pick will be C, given Q: " + round(calcpro_values_next, 5));
		display("\nProbability that the next candy we pick will be L, given Q: " + round(Pro_LICH_values_next, 5) + "\n\n");
		calc_probability(H_Cal_values_next, CL_pvalues, calcpro_values_next, Pro_LICH_values_next, LICH_SEQOBS.substring(1, LICH_SEQOBS.length()));
	}
    //ROUNDOFF IS BASICALLY TO GIVE OUT THE OUTPUT IN PROPER MANNER AND ROUNDS OFF AT 5 PRESICION as asked

	private static String round(double number, int precision) 
	{
		return BigDecimal.valueOf(number).setScale(precision, RoundingMode.HALF_UP).toString();
	}

    //displays the output in result file and keeps on appending the file as its gets new inputs
	private static void display(String stmts) 
	{
		FileWriter out_file = null;
		try 
		{  //parameter ttrue helps to append the file 
			out_file = new FileWriter("result.txt", true);
			out_file.write(stmts);
			out_file.close();
						
		} catch (Exception e) 
		{
			System.out.println(e);
		}		
	}		
}
