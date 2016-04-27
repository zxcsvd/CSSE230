/**
 * This is a tree factory.
 * 
 * @author Curt Clifton. Created Jan 24, 2008.
 */
public final class BuildTree {

	/**
	 * Generates a binary tree whose node contents and pre-order traversal order
	 * come from charElements. For each of those nodes, the corresponding
	 * element in childCodes indicates the number of children, where L means one
	 * left child, R means one right child, and 0 and 2 mean zero and two
	 * children respectively.
	 * 
	 * @param charElements
	 * @param childCodes
	 * @return the binary tree constructed from charElements and childCodes
	 */
	public static BinaryTree preOrderBuild(CharSequence charElements,
			CharSequence childCodes) {

		if (childCodes.length() != charElements.length())
			throw new IllegalArgumentException(
					"The two parameters were not the same length");
		// loop charElements
		BinaryTree bt = new BinaryTree();
		bt.setRoot(preOrderHelper(charElements, childCodes));
		bt.getRoot().setDepth(0);
		return bt;
	}

	/**
	 * Helper function for the recursive deciphering of the childCodes and the
	 * sequence of characters
	 * 
	 * @param seq
	 * @param childCodes
	 * @returns a binary node
	 */
	public static BinaryNode preOrderHelper(CharSequence seq,
			CharSequence childCodes) {
		char a = childCodes.charAt(0);
		if (a == '2') { // two children to be considered
			int counter = 1;
			int index = 0;
			for (int i = 1; i < childCodes.length(); i++) {

				if (childCodes.charAt(i) == '2')
					counter = counter + 1;
				else if (childCodes.charAt(i) == '0') {
					counter--;
				}
				if (counter == 0) {
					index = i;
					break;
				}
			}
			return new BinaryNode(seq.charAt(0), preOrderHelper(
					seq.subSequence(1, index + 1),
					childCodes.subSequence(1, index + 1)), preOrderHelper(
					seq.subSequence(index + 1, seq.length()),
					childCodes.subSequence(index + 1, seq.length())));
		} else if (a == 'L') { // only a left child
			return new BinaryNode(seq.charAt(0), preOrderHelper(
					seq.subSequence(1, seq.length()),
					childCodes.subSequence(1, childCodes.length())), null);

		} else if (a == 'R') {// only a right child
			return new BinaryNode(seq.charAt(0), null, preOrderHelper(
					seq.subSequence(1, seq.length()),
					childCodes.subSequence(1, childCodes.length())));

		} else if (a == '0') {
			return new BinaryNode(seq.charAt(0), null, null);

		} else { // invalid character
			throw new IllegalArgumentException(
					"Invalid character present in arguement");
		}

	}
}
