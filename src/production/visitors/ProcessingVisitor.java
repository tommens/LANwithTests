package production.visitors;

import production.LAN.Packet;
import production.LAN.Printserver;
import production.LAN.Workstation;

// This visitor outputs all information obtained from each node in the LAN network it is traveling over
public class ProcessingVisitor extends LANVisitor {

    public ProcessingVisitor(Packet p) {
        super(p);
    }

    @Override
    public void visit(Printserver ps) {
        super.visit(ps);
        if (getDestination() == ps)
            ps.print(visitingPacket);
    }

    @Override
    public void visit(Workstation w) {
        super.visit(w);
        if (getDestination() == w)
            System.out.println("Packet has reached its destination workstation " + w);
    }

}
