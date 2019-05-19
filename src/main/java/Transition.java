//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public final class Transition {
    // u->v transition
    private String uState;
    private String vState;

    private int c;
    private String d;
    private int transitionID;
    private int executedCount = 1;
    private double g = 0.0D;
    private static int ID_SEED = 1;

    public Transition() {
        this.transitionID = ID_SEED++;
    }

    public Transition(String uState, String vState, int var3) {
        this.transitionID = ID_SEED++;
        this.uState = uState;
        this.vState = vState;
        this.c = var3;
        ActionHandler.getInstance();
        this.d = ActionHandler.getAction(var3).getActionCommand();
    }

    public final String getSourceState() {
        return this.uState;
    }

    public final String getDestinationState() {
        return this.vState;
    }

    public final int c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final int getTransitionID() {
        return this.transitionID;
    }

    public final double f() {
        return this.g;
    }

    public final void getSourceState(String var1) {
        this.uState = var1;
    }

    public final void getDestinationState(String var1) {
        this.vState = var1;
    }

    public final void getSourceState(int var1) {
        this.c = var1;
    }

    public final void c(String var1) {
        this.d = var1;
    }

    public final void setTransitionID(int ID) {
        this.transitionID = ID;
    }

    public final void setExecutionCount(int executionCount) {
        this.executedCount = executionCount;
    }

    public final void setG(double var1) {
        this.g = var1;
    }

    public final boolean compareTransition(Transition transition) {
        if (this.c != transition.c) {
            System.out.println("AgentController: these two transitions have different action ids.");
            return false;
        } else if (!this.uState.equals(transition.uState)) {
            System.out.println("AgentController: these two transitions have different start states.");
            return false;
        } else if (!this.vState.equals(this.vState)) {
            System.out.println("AgentController: these two transitions have different end states.");
            return false;
        } else {
            return true;
        }
    }

    public final void increaseExecutionCount() {
        ++this.executedCount;
    }

    public final int getExecutionCount() {
        return this.executedCount;
    }

    public final String toString() {
        return this.uState + " -> " + this.vState + " <" + this.d + ">executed count: " + this.executedCount;
    }
}
