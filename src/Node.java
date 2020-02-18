/**
 * @author tommens
 *
 * Instances of this class represent nodes in the local area network.
 * Conceptually, this is an abstract class that can be refined by specific
 * kinds of nodes, such as Printservers and Workstations.
 * 
 */
public class Node {
	public String name;

	public Node nextNode;

	public Node(String s) {
		name = s;
	}

	public Node(String s, Node n) {
		this(s); //calls the constructor Node(String s)
		nextNode = n;
	}

	public void accept(Packet p) throws UnknownDestinationException {
		this.send(p);
	}

	protected void send(Packet p) throws UnknownDestinationException {
		nextNode.accept(p);
	}

}
