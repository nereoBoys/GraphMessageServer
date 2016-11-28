package graphmessage.datastructures.btree;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import graphmessage.constants.Constants;

/** Clase que descri el nodo de un arbol b
 * @author jeanpaul
 *
 * @param <T>
 */
@XmlRootElement
public class BTreeNode<T extends Comparable<T>> implements Constants {
	
	private int order = BTREE_DEFAULT_ORDER_SIZE;
	private int key = BTREE_DEFAULT_KEY_SIZE;
	private boolean leaf = true;
	private int keyTally = BTREE_DEFAULT_KEY_TALLY;
	private ArrayList<T> keys = new ArrayList<>(5);
	private ArrayList<BTreeNode<T>> references = new ArrayList<>(5);
	private BTreeNode<T> parent = null;
	
	/**constructor utilizado por jaxb
	 * 
	 */
	public BTreeNode() {}
	
	/** contructor utilizado para crear una intancia de nodo que contenga
	 * el dato especificado como parametro 
	 * 
	 * @param key
	 */
	public BTreeNode(T key) {
		keys.add(key);
		incrementKeyTally();
	}
	
	/**metodo utilizado para añadir un elemento al nodo
	 * 
	 * @param key
	 */
	public void addKey(T key) {
		if(keys.isEmpty() || (keys.get(keys.size()-1).compareTo(key) < 0)) {
			keys.add(key);
		}
		else {
			for(int index = 0; index < keys.size(); index++) {
				if(keys.get(index).compareTo(key) >= 0){
					keys.add(index,key);
					break;
				}
			}	
	    }
		incrementKeyTally();
	}
	
	/** Metodo utilizado para añadir un hijo al nodo
	 * 
	 * @param child
	 */
	public void addReference(BTreeNode<T> child) {

		if(references.isEmpty() || 
				(references.get(references.size()-1).getKeys().get(0)
						.compareTo(child.getKeys().get(0)) < 0)) {
			references.add(child);
		}
		else {
			for(int index = 0; index < keys.size(); index++) {
				if(references.get(index).getKeys().get(0).
						compareTo(child.getKeys().get(0)) >= 0){
					references.add(index,child);
					break;
				}
			}	
	    }
        child.setParent(this);
        
    	if(leaf) {
			setLeaf(false);
		}
    }
	
	public void incrementKeyTally() {
		keyTally++;
	}

	public int getOrder() {
		return order;
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int getKeyTally() {
		return keyTally;
	}

	public void setKeyTally(int keyTally) {
		this.keyTally = keyTally;
	}

	public ArrayList<T> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<T> keys) {
		this.keys = keys;
	}

	public ArrayList<BTreeNode<T>> getReferences() {
		return references;
	}

	public void setReferences(ArrayList<BTreeNode<T>> references) {
		this.references = references;
	}
	public BTreeNode<T> getParent() {
		return parent;
	}
	public void setParent(BTreeNode<T> parent) {
		this.parent = parent;
	}
	
}
