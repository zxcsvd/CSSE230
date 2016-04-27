package anagram;

/**
 * This utility class can test with two strings are anagrams.
 *
 * @author Claude Anderson.
 */
public class Anagram {

	/**
	 * We say that two strings are anagrams if one can be transformed into the
	 * other by permuting the characters (and ignoring case).
	 * 
	 * @param s1
	 *            first string
	 * @param s2
	 *            second string
	 * @return true if s1 is an anagram of s2
	 */
	public static boolean isAnagram(String s1, String s2) {
		if(s1.length() != s2.length()){
			return false;
		}
		else{
			for(int i=0; i<s1.length(); i++){
				if(s1.substring(i,i+1).equals(s2.substring(s2.length()-i-1,s2.length()-i))){
					continue;
				}
				else{
					return false;
				}
			}
			return true;
			
		}
	}
}
