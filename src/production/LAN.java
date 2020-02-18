package production;

/**
 * @author tommens
 *
 */
public class LAN {

	public static void main(String[] args) {

		Workstation w1 = new Workstation("Workstation1");
		Workstation w2 = new Workstation("Workstation2");
		Workstation w3 = new Workstation("Workstation3");
		Printserver p1 = new Printserver("Printer1");
		Printserver p2 = new Printserver("Printer2");

		w1.nextNode = w2;
		w2.nextNode = p1;
		p1.nextNode = w3;
		w3.nextNode = p2;
		p2.nextNode = w1;

		Packet p = new Packet("BlahBlah", p2);

		w1.originate(p);
	}
}
