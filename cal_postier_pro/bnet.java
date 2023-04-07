import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

/**task 2 - bayesian_networks
 **/
public class bnet { 
	// below are the given propabilites
	static double B=0.001; 
	static double E=0.002;
	// storing propabilties into a list as it has dependcies that give them more probailties based on parentnode truth values
	static double A[]= {0.95,0.94,0.29,0.001};  
	static double J[]= {0.90,0.05};
	static double M[]= {0.70,0.01};
	// using array list to compute propability and store it into two array as in numerator and denominator
	static ArrayList<String> num_array = new ArrayList<String>();
	static ArrayList<String> den_array = new ArrayList<String>();

	public static void main(String[] args) {
		int B_ingiven=0,E_ingiven=0,A_ingiven=0,J_ingiven=0,M_ingiven=0;
		//bnet takes 1 to 6(no more, no fewer) command line arguments
		if (args.length <1 || args.length > 6) { 
			System.exit(0);
		}
		int index =-1;
		for(int n= 0; n <args.length; n++) 
		{ 
			if(args[n].equals("given")){
				index=0;
				continue;
			}if(index==-1) {
				num_array.add(args[n]);
				//store the arguments before 'given' in num_array and den_array
			}
			else
			{   den_array.add(args[n]);}
		}	
		if (num_array.size() <1 || num_array.size() > 6) { 
			System.exit(0);
			//exit when total number of arguments are greater than 6 and less than 1
		}
		if(index==0) {
			if (den_array.size() <1 || den_array.size() > 4) {
				System.exit(0);
				//exit when total number of arguments are greater than 6 and less than 1
			}		
		}
		//for printing result we took variables into string by replacing braces and ,
	    String x = Arrays.toString(num_array.toArray()).replace("[", "").replace("]", "").replace(",", "");
		String y = Arrays.toString(den_array.toArray()).replace("[", "").replace("]", "").replace(",", "");
		num_array.addAll(den_array); 
		//adding the missing variableS and appending IT WITH BOOLEN VALUES
		for(int i=0;i< num_array.size();i++) {  
			if (!num_array.contains("Bt")&&!num_array.contains("Bf")) {
				num_array.add("Bt");
				num_array.add("Bf");
				B_ingiven=1;
			}
			if (!num_array.contains("Et")&&!num_array.contains("Ef")) {
				num_array.add("Et");
				num_array.add("Ef");
				E_ingiven=1;
			}
			if (!num_array.contains("At")&&!num_array.contains("Af")) {
				num_array.add("At");
				num_array.add("Af");
				A_ingiven=1;
			}
			if (!(num_array.contains("Jt"))&&!num_array.contains("Jf")) {
				num_array.add("Jt");
				num_array.add("Jf");
				J_ingiven=1;
			}
			if (!num_array.contains("Mt")&&!num_array.contains("Mf")) {
				num_array.add("Mt");
				num_array.add("Mf");
				M_ingiven=1;}}

		double numerator = calculate(B_ingiven, E_ingiven, A_ingiven, J_ingiven, M_ingiven, num_array);
		if(den_array.size()==0) {
			System.out.println("bnet " + x);
			System.out.println("Probability: "+round(numerator,10));
		}
		B_ingiven=E_ingiven=A_ingiven=J_ingiven=M_ingiven=0;
		for(int j=0;j< den_array.size();j++) {
			if (!den_array.contains("Bt")&&!den_array.contains("Bf")) {
				den_array.add("Bt");
				den_array.add("Bf");
				B_ingiven=1;}
			if (!den_array.contains("Et")&&!den_array.contains("Ef")) {
				den_array.add("Et");
				den_array.add("Ef");
				E_ingiven=1;}
			if (!den_array.contains("At")&&!den_array.contains("Af")) {
				den_array.add("At");
				den_array.add("Af");
				A_ingiven=1;}
			if (!den_array.contains("Jt")&&!den_array.contains("Jf")) {
				den_array.add("Jt");
				den_array.add("Jf");
				J_ingiven=1;}
			if (!den_array.contains("Mt")&&!den_array.contains("Mf")) {
				den_array.add("Mt");
				den_array.add("Mf");
				M_ingiven=1;}}
		double denominator = calculate(B_ingiven, E_ingiven, A_ingiven, J_ingiven, M_ingiven, den_array);
		if(den_array.size()>0) {
			System.out.println("bnet " + x + " given " + y);
			System.out.println("Probability: "+round((numerator/denominator),10));
		}
	}
	//ROUNDOFF IS BASICALLY TO GIVE OUT THE OUTPUT IN PROPER MANNER AND ROUNDS OFF AT 10 PRESICION
    private static String round(double d, int precision) 
	{
		return BigDecimal.valueOf(d).setScale(precision, RoundingMode.HALF_UP).toString();
	}
	//FUCNTION used to calculate the true and false values and the summation if boolean wasnt given and returns prob
	public static double calculate(int b_value,int e_value,int a_value,int j_value,int m_value, ArrayList<String> arg_array) {
		double prob=0.0;
		Boolean b_TF=false,e_TF=false,a_TF=false,j_TF=false,m_TF=false;
		if(b_value==0) {
			if(arg_array.contains("Bt")) {
				b_TF=true;
			}
			else b_TF=false;
		}
		if(e_value==0) {
			if(arg_array.contains("Et")) {
				e_TF=true;
			}
			else e_TF=false;
		}
		if(a_value==0) {
			if(arg_array.contains("At")) {
				a_TF=true;
			}
			else a_TF=false;
		}
		if(j_value==0) {
			if(arg_array.contains("Jt")) {
				j_TF=true;
			}
			else j_TF=false;
		}
		if(m_value==0) {
			if(arg_array.contains("Mt")) {
				m_TF=true;
			}
			else m_TF=false;
		}
		for(int i1=0;i1<=b_value;i1++) {
			for(int i2=0;i2<=e_value;i2++) {
				for(int i3=0;i3<=a_value;i3++) {
					for(int i4=0;i4<=j_value;i4++) {
						for(int i5=0;i5<=m_value;i5++) {
							prob+=calc_prob(b_TF, e_TF, a_TF, j_TF, m_TF);
							if(m_value==1 && m_TF==false) m_TF=true;
							else if(m_value==1 && m_TF==true) m_TF=false;
						}
						if(j_value==1 && j_TF==false) j_TF=true;
						else if(j_value==1 && j_TF==true) j_TF=false;
					}
					if(a_value==1 && a_TF==false) a_TF=true;
					else if(a_value==1 && a_TF==true) a_TF=false;
				}
				if(e_value==1 && e_TF==false) e_TF=true;
				else if(e_value==1 && e_TF==true) e_TF=false;
			}
			if(b_value==1 && b_TF==false) b_TF=true;
			else if(b_value==1 && b_TF==true) b_TF=false;
		}
		return prob;
	}
	
