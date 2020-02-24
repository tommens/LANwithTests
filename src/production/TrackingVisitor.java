package production;

// This visitor outputs all information obtained from each node in the LAN network it is traveling over
public class TrackingVisitor extends LANVisitor {

    public TrackingVisitor(Packet p) {
        super(p);
    }

    @Override
    public void visit(Printserver ps) {
        super.visit(ps); // invoke the behaviour implemented in the superclass
        System.out.println("Printserver " + ps + " receives visitor ");
    }

    @Override
    public void visit(Workstation w) {
        super.visit(w); // invoke the behaviour implemented in the superclass
        System.out.println("Workstation " + w + " receives visitor ");
    }

}
