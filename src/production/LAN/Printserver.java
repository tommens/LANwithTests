package production.LAN;

/**
 * @author tommens
 *
 * A special kind of node that has the additional functionality that
 * allows it to print packets it receives, provided that the printserver
 * node is the addressee of the packet.
 * 
 */
public class Printserver extends OutputServer {
	
	public Printserver(String s) {
		super(s);
	}

	public Printserver(String s, Node next) {
		super(s, next);
	}
	
	@Override
	public void output(Packet p) {
		System.out.println(this + " prints: " + p.toString());
	}


}
