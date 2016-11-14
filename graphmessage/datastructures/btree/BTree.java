package graphmessage.datastructures.btree;

public class BTree<T extends Comparable<T>> {

	private BTreeNode<T> root;

	public BTreeNode<T> search(T key) {
		return search(key, root);
	}

	public BTreeNode<T> search(T key, BTreeNode<T> node) {
		if (node != null) {
			int i = 1;
			for (; i <= node.getKeyTally() && (node.getKeys().get(i - 1).compareTo(key) < 0); i++) {
			}
			if (i > node.getKeyTally() || node.getKeys().get(i - 1).compareTo(key) > 0) {
				return search(key, node.getReferences().get(i - 1));
			} else {
				return node;
			}
		} else
			return null;
	}

	public void insert(T key) {

		if (root == null) {
			root = new BTreeNode<T>(key);
		} else {
			BTreeNode<T> node = root;
			while (node != null) {
				if (node.isLeaf()) {
					node.addKey(key);
					if (node.getKeyTally() >= node.getOrder()) {
						split(node);
						break;
					}
					break;
				}

				T greater = node.getKeys().get(node.getKeyTally() - 1);
				if (key.compareTo(greater) > 0) {
					node = node.getReferences().get(node.getKeyTally());
					continue;
				}

				for (int i = 0; i < node.getKeyTally(); i++) {
					if (key.compareTo(node.getKeys().get(i)) <= 0) {
						node = node.getReferences().get(i);
						break;
					}
				}

			}
		}

	}

	private void split(BTreeNode<T> nodeToSplit) {
		BTreeNode<T> node = nodeToSplit;
		int numberOfKeys = node.getKeyTally();
		int medianIndex = numberOfKeys / 2;
		T medianValue = node.getKeys().get(medianIndex);

		BTreeNode<T> left = new BTreeNode<>();
		for (int i = 0; i < medianIndex; i++) {
			left.addKey(node.getKeys().get(i));
		}

		if (!node.isLeaf()) {
			for (int j = 0; j <= medianIndex; j++) {
				BTreeNode<T> c = node.getReferences().get(j);
				left.addReference(c);
			}
		}

		BTreeNode<T> right = new BTreeNode<>();
		for (int i = medianIndex + 1; i < numberOfKeys; i++) {
			right.addKey(node.getKeys().get(i));
		}
		if (!node.isLeaf()) {
			for (int j = medianIndex + 1; j < node.getReferences().size(); j++) {
				BTreeNode<T> c = node.getReferences().get(j);
				right.addReference(c);
			}
		}

		if (node.getParent() == null) {
			root = new BTreeNode<T>();
			root.addKey(medianValue);
			root.addReference(left);
			root.addReference(right);
		} else {
			BTreeNode<T> parent = node.getParent();
			parent.addKey(medianValue);
			parent.getReferences().remove(node);
			parent.addReference(left);
			parent.addReference(right);

			if (parent.getKeyTally() >= parent.getOrder()) {
				split(parent);
			}
		}
	}

	
	public void print() {
		print(root);
	}
	
	private void print(BTreeNode<T> node) {
		for (int i = 0; i < node.getKeyTally(); i++) {
			System.out.print(node.getKeys().get(i) + " ");
		}

		if (!node.isLeaf()){
			for (int j = 0; j < node.getReferences().size() ; j++) { // to print out tree in
				if (node.getReferences().get(j) != null) { // going from left most
					System.out.println(); // child to right most
					print(node.getReferences().get(j)); // child.
				}
			}
		}
	}

	public BTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BTreeNode<T> root) {
		this.root = root;
	}

}
