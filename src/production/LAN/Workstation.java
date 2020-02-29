package production.LAN;

import production.visitors.LANVisitor;

/**
 * @author tommens
 *
 * A special kind of node that has the additional functionality that it
 * can start sending packets over the network when it accepts a visitor.
 * Also, it avoids endless cycling of a packet through the network.
 * 
 */
public class Workstation extends Node {

	public Workstation(String s) {
		super(s);
	}

	public Workstation(String s, Node next) {
		super(s, next);
	}

	@Override
	public void accept(LANVisitor v) {
		if (v.getSource() == null) {
			// visiting packet has not yet been initialised, so let's do it and start iterating
			v.setSource(this);
			this.send(v);
		}
		else if (v.getDestination() == this) {
			//package has reached its destination
			v.visit(this); //visit this workstation and stop traversing
		}
		else if (v.getSource() == this) {
			//package has cycled through the entire network without finding its destination
			v.visit(this);
			System.out.println("Visitor has cycled through the network without finding destination node");
		}
		else {
			v.visit(this);
			this.send(v);
		}
	}

}
