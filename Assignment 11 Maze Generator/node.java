public class node {
    int x;  //holds the x data
    int y;  //holds the y data
    node parent;    //holds the parent data
    
    //no args constructor
    node() {
        parent = null;
    }
    
    //construcot that takes in 2 integers
    node(int temp1, int temp2) {
        x = temp1;
        y = temp2;
        parent = null;
    }
    
}