//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public final class MenuAction extends Action {
    private String c;
    private int d;
    private String e;

    public MenuAction() {
        this.setViewID(-1);
        this.c = "";
        this.d = -1;
        this.e = "";
        this.c("");
    }

    public final void e(String var1) {
        this.c = var1;
    }

    public final void f(String var1) {
        this.e = var1;
    }

    public final String i() {
        return this.e;//onCreateOptionsMenu 리스너 이름으로 추청된다
    }

    public final String toString() {
        return Integer.toHexString(this.getViewID()) + "\t" + this.c + "\t\t" + this.d + "\t" + this.e + "\t\t" + this.e();
    }
}
