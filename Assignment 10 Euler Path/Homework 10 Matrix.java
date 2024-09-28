/*You are going to write two programs. They will both do the same thing, but they will use two different internal 
representations of graphs. The program should ask for  a list of vertices, followed by a list of edges. It should 
then indicate whether an Euler path exists in the graph. The input and display format are shown below: 
Note that vertices can be assumed to be single characters separated by spaces. The program's output should be one of 
either "This graph contains an Euler path." or "This graph does not contain an Euler path.". Also, this graph should 
be counted as an undirected graph. Internally, you should treat it as directed. So for the edge "A B" you should create 
both "A B" and "B A" edges. You can use any programming language you like.
Implement the above program using an adjacency matrix represtation of the graph.*/
//Written by: Jenna Wolf
//Date: 11/05/2023
//Class: CS345, Discrete Structures II

import java.util.Scanner;       //allows input to be made
import java.util.ArrayList;     //allows the arraylist class to be used

public class Main
{
	public static void main(String[] args) 
	{
	    Scanner input = new Scanner(System.in); //names scanner to input
	    String[] verticies;     //holds the verticies data
	    ArrayList<String> edge = new ArrayList<String>();   //holds the edge data
	    String temp = " ";  //holds the temp data and sets it to space
	    int count = 0;      //holds the count data and sets it to 0
	    int num = 0;        //holds the num data and sets it to 0
	    int numOfOdds = 0;  //holds the numOfOdds data and sets it to 0
	    
	    //asks for and takes in the number of verticies
		System.out.println("Enter your Vertacies (seperated by a space): ");
		verticies = input.nextLine().split(" ");
		count = verticies.length;   //sets count to the number of verticies
		
		//creates a blank matrix the size of the number of verticies
		int[][] matrix = new int[count][count];
		for(int i = 0; i < count; i++)
		{
		    for(int j = 0; j < count; j++)
		        matrix[i][j] = 0;
		}

        //asks for and takes in the edges. makes sure a proper edge is added
		System.out.println("Enter Undirected Edges (Enter a . on a line by itself to end.): ");
		for(int i = 0; !temp.equals("."); i++)
		{
		    temp = input.nextLine();
		    if(temp.length() == 3 && !temp.equals("."))
		        edge.add(temp);
		    else if(!temp.equals("."))
		        System.out.println("Please enter a proper edge!");
		}
		
		//goes through each edge and sets the corresponding matrix position to 1
		for(int i = 0; i < edge.size(); i++)
		{
		    temp = edge.get(i);
		    for(int j = 0; j < count; j++)
		    {
		        for(int l = 0; l < count; l++)
		        {
		            if(verticies[j].charAt(0) == temp.charAt(0) && verticies[l].charAt(0) == temp.charAt(2))
		            {
		                matrix[j][l] = 1;
		                matrix[l][j] = 1;
		            }
		        }
		    }
		}
		
		//counts the number of verticies with an odd number of edges
		for(int i = 0; i < count; i++)
		{
		    for(int j = 0; j < count; j++)
		    {
		        if(matrix[i][j] == 1)
		            num++;
		    }
		    if(num % 2 == 1)
		        numOfOdds++;
		    num = 0;
		}
		
		//tells the user weather there is a Euler path or not
		if(numOfOdds > 2)
		    System.out.println("This graph does not contain an Euler path.");
		else
		    System.out.println("This graph contains an Euler path.");
	}
}