package test.graphmessage.datastructures.btree;

import org.junit.Test;

import graphmessage.datastructures.btree.BTree;

public class TestBTree {

	@Test
	public void testTree() {
		BTree<String> tree = new BTree<>();
		tree.insert("a");
		tree.insert("b");
		tree.insert("c");
		tree.insert("d");
		tree.insert("e");
		tree.insert("f");
		tree.insert("g");
		tree.insert("h");
		tree.insert("i");
		tree.insert("j");
		tree.insert("k");
		tree.insert("l");
		tree.insert("m");
		tree.insert("n");
		tree.insert("o");
		tree.insert("p");
		tree.insert("q");
		tree.insert("r");
		tree.insert("s");
		tree.insert("t");
		tree.insert("u");
		tree.insert("v");
		tree.insert("w");
		tree.insert("x");
		tree.insert("y");
		tree.insert("z");
		tree.print();
		
		System.out.print(tree.search("s"));
		
	}
}
