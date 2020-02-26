package production;

import java.util.ArrayList;

public class CollectingVisitor extends LANVisitor {

    private ArrayList<Workstation> workstations = new ArrayList<>();
    private ArrayList<Printserver> printservers = new ArrayList<>();

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
        String output = "The token ring network contains " + workstations.size()+ " workstations and "
                +  printservers.size() + " visitors.\n";
        output += "The workstations are ";
        for (Workstation w : workstations) { output +=  w + " ";}
        output += ".\nThe printservers are ";
        for (Printserver p: printservers) {output += p + " ";}
        return output+".\n";
    }

    public boolean contains(Workstation w) {
        return workstations.contains(w);
    }

    public boolean contains(Printserver ps) {
        return printservers.contains(ps);
    }

}
