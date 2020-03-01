package production.visitors;

import production.LAN.*;

import java.util.ArrayList;

public class CollectingVisitor extends LANVisitor {

    private final ArrayList<Workstation> workstations = new ArrayList<>();
    private final ArrayList<Printserver> printservers = new ArrayList<>();
    private final ArrayList<Fileserver> fileservers = new ArrayList<>();


    public CollectingVisitor(Packet p) {
        super(p);
    }

    @Override
    public void visit(OutputServer o) {
        super.visit(o); // invoke the behaviour implemented in the superclass
        if (o instanceof Printserver) {
            printservers.add((Printserver)o); // added type cast to make it work
        }
        else if (o instanceof Fileserver) {
            fileservers.add((Fileserver)o); // added type cast to make it work
        }
    }

    @Override
    public void visit(Workstation w) {
        super.visit(w); // invoke the behaviour implemented in the superclass
        workstations.add(w);
     }

     public String toString() {
        // use a StringBuilder rather than direct string concatenation for performance reasons.
         final StringBuilder output = new StringBuilder();
         output.append("The token ring network contains ")
                 .append(workstations.size())
                 .append(" workstations, ")
                 .append(printservers.size())
                 .append(" printservers and ")
                 .append(fileservers.size())
                 .append(" fileservers.\n")
                 .append("The workstations are: ");
         workstations.forEach(w -> output.append(w).append(" "));
         output.append("\nThe printservers are: ");
         printservers.forEach(p -> output.append(p).append(" "));
         output.append("\nThe fileservers are: ");
         fileservers.forEach(p -> output.append(p).append(" "));
         return output.toString();
    }

    public boolean containsWorkstation(Node w) {
        return workstations.contains(w);
    }

    public boolean containsPrintserver(Node ps) {
        return printservers.contains(ps);
    }

}
