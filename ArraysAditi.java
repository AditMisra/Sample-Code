/*@Aditi @March 11 2020
 * Program carries out matrix calculations on 2D arrays saved in a file in the user's USB drive.
 * Exception handling and basic menu system is handled in main class while matrix input and 
 * calculations are handled in secondary class. This program can carry out the file input, 
 * matrix addition, subtraction and multiplication, and display the results to the user.
 */
import java.util.*;
import java.io.*;
public class ArraysAditi {
	public static void main(String [] args) {
		Scanner userInput = new Scanner(System.in);
		Scanner in;
		boolean flag=true;
		while(flag) {
			try {
				System.out.println("Please enter the drive letter of the USB that the file you wish to open is in. I.e. \"D\", \"E\", or \"F\"");
				String drive= userInput.nextLine();
				System.out.println("Please enter the name of the file you wish to open. (No extension required)");
				File fileText= new File(drive+":\\"+userInput.nextLine()+".txt");
				//File fileText= new File(System.getProperty("user.dir")+"\\"+userInput.nextLine()+".txt");
				in = new Scanner(fileText);
				int rep=in.nextInt();
				for(int i=0; i<rep; i++)
					Array2DAditi.inputArrays(in);
				flag=false;
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		}
	}
}
/*@Aditi @March 11
 * Class Array2DAditi manages matrix input from a USB file, matrix calculations and display to user.
 * All necessary methods are static thus no object must be created. 
 */
class Array2DAditi{
/*@Aditi @March 11
 * The method inputArrays reads the user file containing the matrices on which the calculations are run.
 * The method saves the array pair into a 3D array and calls the display method, which in turn calls
 * the individual calculations.
 * @Param: Scanner object reading the user's file @Return: void
 */
	public static void inputArrays(Scanner in) {
		int [] [] [] arraySet= new int[2][][];
		for(int i=0; i<2; i++) {
			int rows=in.nextInt();
			int col=in.nextInt();
			arraySet[i]=new int [rows][col];
			for(int r=0;r<rows;r++) {
				for(int c=0; c<col;c++) {
					arraySet[i][r][c] = in.nextInt();
				}
			}
		}
		display(arraySet[0], arraySet[1]);
	}
/*@Aditi @March 11
 * The method display2DArray can display the 2D array argument as specified
 * in the assignment. It is called to display the arrays, their sum, difference and
 * product. 
 * @Param: 2D int array to be displayed @Return: void
 */
	private static void display2DArray(int [] [] arr) {
		for(int r=0; r<arr.length;r++) {
			System.out.print("|\t");
			for(int c=0;c<arr[r].length;c++) {
				System.out.print(arr[r][c]+"\t");
			}
			System.out.println("|");
		}
	}
/*@Aditi @March 11
 * The method checkSumDiff determines if the array pair specified in the user file can be 
 * added/subtracted. This is verified before the sum/diff methods are called to prevent 
 * the program from crashing. This means ensuring the matrices are of the same dimensions.
 * @Param: 2 int [] [] arrays to serve as the tested matrices 
 * @Return: boolean, true if the sum/diff operations can be called
 */	
	private static boolean checkSumDiff(int [] [] arr1, int [] [] arr2) {
		if(arr1.length==arr2.length && arr1[0].length==arr2[0].length)
			return true;
		else
			return false;
	}
/*@Aditi @March 11
 * The method sum calculates the sum of the 2 matrices passed through. 
 * It returns the matrix sum.
 * @Param: 2 int [] [] arrays to serve as matrices added @Return: matrix sum
 */
	private static int [] [] sum(int [] [] arr1, int [] [] arr2){
		int [] [ ] sum = new int [arr1.length][arr1[0].length];
		for(int r=0;r<arr1.length;r++) {
			for(int c=0; c<arr1[0].length;c++) {
				sum[r][c]=arr1[r][c]+arr2[r][c];
			}
		}
		return sum;
	}
/*@Aditi @March 11
 * The method diff calculates the difference of the 2 matrices passed through. 
 * It returns the matrix difference.
 * @Param: 2 int [] [] arrays to serve as matrices subtracted @Return: matrix difference
 */
	private static int [] [] diff(int [] [] arr1, int [] [] arr2){
		int [] [ ] diff = new int [arr1.length][arr1[0].length];
		for(int r=0;r<arr1.length;r++) {
			for(int c=0; c<arr1[0].length;c++) {
				diff[r][c]=arr1[r][c]-arr2[r][c];
			}
		}
		return diff;
	}
/*@Aditi @March 11
 * The method checkMult verifies if the two arrays passed through can be multiplied together.
 * This method is called before the mult method to ensure the program does not crash.
 * @Param: 2 int [] [] arrays to be verified @Return: boolean indicating if matrices can be multiplied
 */
	private static boolean checkMult(int [] [] arr1, int [] [] arr2) {
		if(arr1[0].length==arr2.length)
			return true;
		else
			return false;
	}
/*@Aditi @March 11
 * The method mult calculates the product of the 2 matrices passed through. 
 * It returns the matrix product.
 * @Param: 2 int [] [] arrays to serve as matrices multiplied @Return: matrix product
 */
	private static int [] [] mult(int [] [] arr1, int [] [] arr2){
		int [] [] res=new int [arr1.length][arr2[0].length];
		//row of arr1 kept constant and cycled through every column of arr2
		//move down a row and continue
			for(int row=0; row<arr1.length;row++) {
				int sum=0;
				//cycle through all columns that correspond to row of arr1
				for(int col=0; col<arr2[0].length;col++) {
					//moving down single column of arr2
					for(int k=0;k<arr2.length;k++) {
						sum=sum+arr1[row][k]*arr2[k][col];
					}
					res[row][col]=sum;
					sum=0;
				}
			}
		return res;
	}
/*@Aditi @March 11
 * The method display generates the display shown to the user indicating the arrays inputted and
 * the results of the matrix calculations. It calls the method calculations after verifying them
 * through the checkSumDiff and checkMult methods.
 * @Param: 2 int [] [] arrays to serve as matrices displayed/calculated 
 * @Return: void
 */
	private static void display(int [] [] arr1, int [] [] arr2) {
		System.out.println("=============ARRAY1=============");
		display2DArray(arr1);
		System.out.println("=============ARRAY2=============");
		display2DArray(arr2);
		System.out.println("=============SUM=============");
		if(checkSumDiff(arr1, arr2))
			display2DArray(sum(arr1, arr2));
		else
			System.out.println("Cannot Add the Arrays");
		System.out.println("=============DIFFERENCE========");
		if(checkSumDiff(arr1, arr2))
			display2DArray(diff(arr1, arr2));
		else
			System.out.println("Cannot Subtract the Arrays");
		System.out.println("=============MULTIPLY=========");
		if(checkMult(arr1, arr2))
			display2DArray(mult(arr1, arr2));
		else
			System.out.println("Cannot Multiply the Arrays");
		System.out.println("#########################################");
	}
}
