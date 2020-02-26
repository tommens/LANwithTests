package production;

// Visitor design pattern to allow to visit all nodes in a LAN
public abstract class LANVisitor {

 protected Packet visitingPacket;

 // upon invocation of method visit, the visitor records the current node it is visiting, this is useful for testing purposes
 protected Node current;

 public LANVisitor(Packet p) {
    visitingPacket = p;
    setSource(null); // The originator node will be set upon the first call of the accept method
 }

 public  void visit(Workstation w) {
     current = w;
 }

 public void visit(Printserver ps) {
     current = ps;
 }

 public Node getDestination() {
     return visitingPacket.getDestination();
 }

 public Node getCurrent() {
     return current;
 }
 public Node getSource() {
     return visitingPacket.getOriginator();
 }

 public void setSource(Node n) {
     visitingPacket.setOriginator(n);
 }

}
