import java.util.*;
/*@Aditi Misra @Feb. 8. 2020
 * This program intakes a string of letters between 6 and 30 characters long and returns the largest palindrome (at least 4
 * characters long) in that sequence. If no palindrome is present in the string, the program outputs this result. 
 * If the user inputs an invalid string sequence, the program outputs "invalid input". 
 */
class PalindromeSearch{
	public static void main(String [] args) {
		LargestPalindromeAditi obj1=new LargestPalindromeAditi();
		obj1.inputString();
		System.out.print(obj1.toString());
	}
}
/*@Aditi Misra @Feb. 8 2020
 * The class LargestPalindromeAditi contains the necessary algorithms for the program function. It allows the user to input
 * a valid string, finds the largest palindrome within the string, and creates a String output statement indicating the result
 * to the user.
 */
class LargestPalindromeAditi{
	private String largestPalindrome;
	LargestPalindromeAditi(){}
	//get/set methods
	public void setLargestPalindrome(String largestPalindrome) {this.largestPalindrome=largestPalindrome;}
	public String getLargestPalindrome() {return largestPalindrome;}
/*@Aditi Misra @Feb. 8 2020
 * The method input string allows the user to input a string of characters to be tested for a palindrome. If the input is valid
 * it runs the findLargestPalindromem() method to carry out the program function.
 * @param: none @return: none
 */
	public void inputString() {
		System.out.println("Please enter a string of characters between the length of 6 to 30:");
		Scanner userIn=new Scanner(System.in);
		largestPalindrome=userIn.nextLine().toUpperCase();
		if(largestPalindrome.length()<=30 && largestPalindrome.length()>=6 && largestPalindrome.matches("[A-Z]+"))
			findLargestPalindrome();
		else 
			largestPalindrome = null;
	}
/*@Aditi Misra @Feb. 8 2020
 * The findLargestPalindrome method finds the largest palindrome(s) in a given string of characters. If it is the 
 * largest encountered palindrome (over length 4), it is recorded and the program continues. 
 * Palindromes are saved as the largestPalindrome String. 
 * @param: none @return: none
 */
	private void findLargestPalindrome() {
		String maxP="";
		//i is length of palindrome being searched for
		label:
		for(int i=largestPalindrome.length();i>3;i--) {
			//rep is number of iterations of substrings of length i in the string 
			for(int rep=0;rep<=largestPalindrome.length()-i;rep++) {
				String rev="";
				//create the reverse of the potential palindrome
				for(int j=i+rep-1;j>=rep;j--)
					rev=rev+largestPalindrome.substring(j,j+1);
				if (rev.compareTo(largestPalindrome.substring(rep,i+rep))==0)
					maxP=maxP+rev+",";
				if (rep==largestPalindrome.length()-i && maxP.split(",")[0]!="")
					break label;
			}
		}
		largestPalindrome=maxP;
	}
/*@Aditi Misra @Feb. 8 2020
 * This method returns a string output that denotes the result of the program to the user. It displays either an invalid 
 * input, no palindrome, or the largest palindrome(s) contained in the string.
 * @param: none @return: String
 */
	public String toString() {
		String state="Biggest Palidrome in the string is ";
		if (largestPalindrome==null) 
			return state+"INVALID INPUT";
		else {
			String [] palin=largestPalindrome.split(",");
			if(palin.length==1) {
				if(palin[0]=="")
					return state+"NO PALINDROME";
				else
					return state+palin[0];
			}
			else
				return state+Arrays.deepToString(palin);
		}
	}
}
