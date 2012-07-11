// Matthew Auerbach
//Homework 6
//From Alex's Lab website 

package cs112.hw06;

public class HashTable {
    private int m_size; //size of hash table

    private Node[] m_table; //array of nodes
    
    //constructor for hash table 
    public HashTable(int tableSize) {
        m_size = tableSize;
        m_table = new Node[m_size];
    }
   
    //returns current contents of hash table 
    public Node[] getTableInfo(){
    	return m_table;
    }
    
    //prints hash table 
    public void printTable() {
        for (int i = 0; i < m_size; i++) {
            printBucket(m_table[i], i);
        }
    }
    
    
    public int[] toIntArray() {
        int [] arr = new int[m_size];
        for (int i = 0; i < m_size; ++i) {
            arr[i] = Node.length(m_table[i]);
        }
        return arr;        
    }

   
    private void printBucket(Node p, int ind) {
        if (p == null)
            ;
        else if (p.next == null) {
            System.out.print(p.word + "    [" + ind + "]\n");
        } else {
            System.out.print(p.word + ", ");
            printBucket(p.next, ind);
        }
    }

    static public class Node implements Comparable<Node> {
        String word; //stores word

        public  int[] count; //keeps track of occurrences 

        Node next;

        
        //constructor for node	
        public Node(String w, int docNumber) {
            word = w;
            next = null;
            count= new int[10];
            count[docNumber]=1; 
            
        }
        
        //adds occurrences together 
        public int addNode(){
        	int sum=0;
        	for(int i=0;i<10;i++){
        	sum= count[i] + sum;
        	}
        	return sum;
        }
       
       //compares nodes to figure out placing 
        public int compareTo(Node p){
        	if(this.addNode() > p.addNode()){ //if greater than
        		return -1; 
        		
        	}else if(this.addNode()==p.addNode()){//if same 
        		return this.word.compareTo(p.word);
        		
        	}else if(this.addNode() < p.addNode()){ //if less than
        		return 1;
        		
        	}else{
        		return 0;
        	}
        	        	
        }

        static public int length(Node p) {
            if (p == null) {
                return 0;
            } else {
                return 1 + length(p.next);
            }
        }
    }

    public Boolean member(String s) {
        int h = StringHash.naiveAdd(s, m_size);
        return member(s, m_table[h]);
    }

    Boolean member(String s, Node p) {
        if (p == null)
            return false;
        else if (s.equals(p.word))
            return true;
        else
            return member(s, p.next);
    }

    Boolean member(String s, String[] list) {
        Boolean found = false;
        for (int i = 0; i < list.length; i++) {
            if (s.equals(list[i])) {
                found = true;
                break;
            }
        }
        return found;
    }

    private Node insertAux(String s, Node p,int docNumber) {
        if (p == null)
            return new Node(s, docNumber); // not in bucket, add to end
        else if (s.equals(p.word)) { // already in bucket, do nothing
           p.count[docNumber]++; //if in bucket add count 
        	return p;
            
        } else { //if not in bucket add to table
            p.next = insertAux(s, p.next, docNumber);
            return p;
        }
    }
    
    
    public void insert(String s, int docNumber) {
    	String black[]={"and", "or", "but", "however", "he", "she", "it", "thus", "when", "not", "now","is", "the", "of", "this", "a", "that", "been"};
    	for(int i=0;i<black.length;i++){
    		if(s.equals(black[i])){ //checks to see if word is blacklisted
    			return;
    		}
    	}
        int h = 0;
        h = StringHash.naiveAdd(s, m_size); //adds the word

        m_table[h] = insertAux(s, m_table[h], docNumber); //adds to correct docNumber
    }
}