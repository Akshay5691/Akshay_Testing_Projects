import java.util.Scanner;

import org.testng.annotations.Test;

public class Coding_Problems {

	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Test
	     public void factorialOfGivenNumber() {
		
	     	Scanner sc = new Scanner(System.in);
	     	System.out.print("Enter a number to calculate its factorial: ");
	     	int number = sc.nextInt();
	    	int factorial = 1;
		
	    	for (int i = 1; i <= number; i++) {
			factorial = factorial * i;
			System.out.print( factorial + "  " );
	    	}
	     	System.out.println("Factorial of " + number + " is: " + factorial);
	}
     	@Test
     	 public void primeNumber() {		
    	     for(int number =1; number<=100; number++) {	
		        boolean isPrime=true;	
		      for(int i=2; i<number; i++) {
		       	if(number%i==0) {
	  			isPrime=false;
				break;
		          }		
	          	}
	          	if(isPrime) {
			System.out.print(number + " ");
	     	}
	     }
   }       @Test
	      public void fibonacciSeries() {
		        int n1=0, n2=1, n3, count=10;
		        System.out.print(n1 + " " + n2);
	           	for(int i=2; i<count; i++) {
		        	n3=n1+n2;
		     	System.out.print(" " + n3);
		         	n1=n2;
		     	    n2=n3;
		}
	}     @Test
	      public void reverseString() {
	    	   String str = "Hello World";
	    	   String reversedStr = "";
	    	   for(int i=str.length()-1; i>=0; i--) {
	    		   reversedStr = reversedStr + str.charAt(i);
	    	   }
	    	   System.out.println("Reversed String: " + reversedStr);
	      }
	      @Test
	      public void checkPalindrome() {
	    	   String str = "madam";
	    	   String reversedStr = "";
	    	   for(int i=str.length()-1; i>=0; i--) {
	    		   reversedStr = reversedStr + str.charAt(i);
	    	   }
	    	   if(str.equals(reversedStr)) {
	    		   System.out.println(str + " is a palindrome.");
	    	   } else {
	    		   System.out.println(str + " is not a palindrome.");
	    	   }
	      }
	      @Test
	      public void polindromeNumber() {
	    	   int number = 121;
	    	   int originalNumber = number;
	    	   int reversedNumber = 0;
	    	   while(number != 0) {
	    		   int digit = number % 10;
	    		   reversedNumber = reversedNumber * 10 + digit;
	    		   number = number / 10;
	    	   }
	    	   if(originalNumber == reversedNumber) {
	    		   System.out.println(originalNumber + " is a palindrome number.");
	    	   } else {
	    		   System.out.println(originalNumber + " is not a palindrome number.");
	    	   }
	      }
	      
	      
	      
	      
}
