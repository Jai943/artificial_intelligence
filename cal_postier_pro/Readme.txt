Name: Jayachandra Jarajapu
UTA ID: 1001964536
Secion: 5360 - 002
-------------------------------------------------------------------------------

Programming Language used: JAVA
IDE: - VScode


-------------------------------------------------------------------------------
Note: - not including result file as it automatically creates when we run
-------------------------------------------------------------------------------

Task 1:
Class Compute_posterior

The code initially starts with counting number of Lime and Cherry candies.
we stored teh given probabilty values and hyptohsies prior probability in h_values and CL_pvalues for of picking Cherry Candy or lime candy bagfrom the Bag which was provides in the requirement

while (i < H_values.length)----loop
gives probability from the given values

but when in caluclating Compute_posterior probabilty 
private static void calc_probability(double[]H_Cal_values, double[]CL_pvalues, double calcpro_values, double H_LICH_VALUES, String LICH_SEQOBS) 
followning fucntion was used 
i have to take our input arguement and divide string in place to substrings then calculate probailty

private static String round(double number, int precision) 
It rounds the total probability value up the values by giving the defined precision  = 5

private static void display(String stmts) 
used filewrites to write the output into a files called result.txt

-------------------------------------------------------------------------------------------

Task 2:

Class bnet

This class implements a program that calculates and gives out the probability of any combination of events given any other combination of events,given a Bayesian network establishing relations 
between events domain together with complete specifications of all probability distributions.

Task 2 Functions: 

public static double calc_prob(boolean b, boolean e, boolean a, boolean j, boolean m)
This function has arguments which are boolean, specifying if the corresponding event (burglary, earthquake, alarm, john-calls, mary-calls) is true or false.
which will be later used by calculate function
mostly wrtten using for and if loops using some Referred websites listed at the EOD.

public static double calculate(int b_value,int e_value,int a_value,int j_value,int m_value, ArrayList<String> arg_array) {
This function calls calc_prob function to compute the probability by considering different combination of values for the missing elements in the numerator and denominitor.
and return probability ehich will be later used by main Class
mostly wrtten using for and if loops using some Referred websites listed at the EOD.

private static String round(double d, int precision) 
It rounds the total probability value up the values by giving the defined precision



-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

Compilation Instructions:
Task1:

     $ javac Compute_posterior.java

     $ java Compute_posterior [ARG]

example----- 

     $ java Compute_posterior CLLCCLL 

open result file to check outputs

note: - result file will be having all the outputs as im appending outputs 

-------------------------------------------------------------------------------------------
Compilation Instructions:
Task2:

     $ javac bnet.java

     $ java bnet [arg1] [arg2] [arg3] [arg4] [arg5] [arg6]

example------

     $ java bnet Jt given Et Bf


note: - result will be displayed in command prom

-------------------------------------------------------------------------------------------

Referred Links fro both Tasks:-

https://www.javatpoint.com/array-in-java
https://answers.unity.com/questions/272993/loop-through-array-until-certain-value-is-found.html
http://www.baeldung.com/java-write-to-file
https://stackoverflow.com/questions/4885254/string-format-to-format-double-in-java


-------------------------------------------------------------------------------------------


