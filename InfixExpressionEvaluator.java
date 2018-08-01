/**
 * Implement methods: 
 *  	private String convertToPostfix(String infix)
 *      private double evaluatePostfix(String postfix)
 *	
 * Look at algorithms in the methods
*/

import java.util.*;

public class InfixExpressionEvaluator
{
   // This is a variable table. It contains <name,value> pairs
   // Do not modify! 
   Map<Character,Double> variableValues = new HashMap<>();


   /** Convert a valid infix expression to a postfix expression 
       Must only use variable names as defined in variable table

       @param infix :  A valid infix expression.
       @return Equivalent postfix expression */

   private String convertToPostfix(String infix)
   {
	   Stack<Character> S = new Stack();
	   StringBuffer PE = new StringBuffer();
	   char ch;	  
	   char TopSymbol = 0;
	   int symbol = 0;
	   
	   for(int i = 0; i < infix.length(); i++) {
		   ch = infix.charAt(i);
		  
		   if(Character.isLetter(ch)) {
			   PE.append(ch);
		   }
		   
		   switch(ch) {
		   	  
		      case '(': S.push(ch); break;
		      case ')':	TopSymbol = S.pop();
		      	while(TopSymbol != '(') {
		      		PE.append(TopSymbol);
		      		TopSymbol = S.pop();
		      	} break;
		      case '+': 
		      case '-':
		      case '*':
		      case '/': 
		      	while((!S.isEmpty() && (S.peek() != '(') && 
		      			(precedence(ch) <= precedence(S.peek())))) {
		      		TopSymbol = S.pop();
		      		PE.append(TopSymbol);
		      } 
		      	S.push(ch); break;
		    	  	    
		      default: break;
		    	
		   }
		   
	   }
	   while(!S.empty()) {
		   TopSymbol = S.pop();
		   PE.append(TopSymbol);
	   }

	System.out.println(PE.toString());
	return PE.toString();
   } // end convertToPostfix


/** Evaluates a postfix expression.
       Must only use variable names as defined in variable table

       @param postfix : A valid postfix expression.
       @return The double result of the postfix expression. */

   private double evaluatePostfix(String postfix)
   {
	   Stack<Double> S = new Stack();
	   char ch;
	   double a, b;
	   double x = 0;
	   double result = 0;
	   for(int j = 0; j < postfix.length(); j++) {
		   ch = postfix.charAt(j);
		   
		   if(checkValidVariable(ch)) {
			   x = getVariableValue(ch);
			   S.push(x);
		   } else if(checkValidOperator(ch)) {
			   b = S.pop();
			   a = S.pop();
			   switch(ch) {
			   case '+': x = a+b; break;
			   case '-': x = a-b; break;
			   case '/': x = a/b; break;
			   case '*': x = a*b; break;
			   }
			   S.push(x);			   
			   
		   }
		   
	   }
	  result = S.pop();
      return result; // change it
   } // end evaluatePostfix


   private int precedence(char operand) {
	switch(operand) {
		case '+':
		case '-':
			return 0;
		case '*':
		case '/':
			return 1;
	}
	return 0;
}


   //----------------------------------------------------------------
   // Do not modify anything below 
   //----------------------------------------------------------------
   
   
   // Check a character op is a valid operator, i.e. +, -, * or /  
   private boolean checkValidOperator(char op)
   {
      return ((op == '+') || (op == '-') || (op == '*') || (op == '/'));
   }

   // Check variable var is defined in variable table 
   private boolean checkValidVariable(char var)
   {
      return variableValues.containsKey(var);
   }

   // Retrieve variable values from variable table 
   private double getVariableValue(char var)
   {
      return variableValues.get(var).doubleValue();
   } 
    
   // Read variable values into a variable table 
   void setupVariables() {
	   Scanner s = new Scanner(System.in);
	   char  var = 'A';
	   double val = 3.5; 
	   System.out.println("\n\nCreate Variable Table, please input variable info:\n");
	   while (var != '0') {
	   	System.out.print("Enter name and value, example: A 3.5 (enter 0 0 to exit) : ");
		var = s.next().charAt(0);
		val = s.nextDouble();
	        if (var == '0') continue;
   		variableValues.put(var, val);
	   }
	   System.out.println("\nVariable table :" + variableValues);
   }
   	

