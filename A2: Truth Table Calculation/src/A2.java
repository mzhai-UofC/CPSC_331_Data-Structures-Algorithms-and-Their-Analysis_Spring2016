/* Name: Muzhou,Zhai 
 * Course: CPSC331
 * Tutorial Section: T02
 * Assignment 2
 * Due Date: June 9th, 2016
 */
import java.util.Scanner;
import java.util.Stack;
public class A2 {
		//SP1: get the input logical expression from the user
		public static void main(String[] args) {
			try{
				//introduce how to use this program to users
				System.out.println("explain:");
				System.out.println("This programs allows input: variables A-Z,a-z, and symbols '+''*''-'");
				System.out.println("Character '*' represents logical AND");
				System.out.println("Character '+' represents logical OR");
				System.out.println("Character '-'represents logical NOT");
				System.out.println("'1' stands for ture and '0' stands for false");
				//initialiing a string str for an example to user
				String str="A+B";
				System.out.println("For example, if you enter the logical expression "+str+" The result will be");
				//creating a new scanner s for getting the string from user
				Scanner s=new Scanner(System.in);
				//to check if the input string is empty or null
				while (!(str.equals("") || str == null)) {
					//if not, excute the printTruthTable method
					TruthTable.printTruthTable(str);
					System.out.println("Please enter a logical expression:");
					//Scan in the input string stored in str 
					str=s.nextLine();
					}
				} 
			//Exception handler for the invalid input string 
			catch (Exception e) {
					System.out.println("error occurs!");
				}
				System.out.println("The program exits");
			}
		}
		//SP2: Calculating the truth value among the subexpressions
		//a abstrct method for calculating and print the truth table
		abstract class TruthTable{
			//a method to decide the order of each symbols
			private static int Priority(char op)
			{
				switch (op) {
				//or
				case '+':
					return 1;
				//and
				case '*':
					return 2;
				//not
				case '-':
					return 3;
				//the begining of a pair of '()'
				case '(':
				default:
					return 0;
				}
			}
			//This method works for converting strings to suffix
			private static String toSuffix(String str) throws Exception {
				//creating a new string buffer to get each characters from the string.
				StringBuffer strBuf = new StringBuffer();
				//using a new stack
				Stack<Character> stack = new Stack<Character>();
				//pushing the symbol '=' into the stack, for the futher calculation.
				stack.push('=');
				//setting i as an counter variable
				int i = 0;
				//searching each symbols from the beginning of the string.
				char ch = str.charAt(i);
				//since the symbol '=' dose not being calculated in this program, so every valid expression has the same case
				while (ch!='=') {
					//empty space is not allowed
					if (ch == ' ')
						throw new Exception("empty space is not allowed");
					//checking if there is a complete pair of '()'
					else if (ch == '(')
						//pushing '(' into the stack
						stack.push('(');
					//lack of '('
					else if (ch == ')') {
						while (stack.peek() != '(') {
							if (stack.peek() == '=')
								throw new Exception("Lack of'('");
							//poping the former symbol from the stack and the string buffer
							strBuf.append(stack.pop());
						}
						//poping the former symbol from the stack
						stack.pop();
						//checking the input symbols, only these 3 symbols are allowed
					} else if (ch == '*' || ch == '+' || ch == '-') {
						//n new variable char w for checking the priority of the symbols
						char w = stack.peek();
						//if w is piror of ch
						while (Priority(w) >= Priority(ch)) {
							//give the value w to the string buffer 
							strBuf.append(w);
							//poping the former symbol from the stack
							stack.pop();
							//give the value to w
							w = stack.peek();
						}
						//pushing ch into the stack
						stack.push(ch);
					} else {
						//checking if the input variable is valid
						if (!((ch >= 'A' && ch <= 'Z') || (ch>= 'a'&& ch<='z')||ch=='0' || ch=='1'))
							//if not, a exception handler will be thrown
							throw new Exception("unexcepted symbols or variable");
						//excute strBuf.append()
						strBuf.append(ch);
					}
					// increase the counter i
					ch=str.charAt(++i);
				}
				//poping the former symbol from the stack
				ch = stack.pop();
				while (ch != '=') {
					//checking if there is a complete pair of '()'
					if (ch == '(')
						//if not, an exception handler will be thrown
						throw new Exception("lack of '('");
					else {
						//excute strBuf.append()
						strBuf.append(ch);
						//poping the former symbol from the stack, and the char ch will get the value
						ch = stack.pop();
					}
				}
				//return the value
				strBuf.append('=');
				return strBuf.toString();
			}
			//a method for calculation the truth value of the transformed surffix
			private static boolean runSuffix(String str) throws Exception
			{
				//creating a new stack in the boolean type
				Stack<Boolean> stack=new Stack<Boolean>();
				//initializing a counter for the calculation
				int i=0;
				//2 boolean type number for checking the truth value
				Boolean num1,num2;
				//the tesing symbol '='
				while(str.charAt(i)!='=')
				{
					//getting the truth value of each variable
					if(str.charAt(i)=='0')
						stack.push(false);
					else if(str.charAt(i)=='1')
						stack.push(true);
						//comparing the 2 variables adjacent to the symbol "*"
					else if(str.charAt(i)=='*')
					{
						if(stack.size()<2)
							throw new Exception("Lack of variable");
						num1=stack.pop();
						num2=stack.pop();
						//return and store the value according to the calculating result
						if(num1.equals(true) && num2.equals(true))
							stack.push(true);
						else
							stack.push(false);
					}
					//comparing the 2 variables adjacent to the symbol "+"
					else if(str.charAt(i)=='+')
					{
						if(stack.size()<2)
							throw new Exception("lack of variable");
						num1=stack.pop();
						num2=stack.pop();
						//return and store the value according to the calculating result
						if(num1.equals(false) && num2.equals(false))
							stack.push(false);
						else
							stack.push(true);
					}
					//comparing the 2 variables adjacent to the symbol "-"
					else if(str.charAt(i)=='-')
					{
						if(stack.size()<1)
							throw new Exception("lack of variable");
						num1=stack.pop();
						//return and store the value according to the calculating result
						if(num1.equals(true))
							stack.push(false);
						else
							stack.push(true);
					}
					//increasing the counter by 1 at the end of the loop
					i++;
				}
				//error symbol
				if(stack.size()!=1)
					throw new Exception("lack of variable");
				return stack.pop();
			}
			//SP3: printing the truth table
			//a method for printing the truth table
			public static void printTruthTable(String str){
				try{
					//adding the test symbol to suffix
					String suffix=toSuffix(str+"=");
					StringBuffer strBuf;
					//include A-Z and a-z, so totally size of 52
					boolean[] letterExist=new boolean[52];
					//the false case
					//counter i for the loop
					for(int i=0;i<52;i++)
						letterExist[i]=false;
					//counter i for the loop
					for(int i=0;i<str.length();i++){
						//for the lower case
						int l=(str.charAt(i)-'a');
						//for the upper case 
						int K=(str.charAt(i)-'A');
						//the bound of all the variables
						if((K>=0 && K<=25)||(l>=0&&l<=25)){
							//the true case
							letterExist[K]=true;
						}
					}
					// letter counter for print out the symbol to the truth table
					int letterCount=0;
					char[] letter=new char[52];
					for(int i=0;i<52;i++){
						if(letterExist[i]){
							letter[letterCount]=(char)(i+'A');
							letterCount++;
							System.out.print((char)(i+'A')+" ");
						}
					}
					
					System.out.println(str);
					System.out.println();
					//this section is for printout the suffix result from the variables
					int times=(int)Math.pow(2, letterCount);
					for(int i=0;i<times;i++){
						strBuf=new StringBuffer(suffix);
						//the loop counter 
						int num=i;
						for(int j=0;j<letterCount;j++){
							//print the truth value cases for the variables
							int bit=num%2;
							num/=2;
							//for print
							System.out.print((char)(bit+'0')+" ");
							for(int k=0;k<strBuf.length();k++){
								if(strBuf.charAt(k)==letter[j])
									strBuf.setCharAt(k, (char)(bit+'0'));
							}
						}
						//the true case
						if(runSuffix(strBuf.toString()))
							System.out.print("1");
						//the false case
						else
						System.out.print("0");
						System.out.println();
					}
					System.out.println();
					//exception handler for invalid expression
				}catch(Exception e){
					System.out.println();
					System.out.println("The expression you entered is incorrect, please try again");
					if(e.getMessage().equals("") || e.getMessage()==null)
						System.out.println("error");
					else
						System.out.println("error"+e.getMessage());
				}
			}
		}
