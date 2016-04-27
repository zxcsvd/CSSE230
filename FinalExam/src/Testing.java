import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {
		
	private static int points = 0;
	
	@Test
	public void testMakeTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> bb = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> b3 = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> b4 = new BinarySearchTree<Integer>();
		ArrayList<BinarySearchTree<Integer>> forest = new ArrayList<BinarySearchTree<Integer>>();
		forest.add(bb);
		forest.add(b3);
		forest.add(b4);
		b.makeTree(forest);
		Iterator<BinarySearchTree<Integer>.BinaryNode> i = b.iterator();
		assertFalse(i.hasNext());
		points += 5;
		b.insert(4);
		bb.insert(4);
		b3.insert(4);
		b4.insert(4);
		b.makeTree(forest);		
		i = b.iterator();
		assertTrue(i.hasNext());
		while (i.hasNext()) {
			assertEquals(4, i.next().getElement());
			assertEquals(4, i.next().getElement());
			assertEquals(4, i.next().getElement());
			assertEquals(4, i.next().getElement());
		}
		points += 5;
		b = new BinarySearchTree<Integer>();
		bb = new BinarySearchTree<Integer>();
		b3 = new BinarySearchTree<Integer>();
		b4 = new BinarySearchTree<Integer>();	
		for (int k = 1; k < 30; k++) b.insert(k);
		for (int k = 30; k < 60; k++) b4.insert(k);
		for (int k = 60; k < 90; k++) bb.insert(k);
		for (int k = 90; k < 120; k++) b3.insert(k);
		forest = new ArrayList<BinarySearchTree<Integer>>();
		forest.add(bb);
		forest.add(b3);
		forest.add(b4);
		b.makeTree(forest);
		i = b.iterator();
		for (int l = 1; l < 120; l++) {
			assertTrue(i.hasNext());
			assertEquals(l, i.next().getElement());
		}
		points += 5;
	}
	
	@Test
	public void testIsConnected(){
		Graph g = new Graph();
		g.addNode("1", new Integer(1));
		g.addNode("2", new Integer(2));
		g.addNode("3", new Integer(3));
		g.addNode("4", new Integer(4));
		assertFalse(g.isConnected());
		points += 5;
		g.addEdge("1", "4", 1);
		g.addEdge("4", "1", 1);
		g.addEdge("2", "3", 1);
		g.addEdge("3", "2", 1);
		assertFalse(g.isConnected());
		points += 5;
		g.addEdge("3", "4", 1);
		g.addEdge("4", "3", 1);
		assertTrue(g.isConnected());
		points += 15;
	}

	@Test
	public void testLargestCompleteSubTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(null, b.largestCompleteSubTree());
		b.insert(20);
		assertEquals(20, b.largestCompleteSubTree().getElement());
		b.insert(30);
		b.insert(40);
		b.insert(50);
		assertEquals(50, b.largestCompleteSubTree().getElement());
		points += 4;
		b.insert(25);
		assertEquals(25, b.largestCompleteSubTree().getElement());
		points += 4;
		b.insert(35);
		assertEquals(40, b.largestCompleteSubTree().getElement());	
		points += 4;
		b.insert(22);
		assertEquals(40, b.largestCompleteSubTree().getElement());	
		points += 4;
		b.insert(27);
		assertEquals(30, b.largestCompleteSubTree().getElement());	
		points += 4;
	}
	
	@AfterClass
	public static void testNothing(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		System.out.print(b.myName());
		System.out.println(points);
	}
}

