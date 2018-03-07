/* Name: Muzhou,Zhai 
 * Course: CPSC331
 * Tutorial Section: T02
 * Assignment 1
 */
import java.util.Scanner;
public class A1_methods {
	/*Subprogram 1:
	 input:
	 i. Size of matrix A (user input, i.e., SP 1 asks the user to define size parameters)
	 ii. Size of matrix B (user input, i.e., SP 1 asks the user to define size parameters)
	 iii. Elements of matrix A (user input, i.e., SP 1 asks the user to define each element)
	 iv. Elements of matrix B (user input, i.e., SP 1 asks the user to define each element)
	 output:
	 i. A 2-dimensional array of size m × n representing matrix A
	 ii. A 2-dimensional array of size n × p representing matrix B
	 */
/*
 * Precondition P1:
m: a positive integer of row number of matrixA(1<=m<=5)
n: a positive integer of column number of matrixA(1<=n<=5)
n: a positive integer of row number of matrixB(1<=n<=5)
P: a positve number of column number of matrixB(1<=p<=5)
elements of matrixA in the position
matrixA[0][0]; matrixA[0][1]; … ; matrixA[m][n-1]; matrixA[m][n]
elements of matrixB in the position
matrixB[0][0]; matrixB[0][1]; … ; matrixB[m][n-1]; matrixB[n][p]
-key: the column number of matrixA should equal to the row number of matrixB, that are both n.

postcondition Q1:
-matrixA: a 2 dimensional array of m rows and n columns, with entries
matrixA[0][0]; matrixA[0][1]; … ; matrixA[m][n-1]; matrixA[m][n]
-matrixB: a 2 dimensional array of n rows and p columns, with entries
matrixB[0][0]; matrixB[0][1]; … ; matrixB[n][p-1]; martixB[n][p]
-inputs (and other variables) have not changed.

This pair of precondition and postcondition describe what should happen for a “successful subprogram1”.

Precondition P2:
m: a positive integer of row number of matrixA(1<=m<=5)
n: a positive integer of column number of matrixA(1<=n<=5)
n: a positive integer of row number of matrixB(1<=n<=5)
P: a positve number of column number of matrixB(1<=p<=5)
elements of matrixA in the position
matrixA[0][0]; matrixA[0][1]; … ; matrixA[m][n-1]; matrixA[m][n]
elements of matrixB in the position
matrixB[0][0]; matrixB[0][1]; … ; matrixB[n][p-1]; matrixB[n][p]
-key: the column number of matrixA does not equal to the row number of matrixB, that are both n.

Postcondition Q2:
-A notFoundException is thrown.
-inputs (and other variables) have not changed.

This pair of precondition and postcondition describe what should happen for an “unsuccessful subprogram2”.

 */
	//creating new parameters 
	int m; int n; int p; int x; int temp; 
	//creating 3 2-dentional arrays that will be calculated later 
	int matrixA[][]; int matrixB[][]; int matrixC[][];
	//a method for getting the row number of matrixA from the user
	public void Arow(){
		//creating a new scanner
		Scanner Am = new Scanner(System.in);
		//print out a notification to let user input an integer
		System.out.println("Please enter the row number of the Matrix A: ");
		//scan in the number that the user input
		int m1 = Am.nextInt();
		//Exception handler: for the case that the user's input is out of bound
		if((m1<1)||(m1>5)){
			//if the input is out of bound, notice user to input another integer 
			System.out.println("Please enter an integer between 1 and 5: ");
			//execute the scanner again
			Arow();
		}
		//until the input is between 1 and 5
		else{
		//parameter m equal the final input. 
		m=m1;
		}
	}
	//a method for getting the column number of matrixA from the user
	public void Acolumn(){
		//creating a new scanner
		Scanner An = new Scanner(System.in);
		//print out a notification to let user input an integer
		System.out.println("Please enter the coloumn number of the Matrix A: ");
		//scan in the number that the user input
		int n1 = An.nextInt();
		//Exception handler: for the case that the user's input is out of bound
		if((n1<1)||(n1>5)){
			//if the input is out of bound, notice user to input another integer 
			System.out.println("Please enter an integer between 1 and 5: ");
			//execute the scanner again
			Acolumn();
		}
		//until the input is between 1 and 5
		else{
		//parameter n equal the final input. 
		n=n1;
		}
	}
	//Matrix B
	//a method for getting the row number of matrixB from the user
	public void Brow(){
		//creating a new scanner
		Scanner Bn = new Scanner(System.in);
		//print out a notification to let user input an integer
		System.out.println("Please enter the row number of the Matrix B: ");
		//scan in the number that the user input
		int n2 = Bn.nextInt();
		//Exception handler: for the case that the user's input is not equal to n
		if(n2 != n){
			//if the input is out of bound, notice user to input another integer 
			System.out.println("The row number of matrix B should be equal to the column number of matrix A,");
			System.out.println("otherwise the calculation is invalid.");
			//execute the scanner again
			Brow();
		}
		//until the input is equal to n
		else{
		//parameter n equal the final input. 
		n=n2;
		}
	}
	//a method for getting the column number of matrixB from the user
	public void Bcolumn(){
		//creating a new scanner
		Scanner Bp = new Scanner(System.in);
		//print out a notification to let user input an integer
		System.out.println("Please enter the coloumn number of the Matrix B: ");
		//scan in the number that the user input
		int p1 = Bp.nextInt();
		//Exception handler: for the case that the user's input is out of bound
		if((p1<1)||(p1>5)){
			//if the input is out of bound, notice user to input another integer 
			System.out.println("Please enter an integer between 1 and 5: ");
			//execute the scanner again
			Bcolumn();
		}
		//until the input is between 1 and 5
		else{
		//parameter p equal the final input. 
		p=p1;
		}
	}
	/*Subprogram2:
	 * Input:
	    i. A 2-dimensional array of size m × n representing matrix A
		ii. A 2-dimensional array of size n × p representing matrix B
	   Output:
		i. A 2-dimensional array of size m × p representing matrix C
	 */
	//A method for calulate the mutiplication result of two matrixs.
/*
 * Precondition P1: Inputs include
-matrixA: a 2 dimensional array of m rows and n columns, with entries
matrixA[0][0]; matrixA[0][1]; … ; matrixA[m][n-1]; matrixA[m][n]
-matrixB: a 2 dimensional array of n rows and p columns, with entries
matrixB[0][0]; matrixB[0][1]; … ; matrixB[n][p-1]; martixB[n][p]
-key: the column number of matrixA should equal to the row number of matrixB, that are both n.
.
Postcondition Q1:
-output is a new 2 dimensional array of m rows and p columns, with entris
matrixC[0][0]; matrixC[0][1]; … ; matrixC[m][p-1]; matrixC[m][p]. If and only if key (the column number of matrixA should equal to the row number of matrixB, that are both n.) is satisfied.
-inputs (and other variables) have not changed.

This pair of precondition and postcondition describe what should happen for a “successful subprogram2.”

Precondition P2:
-matrixA: a 2 dimensional array of m rows and n columns, with entries
matrixA[0][0]; matrixA[0][1]; … ; matrixA[m][n-1]; matrixA[m][n]
-matrixB: a 2 dimensional array of n rows and p columns, with entries
matrixB[0][0]; matrixB[0][1]; … ; matrixB[n][p-1]; martixB[n][p]
-key: the column number of matrixA does not equal to the row number of matrixB, that are not both n.

Postcondition Q2:
-A notFoundException is thrown.
-inputs (and other variables) have not changed.

This pair of precondition and postcondition describe what should happen for an “unsuccessful subprogram2”.
 */
	public void Calculation(){
		//initializing the size of matrixA
		int [][] matrixA=new int[m][n];
		//The outer loop for the row number of the first dimension of the matrix[][].
		for(int i=0; i<m; i++){
			//The inner loop for the column number of the second dimension of the matrix[][].
			for(int j=0; j<n; j++){
				//creating a new scanner
				Scanner Aelement = new Scanner(System.in);
				//notice the user to input elements one by one
				System.out.println("please enter the No."+(j+1)+ " elements in line " +(i+1)+" (matrixA):");
				//get the input element one by one.
				matrixA[i][j]=Aelement.nextInt();
				}	
			}
		//initializing the size of matrixB
		int [][] matrixB=new int[n][p];
		//The outer loop for the row number of the first dimension of the matrix[][].
		for(int i=0; i<n; i++){
			//The inner loop for the column number of the second dimension of the matrix[][].
			for(int j=0; j<p; j++){
				//creating a new scanner
				Scanner Belement = new Scanner(System.in);
				//notice the user to input elements one by one
				System.out.println("please enter the No."+(j+1)+ " elements in line " +(i+1)+" (matrixB):");
				//get the input element one by one.
				matrixB[i][j]=Belement.nextInt();
			}	
		}
		//initializing the size of matrixB
		int [][] matrixC = new int[m][p];
		//The outer loop for the row number of the first dimension of the matrix[][].
		for(int i = 0;i<m ;i++)
		{
			//The middle loop for the column number of the second dimension of the matrix[][].
			for(int j = 0;j<p;j++)
			{
				//initializing the parameter temp = 0
				temp = 0;
				//The inner loop for culculating the new elements of matrixC[][] 
				for(x = 0;x<n;x++)
				{
					//temp = temp + matrixA[i][x]*matrixB[x][j]
					temp+=matrixA[i][x]*matrixB[x][j];
				}
				//the value of matrixC[i][j] is equal to temp
				matrixC [i][j] = temp;
				}
			}
		/*Subprogram3:
	    Input:
		i. A 2-dimensional array of size m × n representing matrix A
		ii. A 2-dimensional array of size n × p representing matrix B
		iii. A 2-dimensional array of size m × p representing matrix C
		Output:
		i. Print on screen arrays A, B and C in their rectangular form, one after the other.
		 */
/*
 * Precondition P1:
-matrixA: a 2 dimensional array of m rows and n columns, with entries
matrixA[0][0]; matrixA[0][1]; … ; matrixA[m][n-1]; matrixA[m][n]
-matrixB: a 2 dimensional array of n rows and p columns, with entries
matrixB[0][0]; matrixB[0][1]; … ; matrixB[n][p-1]; martixB[n][p]
-metrixC: a2 dimensional array of m rows and p columns, with entris
matrixC[0][0]; matrixC[0][1]; … ; matrixC[m][p-1]; matrixC[m][p]. 
-key: the column number of matrixA should equal to the row number of matrixB, that are both n.
	matrix A * matrix B =matrix C, which can be explained as:
	temp=temp + matrixA[i][x]*matrixB[x][j]
            matrixC[i][j] = temp

postcodition Q1: 
-matrixA: a 2 dimensional array of m rows and n columns on screen, with entries
matrixA[0][0]; matrixA[0][1]; … ; matrixA[m][n-1]; matrixA[m][n]
-matrixB: a 2 dimensional array of n rows and p columns on screen, with entries
matrixB[0][0]; matrixB[0][1]; … ; matrixB[n][p-1]; martixB[n][p]
-metrixC: a2 dimensional array of m rows and p columns on screen, with entris
matrixC[0][0]; matrixC[0][1]; … ; matrixC[m][p-1]; matrixC[m][p].
- input and other variables does not change.

 This pair of precondition and postcondition describe what should happen for a “successful subprogram3.”

Precondition P2:
-matrixA: a 2 dimensional array of m rows and n columns, with entries
matrixA[0][0]; matrixA[0][1]; … ; matrixA[m][n-1]; matrixA[m][n]
-matrixB: a 2 dimensional array of n rows and p columns, with entries
matrixB[0][0]; matrixB[0][1]; … ; matrixB[n][p-1]; martixB[n][p]
-metrixC: a2 dimensional array of m rows and p columns, with entris
matrixC[0][0]; matrixC[0][1]; … ; matrixC[m][p-1]; matrixC[m][p]. 
-key: the column number of matrixA is not to the row number of matrixB, that are not both n.
	matrix A * matrix B =matrix C, which can be explained as:
	temp=temp + matrixA[i][x]*matrixB[x][j]
            matrixC[i][j] = temp

Postcondition Q2:
-A notFoundException is thrown.
-inputs (and other variables) have not changed.

This pair of precondition and postcondition describe what should happen for an “unsuccessful subprogram3”.
 */
		//print a blank line
		System.out.println();
		//print the "The matrix A you entered is:"on the screen.
		System.out.println("The matrix A you entered is:");
		//The outer loop for the row number of the first dimension of the matrix[][].
		for(int i=0; i<m; i++){
			//The inner loop for the column number of the second dimension of the matrix[][].
			for(int j=0; j<n; j++){
				//print the input element on the [i][j] position inside the matrixA.
				System.out.print(matrixA[i][j]+"\t");
			}
			//adding two blank lines
			System.out.println("\n\n");
		}
		//print a blank line
		System.out.println();
		//print the "The matrix B you entered is:"on the screen.
		System.out.println("The matrix B you entered is:");
		//The outer loop for the row number of the first dimension of the matrix[][].
		for(int i=0; i<n; i++){
			//The inner loop for the column number of the second dimension of the matrix[][].
			for(int j=0; j<p; j++){
				//print the input element on the [i][j] position inside the matrixB.
				System.out.print(matrixB[i][j]+"\t");
			}
			//adding two blank lines
			System.out.println("\n\n");
		}
		//print a blank line
		System.out.println();
		//print some notification on the screen
		System.out.println("matrix A * matrix B = matrix C");
		//print the "The matrix C is:"on the screen.
		System.out.println("The matrix c is:");
		//The outer loop for the row number of the first dimension of the matrix[][].
		for(int i = 0;i<m;i++)
		{
			//The inner loop for the column number of the second dimension of the matrix[][].
			for(int j = 0;j<p;j++)
			{
				//print the input element on the [i][j] position inside the matrixC.
				System.out.print(matrixC[i][j]+"\t");
			}
			//adding two blank lines
			System.out.println("\n\n");
		}
	}
}
	
