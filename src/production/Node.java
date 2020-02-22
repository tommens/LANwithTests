package production;

/**
 * @author tommens
 *
 * Instances of this class represent nodes in the local area network.
 * This is an abstract class that can be refined by specific
 * kinds of nodes, such as Printservers and Workstations.
 * 
 */
public abstract class Node {

	private String name;

	private Node nextNode;

	public Node(String s) {
		name = s;
	}

	public Node(String s, Node n) {
		this(s); //calls the constructor Node(String s)
		nextNode =n;
	}

	public String toString() {
		return name;
	}

	public abstract void accept(LANVisitor v);

	public void send(LANVisitor v) {
		getNextNode().accept(v);
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node n) {
		nextNode = n;
	}
}
