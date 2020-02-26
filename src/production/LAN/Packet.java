package production.LAN;

/**
 * @author tommens
 *
 * Instances of this class represent packets that can be sent over
 * the token ring network to perform a certain task. For the time being, they can only be
 * printed on a Printserver.
 * 
 */
public class Packet {
	private String contents; // the contents of the packet

	private Node originator; // the node where the packet originates from

	private Node destination; // the target destination of the packet

	public Packet(String c, Node dest) {
		contents = c;
		setDestination(dest);
	}

	public Node getDestination() {
		return destination;
	}

	public Node getOriginator() {
		return originator;
	}

	public void setOriginator(Node n) {
		originator = n;
	}

	public void setDestination(Node n) {
		destination = n;
	}

	public String toString() { return contents; }
}
