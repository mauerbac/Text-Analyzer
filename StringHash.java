// Matthew Auerbach
//Homework 6
//From Alex's Lab website 
package cs112.hw06;


public class StringHash {
	
    static final String HASH_NAIVE_ADD = "hash_naive_add";
	
     //returns hash value for string
	static int naiveAdd(String s, int size) {
		int sum = 0;
		for(int i = 0; i< s.length(); i++) {
			sum += s.charAt(i);
			sum %= size;
		}
		return sum; 
	}
}