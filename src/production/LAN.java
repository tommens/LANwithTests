package production;

/**
 * @author tommens
 *
 */
public class LAN {

	public static void main(String[] args) {

		Workstation w1 = new Workstation("w1");
		Workstation w2 = new Workstation("w2");
		Workstation w3 = new Workstation("w3");
		Printserver ps1 = new Printserver("p1");
		Printserver ps2 = new Printserver("p2");

		w1.setNextNode(w2);
		w2.setNextNode(ps1);
		ps1.setNextNode(w3);
		w3.setNextNode(ps2);
		ps2.setNextNode(w1);

		System.out.println("******* Tracking visitor with printserver destination: *******");
		Packet p = new Packet("BlahBlah", ps2);
		TrackingVisitor tv = new TrackingVisitor(p);
		w1.accept(tv); // visitor is given to the workstation to start iterating, should stop when ps2 is reached

		System.out.println("******* Processing visitor with printserver destination: *******");
		ProcessingVisitor pv = new ProcessingVisitor(p);
		w1.accept(pv); //visitor is given to the workstation to start iterating, should print contents of packet p on ps2

		System.out.println("******* Processing visitor with package without existing destination: *******");
		p.setDestination(new Printserver("Printer3"));
		pv = new ProcessingVisitor(p);
		w1.accept(pv);

		System.out.println("******* Tracking visitor with package without existing destination: *******");
		tv = new TrackingVisitor(p);
		w1.accept(tv);

		System.out.println("******* Processing visitor with destination = some other workstation: *******");
		p.setDestination(w3);
		pv = new ProcessingVisitor(p);
		w1.accept(pv);

		System.out.println("******* Tracking visitor with destination = some other workstation: *******");
		p.setDestination(w3);
		tv = new TrackingVisitor(p);
		w1.accept(tv);

		System.out.println("*** Collecting visitor : ***");
		p.setDestination(w1);
		CollectingVisitor cv = new CollectingVisitor(p);
		w1.accept(cv);
		System.out.println(cv);
	}
}
