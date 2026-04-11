package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

import org.testng.annotations.Test;

public class coding {

	@Test
	public void factorialOfGivenNumber() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a number to calculate its factorial: ");
		int number = sc.nextInt();
		int factorial = 1;

		for (int i = 1; i <= number; i++) {
			factorial = factorial * i;
			
		}
		System.out.println("Factorial of " + number + " is: " + factorial);
	}

	@Test
	public void primeNumber() {
		for (int number = 1; number <= 100; number++) {
			boolean isPrime = true;
			for (int i = 2; i < number; i++) {
				if (number % i == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				System.out.print(number + " ");
			}
		}
	}

	@Test
	public void reverseString() {
		String str = "Hello World";
		String reversedStr = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			reversedStr = reversedStr + str.charAt(i);
		}
		System.out.println("Reversed String: " + reversedStr);
	}

	@Test
	public void checkPalindromeString() {
		String str = "madam";
		String reversedStr = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			reversedStr = reversedStr + str.charAt(i);
		}
		if (str.equals(reversedStr)) {
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
		while (number > 0) {

			int digit = number % 10;
			reversedNumber = reversedNumber * 10 + digit;
			number = number / 10;
		}
		if (originalNumber == reversedNumber) {
			System.out.println(originalNumber + " is a palindrome number.");
		} else {
			System.out.println(originalNumber + " is not a palindrome number.");
		}
	}

	@Test
	public void fibonacciSeries() {
		int n1 = 0, n2 = 1, n3, count = 10;
	
		for (int i = 1; i < count; i++) {
			n3 = n1 + n2;
			System.out.print(" " + n3);
			n1 = n2;
			n2 = n3;
		}
	}

	@Test
	public void removeVowelsFromStrings() {

		List<String> list = new ArrayList<String>();
		list.add("Hyderabad");
		list.add("Bangalore");
		list.add("Chennai");
		list.add("Pune");
		System.out.println(list);

		for (int i = 0; i < list.size(); i++) {

			String city = list.get(i).toLowerCase();
			String [] vowels= {"a","e","i","o","u"};

			for (int j = 0; j < vowels.length; j++) {

				if (city.contains(vowels[j])) {

					String[] splitted = city.split(vowels[j]);

					String string = String.join("", splitted);
					list.set(i, string);

				}
			}
		}

		System.out.println(list);

	}

	@Test
	public void removeVowelsFromString() {
		String city = "Hyderabad";

		String [] vowels= {"a","e","i","o","u"};

		for (int j = 0; j < vowels.length; j++) {

			if (city.contains(vowels[j])) {

				String[] splitted = city.split(vowels[j]);

				city = String.join("", splitted);
			}
		}
		System.out.println(city);

	}

	@Test
	public void reverseEachStringInSentence() {

		String sentence = "I am learning Selenium Automation";
		String[] words = sentence.split(" ");
		String reverseWords = "";

		for (int i = 0; i < words.length; i++) {

			String word = words[i];
			String reversedWord = "";
			for (int j = word.length() - 1; j >= 0; j--) {

				reversedWord += word.charAt(j);

			}
			System.out.println(reversedWord);

			reverseWords = reverseWords + reversedWord + " ";
		}
		System.out.println("Reversed Sentence: " + reverseWords.trim());

	}

	@Test
	public void CountingVowelsInString() {

		String sentence = "I am learning Selenium Automation";
		String sentence1 = sentence.toLowerCase();
		int VowelsCount = 0;
		for (int i = 0; i < sentence1.length(); i++) {

			char ch = sentence1.charAt(i);
			char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

			for (int j = 0; j < vowels.length; j++) {

				if (ch == vowels[j]) {
					VowelsCount++;
					break;
				}
			}

		}
		System.out.print(VowelsCount);

	}

	@Test
	public void countingStringsInSentence() {
		String sentence = "I am learning Selenium Automation";
		String[] words = sentence.split(" ");
		int count = 0;
		for (int i = 0; i < words.length; i++) {

			count++;
		}
		System.out.println(count);

	}

	@Test
	public void evenOrOddNumber() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("enter number");
		int number = sc.nextInt();

		if (number % 2 == 0) {
			System.out.println("even number");
		} else {
			System.out.println("odd number");
		}

	}

	@Test
	public void countingRepeatingCharactersInString() {

		String name1 = "AkshayChary";
		String name = name1.toLowerCase();
		String counted = "";

		for (int i = 0; i < name.length(); i++) {
			
			if (counted.contains(String.valueOf(name.charAt(i)))) {
				continue;
			}

			int count = 0;
			for (int j = 0; j < name.length(); j++) {
				if (name.charAt(i)== name.charAt(j)) {
					count++;
				}
			}

			counted = counted + name.charAt(i);
			System.out.println(name.charAt(i) + " " + count);
		}

	}

	@Test
	public void countingAllCharactersInString() {

		String name1 = "AkshayChary";
		String name = name1.toLowerCase();

		for (int i = 0; i < name.length(); i++) {
			int count = 0;
			for (int j = 0; j < name.length(); j++) {
				if (name.charAt(i) == name.charAt(j)) {
					count++;
				}
			}
			System.out.println(name.charAt(i) + " " + count);
		}

	}

	@Test
	public void eliminatingExtraSpaces() {

		String sentence = "my   name   is   akshay  chary";
		String[] words = sentence.split("\\s+");
		String correctFormate = "";
		for (int i = 0; i < words.length; i++) {

			correctFormate += words[i] + " ";

		}
		System.out.println(correctFormate);
	}

	@Test
	public void convertionOfUpperCaseAndKLowerCase() {

		String name = "AKShayCHAry";
		String upper = name.toUpperCase();

		String changed = "";
		for (int i = 0; i < name.length(); i++) {

			char character = name.charAt(i);
			if (character == upper.charAt(i)) {

				changed += Character.toLowerCase(character);
			} else {

				changed += Character.toUpperCase(character);
			}

		}
		System.out.println(changed);
	}

	@Test
	public void findLargestNumberInArray() {

		int[] numbers = { 10, 40, 50, 20, 30 };
		int max = numbers[0];

		for (int i = 0; i < numbers.length; i++) {

			if (max < numbers[i]) {

				max = numbers[i];
			}
		}
		System.out.println("largest number in array: " + max);

	}

	@Test
	public void removeSpacesFromString() {

		String sentence = "verify User Is Able To Search Item";
		String modified = sentence.replace(" ", "");
		System.out.println(modified);

	}

	@Test
	public void sortingArrayMaxToMin() {

		int[] array = { 10, 40, 20, 80, 50, 30, 60 };
		int temp;
		

		for (int i = 0; i < array.length; i++) {

			for (int j = i + 1; j < array.length; j++) {

				if (array[i] < array[j]) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}

		}
		for (int num : array)
			System.out.print(num + " ");

	}

	@Test
	public void sortingArrayMinToMax() {

		int[] array = { 10, 40, 20, 80, 50, 30, 60 };
		int min;
		int temp;

		for (int i = 0; i < array.length; i++) {

			for (int j = i + 1; j < array.length; j++) {

				if (array[i] > array[j]) {
					min = array[i];
					array[i] = array[j];
					array[j] = min;
				}
			}

		}
		for (int num : array)
			System.out.print(num + " ");

	}

	@Test
	public void findLongestWordInSentence() {

		String sentence = "I am learning Selenium Automations ";
		String[] words = sentence.split(" ");
		int max = words[0].length();
		String maxWord = "";

		for (int i = 0; i < words.length; i++) {

			if (max < words[i].length()) {
				max = words[i].length();
				maxWord = words[i];

			}
		}
		System.out.println(maxWord);

	}

	@Test
	public void alphabetsAscendingOrderInString() {

		String alphabets = "hsbdfbjkbskjbjkbaskltertiuweyqpcxbncmf";

		char[] chars = alphabets.toCharArray();
		Arrays.sort(chars);
		String abc = Arrays.toString(chars);

		System.out.println(chars);

	}
    @Test
	public void alphabetsAscendingOrderInStringWithOutSort() {

		String alphabets = "hsbdfbjkbskjbjkbaskltertiuweyqpcxbncmf";

		char[] chars = alphabets.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			for (int j = i + 1; j < chars.length; j++) {

				if (chars[i] > chars[j]) {
					char temp = chars[i];
					chars[i] = chars[j];
					chars[j] = temp;
				}
			}
		}

		System.out.println(new String(chars));
	}

	@Test
	public void stringsAscendingOrder() {

		List<String> fruits = new ArrayList<String>();
		fruits.add("banana");
		fruits.add("anana");
		fruits.add("znana");
		fruits.add("danana");

		Collections.sort(fruits);
		for (String string : fruits) {
			System.out.println(string);
		}

	}

	@Test
	public void stringsAscendingOrderWithoutSort() {

		List<String> fruits = new ArrayList<>();
		fruits.add("banana");
		fruits.add("anana");
		fruits.add("znana");
		fruits.add("danana");

		for (int i = 0; i < fruits.size(); i++) {
			for (int j = i + 1; j < fruits.size(); j++) {

				if (fruits.get(i).compareTo(fruits.get(j)) > 0) {
					String temp = fruits.get(i);
					fruits.set(i, fruits.get(j));
					fruits.set(j, temp);
				}
			}
		}

		for (String fruit : fruits) {
			System.out.println(fruit);
		}
	}

	@Test
	public void removeDuplicatesFromArray() {

		int[] numbers = { 10, 20, 10, 30, 20, 10, 30, 40, 50, 40 };

		LinkedHashSet<Integer> number = new LinkedHashSet<Integer>();
		for (int i = 0; i < numbers.length; i++) {
			number.add(numbers[i]);
		}
		System.out.println(number);

	}

	@Test
	public void removeDuplicatesWithoutSet() {

		int[] numbers = { 10, 20, 10, 30, 20, 10, 30, 40, 50, 40 };

		int[] temp = new int[numbers.length];
		int index = 0;

		for (int i = 0; i < numbers.length; i++) {
			boolean isDuplicate = false;

			for (int j = 0; j < i; j++) {
				if (numbers[i] == numbers[j]) {
					isDuplicate = true;
					break;
				}
			}

			if (isDuplicate==false) {
				temp[index++] = numbers[i];
			}
		}

		for (int i = 0; i < index; i++) {
			System.out.print(temp[i] + " ");
		}
	}

	@Test
	public void replaceCharactersInString() {

		String sentence = "I am learning Selenium Automations ";
		sentence = sentence.replace('a', 'b');
		System.out.println(sentence);

	}
	
	
	public void sumOfPositiveNumber() {
		int[]number = {1, 3 , 5,7,-6,-5};
		int sum=0;
		
		
		for (int i = 0; i < number.length; i++) {
			int j = number[i];
			
			if(j>0) {
				sum+=j;		
			}	
		}
		
		System.out.println(sum);
			
		}
	
	
	
	@Test
	public void removeDuplicatesFromString() {	
	
		String str = "programming";
		String result = "";


		for (int i = 0; i < str.length(); i++) {
			
		if (!result.contains(String.valueOf(str.charAt(i)))) {
		result += str.charAt(i);
		   }
		}


		System.out.println(result); 
	}
	
	@Test
	public void PrintDuplicateStringsFromArray() {	
		
		String[] arr = {"java", "selenium", "api", "java", "testing", "api"};


		for (int i = 0; i < arr.length; i++) {
		for (int j = i + 1; j < arr.length; j++) {
			
		if (arr[i].equals(arr[j])) {
		System.out.println(arr[i]);
		break;
		           }
	         	}
		   }
		}
		
		
		
	@Test	
	public void PrintDuplicates(){
	
	String str = "akshay";

	for (int i = 0; i < str.length(); i++) {
	    for (int j = i + 1; j < str.length(); j++) {
	        if (str.charAt(i) == str.charAt(j)) {
	            System.out.println(str.charAt(i));
	            
	            break;
	        }
	    }
	}
	
	
	
	
	
		}
	public void printVowels() {

        String str1 = "hello";
        String str = str1.toLowerCase();
        

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u'
               ) {

                System.out.println(ch + " is a Vowel");

            } else if ((ch >= 'a' && ch <= 'z')) {

                System.out.println(ch + " is a Consonant");
            }
        }
    }
	
	
	
	  public void sumOfNumbersInString() {

	        String str = "a1b2c3";
	        int sum = 0;

	        for (int i = 0; i < str.length(); i++) {
	            char ch = str.charAt(i);

	            if (Character.isDigit(ch)) {
	                sum += Character.getNumericValue(ch); 
	            }
	        }

	        System.out.println("Sum: " + sum);
	  }

}
