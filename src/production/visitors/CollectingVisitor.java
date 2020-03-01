package production.visitors;

import production.LAN.*;

import java.util.ArrayList;

public class CollectingVisitor extends LANVisitor {

    private final ArrayList<Workstation> workstations = new ArrayList<>();
    private final ArrayList<Printserver> printservers = new ArrayList<>();

    public CollectingVisitor(Packet p) {
        super(p);
    }

    @Override
    public void visit(Printserver ps) {
        super.visit(ps); // invoke the behaviour implemented in the superclass
        printservers.add(ps);
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
                 .append(" workstations")
                 .append(" and ")
                 .append(printservers.size())
                 .append(" printservers")
                 .append(".\nThe workstations are: ");
         workstations.forEach(w -> output.append(w).append(" "));
         output.append("\nThe printservers are: ");
         printservers.forEach(p -> output.append(p).append(" "));
         return output.toString();
    }

    public boolean containsWorkstation(Node w) {
        return workstations.contains(w);
    }

    public boolean containsPrintserver(Node ps) {
        return printservers.contains(ps);
    }

}
