package production;

/**
 * @author tommens
 *
 * A special kind of production.Node that has the additional functionality that
 * allows it to print packets it receives, provided that the production.Printserver
 * node is the addressee of the production.Packet.
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
		System.out.println(this + " prints: " + p.contents);
	}

	public void accept(LANVisitor v) {
		if (v.visitingPacket.getDestination() == this) {
			v.visit(this); // just visit the destination and stop iterating...
		}
		else {
			v.visit(this);
			this.send(v);
		}
	}

}
