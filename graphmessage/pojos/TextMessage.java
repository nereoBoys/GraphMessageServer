package graphmessage.pojos;

import graphmessage.datastructures.btree.BTree;

public class TextMessage {
	
	private Message message;
	private BTree<String> bTree;

	public TextMessage() {}
	
	public TextMessage(Message message) {
		this.message = message;
		createBTree(message);
	}
	
	private BTree<String> createBTree(Message message) {
		BTree<String> bTree = new BTree<>();
		String[] words = message.getContent().split(" ");
		for(String word:words) {
			bTree.insert(word);
		}
		return bTree;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public BTree<String> getbTree() {
		return bTree;
	}
	public void setbTree(BTree<String> bTree) {
		this.bTree = bTree;
	}

}
