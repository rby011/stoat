//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Action {
    private static int IDE_SEED = 1;
    private int actionID;
    private int viewID;
    private String viewCIS;//이게 모지???
    private String viewText;
    private String actionType;
    private String actionCommand;
    private String activityName;
    public String actionSource;
    public int b;

    public Action() {
    }

    public final void setActionID() {
        this.actionID = IDE_SEED++;
    }

    public final void setViewID(int id) {
        this.viewID = -1;
    }

    public final void a(String var1) {
        this.viewCIS = var1;
    }

    public final void setViewText(String viewText) {
        this.viewText = viewText;
    }

    public final void c(String var1) {
        this.activityName = var1;
    }

    public final void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public final void setActionCommand(String var1, String var2) {
        String var3;
        if ((var3 = this.viewText) != null) {
            var3 = var3.replaceAll("\n", "").replaceAll(";", "");
            var3 = var2 + "@\"" + var3 + "\"";
        } else {
            var3 = var2 + "@null";
        }

        if (var2.equals("")) {
            this.actionCommand = var1;
        } else {
            this.actionCommand = var1.substring(0, var1.length() - 1) + ":" + var3 + "\n";
        }
    }

    private String getCommand() {
        int var1;
        // ASCII ":" colon
        return (var1 = this.actionCommand.lastIndexOf(58)) == -1 ? this.actionCommand : this.actionCommand.substring(0, var1);
    }

    public final int getActionID() {
        return this.actionID;
    }

    public final int getViewID() {
        return this.viewID;
    }

    public final String getViewText() {
        return this.viewText;
    }

    public final String e() {
        return this.activityName;
    }

    public final String getActionType() {
        return this.actionType;
    }

    public final String getActionCommand() {
        return this.actionCommand;
    }

    public final String toActionString() {
        String var1 = "\t";
        var1 = var1 + "action_id: " + this.actionID + "; view_id: " + this.viewID;
        if (this.viewText != null) {
            var1 = var1 + "; view_text: " + this.viewText;
        } else {
            var1 = var1 + "; view_text: [ ]";
        }

        if (this.actionType != null) {
            var1 = var1 + "; action_type: " + this.actionType;
        } else {
            var1 = var1 + "; action_type: [ ]";
        }

        if (this.actionCommand != null) {
            var1 = var1 + "; action_cmd: " + this.actionCommand;
        } else {
            var1 = var1 + "; action_cmd: [ ]";
        }

        if (this.actionSource != null) {
            var1 = var1 + "; action_source: " + this.actionSource;
        } else {
            var1 = var1 + "; action_source: [ ]";
        }

        return var1;
    }

    public int hashCode() {
        String actionCommand = this.actionCommand;
        int hash = 0;

        for (int i = 0; i < actionCommand.length(); ++i) {
            hash += actionCommand.charAt(i);
        }

        return hash + this.viewID;
    }

    public boolean compare(Object object) {
        System.out.println("equal");
        if (object instanceof Action) {
            String thisCommand = this.getCommand();
            Action given;
            String givenCommand = (given = (Action) object).getCommand();
            System.out.println("[Action] AgentController: cmd: " + thisCommand + ", view cis: " + this.viewCIS + ", activity name: " + this.activityName);
            System.out.println("equal:" + thisCommand + "|" + givenCommand + "|");
            System.out.println("cmd2'TestManager view cis: " + given.viewCIS + " activity name" + given.activityName);
            if (thisCommand.equals(givenCommand) && this.viewCIS.equals(given.viewCIS) && this.activityName.equals(given.activityName)) {
                this.b = given.actionID;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
