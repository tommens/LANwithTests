/**
 * @author tommens
 *
 * Instances of this class represent packets that can be sent over
 * the LAN to perform a certain task.For the time being, they can only be
 * printed on a Printserver.
 * 
 */
public class Packet {
	public String contents;

	public Node originator;

	public Node addressee;

	public Packet(String c, Node a) {
		contents = c;
		addressee = a;

	}

}
