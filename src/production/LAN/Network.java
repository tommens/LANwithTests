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
            else if (n.contains("fileserver")) {
                nodes.add(new Fileserver(n));
            }
            else {
                // TO DO ERROR CHECKING IF NONE OF THE CASES IS SATISFIED!
                System.out.println("The string " + n + " cannot be matched to any of the known node types!");
            }
        }
        this.makeCircular();
    }

    // look for the first node in the network that has a specific name s.
    // returns null if no such node exists in the network.
    public Node findNode(String s) {
        Node w = null;
        for (Node n:nodes) {
            if (n.toString().equals(s)) {
                w= n; // find the first node with the specified name in the network
                break; // stop iterating from the loop
            }
        }
         return w;
    }

    // !!! USE OF REFLECTION MECHANISM IN JAVA SINCE A CLASS IS PASSED AS ARGUMENT INSTEAD OF AN OBJECT... !!!
    // look for the first node in the network that has a specific type (e.g. Workstation.class, Printserver.class).
    // returns null if no such node exists in the network.
    public Node findNode(Class<?> type) {
        Node w = null;
        for (Node n:nodes) {
            if (n.getClass().equals(type)) {
                w= n; // find the first node of the specified type in the network
                break; // stop iterating from the loop
            }
        }
        return w;
    }
}
