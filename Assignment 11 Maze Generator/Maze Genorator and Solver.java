/*Your task is to write a program which generates mazes and then solves them.
Note that the way the program should display its output is "#" wall " " (space) - empty space
"S" - Starting point "E" - Ending Point. When it displays the solution, it places a "o" in 
each grid spot where the line goes. If you choose to not display the solution, the program 
should display its closing message and exit. Note that the maze should be randomly generated 
using the randomized Prim's algorithm. You may write your program in any language that you wish, 
just submit a zip archive of all the files needed to run your code. Enjoy! */
//Written by: Jenna Wolf
//Date: 11/13/2023
//Class: CS345, Discrete Structures II

import java.util.Scanner;   //lets inputs be made
import java.util.Random;    //lets random happen
import java.util.ArrayList; //adds the arraylist class

public class Main
{
	public static void main(String[] args) 
	{
	    Scanner input = new Scanner(System.in);     //names the input object
	    Random rand = new Random();         //names the random object
	    int col = 0, row = 0;               //holds the col and row data and sets them to 0
	    char decision = 'n';                //holds the decision data and sets it to n
	    
	    //takes in the col and row data from the user
		System.out.println("Welcome to the Maze Generator!");
		System.out.print("How many columns? ");
		col = input.nextInt();
		System.out.print("How many rows? ");
		row = input.nextInt();
		
		String[][] maze = new String[col][row]; //creates the maze of size col and row
		
		//sets each space to X
		for(int i = 0; i < row; i++)
		{
		    for(int j = 0; j < col; j++)
		        maze[j][i] = "X";
		}
		
		//calls the generateMaze function and sets start to the node returned
		node start = generateMaze(maze, rand, col, row);
		
		//outputs the unsolved maze
	    for(int i = 0; i < row; i++)
		{
		    for(int j = 0; j < col; j++)
		        System.out.print(maze[j][i]);
		    System.out.println();
		}
		
		//asks the user if they want the maze solved and makes sure a proper input is made
		System.out.print("Would you like to see the solution? (y/n): ");
		decision = input.next().charAt(0);
		while(decision != 'y' && decision != 'n')
		{
		    System.out.print("Please enter y or n only! ");
		    decision = input.next().charAt(0);
		}
		
		//if decision is yes, finds the solution to the maze
		if(decision == 'y')
		{
		    ArrayList<node> queue = new ArrayList<>();      //holds the queue data
		    boolean[][] visited = new boolean[col][row];    //holds the visited data
		    ArrayList<node> path = new ArrayList<>();       //holds the path data
		    node current, next;     //holds the current and next data
		    boolean test = false;   //holds the test data and sets it to false
		    int x = 0, y = 0;       //holds the x and y data and sets them to 0
		    
		    queue.add(start);   //adds start to queue
		    visited[start.x][start.y] = true;   //sets the start block in visited to true
		    
		    //loops while there is a variable in queue
		    while(!queue.isEmpty())
		    {
		        current = queue.remove(0);  //sets current to the start of the queue and removes it from queue
		        x = current.x;  //sets x to currents x
		        y = current.y;  //sets y to currents y
		        
		        //checks to see if we hit E. if we did, creates a path from S to E and breaks the while loop
		        if(maze[x][y].equals("E"))
		        {
		            while(current != null)
		            {
		                path.add(current);
		                current = current.parent;
		            }
		            break;
		        }
		        
		        //checks 1 up, 1 down, 1 left, and 1 right. if conditions are met, sets the parent node,
		        //adds node to queue, and sets the visited block to true
		        if(x > 0 && (maze[x - 1][y].equals(" ") || maze[x - 1][y].equals("E")) && !visited[x - 1][y])
		        {
		            next = new node(x - 1, y);
		            next.parent = current;
		            queue.add(next);
		            visited[x - 1][y] = true;
		        }
		        if(x < col - 1 && (maze[x + 1][y].equals(" ") || maze[x + 1][y].equals("E")) && !visited[x + 1][y])
		        {
		            next = new node(x + 1, y);
		            next.parent = current;
		            queue.add(next);
		            visited[x + 1][y] = true;
		        }
		        if(y > 0 && (maze[x][y - 1].equals(" ") || maze[x][y - 1].equals("E"))  && !visited[x][y - 1])
		        {
		            next = new node(x, y - 1);
		            next.parent = current;
		            queue.add(next);
		            visited[x][y - 1] = true;
		        }
		       if(y < row - 1 && (maze[x][y + 1].equals(" ") || maze[x][y + 1].equals("E")) && !visited[x][y + 1])
		        {
		            next = new node(x, y + 1);
		            next.parent = current;
		            queue.add(next);
		            visited[x][y + 1] = true;
		        }
		    }
		    
		    //outputs the solved version of the maze. uses if and for loops to see if we need to
		    //output path or maze.
		    for(int i = 0; i < row; i++)
		    {
		        for(int j = 0; j < col; j++)
		        {
		            current = new node(j, i);
		            for(int t = 0; t < path.size(); t++)
		            {
		                if(path.get(t).x == current.x && path.get(t).y == current.y && 
		                    (!maze[j][i].equals("S") && !maze[j][i].equals("E")))
		                {
		                    System.out.print("o");
		                    test = true;
		                }
		            }
		                if(!test)
		                    System.out.print(maze[j][i]);
		                test = false;
		        }
		        System.out.println();
		    }
		    
		    //goodbye message
		    System.out.println("Thank you for playing, now get back to your school work!");
		}
		else
		    System.out.println("Then leave!");  //goodbye message
	}
	
