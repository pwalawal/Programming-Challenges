/*
The assignment is implemented in java programming language. 
The main aim of this assignment was to implement an event counter. 
The event counter had two fields ID and count where each count is number of active events with given ID. 
The event counter was to be implemented using Red Black Tree. 

*/
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class bbst {
	
		  
	 //color of the nodes
	 private final int Red = 0;
	 private final int Black = 1;

	 private class Red_Black_Node {
		  
		 int ID,count,color;
		 Red_Black_Node left, right,parent;
		 

		 //Constructor for node
		 Red_Black_Node(int ID,int count)
		 {
			 this.ID=ID;
			 this.count=count;
		     left = right = parent=nil;
		     this.color=Black;
		 }
	 	}
	
	 //Creating a nil node and root node
	 private final Red_Black_Node nil = new Red_Black_Node(-1, -1); 
	 private Red_Black_Node root = nil;
	 
	 //A function to create Red Black Tree from input
	 
	 Red_Black_Node Initial_RBT_Creation(int ID_arr[],int count_arr[], int start, int end) {
		 
		//base case
	     if (start > end) {
	         return null;
	     }
	     
	     //Recursively creating Balanced Binary Search Tree
	     int mid = (start + end) / 2;
	     Red_Black_Node node = new Red_Black_Node(ID_arr[mid],count_arr[mid]);
	    
	     //Creating Left Subtree Recursively
	     node.left = Initial_RBT_Creation(ID_arr,count_arr, start, mid - 1);
	     //Assigning parent node to left subtree
	     if(node.left!=null)
	     node.left.parent=node;
	     if(node.left==null)
	    	 node.left=nil;

	     //Creating Right Subtree Recursively
	     node.right = Initial_RBT_Creation(ID_arr,count_arr, mid + 1, end);
	     //Assigning parent node to right subtree
	     if(node.right!=null)
	     node.right.parent=node;
	    if(node.right==null)
	    	 node.right=nil;
	     root=node; 
	     //converting balanced binary search tree to red black tree
	     node=BST_TO_RBT(node);
	     return node;
	     
	 }

	 //Binary Search Tree to Red Black Tree
	 Red_Black_Node BST_TO_RBT(Red_Black_Node node)
	 {
		 Red_Black_Node temp_node=node;
		 //Assigning black color to root node
		 if(temp_node.parent==null){
			
			temp_node.color=Black;
			//Recursively coloring the tree
			if(node.left!=null)
			temp_node.left=BST_TO_RBT(temp_node.left);
			if(node.right!=null)
			temp_node.right=BST_TO_RBT(temp_node.right);
			
		}
		else
		{
			//Recursively coloring the tree
			if(temp_node.parent.color==Black)
			{
				temp_node.color=Red;
				if(node.left!=null)
				temp_node.left=BST_TO_RBT(temp_node.left);
				if(node.right!=null)
				temp_node.right=BST_TO_RBT(temp_node.right);
				
			}
			else
			{
				temp_node.color=Black;
				if(node.left!=null)
				temp_node.left=BST_TO_RBT(temp_node.left);
				if(node.right!=null)
				temp_node.right=BST_TO_RBT(temp_node.right);
				
			}
			
			
			
		}
		//Returning the Red Black Tree
		return temp_node;
	 
	 }

	 //Return the count of theID operation
	 void Count(int theID){
		  
		 //calling the RBT_Count by passing the Red Black tree and theID
		 Red_Black_Node countNode=RBT_Count(root,theID);
		 
		//Count the counter of given theID
		 if(countNode==null){
			 System.out.println(0); 
		 }
		 else
			 System.out.println(countNode.count);
	 
	 }
	 
	 //This function will return the node from tree if theID is present as ID of node
	 Red_Black_Node RBT_Count(Red_Black_Node node,int theID)
	 {
		 while(node!=null && node.ID!=theID)
		 {
			 if(theID<node.ID)
			 {
				 node=node.left;
			 }
			 else
			 {
				 node=node.right;
			 }
		 }
		 if(node==null || node==nil){
			 
			 return null;
			 
		 }
		 else
		 {
			 return node;
		 }
		 
	 }
	 
	 //Get the total count InRange from ID1 to ID2
	 void InRange(int theID1,int theID2){
		 
		 System.out.println(RBT_InRange(root,theID1,theID2));
		 
	 }
	 
	 
	 int RBT_InRange(Red_Black_Node node,int theID1,int theID2){
		 
		 int total=0;
		 
		 //Find the closest node with ID close to theID 
		 Red_Black_Node RBT_Left=ClosestTotheID(root,theID1);
		 Red_Black_Node RBT_Right=ClosestTotheID(root,theID2);
		 
		 
		 //Since the range is is inclusive if Left ID is smaller than given theID1, find its successor 
		 if(RBT_Left.ID<theID1)
		 {
			 RBT_Left=RBT_Successor(RBT_Left);
		 }
		 
		 
		//Since the range is is inclusive if Right ID is greater than given theID1, find its predecessor
		 if(RBT_Right.ID>theID2)
		 {
			 RBT_Right=RBT_Predecessor(RBT_Right);
		 }
		 
		
		 while( RBT_Left!=nil && RBT_Left.ID<=RBT_Right.ID ){
			 
			 total+=RBT_Left.count;
			 
			 RBT_Left=RBT_Successor(RBT_Left);
		 }
		 
		 
		 return total;
		 
	 }
	 
	 //increase operation
	 void Increase(int theID,int m)
	 {
		 try{
			 
		 
		 Red_Black_Node increaseNode=RBT_Count(root,theID);
		 if(increaseNode==null)
		 {
			 //If not present add theID in tree
			 Red_Black_Node newNode=new Red_Black_Node(theID,m);
			 System.out.println(newNode.count);
			 Red_Black_Insert(newNode);
			 
			 increaseNode=RBT_Count(root,theID);
			 
			 
			 
		 }
		 else
		 {
			 //Get the count of ID
			 increaseNode.count=increaseNode.count+m;
			 System.out.println(increaseNode.count);
		 }
		 }
		 catch(Exception e)
		 {
			 
		 }
	 }
	 
	 
	void Red_Black_Insert(Red_Black_Node node)
	{
		Red_Black_Node x=root;
		Red_Black_Node y=nil;
		while(x!=nil && x!=null){
			y=x;
		
			
			//If the node is Less , go To Left Subtree
			if(node.ID<x.ID)
			{
				if(x.left!=null)
				{
					x=x.left;
					
				}
				
				
				{
					x=nil;
				}
					
				
			}
			//If Node is greater go to right subtree
			else
			{
				if(x.right!=null)
				{
					x=x.right;
					
				}
				else
				{
					x=nil;
				}
				
				
				
			}
		}
		if(y!=null){
			node.parent=y;
			
		}
		else
		{
			node.parent=nil;
		}
		
		if(y==nil)
		{
			root=node;
		}
		else if(node.ID<y.ID)
		{
			
			y.left=node;
		}
		else
		{
			y.right=node;
		}
		
		node.right=nil;
		node.left=nil;
		node.color=Red;
		
		//Fix the Red Black Tree 
		Red_Black_Insert_Fix(node);
		
		
	}






	void Red_Black_Insert_Fix(Red_Black_Node node)
	{
		try
		{
		Red_Black_Node y;
		
		//While loop will run till Red Black tree properly  satisfies its property
		while(node.parent.color==Red)
		{
			
			if(node.parent==node.parent.parent.left)
			{
				//Assigning node's parent's parent's right subtree to y
				y=node.parent.parent.right;
				
				if(y.color==Red)
				{

					node.parent.color=Black;
					//If y's color is red change it to black
					y.color=Black;
					
					node.parent.parent.color=Red;
					node=node.parent.parent;
					

					if(node.parent==null)
					{
						node.parent=nil;
					}
						
					
				}
				else 
				{
					
					if(node.parent==null)
					{
						node.parent=nil;
					}
					
					if(node==node.parent.right)
					{
						
						node=node.parent;
						Left_Rotate(node);
						
					}
					node.parent.color=Black;
					node.parent.parent.color=Red;
					Right_Rotate(node.parent.parent);
					
				}
			}
			else
			{
				
				y=node.parent.parent.left;
				
				if(y.color==Red)
				{
					node.parent.color=Black;
					y.color=Black;
					node.parent.parent.color=Red;
					node=node.parent.parent;
					
					if(node.parent==null)
					{
						
						node.parent=nil;
					}
				}
				else 
				{
					
					if(node.parent==null)
					{
						node.parent=nil;
					}
					if(node==node.parent.left)
					{
						node=node.parent;
						Right_Rotate(node);
						
					}
					node.parent.color=Black;
					node.parent.parent.color=Red;
					
					Left_Rotate(node.parent.parent);
				}
			
			}
		}
		
		root.color=Black;
		}
		catch(Exception e)
		{
			
			
		}
		
	}


	void Left_Rotate(Red_Black_Node node)
	{
		try{
			Red_Black_Node y;
			y=node.right;
			//turn y's left subtree to node's right sub tree
			node.right=y.left;
			if(y.left!=nil)
			{
				y.left.parent=node;
			}
			//link parent of node to y'parent
			y.parent=node.parent;
			if(node.parent==nil)
			{
				root=y;
			}
			else if(node==node.parent.left)
			{
				node.parent.left=y;
			}
			else
			{
				node.parent.right=y;
			}
			//Put node on y's Left subtree
			y.left=node;
			node.parent=y;
		}
		catch(Exception e){
			
		}
		
	}


	void Right_Rotate(Red_Black_Node node)
	{
		try
		{
			Red_Black_Node y;
			y=node.left;
			//turn node's left subtree to y's right sub tree
			node.left=y.right;
			if(y.right!=nil)
			{
				y.right.parent=node;
			}
			y.parent=node.parent;
			if(node.parent==nil)
			{
				root=y;
			}
			else if(node==node.parent.right)
			{
				node.parent.right=y;
			}
			else
			{
				node.parent.left=y;
			}
			//Put node in y' right subtree
			y.right=node;
			node.parent=y;
		}
		catch(Exception e)
		{
			
		}
		
		
	}

    //Reduce the count of ID
	 void Reduce(int theID,int m)
	 {
		 
			 Red_Black_Node reduceNode=RBT_Count(root,theID);
			 //If TheID is not present in tree
			 if(reduceNode==null)
			 {
				 System.out.println(0);
			 }
			 else
			 {
				 reduceNode.count=reduceNode.count-m;
				 //If the count goes below 0
				 if(reduceNode.count<=0)
				 {
					 System.out.println(0);
					 Red_Black_Delete(reduceNode);
					 
				 }
				 //else print the remaining count
				 else
				 {
					 System.out.println(reduceNode.count);
				 }
				 
			 }
			 
		
		 
	 }
	 
	 
	 //Delete the node if count goes below 0
	 void Red_Black_Delete(Red_Black_Node node){
		 
		 try
		 {
			 Red_Black_Node x;
			 Red_Black_Node y=node;
			 //Store the node's original color in a variable
			 int y_original_color=y.color;
			 if(node.left==nil){
				 x=node.right;
				 //Call transplant operation with node and node's right child
				 Red_Black_Transplant(node,node.right);
			 }
			 else if(node.right==nil)
			 {
				 x=node.left;
				//Call transplant operation with node and node's leftchild
				 Red_Black_Transplant(node,node.left);
			 }
			 else
			 {
				 //Minimum node in node's right subtree
				 y=RBT_Tree_Minimum(node.right);
				 y_original_color=y.color;
				 x=y.right;
				 if(y.parent==node)
				 {
					 x.parent=y;
				 }
				 else
				 {
					 Red_Black_Transplant(y,y.right);
					 y.right=node.right;
					 y.right.parent=y; 
				 }
				 
				 Red_Black_Transplant(node,y);
				 y.left=node.left;
				 y.left.parent=y;
				 y.color=node.color;
				 
			 }
			 //call fixup to fix the red black tree 
			 if(y_original_color==Black)
			 {
				 Red_Black_Delete_Fixup(x);
			 }
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
		 
	 }
	 
	 void Red_Black_Transplant(Red_Black_Node node1,Red_Black_Node node2)
	 {
		 //If parent is null assign node to root
		 if(node1.parent==nil || node1.parent==null)
		 {
			 root=node2;
		 }
		 else if(node1==node1.parent.left)
		 {
			node1.parent.left=node2; 
		 }
		 else
		 {
			 node1.parent.right=node2;
		 }
		 //Assign node1's parent to node2's parent 
		 node2.parent=node1.parent;
		 
	 }
	 
	 
	 
	 void Red_Black_Delete_Fixup(Red_Black_Node node)
	 {
		 try
		 {
		 Red_Black_Node y;
		 while(node!=root && node.color==Black )
		 {
			 
			 if(node==node.parent.left)
			 {
				 y=node.parent.right;
				 if(y.color==Red)
				 {
					 y.color=Black;
					 node.parent.color=Red;
					 Left_Rotate(node.parent);
					 y=node.parent.right;
				 }
				 if( y.left.color == Black && y.right.color == Black)
				 {
					 y.color=Red;
					 node=node.parent;			 
				 }
				 else 
					 {
					 	if(y.right.color == Black)
					 	{
					 		y.left.color= Black;
					 		y.color=Red;
					 		Right_Rotate(y);
					 		y=node.parent.right;
					 	}
					 	
					 	y.color= node.parent.color;
					 	node.parent.color = Black;
					 	y.right.color = Black;
					 	Left_Rotate(node.parent);
					 	node=root;
					 }
			 }
			 
			 else
			 {
				 
				 y=node.parent.left;
				 if(y.color==Red)
				 {
					 y.color=Black;
					 node.parent.color=Red;
					 Right_Rotate(node.parent);
					 y=node.parent.left;
				 }
				 if( y.right.color == Black && y.left.color == Black)
				 {
					 y.color=Red;
					 node=node.parent;			 
				 }
				 else 
					 {
					 	if(y.left.color == Black)
					 	{
					 		y.right.color= Black;
					 		y.color=Red;
					 		Left_Rotate(y);
					 		y=node.parent.left;
					 	}
					 	
					 	y.color= node.parent.color;
					 	node.parent.color = Black;
					 	y.left.color = Black;
					 	Right_Rotate(node.parent);
					 	node=root;
					 }
				 
				 
			 }
			 
		 }
		 node.color=Black;
		 }
		 catch(Exception e)
		 {
			 
		 }
	 }
	 
	 
	//Find the Next of the given theID i.e node with smallest ID greater then given theID
	 void Next(int theID)
	 {
		 Red_Black_Node temp_NextNode=ClosestTotheID(root,theID);
		 
		 if(temp_NextNode.ID>theID && temp_NextNode!=nil)
		 {
			 System.out.println(temp_NextNode.ID+" "+temp_NextNode.count);
		 }
		 else
		 {
			 
			 if(RBT_Successor(temp_NextNode)==null || RBT_Successor(temp_NextNode)==nil )
			 {
				 System.out.println(0+" "+0);
			 }
			 else
			 System.out.println(RBT_Successor(temp_NextNode).ID+" "+RBT_Successor(temp_NextNode).count);
		 }
		 
		 
	 }
	//Find the Previous of the given theID i.e node with greatest ID less then given theID
	 void Previous(int theID)
	 {
		 Red_Black_Node temp_PreviousNode=ClosestTotheID(root,theID);
		 
		 if( temp_PreviousNode.ID<theID && temp_PreviousNode!=nil)
		 {
			 System.out.println(temp_PreviousNode.ID+" "+temp_PreviousNode.count);
		 }
		 else
		 {
			 if( temp_PreviousNode==nil)
			 {
				 System.out.println(0+" "+0);
			 }
			 else if(RBT_Predecessor(temp_PreviousNode)==null )
			 {
				 System.out.println(0+" "+0);
			 }
			 else

			 System.out.println(RBT_Predecessor(temp_PreviousNode).ID+" "+RBT_Predecessor(temp_PreviousNode).count);
		 }
		 
	 }
	 
	//Find the Successor of the given node i.e node with smallest ID greater then given node's ID 
	 Red_Black_Node RBT_Successor(Red_Black_Node node)
	 {
		 if(node.right!=nil)
		 {
			 return RBT_Tree_Minimum(node.right);
		 }
		 Red_Black_Node parent_node=node.parent;
		 while(parent_node!=nil && node==parent_node.right)
		 {
			 node=parent_node;
			 parent_node=parent_node.parent;
			 
		 }
		 return parent_node;
	 }
	 
	 
	 //Find the predecessor of the given node i.e node with greatest ID less then given node's ID
	 Red_Black_Node RBT_Predecessor(Red_Black_Node node)
	 {
		 if(node.left!=nil)
		 {
			 return RBT_Tree_Maximum(node.left);
		 }
		 Red_Black_Node parent_node=node.parent;
		 while(parent_node!=nil && node==parent_node.left)
		 {
			 node=parent_node;
			 parent_node=parent_node.parent;
			 
		 }
		 return parent_node;
	 }
	 
	 //Find the minimum node
	 Red_Black_Node RBT_Tree_Minimum(Red_Black_Node node)
	 {
		 while(node.left!=nil)
		 {
			 node=node.left;
		 }
		 return node;
	 }
	 
	 

	 //Find the maximum node
	 Red_Black_Node RBT_Tree_Maximum(Red_Black_Node node)
	 {
		 while(node.right!=nil)
		 {
			 node=node.right;
		 }
		 return node;
	 }
	 
	 

	 

	 
	 Red_Black_Node ClosestTotheID(Red_Black_Node node, int theID) {

		 //Find the node which has ID closest to theID
		 if (node == null) return null;

		 Red_Black_Node closestNode = node;
		 int closestDiff = Integer.MAX_VALUE;

		 while(node != null) {
		 if (node.ID == theID) {
			 
			 return node; 
		 }
			 
		 //Find the absolute value between theID and node's ID
		 int diff = Math.abs( theID - node.ID);
		 if (diff < closestDiff) {
		 closestDiff = diff;
		 closestNode = node;
		 }
		  if(theID > node.ID)
			 {
			  node = node.right;
			 
		 }
		  else
		  {
			  node=node.left;
		  }
		 }
		 
		 return closestNode;
		 }
	 
	 

	  
	 public static void main(String[] args) {
	     bbst tree = new bbst();
	    
	     if(args.length<0)
	     {
	    	 System.out.println("Please pass the argument");
	    	 System.exit(1);
	     }
	     else
	     {
	    	 
	    	 try{
	    
	    		 FileInputStream fistream = new FileInputStream(args[0]);
	    		 
	    		 DataInputStream dis = new DataInputStream(fistream);
	    		 BufferedReader br = new BufferedReader(new InputStreamReader(dis));
	    		 Scanner input=new Scanner(System.in);

	    		 String strLine = null;
	    		 //Read 1st File Line to get the size
	    		 strLine = br.readLine();
	    		 int n = Integer.parseInt(strLine);
	    		 int i=0;
	    		 int j=0;
	    		 
	    		int arr1[]=new int[n];
	    		int arr2[]=new int[n];
	    		//Read file line by line
	    		 while ((strLine = br.readLine()) != null)  
	    		 {
	    			 
	    		
	     		    String [] split = strLine.split(" ");
	     	            String a = split[0];
	     	            String b = split[1];
	     	            //Parse the input string into integer
	     	            arr1[i++] = Integer.parseInt(a);
	     	            arr2[j++] = Integer.parseInt(b);
	     	            
	     	          
	     	     
	    			 
	    			 	
	    	            
	    		 }
	    		//Create red black tree
	    		 tree.Initial_RBT_Creation(arr1,arr2, 0, n - 1);
	    		 dis.close();
	    		 
	    		
	    		 
	    		 String[] stringArr;
	    		 do
	    			 {
	    			 String strng = input.nextLine();
	                 stringArr = strng.split("\\s+");
	                 int theID,ID1,ID2,m;
	                 
	                 switch (stringArr[0])
	                 {
	                     case "increase":
	                         tree.Increase(Integer.parseInt(stringArr[1]), Integer.parseInt(stringArr[2]));     
	                         break;

	                     case "reduce":
	                         tree.Reduce(Integer.parseInt(stringArr[1]), Integer.parseInt(stringArr[2]));
	                         break;

	                     case "count": 
	                         tree.Count(Integer.parseInt(stringArr[1]));              
	                         break;

	                     case "inrange":
	                         tree.InRange(Integer.parseInt(stringArr[1]), Integer.parseInt(stringArr[2]));
	                         break;

	                     case "next":
	                         tree.Next(Integer.parseInt(stringArr[1]));
	                         break;

	                     case "previous":
	                         
	                         tree.Previous(Integer.parseInt(stringArr[1]));
	                         break;

	                     case "quit":
	                
	                         System.exit(0);
	                 }
	             	}
			while(!stringArr[0].equalsIgnoreCase("quit"));
	    		 
	    		

	    	 }
	    	 catch (Exception e)
	    	 {//Catch exception if any
	    		 System.err.println("Error: " + e.getMessage());
	    	 }
	     
	
	     }
	     
	     
	    
	 }
	}




