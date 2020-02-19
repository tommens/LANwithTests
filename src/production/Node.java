package production;

/**
 * @author tommens
 *
 * Instances of this class represent nodes in the local area network.
 * Conceptually, this is an abstract class that can be refined by specific
 * kinds of nodes, such as Printservers and Workstations.
 * 
 */
public class Node {
	private String name;

	private Node nextNode;

	public Node(String s) {
		setName(s);
	}

	public Node(String s, Node n) {
		this(s); //calls the constructor production.Node(String s)
		setNextNode(n);
	}

	public void accept(Packet p) throws UnknownDestinationException {
		p.track("Node " + getName() + " accepts packet");
		this.send(p);
	}

	public void send(Packet p) throws UnknownDestinationException {
		p.current = getNextNode();
		p.track("Node " + getName() + " sends packet to next node " + getNextNode().getName());
		getNextNode().accept(p);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
}
