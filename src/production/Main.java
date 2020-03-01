package production;

import production.LAN.*;
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

		Network lan = new Network(Arrays.asList("workstation1", "workstation2", "printserver1", "workstation3", "fileserver1", "printserver2"));

		// find the first node of the Workstation in the network and use this as originator node in the code later on.
		Node originator = lan.findNode(Workstation.class);
		// As an alternative, we could have used the following code that looks for a node with the specified name:
		// Node originator = lan.findNode("workstation1");

		// find a node in the network that matches the name "printserver2". (This node will be of type Printserver.)
		Node printer = lan.findNode("printserver2");

		System.out.println("******* Tracking visitor with printserver destination: *******");
		Packet p = new Packet("BlahBlah", printer);
		originator.accept(new TrackingVisitor(p));

		System.out.println("******* Processing visitor with printserver destination: *******");
		originator.accept(new ProcessingVisitor(p));

		System.out.println("******* Processing visitor with fileserver destination: *******");
		p.setDestination(lan.findNode("fileserver1"));
		originator.accept(new ProcessingVisitor(p));

		System.out.println("******* Processing visitor with package without existing destination: *******");
		p.setDestination(new Printserver("printer4"));
		originator.accept(new ProcessingVisitor(p));

		System.out.println("******* Tracking visitor with package without existing destination: *******");
		originator.accept(new TrackingVisitor(p));

		System.out.println("******* Processing visitor with destination = some other workstation: *******");
		p.setDestination(lan.findNode("workstation3"));
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
