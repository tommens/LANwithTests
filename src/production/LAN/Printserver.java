package production.LAN;

import production.visitors.LANVisitor;

/**
 * @author tommens
 *
 * A special kind of node that has the additional functionality that
 * allows it to print packets it receives, provided that the printserver
 * node is the addressee of the packet.
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
		System.out.println(this + " prints: " + p.toString());
	}

	@Override
	public void accept(LANVisitor v) {
		if (v.getDestination() == this) {
			v.visit(this); // just visit the destination and stop iterating...
		}
		else {
			v.visit(this);
			this.send(v);
		}
	}

}
