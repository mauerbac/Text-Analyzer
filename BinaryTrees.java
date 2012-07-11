// Matthew Auerbach
//Homework 6
//imported from Prof.Synder CS112 Class Notes

package cs112.hw06;


public class BinaryTrees {

	private Node root;	
	
	public BinaryTrees(){
		this.root=null;
	}
	
    static class Node {
        HashTable.Node key;
        Node left, right;
        
        //constructor for Node of type hash table node
        public Node(HashTable.Node p) {
            left = null;
            right = null;
            key= p;
        }
    }
    
    //insert function
    public void insert(HashTable.Node p){
    	root= insertAux(root,p);
    }
    
    //insert helper method
    private static Node insertAux(Node t,HashTable.Node key) {
        if (t == null )
            return new Node(key);   
        
        if (t.key.compareTo(key)>0) {
            t.left = insertAux(t.left, key); //inserts to the left
            return t;
            
        } else if (t.key.compareTo(key)<0) { //inserts to the right
            t.right = insertAux(t.right, key);
            return t;
            
        } else
            return t;
    }
    
    //print function
    public void printTree(int docNumber){
    	printTreeAux(root,docNumber);
    }
   
    
   //print helper function 
    private void printTreeAux(Node t,int docNumber) {
           if( t != null ) {
                printTreeAux(t.left,docNumber); //prints all left words first
                
                System.out.print(t.key.word); 
               
                printSpace(15-t.key.word.length()); //inserts correct space to be aligned properly
                
                for(int i=0;i<=docNumber;i++){
                	System.out.print("["+ t.key.count[i] + "]");
                }
                System.out.println(); 
                
                printTreeAux(t.right,docNumber); //print all right words
           }
        }
        
    //prints spaces 
      public void printSpace(int space){
    	  
      	for(int i=0;i<space;i++ ){
      		System.out.print(" ");
      	}
      }  
}


