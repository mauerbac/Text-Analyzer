/*Matthew Auerbach (Mauerbac@bu.edu)
 * CS 112
 * Homework 6
 * 5/6/11
 * 
 * Program compares up to 10 document. It lists the number of times a word occurs by individual document. 
 */
package cs112.hw06;

import java.util.Scanner;


public class TextAnalyzer {

	public static void main (String [] args){
		Scanner console = new Scanner(System.in); //create a scanner 
		
		welcome(); //print welcome message
		
		String line= console.nextLine(); //store first line in variable line
		
		int docNumber=0; //create variable docNumber, start at 0
				
		HashTable hash1= new HashTable(71537); //create hash table of size 71537
		
		while(!(line.equals("$end$"))){ //keep looping until we get to end of doc
			if(line.equals("$next$")){ //if new document, increment docNumber
				docNumber++;
				
			}else{
				String[] lineArray = line.toLowerCase().split("\\W+"); //strip line of punctuation and make lower case
				
				for(int i=0;i<lineArray.length;i++){
					hash1.insert(lineArray[i],docNumber);  //insert word by word into hash table
				}	
			}
			line= console.nextLine(); //consume next line, starts process again

		}//while loop ends
		
		BinaryTrees tree1=new BinaryTrees(); //creates a binary tree
		
		HashTable.Node[] currentCont= hash1.getTableInfo(); //stores current hash table into a new array of hash table
		
		
		for(int i=0;i<currentCont.length;i++){ //cycle through whole hash table
			if(!(currentCont[i]==null)){ //while current hash table is not null 
				HashTable.Node p = currentCont[i]; //take current node and store in p
				
				
				while(p!=null){ //while bucket is not null 
					tree1.insert(p); //insert node into BST
					p=p.next; //move to next node 
				}
			}
		}
		tree1.printTree(docNumber); //print results 
	}
	
	
	 //method for printing welcome statement 
	public static void welcome(){
		System.out.println("Welcome to the Text  Intersection Program!\n\nYou may load up to 100 documents by typing each to the standard input, separating each by " +
		"line starting with\nthe word \"$next$\"; insert a line startingwith \"$end$\" to terminate the list and output the results.");
	}
}
