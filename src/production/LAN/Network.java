package production.LAN;

import java.util.ArrayList;
import java.util.List;

// Creation of a token ring network composed of a circular list of nodes.
public class Network {

    private final ArrayList<Node> nodes;

/*    public Network(ArrayList<Node> list) {
        //TO DO: VERIFY THAT EVERY node in this list is instantiated (i.e. no null objects!)
        nodes = list; //probably cloning is more appropriate here...
        this.makeCircular();
    }*/

    private void makeCircular() {
        // Makes the list of nodes circular by directing each node to its successor.
        // (The last node in the list will point to the very first one.)
        for (Node n : nodes) {
            if (nodes.indexOf(n) < nodes.size() - 1) {
                n.setNextNode(nodes.get(nodes.indexOf(n) + 1));
            } else {
                n.setNextNode(nodes.get(0));
            }
        }
    }

    // provide a list of strings as input
    // and generate a list of nodes as output.
    // These nodes must each point to the next one in the list
    public Network(List<String> nodeNames) {
        // Create a list of subclasses of Nodes, based on the names provided as input.
        // TO DO ERROR CHECKING IF NONE OF THE CASES IS SATISFIED!
        nodes = new ArrayList<>();
        for (String n : nodeNames) {
            if (n.contains("workstation")) {
                nodes.add(new Workstation(n));
            } else if (n.contains("printserver")) {
                nodes.add(new Printserver(n));
            }
        }
        this.makeCircular();
    }

    // Returns the first workstation in the network with name s.
    // Returns null if the network does not contain any such workstation.
    public Workstation findWorkstation(String s) {
        Workstation w = null;
        for (Node n: nodes) {
            if ((n instanceof Workstation) && (n.toString().equals(s))) {
                w = (Workstation) n; // select the  workstation with name s
                break; // stop iterating from the loop
            }
        }
        // return the workstation found, or null if no such workstation exists
        return w;
    }

    // Returns the first printserver in the network.
    // Returns null if the network does not contain any printserver.
    public Printserver findPrintserver(String s) {
        Printserver w = null;
        for (Node n: nodes) {
            if ((n instanceof Printserver) && (n.toString().equals(s))) {
                w = (Printserver) n; // find the first printserver in the network
                break; // stop iterating from the loop
            }
        }
        // return the printserver found, or null if no such printserver exists
        return w;
    }

}
