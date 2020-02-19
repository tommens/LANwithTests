package production;

/**
 * @author tommens
 *
 * A special kind of production.Node that has the additional functionality that it
 * can start sending packets over the network by means of the "originate"
 * method. Also, it avoids endless cycling of a packet through the network.
 * 
 */
public class Workstation extends Node {

	public Workstation(String s) {
		super(s);
	}

	public Workstation(String s, Node next) {
		super(s, next);
	}

	public void originate(Packet p) {
		try {
			p.originator = this;
			p.current = this;
			this.send(p);
			}
		catch(UnknownDestinationException e)
			{
			System.err.println(e.toString());
			}
	}

	public void accept(Packet p) throws UnknownDestinationException {
		if(p.originator == this) {
			p.track("Packet has cycled through network without finding its destination");
			throw new UnknownDestinationException(
				"Packet has unknown destination " + p.addressee.getName()); }
		else
			if (p.addressee == this) {
				p.track("Package has reached its destination " + getName());
			}
			else
			super.accept(p);
	}
}
