import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int totalPoints = 0;
	private static int fullNodeCountPoints = 0;
	private static int fullNodeListPoints = 0;
	private static int depthPoints = 0;
	private static int heightSumPoints = 0;

	@Test
	public void testFullNodeCountBase() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.countFullNodes());
		b.insert(20);
		b.insert(10);
		assertEquals(0, b.countFullNodes());
		fullNodeCountPoints += 1;
	}

	@Test
	public void testFullNodeCount2() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		b.insert(10);
		b.insert(30);
		assertEquals(1, b.countFullNodes());
		fullNodeCountPoints += 1;
		b.insert(40);
		b.insert(38);
		b.insert(15);
		assertEquals(1, b.countFullNodes());
		fullNodeCountPoints += 2;
		b.insert(5);
		assertEquals(2, b.countFullNodes());
		fullNodeCountPoints += 2;
		b.insert(25);
		assertEquals(3, b.countFullNodes());
		fullNodeCountPoints += 2;
		b.insert(50);
		assertEquals(4, b.countFullNodes());
		fullNodeCountPoints += 3;
	}

	@Test
	public void testFullNodeListBase() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		b.insert(10);
		assertEquals("[]", b.getFullNodeList().toString());
		fullNodeListPoints += 2;
	}

	@Test
	public void testFullNodeList1() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		b.insert(10);
		b.insert(30);
		assertEquals("[20]", b.getFullNodeList().toString());
		fullNodeListPoints += 4;
	}

	@Test
	public void testFullNodeList2() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		b.insert(10);
		b.insert(30);
		b.insert(40);
		b.insert(38);
		b.insert(5);
		b.insert(15);
		assertEquals("[20, 10]", b.getFullNodeList().toString());
		fullNodeListPoints += 6;
	}

	@Test
	public void testFullNodeList3() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		b.insert(10);
		b.insert(30);
		b.insert(40);
		b.insert(38);
		b.insert(5);
		b.insert(15);
		b.insert(25);
		b.insert(50);
		assertEquals("[20, 10, 30, 40]", b.getFullNodeList().toString());
		fullNodeListPoints += 10;
	}

	@Test
	public void testGetDepth3() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		b.insert(10);
		b.insert(30);
		b.insert(40);
		b.insert(38);
		b.insert(5);
		b.insert(15);
		b.insert(50);
		assertEquals(0, b.getDepth(20));
		assertEquals(1, b.getDepth(10));
		assertEquals(1, b.getDepth(30));
		depthPoints += 3;
		assertEquals(2, b.getDepth(40));
		assertEquals(3, b.getDepth(38));
		depthPoints += 4;
		assertEquals(2, b.getDepth(5));
		assertEquals(2, b.getDepth(15));
		assertEquals(3, b.getDepth(50));
		depthPoints += 6;
	}

	@Test
	public void testGetDepthNotFound() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(-1, b.getDepth(20));
		depthPoints += 1;
		b.insert(20);
		b.insert(10);
		b.insert(30);
		b.insert(40);
		b.insert(38);
		b.insert(5);
		b.insert(15);
		b.insert(50);
		assertEquals(-1, b.getDepth(99));
		depthPoints += 2;
	}

	@Test
	public void testSumOfHeightsRoot() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		assertEquals(0, b.getSumOfHeights());
		heightSumPoints += 1;
	}
		
	@Test
	public void testSumOfHeights3() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		assertEquals(0, b.getSumOfHeights());
		b.insert(10);
		assertEquals(1, b.getSumOfHeights());
		b.insert(30);
		assertEquals(1, b.getSumOfHeights());
		heightSumPoints += 2;
		b.insert(40);
		assertEquals(3, b.getSumOfHeights());
		heightSumPoints += 3;
		b.insert(38);
		assertEquals(6, b.getSumOfHeights());
		heightSumPoints += 4;
		b.insert(5);
		assertEquals(7, b.getSumOfHeights());
		b.insert(15);
		assertEquals(7, b.getSumOfHeights());
		b.insert(25);
		assertEquals(7, b.getSumOfHeights());
		b.insert(50);
		assertEquals(7, b.getSumOfHeights());
		heightSumPoints += 6;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.printf("1. sumHeights points: %d/16\n",heightSumPoints);
		System.out.printf("2. getDepth points: %d/16\n",depthPoints);
		System.out.printf("[3a fullNodeCount points: %d/11]\n",fullNodeCountPoints);
		System.out.printf("[3b. fullNodeList points: %d/22]\n",fullNodeListPoints);
		int fullNodeMaxPoints = Math.max(fullNodeCountPoints, fullNodeListPoints);
		System.out.printf("3. (Max of 3a and 3b): %d/22\n",fullNodeMaxPoints);
		
		totalPoints = heightSumPoints + depthPoints + fullNodeMaxPoints;
		System.out.printf("Points: %d/54\n",totalPoints);
	}
}