package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			return add(x, root);
		}
	}
	
	private boolean add(E x, BinaryNode<E> node) {
		int compare = x.compareTo(node.element);
		BinaryNode<E> newNode = new BinaryNode<E>(x);
		if(compare > 0) {
			if(node.right == null) {
				node.right = newNode;
				size++;
				return true;
			} else {
				return add(x, node.right);
			}
		} else if(compare < 0) {
			if(node.left == null) {
				node.left = newNode;
				size++;
				return true;
			} else {
				return add(x, node.left);
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> node) {
		if(node == null)
			return 0;
		else
			return 1 + Math.max(height(node.left), height(node.right));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	public void printTree(BinaryNode<E> node) {
		if(node != null) {
			printTree(node.left);
			System.out.println(node.element);
			printTree(node.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] array = (E[])new Comparable[size];
		toArray(root, array, 0);
		root = buildTree(array, 0, array.length-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n != null) {
			index = toArray(n.left, a, index);
			a[index++] = n.element;
			return toArray(n.right, a, index);
		} else {
			return index;
		}
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(first > last)
			return null;
		int middle = (first+last)/2;
		BinaryNode<E> root = new BinaryNode<E>(a[middle]);
		root.left = buildTree(a, first, middle-1);
		root.right = buildTree(a, middle+1, last);
		return root;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
	public static void main(String[] args) {
		BSTVisualizer bst = new BSTVisualizer("Hej", 700, 700);
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(5);
		tree.add(4);
		bst.drawTree(tree);
		
	}
	
}