   // This starts infix evaluations
   // Must enter valid infix expressions, otherwise, may get unexpected results
   // Enter "exit" to terminate loop
   void evaluate() {
	Scanner scanner;
	String inputInfix;
	String postfix;
	double result;
        int i=0;

	System.out.println("\nStart to evaluate infix expressions....");
       	scanner = new Scanner( System.in ); // scanner for input
        do                                                                  
        {                                                                   
	   try {
              System.out.print( "Enter a valid infix expression string (enter \"exit\" to terminate):" );

	      // scan next input line
              inputInfix = scanner.nextLine();                            

	      if (inputInfix.equals("exit"))
		 break; // loop

              i++;
              System.out.println("   Evaluate expression #"+ i+" : " + inputInfix);
              postfix=convertToPostfix(inputInfix);
              System.out.println("   Equivalent postfix: " + postfix);
              result =evaluatePostfix(postfix);
              System.out.printf("   Result : %.2f\n", result);
      	   } catch (Exception e) {
              System.out.println("   Exception...."+e.getMessage());
	   }
	   

        } while ( true ); // end do...while                         
 
   }  

   // Run quick tests
   void quickTest() {
	   char  var = 'A';
	   double val = 3.5; 
	   String inputInfix=null;
	   String postfix=null;

	   System.out.println("\n\nVariable table for quick test");
   	   variableValues.put('A', 5.5);
   	   variableValues.put('B', -4.5);
   	   variableValues.put('C', 90.0);
   	   variableValues.put('D', -5.0);
	   System.out.println("\nVariable table :" + variableValues);

	   inputInfix="(A)";
           System.out.println("\nConvert infix expression to postfix expression: " + inputInfix);
           System.out.println("Equivalent postfix: " + convertToPostfix(inputInfix));

	   inputInfix="(A+(B+C))";
           System.out.println("\nConvert infix expression to postfix expression: " + inputInfix);
           System.out.println("Equivalent postfix: " + convertToPostfix(inputInfix));

	   inputInfix="(A*(B+C))";
           System.out.println("\nConvert infix expression to postfix expression: " + inputInfix);
           System.out.println("Equivalent postfix: " + convertToPostfix(inputInfix));

	   inputInfix="(A-(B+C)/D)";
           System.out.println("\nConvert infix expression to postfix expression: " + inputInfix);
           System.out.println("Equivalent postfix: " + convertToPostfix(inputInfix));

	   inputInfix="A*(B+C-D)";
           System.out.println("\nConvert infix expression to postfix expression: " + inputInfix);
           System.out.println("Equivalent postfix: " + convertToPostfix(inputInfix));

	   inputInfix="A*B+(C-D)-D*B";
           System.out.println("\nConvert infix expression to postfix expression: " + inputInfix);
           System.out.println("Equivalent postfix: " + convertToPostfix(inputInfix));

	   postfix="A";
           System.out.println("\nEvaluate postfix expression: " + postfix);
           System.out.printf("Result : %.2f\n", evaluatePostfix(postfix));

	   postfix="ABC++";
           System.out.println("\nEvaluate postfix expression: " + postfix);
           System.out.printf("Result : %.2f\n", evaluatePostfix(postfix));

	   postfix="ABC+*";
           System.out.println("\nEvaluate postfix expression: " + postfix);
           System.out.printf("Result : %.2f\n", evaluatePostfix(postfix));

	   postfix="ABC+D/-";
           System.out.println("\nEvaluate postfix expression: " + postfix);
           System.out.printf("Result : %.2f\n", evaluatePostfix(postfix));

	   postfix="ABC+D-*";
           System.out.println("\nEvaluate postfix expression: " + postfix);
           System.out.printf("Result : %.2f\n", evaluatePostfix(postfix));

	   postfix="AB*CD-+DB*-";
           System.out.println("\nEvaluate postfix expression: " + postfix);
           System.out.printf("Result : %.2f\n", evaluatePostfix(postfix));

   	   variableValues.clear();
   }

} 
                 
