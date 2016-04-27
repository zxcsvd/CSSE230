import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {
	private static int points = 0;
	
	@Test
	public void testingBasicListInsert(){
		SkipList<Integer> s = new SkipList<Integer>();
		int size = 8;
		for (int i = 1; i < size; i++) s.insert(i, 1);
//		System.out.println(s);
		SkipList<Integer>.Node temp = s.root.getLinks().get(0);;
		for (int i = 1; i < size; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(1, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		assertTrue(temp == null);
		points += 5;
	}
	
	@Test
	public void testingInscreasingInsert(){
		SkipList<Integer> s = new SkipList<Integer>();
		int size = 8;
		for (int i = 1; i < size; i++) s.insert(i, i);
//		System.out.println(s);
		for (int i = 0; i < size-1; i++) {
			assertEquals(new Integer(i+1), s.root.getLinks().get(i).getElement());
		}
		assertEquals(null, s.root.getLinks().get(7));
		SkipList<Integer>.Node temp = s.root.getLinks().get(0);
		for (int i = 1; i < size; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(i, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		assertTrue(temp == null);
		points += 5;
	}
	
	@Test
	public void testingDecreasingInsert(){
		SkipList<Integer> s = new SkipList<Integer>();
		int size = 8;
		for (int i = 1; i < size; i++) s.insert(i, size-i);
//		System.out.println(s);
		for (int i = 0; i < size-1; i++) {
			assertEquals(new Integer(1), s.root.getLinks().get(i).getElement());
		}
		assertEquals(null, s.root.getLinks().get(7));
		SkipList<Integer>.Node temp = s.root.getLinks().get(0);
		for (int i = 1; i < size; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(size - i, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		assertTrue(temp == null);
		points += 5;

	}
	
	
	@Test
	public void testingBasicRemove(){
		SkipList<Integer> s = new SkipList<Integer>();
		int size = 8;
		for (int i = 1; i < size; i++) s.insert(i, 1);
		s.remove(4);
//		System.out.println(s);
		SkipList<Integer>.Node temp = s.root.getLinks().get(0);
		for (int i = 1; i < 4; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(1, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		for (int i = 5; i < size; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(1, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		assertTrue(temp == null);
		for (int i = 1; i < size; i++) {
			s.remove(i);
		}
//		System.out.println(s);
		assertTrue(s.root.getLinks().get(0) == null);
		points += 5;
	}
	
	@Test
	public void testingInscreasingRemove(){
		SkipList<Integer> s = new SkipList<Integer>();
		int size = 8;
		for (int i = 1; i < size; i++) s.insert(i, i);
		s.remove(6);
//		System.out.println(s);
		SkipList<Integer>.Node temp = s.root.getLinks().get(0);
		for (int i = 1; i < 6; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(i, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		for (int i = 7; i < size; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(i, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		assertTrue(temp == null);
		for (int i = 1; i < size; i++) {
			s.remove(i);
		}
//		System.out.println(s);
		assertTrue(s.root.getLinks().get(0) == null);
		points += 5;
	}
	
	@Test
	public void testingDecreasingRemove(){
		SkipList<Integer> s = new SkipList<Integer>();
		int size = 8;
		for (int i = 1; i < size; i++) s.insert(i, size-i);
		s.remove(3);
//		System.out.println(s);
		SkipList<Integer>.Node temp = s.root.getLinks().get(0);
		for (int i = 1; i < 3; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(size-i, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		for (int i = 4; i < size; i++){
			assertFalse(temp == null);
			assertEquals(new Integer(i), temp.getElement());
			assertEquals(size-i, temp.getLinks().size());
			temp = temp.getLinks().get(0);
		}
		assertTrue(temp == null);
		for (int i = 1; i < size; i++) {
			s.remove(i);
		}
//		System.out.println(s);
		assertTrue(s.root.getLinks().get(0) == null);
		points += 5;

	}

	@Test
	public void testingLogBehavior(){
		SkipList<Integer> t = new SkipList<Integer>();
		int nums = 2000000;
		int[] a = new int[nums];
		// populating array
		for (int i = 0; i < nums; i++){
			a[i] = i;
		}
		int i1;
		int i2;	
		int temp2;
		// shuffling array
		for (int i = 0; i < nums; i++) {
		    i1 = (int) (Math.random() * nums);
		    i2 = (int) (Math.random() * nums);
		    temp2 = a[i1];
		    a[i1] = a[i2];
		    a[i2] = temp2;
		}	
		for (int i = 1; i < nums; i++) t.insert(a[i]);
		SkipList<Integer>.Node temp = t.root.getLinks().get(0);
		for (int i = 1; i < nums; i++){
			assertFalse(temp == null);
			temp = temp.getLinks().get(0);
		}
		assertTrue(temp == null);
		
		for (int i = 1; i < nums; i++) t.remove(a[i]);
		assertTrue(t.root.getLinks().get(0) == null);
		points += 20;
	}
	

	@AfterClass
	public static void testNothing(){
		System.out.println("Points: " + points);
	}
	
}