	public static node generateMaze(String[][] maze, Random rand, int col, int row)
	{
	    int X1 = rand.nextInt(col); //holds the X1 data and sets it to a random varaible less than col
	    int Y1 = rand.nextInt(row); //holds the Y1 data and sets it to a random varaible less than row
	    ArrayList<node> list = new ArrayList<>();   //holds the list data
	    int count = 0;  //holds the count data and sets it to 0
	    node lastNode = new node(X1, Y1);   //holds the lastNode data and sets it to X2 and Y1
	    
	    maze[X1][Y1] = " "; //sets the start to a blank
	    addNodes(list, col, row, X1, Y1);   //calls addNodes and adds the nodes to list
	    
	    //loops while list it not empty
	    while(!list.isEmpty())
	    {
	        int x = 0, y = 0;   //holds the x and y data and sets it to 0
	        int index = rand.nextInt(list.size());  //holds the index data and sets it to a random variable on list
	        node cell = list.get(index);    //holds the cell data and sets it to the value on list that matches index
	        list.remove(index);             //removes the value on list that matches index
	        x = cell.x;     //sets x to cell.x
	        y = cell.y;     //sets y to cell.y
	        count = 0;      //sets count to 0
	        
	        //checks 2 above, 2 below, 2 right, and 2 left of the current node and sets. if conditions
	        //are met, adds one to count and sets the lastNode
	        if(x > 1 && maze[x - 2][y] == " ")
	        {
	            count++;
	            lastNode = new node(x - 2,y);
	        }
	        if(x < col - 2 && maze[x + 2][y] == " ")
	        {
	            count++;
	            lastNode = new node(x + 2,y);
	        }
	        if(y > 1 && maze[x][y - 2] == " ")
	        {
	            count++;
	            lastNode = new node(x,y - 2);
	        }
	        if(y < row - 2 && maze[x][y + 2] == " ")
	        {
	            count++;
	            lastNode = new node(x,y + 2);
	        }
	        if(count == 1 && x != col - 1 && x != 0 && y != row - 1 && y != 0)
	        {
	            maze[x][y] = " ";
	            maze[(lastNode.x + cell.x) / 2][(lastNode.y + cell.y) / 2] = " ";
	            addNodes(list, col, row, x, y);
	        }
	    }
	    
	    maze[X1][Y1] = "S"; //sets the start of the maze to S
	    maze[lastNode.x][lastNode.y] = "E"; //sets the end of the maze to E
	    
	    return new node(X1, Y1);    //returns the first node (S block)
	}
	
	public static void addNodes(ArrayList<node> list, int col, int row, int x, int y)
	{
	    //checks 2 up, 2 down, 2 left, and 2 right of current node. if conditions are met,
	    //node is added to the list.
	    if(x > 1)
	        list.add(new node(x - 2, y));
	    if(x < col - 2)
	        list.add(new node(x + 2, y));
	    if(y > 1)
	        list.add(new node(x, y - 2));
	    if(y < row - 2)
	        list.add(new node(x, y + 2));
	}
}