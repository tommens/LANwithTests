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
	public String contents;

	public Node originator;

	public Node addressee;

	public boolean tracked = false; // when set to true, prints extra info when running the code

	public void setTracking(boolean t) {
		tracked = t;
	}

	public void track(String s) {
		if (tracked) System.out.println(s);
	}

	public Packet(String c, Node a) {
		contents = c;
		addressee = a;
	}

}
