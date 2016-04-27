package hardysTaxi;

import java.util.List;
import java.util.ArrayList;
import static hardysTaxi.TaxicabNumber.cube;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all
 * "taxicab numbers" that are less than n.  These are represented as TaxicabNumber objects:
 * each includes the sum and two different ways of writing that sum as
 * a sum of two cubes.
 * 
 * @author anderson  November, 2010.
 *
 */
public class Hardy {
	
	/**
	 * Returns floor of the cube root of n.
	 * Can you see why this method is useful for this problem?
	 * This is not a very efficient implementation.  
	 * (Not required) Can you think of a more efficient approach?
	 * 
	 * @param n a positive integer
	 * @return integer cube root of n
	 */
	public static int cubeRootFloor(int n){
		// Very inefficient, but quick to write.
		int i=0;
		while (cube(i) <= n)
			i++;
		return i-1; 
	}
		
	/**
	 * Computes and returns a sorted list of all taxicab numbers less than n.
	 * @param n a positive integer
	 * @return a List<TaxicabNumber>  object.  Each object contains the sum and two different ways to write it as a sum of cubes.
	 */
	
	public static List<TaxicabNumber> hardySolutionsLessThan(int n) {
		List<TaxicabNumber> result = new ArrayList<TaxicabNumber>();  // You are to populate this list.
		int limit = cubeRootFloor(n);
		for(int a=1; a<=limit; a++){
			for(int b=a; b<=limit; b++){
				int s1 = a*a*a+b*b*b;
				if(s1<n){
					for(int c=1; c<=limit; c++){
						for(int d=c; d<=limit; d++){
							int s2 = c*c*c+d*d*d;
							if(s2 == s1 && a != c && b != d){
								if(result.size()==0){
									result.add(new TaxicabNumber(s1, a, b, c, d));
//									System.out.println(s1 +" "+a+" "+b+" "+c+" "+d);
								}
								else{
									boolean test = true;
									for(int i=0; i<result.size();i++){
										if(result.get(i).getS() == s1){
											test = false;
											break;
										}
									}
									if(test == true){
										result.add(new TaxicabNumber(s1, a, b, c, d));
									}
								}

							}
						}
					}
				}
				

				
			}
		}

		java.util.Collections.sort(result);
		return result;
	}
	
	/**
	 * main() is provided in case you want to test your code other than 
	 * with unit tests. Just put try various arguments in the method call below.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(hardySolutionsLessThan(1730));
	}

}
