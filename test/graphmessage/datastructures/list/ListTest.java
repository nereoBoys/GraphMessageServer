package test.graphmessage.datastructures.list;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import graphmessage.datastructures.list.List;

public class ListTest {
	
	@Test
	public void testGetFirstNode() {
		List<String> list = new List<>();
		list.insertTail("primero");
		list.insertTail("segundo");
		list.insertTail("tercero");
		assertEquals("primero",list.getNode(0).getData());
	}
	
	@Test
	public void testGetSecondNode() {
		List<String> list = new List<>();
		list.insertTail("primero");
		list.insertTail("segundo");
		list.insertTail("tercero");
		assertEquals("segundo",list.getNode(1).getData());
	}
	
	@Test
	public void testGetOutOfBoundsNode() {
		List<String> list = new List<>();
		list.insertTail("primero");
		list.insertTail("segundo");
		list.insertTail("tercero");
		assertEquals(null,list.getNode(3));
	}

}
