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
			this.send(p);
			}
		catch(UnknownDestinationException e)
			{
			System.err.println(e.toString());
			}
	}

	public void accept(Packet p) throws UnknownDestinationException {
		if(p.originator == this)
			throw new UnknownDestinationException(
				"production.Packet with contents " + p.contents +
				" has unknown destination " + p.addressee.name);
		else
			super.accept(p);
	}
}
