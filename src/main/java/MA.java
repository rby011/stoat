//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public final class MA extends Action {
    public MA() {
        this.setActionType("click");
    }

    public final String toString() {
        return Integer.toHexString(this.getViewID()) + " " + this.getViewText() + " " + this.getActionType();
    }
}
