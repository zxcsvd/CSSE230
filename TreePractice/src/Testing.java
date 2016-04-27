import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int points = 0;

	@Test
	public void testCountPositivesBaseCase() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.countPositives());
		points += 1;
	}

	@Test
	public void testCountPositivesZeroAndPositive() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		for (int i = -1000; i < 1; i++) {
			b.insert(i);
		}
		assertEquals(0, b.countPositives());
		for (int i = 1; i <= 1000; i++) {
			b.insert(i);
		}
		assertEquals(1000, b.countPositives());
		points += 1;
	}

	@Test
	public void testCountPositivesPositiveAndZero() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b = new BinarySearchTree<Integer>();
		for (int i = 1000; i > 0; i--) {
			b.insert(i);
		}
		assertEquals(1000, b.countPositives());
		for (int i = 0; i >= -1000; i--) {
			b.insert(i);
		}
		assertEquals(1000, b.countPositives());
		points += 1;
	}

	@Test
	public void testCountPositivesMix() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b = new BinarySearchTree<Integer>();
		b.insert(0);
		int size = 16;// 128
		int v = size / 2;
		int temp;
		while (v > 0) {
			temp = v;
			while (temp < size) {
				b.insert(temp);
				b.insert(-temp);
				temp += v;
			}
			v = v / 2;
		}
		assertEquals(15, b.countPositives());
		points += 1;
	}

	@Test
	public void testLeafCountBaseCases() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.countLeaves());
		b.insert(10);
		assertEquals(1, b.countLeaves());
		points += 1;
	}

	@Test
	public void testLeafCountSmallTreeWithNonLeaf() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(5);
		b.insert(15);
		assertEquals(2, b.countLeaves());

		b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(2);
		assertEquals(2, b.countLeaves());

		b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(2);
		b.insert(7);
		assertEquals(3, b.countLeaves());

		points += 1;
	}

	@Test
	public void testLeafCountOnWorstCaseTree() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(5);
		b.insert(9);
		b.insert(6);
		b.insert(7);
		assertEquals(1, b.countLeaves());
		points += 1;
	}

	@Test
	public void testLeafCountOnBestCaseTree() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(30);
		b.insert(25);
		b.insert(35);
		assertEquals(4, b.countLeaves());
		points += 1;
	}

	@Test
	public void testLeafCountOnMediumTree() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(20);
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(30);
		b.insert(25);
		b.insert(28);
		b.insert(22);
		b.insert(21);
		assertEquals(4, b.countLeaves());
		points += 1;
	}

	@Test
	public void testLeafCountOnLargeRandomTrees() {
		// TODO: Read this test case. Interesting, huh? I left in a bunch of
		// commented out experiments I did in case you want to explore what
		// happens when random elements are inserted.
		//
		// Feel free to change n.
		int n = 100;
		// Generate a random permutation of n ints (just so there are no
		// duplicates).
		ArrayList<Integer> nums = new ArrayList<Integer>(n);
		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}

		int min = n, max = 1;

		// Run on random trees a bunch of times. Feel free to change the number
		// of times it runs
		int nSimulations = 100;
		for (int i = 0; i < nSimulations; i++) {
			// Shuffle them
			Collections.shuffle(nums);

			BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
			for (int num : nums) {
				b.insert(num);
			}
			int leaves = b.countLeaves();
			// System.out.printf("Random tree has %d leaves\n", leaves);
			if (leaves < min)
				min = leaves;
			if (leaves > max)
				max = leaves;

			// Can you see why this must be true? In practice, you'll find that
			// the range of leaf counts is much smaller than this.
			assertTrue(leaves >= 1 && leaves <= Math.ceil(n / 2));
		}
		// System.out.printf("Random trees had leaf counts in range [%d,%d]\n",
		// min, max);
	}

	@Test
	public void testNumChildrenOfEachNodeBaseCases() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals("", b.numChildrenOfEachNode());
		b.insert(10);
		assertEquals("0", b.numChildrenOfEachNode());
		points += 1;
	}

	@Test
	public void testNumChildrenOfEachNodeInc() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		for (int i = 1; i < 11; i++) {
			b.insert(i);
		}
		assertEquals("1111111110", b.numChildrenOfEachNode());
		points += 1;
	}

	@Test
	public void testNumChildrenOfEachNodeDec() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		for (int i = 10; i > 0; i--) {
			b.insert(i);
		}
		assertEquals("1111111110", b.numChildrenOfEachNode());
		points += 1;
	}

	@Test
	public void testNumChildrenOfEachNodeFull() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		int size = 16;// 128
		int v = size / 2;
		int temp;
		while (v > 0) {
			temp = v;
			while (temp < size) {
				b.insert(temp);
				temp += v;
			}
			v = v / 2;
		}
		assertEquals("222002002200200", b.numChildrenOfEachNode());
		points += 1;
	}

	@Test
	public void testZigZagBaseCases() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertTrue(b.isZigZag());
		b.insert(10);
		assertTrue(b.isZigZag());
		b.insert(1);
		points += 1;
	}

	@Test
	public void testZigZagSimplePositive() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(1);
		assertTrue(b.isZigZag());
		b.insert(9);
		assertTrue(b.isZigZag());
		b.insert(2);
		assertTrue(b.isZigZag());
		b.insert(7);
		assertTrue(b.isZigZag());
		points += 1;
	}

	@Test
	public void testZigZagSimpleNegative() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		assertTrue(b.isZigZag());
		b.insert(1);
		b.insert(9);
		b.insert(2);
		b.insert(7);
		b.insert(8);
		assertFalse(b.isZigZag());
		b.insert(11);
		assertFalse(b.isZigZag());

		b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(9);
		b.insert(11);
		assertFalse(b.isZigZag());
		points += 1;
	}

	@Test
	public void testZigZagNegative() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		assertTrue(b.isZigZag());
		b.insert(5);
		b.insert(2);
		b.insert(3);
		b.insert(4);
		assertFalse(b.isZigZag());
		points += 1;
	}

	@Test
	public void testZigZagNegative2() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(2);
		assertTrue(b.isZigZag());
		b.insert(8);
		b.insert(6);
		b.insert(1);
		assertFalse(b.isZigZag());
		points += 1;
	}

	@Test
	public void testZigZagBigTreeLeaf() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertTrue(b.isZigZag());
		for (int i = 1; i < 9; i++) {
			b.insert(i);
			b.insert(20 - i);
		}
		b.insert(10);
		b.insert(9);
		assertFalse(b.isZigZag());
		points += 1;
	}

	@Test
	public void testZigZagBigTreeMiddle() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		for (int i = 1; i < 4; i++) {
			b.insert(i);
			b.insert(20 - i);
		}
		assertTrue(b.isZigZag());
		b.insert(5);
		for (int i = 7; i < 10; i++) {
			b.insert(i);
			b.insert(20 - i);
		}
		assertFalse(b.isZigZag());
		points += 1;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.printf("Points: %d/20\n",points);
	}
}