	//fucntion calc_prob gives out the proper probability values based upon truth value from above function
	//and return the probibility based on truth values

	public static double calc_prob(boolean b, boolean e, boolean a, boolean j, boolean m) {
		double B_givenval=0.0;
		if(b) {
			B_givenval=B;}
		else {
			B_givenval = 1-B;}
		double E_givenval;
		if(e) {
			E_givenval=E;}
		else {
			E_givenval = 1-E;}

		double A_givenval = 0.0;
		if(a) {
			if(b==true && e==true)
				A_givenval = A[0];
			else if(b==true && e==false)
				A_givenval = A[1];
			else if(b==false && e==true)
				A_givenval = A[2];
			else if(b==false && e==false)
				A_givenval = A[3];}
		else
		{
			if(b==true && e==true)
				A_givenval = 1-A[0];
			else if(b==true && e==false)
				A_givenval = 1-A[1];
			else if(b==false && e==true)
				A_givenval = 1-A[2];
			else if(b==false && e==false)
				A_givenval = 1-A[3];}

		double J_givenval = 0.0;
		if(j) {
			if(a==true)
				J_givenval = J[0];
			else if(a==false)
				J_givenval = J[1];}

		else
		{
			if(a==true)
				J_givenval =1-J[0];
			else if(a==false)
				J_givenval =1-J[1];}

		double M_givenval = 0.0;
		if(m) {
			if(a==true)
				M_givenval = M[0];
			else if(a==false)
				M_givenval = M[1];}
		else
		{
			if(a==true)
				M_givenval =1-M[0];
			else if(a==false)
				M_givenval =1-M[1];
		}
		return B_givenval*E_givenval*A_givenval*J_givenval*M_givenval; 
	}

}