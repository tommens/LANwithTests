/**
 * @author tommens
 *
 * A special kind of Node that has the additional functionality that
 * allows it to print packets it receives, provided that the Printserver
 * node is the addressee of the Packet.
 * 
 */
public class Printserver extends Node {
	
	public Printserver(String s) {
		super(s);
	}

	public Printserver(String s, Node next) {
		super(s, next);
	}
	
	public void print(Packet p) {
		System.out.println("Printing packet with contents " + p.contents);
	}

	public void accept(Packet p) throws UnknownDestinationException {
		if(p.addressee == this)
			this.print(p);
		else
			super.accept(p);
	}

}
