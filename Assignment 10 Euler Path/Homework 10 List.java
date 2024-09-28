/*You are going to write two programs. They will both do the same thing, but they will use two different internal 
representations of graphs. The program should ask for  a list of vertices, followed by a list of edges. It should 
then indicate whether an Euler path exists in the graph. The input and display format are shown below: 
Note that vertices can be assumed to be single characters separated by spaces. The program's output should be one of 
either "This graph contains an Euler path." or "This graph does not contain an Euler path.". Also, this graph should 
be counted as an undirected graph. Internally, you should treat it as directed. So for the edge "A B" you should create 
both "A B" and "B A" edges. You can use any programming language you like.
Implement the above program using an adjacency list represtation of the graph.*/
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
	    String[] num;           //holds the num data
	    ArrayList<String> edge = new ArrayList<String>();   //holds the edge data
	    ArrayList<String> list = new ArrayList<String>();   //holds the list data
	    String temp = " ";      //holds the temp data and sets it to space
	    String ch = " ";        //holds the ch data and sets it to space
	    int count = 0;          //holds the count data and sets it to 0
	    int numOfOdds = 0;      //holds the numOfOdds data and sets it to 0
	    
	    //asks for and takes in the number of verticies
		System.out.println("Enter your Vertacies (seperated by a space): ");
		verticies = input.nextLine().split(" ");
		count = verticies.length;   //sets count to the number of verticies
		
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
		
		//adds the verticies to the list array
		for(int i = 0; i < count; i++)
		    list.add(verticies[i]);
		
		//goes through each edge and adds them to the correct verticies, making a list
		for(int i = 0; i < edge.size(); i++)
		{
		    temp = edge.get(i);
		    for(int j = 0; j < count; j++)
		    {
		        ch = list.get(j);
		        if(ch.charAt(0) == temp.charAt(0))
		            list.set(j, list.get(j) + " " + temp.charAt(2));
		        if(ch.charAt(0) == temp.charAt(2))
		            list.set(j, list.get(j) + " " + temp.charAt(0));
		    }
		}
		    
		//counts the number of verticies with an odd number of edges
		for(int i = 0; i < count; i++)
		{
		    temp = list.get(i);
		    num = temp.split(" ");
		    if((num.length - 1) % 2 == 1 )
		        numOfOdds++;
		}
		
		//tells the user weather there is a Euler path or not
		if(numOfOdds > 2)
		    System.out.println("This graph does not contain an Euler path.");
		else
		    System.out.println("This graph contains an Euler path.");
	}
}
