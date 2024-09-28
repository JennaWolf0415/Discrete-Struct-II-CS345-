/* 1. Prompt the user to enter 5-Tuples in standard form. Each 5-tuple in the table is entered on a line by itself, 
and input is terminated by entering a period '.' on a line by itself.
2. The user is then prompted to enter the initial tape string (it may be blank).
3. The user is then prompted for the maximum number of iterations.
4. The machine then executes.  
5. In each iteration, the machine prints its tape and state.  Write {state} to the left of the current position on the tape.
6. The machine ends its run when it reaches a halting state (that is, it reaches a configuration not in its table),
or when the maximum number of iterations have been reached.  The machine should indicate which is the case.
7. The machine prints its final state.*/
//Written by: Jenna Wolf
//Date: 9/25/2023
//Class: CS345, Discrete Structures II


import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) 
	{
	    //holds all the different data values
	    Scanner input = new Scanner(System.in);
	    ArrayList<String> tups = new ArrayList<String>();
	    String tup = "";
	    char temp;
	    int ptr = 0;
	    int check = 0;
		int max = 0;
		int count = 0;
		
		//take in tuples
		System.out.println("Please enter your tuples. Put a . with nothing else when you are done.");
		do{
		    tup = input.nextLine();
		    if(tup.length() == 5 && !tup.equals("."))
		        tups.add(tup);
		    else if(!tup.equals("."))
		        System.out.print("Please enter a proper tuple! ");
		}while(!tup.equals("."));
		
		//take in tape
		System.out.print("Please enter your tape: ");
		String tape = input.nextLine();
		
		//take in max
		System.out.print("Please enter the max number of iterations: ");
		max = input.nextInt();
		
		//prints out first iteration
		String[] config = tups.get(0).split("");
		System.out.print("{" + config[0] + "}");
		System.out.println(tape);
		
		while(true)
		{
		    //counts the iterations
		    if(count == max)
		        break;
              
            //adds to tape  
		    if(!tape.isEmpty())
		    {
		        if(ptr >= 0 || ptr < (tape.length() + 1))
		        {
		            if(ptr < tape.length())
		                tape = tape.substring(0, ptr) + config[2] + tape.substring(ptr + 1);
		            else
		                tape = tape.substring(0, ptr) + config[2];
		        
		        }
		        else
		        {
		            System.out.println("HALTED");
                    break; 
		        }
		    }
		    else
		        tape = config[2];
		    
		    //Move position of pointer
		    if(config[3].equals("R")) 
                ptr++;
            else if (config[3].equals("L"))
            {
                if(ptr == 0)
                    tape = " " + tape;
                else
                    ptr--;  
            }
            else if(!config[3].equals("N"))
            {
                System.out.println("Invalid Input, Machine HALTED");
                break;
            }
            
            //print current tape
            for(int i = 0; i < tape.length(); i++)
            {
                if(i == ptr && check == 0)
                {
                    System.out.print("{" + config[4] + "}");
                    check = 1;
                    i--;
                }
                else
                    System.out.print(tape.charAt(i));
            }
            if(check == 0)
                System.out.print("{" + config[4] + "}");
            check = 0;
            System.out.println();
            
            //switch to correct config
            for(int i = 0; i < tups.size(); i++)
            {
                if(ptr >= 0 && ptr < tape.length())
                    temp = tape.charAt(ptr);
                else
                    temp = ' ';
                    
                if(tups.get(i).charAt(0) == config[4].charAt(0) && tups.get(i).charAt(1) == temp)
                {
                    config = tups.get(i).split("");
                    check = 1;
                    break;
                }
                
            }
            if(check == 0)
            {
                System.out.print("HALTED");
                break;
            }
                
            check = 0;  //set check to 0
            count++;    //add 1 to count
		}
		
		if(count == max)
		    System.out.println("Max Iteration Reached");
		    
		System.out.println("Final Configuration " + config[0]);
		
		
	}
}