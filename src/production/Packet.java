package production;

/**
 * @author tommens
 *
 * Instances of this class represent packets that can be sent over
 * the production.LAN to perform a certain task.For the time being, they can only be
 * printed on a production.Printserver.
 * 
 */
public class Packet {
	public String contents; // the contents of the packet

	public Node originator; // the node where the packet originates from

	private Node destination; // the target destination of the packet

	public Packet(String c, Node dest) {
		contents = c;
		setDestination(dest);
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node n) {
		destination = n;
	}

}
