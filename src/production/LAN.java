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
		Printserver ps1 = new Printserver("Printer1");
		Printserver ps2 = new Printserver("Printer2");

		w1.setNextNode(w2);
		w2.setNextNode(ps1);
		ps1.setNextNode(w3);
		w3.setNextNode(ps2);
		ps2.setNextNode(w1);

		Packet p = new Packet("BlahBlah", ps2);

		//Send the packet p to its destination printserver ps2
		System.out.println("******* Without tracking: *******");
		w1.originate(p);

	 	System.out.println("******* With tracking: *******");
		p.setTracking(true);
		w1.originate(p);

		System.out.println();
		p.addressee=new Node("Printer3");
		w1.originate(p);

		System.out.println();
		p.addressee=w3;
		w1.originate(p);
	}
}
