package production;

import production.LAN.Network;
import production.LAN.Packet;
import production.LAN.Printserver;
import production.LAN.Workstation;
import production.visitors.CollectingVisitor;
import production.visitors.ProcessingVisitor;
import production.visitors.TrackingVisitor;

import java.util.Arrays;

/**
 * @author tommens
 *
 */
public class Main {

	public static void main(String[] args) {

		Network lan = new Network(Arrays.asList("workstation1", "workstation2", "printserver1", "workstation 3", "printserver2"));
		Workstation originator = lan.findWorkstation("workstation1");
		Printserver printer = lan.findPrintserver("printserver2");
		System.out.println(printer);

		System.out.println("******* Tracking visitor with printserver destination: *******");
		Packet p = new Packet("BlahBlah", printer);
		originator.accept(new TrackingVisitor(p));

		System.out.println("******* Processing visitor with printserver destination: *******");
		originator.accept(new ProcessingVisitor(p));
		//w1.accept(pv); //visitor is given to the workstation to start iterating, should print contents of packet p on ps2

		System.out.println("******* Processing visitor with package without existing destination: *******");
		p.setDestination(new Printserver("printer4"));
		originator.accept(new ProcessingVisitor(p));

		System.out.println("******* Tracking visitor with package without existing destination: *******");
		originator.accept(new TrackingVisitor(p));

		System.out.println("******* Processing visitor with destination = some other workstation: *******");
		p.setDestination(lan.findWorkstation("workstation3"));
		ProcessingVisitor pv = new ProcessingVisitor(p);
		originator.accept(pv);

		System.out.println("******* Tracking visitor with destination = some other workstation: *******");
		TrackingVisitor tv = new TrackingVisitor(p);
		originator.accept(tv);

		System.out.println("*** Collecting visitor : ***");
		p.setDestination(originator);
		CollectingVisitor cv = new CollectingVisitor(p);
		originator.accept(cv);
		System.out.println(cv);
	}
}
